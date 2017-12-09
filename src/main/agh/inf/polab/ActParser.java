package agh.inf.polab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActParser {
    private Scanner scanner;
    private Act act=new Act();

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
            if (Pattern.matches(element.findRegex(), line))
                return true;
        return false;
    }

    public Act parse(){

        parseFirstLines();

        ActComponent root=new ActComponent(new IdentifiedEditorialUnit(EditorialUnit.Root,""));
        SearchUnit(root,getPreParsedLine());

        act.addComponents(root);

        act.addPreable(root.getContent());
        root.removeContent();

        return act;
    }

    public void parseFirstLines() {
        String line=getPreParsedLine();
        if( Pattern.matches("Dz\\.U\\. \\d{4} Nr \\d+ poz\\. \\d+.*",line))
            act.setJournalOfLaws(line);
        line="";

        for(int i=0;i<3;i++){
            line=line+"/n"+getPreParsedLine();
        }
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

        if(line.equals(""))
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
            for (EditorialUnit findingUnit : actComp.id.editUnitType.lowers()) {
                Pattern p = Pattern.compile(findingUnit.findRegex());
                Matcher m = p.matcher(line);

                while (m.matches()) {

                    line = line.replaceFirst(findingUnit.removeRegex(),"");

                    IdentifiedEditorialUnit id=new IdentifiedEditorialUnit(findingUnit,m.group("id"));

                    ActComponent newActComp = new ActComponent(id);
                    actComp.addChild(newActComp);

                    line = this.SearchUnit(newActComp,line);
                    m = p.matcher(line);
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
