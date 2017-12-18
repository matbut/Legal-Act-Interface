package agh.inf.polab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActParser {
    private PreParser preParser;
    private Act act=new Act();

    public ActParser(String fileName) throws FileNotFoundException{
        preParser=new PreParser(fileName);
    }

    public void parseFirstLines() {
        String line="";
        if(!preParser.endOfFile())
            line=preParser.getLine();

        if(Pattern.matches("Dz\\.U\\. \\d{4} Nr \\d+ poz\\. \\d+.*",line) && !preParser.endOfFile()){
            line=line+"\n"+preParser.getLine();
        }
        for(int i=0;i<2 && !preParser.endOfFile();i++)
            line=line+"\n"+preParser.getLine();
        act.setTitle(line);
    }
    public Act parse() {

        parseFirstLines();

        ActComponent root=new ActComponent(new IdentifiedEditorialUnit(EditorialUnit.Root,""));
        SearchUnit(root,preParser.getLine());

        act.setRoot(root);

        act.setPreable(root.getContent());
        root.removeContent();

        return act;
    }

    public String SearchUnit(ActComponent actComp,String line){

        line=getContent(actComp,line);
        line=FindUnits(actComp,line);

        if(actComp.id.editUnitType==EditorialUnit.Article){
            act.addArticle(actComp);
        }

        return line;
    }
    public String getContent(ActComponent actComp,String line){

        if(line.equals("") && !preParser.endOfFile())
            line=preParser.getLine();

        while(!preParser.endOfFile() && preParser.isText(line)) {
            actComp.addContent(line);
            line=preParser.getLine();
        }

        if(preParser.isText(line))
            actComp.addContent(line);
        return line;
    }
    public String FindUnits(ActComponent actComp,String line){

        if(actComp.id.editUnitType.lowers()!=null) {
            boolean restart=true;

            while(restart) {

                Iterator<EditorialUnit> iterator = Arrays.asList(actComp.id.editUnitType.lowers()).iterator();

                restart = false;
                while (iterator.hasNext() && !restart) {

                    EditorialUnit findingUnit = iterator.next();

                    Pattern p = Pattern.compile(findingUnit.findRegex());
                    Matcher m = p.matcher(line);

                    while (m.matches()) {
                        line = line.replaceFirst(findingUnit.removeRegex(), "");

                        IdentifiedEditorialUnit id = new IdentifiedEditorialUnit(findingUnit, m.group("id"));

                        ActComponent newActComp = new ActComponent(id);
                        actComp.addChild(newActComp);

                        //wyjatek dla rodzialu(!!!)
                        if (findingUnit == EditorialUnit.Chapter && !preParser.endOfFile())
                            newActComp.addContent(preParser.getLine());

                        line = this.SearchUnit(newActComp, line);
                        m = p.matcher(line);
                        restart = true;
                    }
                }
            }
        }
        return line;
    }




}
