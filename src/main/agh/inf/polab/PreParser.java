package agh.inf.polab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PreParser {
    private Scanner scanner;
    private String line;

    public PreParser(File file) throws FileNotFoundException {
        scanner=new Scanner(file);
        scanner.useDelimiter(Pattern.compile(System.getProperty("line.separator")));

        getNewLine();
    }

    public void clearLine(String regex){
        line = line.replaceFirst(regex, "");
        if(line.isEmpty())
            getNewLine();
    }
    public void clearLine(){
        clearLine(".*");
    }
    public String getLine() {
        return line;
    }
    public boolean endOfFile(){
        return line.isEmpty();
    }

    private void getNewLine() {
        line="";
        if(scanner.hasNext())
            line = scanner.nextLine();

        while (scanner.hasNext() && DeletedExpr.is(line))
            line = scanner.nextLine();

        if(!line.isEmpty() && !IdentifiedEditorialUnit.is(line))
            if (Pattern.matches(".*-$",line))
                line = line.replaceFirst("-$", "");
            else
                line+=" ";
    }
}
