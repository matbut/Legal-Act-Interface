package agh.inf.polab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActParser {
    private Scanner scanner;
    private Act act=new Act();
    private boolean title=false;

    public ActParser(String fileName) throws FileNotFoundException{
        scanner=new Scanner(new File(fileName)).useDelimiter(Pattern.compile(System.getProperty("line.separator")));
    }

    public boolean isEditorialUnit(String line){
        return matchesTo(line,EditorialUnit.values());
    }
    public boolean isDeletedExpr(String line){
        return matchesTo(line,DeletedExpr.values());
    }
    public boolean isText(String line) {

        return !isEditorialUnit(line);
    }
    public boolean matchesTo(String line,IHasRegex[] collection) {
        for (IHasRegex element : collection)
            if (Pattern.matches(element.findRegex(), line)) {

                return true;
            }
        return false;
    }

    public Act parse() {

        parseFirstLines();

        ActComponent root=new ActComponent(new IdentifiedEditorialUnit(EditorialUnit.Root,""));
        SearchUnit(root,getPreParsedLine());

        act.addComponents(root);

        act.addPreable(root.getContent());
        root.removeContent();

        return act;
    }

    public void parseFirstLines() {
        String line="";
        if(!endOfFile())
            line=getPreParsedLine();

        if(Pattern.matches("Dz\\.U\\. \\d{4} Nr \\d+ poz\\. \\d+.*",line) && !endOfFile()){
            line=line+"\n"+getPreParsedLine();
        }
        for(int i=0;i<2 && !endOfFile();i++)
            line=line+"\n"+getPreParsedLine();
        act.setTitle(line);
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

        if(line.equals("") && !endOfFile())
            line=getPreParsedLine();

        while(!endOfFile() && isText(line)) {
            actComp.addContent(line);
            line=getPreParsedLine();
        }

        if(isText(line))
            actComp.addContent(line);
        return line;
    }

    public String FindUnits(ActComponent actComp,String line){

        if(actComp.id.editUnitType.lowers()!=null) {



            //EditorialUnit findingUnit=iterator.next();


            //for (EditorialUnit findingUnit : actComp.id.editUnitType.lowers()) {


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
                        if (findingUnit == EditorialUnit.Chapter && !endOfFile())
                            newActComp.addContent(getPreParsedLine());

                        line = this.SearchUnit(newActComp, line);
                        m = p.matcher(line);
                        restart = true;
                    }
                }
            }
        }
        return line;
    }

    public String getPreParsedLine() {
        String line = scanner.nextLine();
        while(!endOfFile() && isDeletedExpr(line))
            line = scanner.nextLine();
        if (Pattern.matches(".*-$",line))
            line.replaceFirst("-$","");
        return line;
    }

    public boolean endOfFile(){
        return !scanner.hasNextLine();
    }
}
