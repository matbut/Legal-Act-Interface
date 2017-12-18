package agh.inf.polab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PreParser {
    private Scanner scanner;

    public PreParser(String fileName) throws FileNotFoundException {
        scanner=new Scanner(new File(fileName)).useDelimiter(Pattern.compile(System.getProperty("line.separator")));
    }


    public String getLine() {
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
}
