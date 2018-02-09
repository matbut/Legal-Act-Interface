package agh.inf.polab.act;

import agh.inf.polab.act.elements.DeletedExpr;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Do text file pre-parsing. Working like buffer.
 * Delete "DeletedExpr" from lines.
 * Delete "-" from the end of lines.
 */
class PreParser {
    private Scanner scanner;
    private String line;

    PreParser(File file) throws FileNotFoundException {
        scanner=new Scanner(file);
        scanner.useDelimiter(Pattern.compile(System.getProperty("line.separator")));

        getNewLine();
    }

    //Pre parser avaliable methods:

    void clearLine(String regex){
        line = line.replaceFirst(regex, "");
        if(line.isEmpty())
            getNewLine();
    }

    void clearLine(){
        clearLine(".*");
    }

    String getLine() {
        return line;
    }

    boolean endOfFile(){
        return line.isEmpty();
    }

    //the most important method, remove illegal expresions:

    private void getNewLine() {
        line="";
        if(scanner.hasNext())
            line = scanner.nextLine();

        while (scanner.hasNext() && DeletedExpr.is(line))
            line = scanner.nextLine();

        if(!line.isEmpty())
            if (Pattern.matches(".*-$",line))
                line = line.replaceFirst("-$", "");
            else
                line+=" ";
    }
}
