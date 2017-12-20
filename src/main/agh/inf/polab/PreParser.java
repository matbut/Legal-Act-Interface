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

        if (Pattern.matches(".*-$",line))
            line = line.replaceFirst("-$", "");
    }

    /*
    public boolean isEditorialUnit(){
        return matchesTo(EditorialUnit.values());
    }
    private boolean isDeletedExpr(){
        return matchesTo(DeletedExpr.values());
    }
    private boolean matchesTo(IHasRegex[] collection) {
        for (IHasRegex element : collection)
            if (Pattern.matches(element.findRegex(), line)) {
                return true;
            }
        return false;
    }
    */

        /*
        private LinkedList<IdentifiedEditorialUnit> zip(List<EditorialUnit> editUnitList, List<String> idList){
        if(editUnitList.size()!=idList.size())
            throw new IllegalArgumentException("Illegal path");

        LinkedList<IdentifiedEditorialUnit> idEditUnitList=new LinkedList<>();

        Iterator<EditorialUnit> editUnitIterator = editUnitList.iterator();
        Iterator<String> idIterator = idList.iterator();

        while(editUnitIterator.hasNext())
            idEditUnitList.add(new IdentifiedEditorialUnit(editUnitIterator.next(),idIterator.next()));

        return idEditUnitList;

     */
}
