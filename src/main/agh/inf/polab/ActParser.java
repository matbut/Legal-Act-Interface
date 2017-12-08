package agh.inf.polab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActParser {
    private Scanner scanner;

    public ActParser(String fileName) throws FileNotFoundException{
        scanner=new Scanner(new File(fileName)).useDelimiter(Pattern.compile(System.getProperty("line.separator")));
    }


    public boolean isEditorialUnit(String line){
        return matchesTo(line,EditorialUnit.values());
    }
    public boolean isDeletedExpr(String line){
        return matchesTo(line,DeletedExpr.values());
    }
    public boolean isNavigationUnit(String line){
        return matchesTo(line,NavigationUnit.values());
    }

    public boolean isText(String line) {
        return !isEditorialUnit(line); // && !isNavigationUnit(line);
    }

    public boolean matchesTo(String line,IHasRegex[] collection) {
        for(IHasRegex element: collection)
            if(Pattern.matches(element.findRegex(),line))
                return true;
        return false;
    }

    public ActComponent parse(){
        ActComponent root=new ActComponent(EditorialUnit.Root,"");
        SearchUnit(root,getPreParsedLine());

        return root;
    }

    public String SearchUnit(ActComponent actComp,String line){

        line=getContent(actComp,line);
        line=FindUnits(actComp,line);

        return line;
    }

    public String getContent(ActComponent actComp,String line){

        while(!endOfFile() && isText(line)) {
            actComp.addContent(line);
            line=getPreParsedLine();
        }

        if(isText(line))
            actComp.addContent(line);
        return line;
    }

    public String FindUnits(ActComponent actComp,String line){

        if(actComp.editUnitType.lowers()!=null) {
            for (EditorialUnit findingUnit : actComp.editUnitType.lowers()) {
                Pattern p = Pattern.compile(findingUnit.findRegex());
                Matcher m = p.matcher(line);

                while (m.matches()) {
                    String unitNum = m.group(1);

                    line = line.replaceFirst(findingUnit.removeRegex() + " ", "");
                    line = line.replaceFirst(findingUnit.removeRegex(), "");

                    ActComponent newActComp = new ActComponent(findingUnit, unitNum);
                    actComp.addChild(newActComp);

                    line = this.SearchUnit(newActComp, line);
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
        return line;
    }

    public boolean endOfFile(){
        return !scanner.hasNextLine();
    }

}
