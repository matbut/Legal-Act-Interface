package agh.inf.polab;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args){
        try {

            ProgSpecyfication progSpec = new ProgSpecyfication(args);


            ActParser actParser = new ActParser(progSpec.getFileName());

            Act act = actParser.parse();

            PrinterAll printer = new PrinterAll();
            System.out.println(printer.print(act));

            PrinterTableOfContent tprinter= new PrinterTableOfContent();
            System.out.println(tprinter.print(act));



            LinkedList<IdentifiedEditorialUnit> path = new LinkedList<>();
            path.add(new IdentifiedEditorialUnit(EditorialUnit.Article,"4"));
            path.add(new IdentifiedEditorialUnit(EditorialUnit.Passagge,"1"));


            SearchContent searchContent = new SearchContent();
            ActComponent actComponent=searchContent.search(act,path);
            System.out.println(actComponent.id.toString());

            LinkedList<IdentifiedEditorialUnit> path2 = new LinkedList<>();
            path2.add(new IdentifiedEditorialUnit(EditorialUnit.Chapter,"II"));


            SearchTableOfContent searchTableOfContent = new SearchTableOfContent();
            actComponent=searchTableOfContent.search(act,path2);
            System.out.println(actComponent.id.toString());


        }catch(FileNotFoundException  e){
            System.out.println("Nie znaleziono pliku");
            e.printStackTrace();
        }catch(IllegalArgumentException e) {
            System.out.println("Błędny argument " + e);
            e.printStackTrace();
        }catch(InputMismatchException e){
            System.out.println("InputMismatchException");
            e.printStackTrace();
        }catch(NoSuchElementException e){
            System.out.println("NoSuchElementException");
            e.printStackTrace();
        }
    }

}
