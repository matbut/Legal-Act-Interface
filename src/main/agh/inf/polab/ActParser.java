package agh.inf.polab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActParser {
    private PreParser preParser;
    private Act act=new Act();
    private static String DzU="Dz\\.U\\. \\d{4} Nr \\d+ poz\\. \\d+.*";
    private String line="";

    public ActParser(String fileName) throws FileNotFoundException{
        preParser=new PreParser(fileName);
    }
    public Act parse() throws InputMismatchException{
        parseFirstLines();

        line=preParser.getLine();
        ActComponent root=new ActComponent(new IdentifiedEditorialUnit(EditorialUnit.Root,""));
        SearchUnit(root);

        if(!preParser.endOfFile())
            throw new InputMismatchException("Illegal statement before " + preParser.getLine());

        act.setRoot(root);
        act.setPreable(root.getContent());
        root.removeContent();
        return act;
    }

    public void parseFirstLines() throws InputMismatchException {
        String line="";
        if(!preParser.endOfFile())
            line=preParser.getLine();

        if(Pattern.matches(DzU,line) && !preParser.endOfFile()){
            line=line+"\n"+preParser.getLine();
        }
        for(int i=0;i<2 && !preParser.endOfFile();i++)
            line=line+"\n"+preParser.getLine();

        if(preParser.endOfFile())
            throw new InputMismatchException("To short file ");
        act.setTitle(line);
    }
    public void SearchUnit(ActComponent actComp){

        getContent(actComp);
        FindUnits(actComp);

        if(actComp.id.editUnitType==EditorialUnit.Article){
            act.addArticle(actComp);
        }

    }
    public void getContent(ActComponent actComp){

        if(line.equals("") && !preParser.endOfFile())
            line=preParser.getLine();

        while(!preParser.endOfFile() && preParser.isText(line)) {
            actComp.addContent(line);
            line=preParser.getLine();
        }

        if(preParser.isText(line))
            actComp.addContent(line);
    }
    public void FindUnits(ActComponent actComp){

        if(actComp.id.editUnitType.isLastOne())
            return;

        boolean restart=true;
        while(restart) {
            Iterator<EditorialUnit> iterator = Arrays.asList(actComp.id.editUnitType.lowers()).iterator();

            restart = false;
            while (iterator.hasNext() && !restart) {

                EditorialUnit findingUnit = iterator.next();

                Pattern p = Pattern.compile(findingUnit.findRegex());
                Matcher m = p.matcher(line);

                while (m.matches()) {
                    restart = true;
                    line = line.replaceFirst(findingUnit.removeRegex(), "");

                    IdentifiedEditorialUnit id = new IdentifiedEditorialUnit(findingUnit, m.group("id"));

                    ActComponent newActComp = new ActComponent(id);
                    actComp.addChild(newActComp);

                    //wyjatek dla wczytywania tytułu rodzialu()
                    if (findingUnit == EditorialUnit.Chapter && !preParser.endOfFile())
                        newActComp.addContent(preParser.getLine());

                    SearchUnit(newActComp);
                    m = p.matcher(line);
                }
            }
        }
    }
}

