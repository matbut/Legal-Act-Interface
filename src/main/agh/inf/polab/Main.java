package agh.inf.polab;

import java.io.FileNotFoundException;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args){
        try {

            ProgSpecyfication progSpec = new ProgSpecyfication(args);
            ActParser actParser = new ActParser(progSpec.getFileName());

            Act act = actParser.parse();

            act.printTableOfContent();
            act.printAll();

            //LinkedList<IdentifiedEditorialUnit> path = new LinkedList<>();
            //path.add(new IdentifiedEditorialUnit(EditorialUnit.Article,"228"));
            //path.add(new IdentifiedEditorialUnit(EditorialUnit.Passagge,"4"));

            //ActComponent actComponent=act.search(path);
            //if(actComponent!=null)
            //    actComponent.printAll();
            //else
            //    System.out.println("Nie ma takiego elementu w tej ustawie.");


        }catch(FileNotFoundException  e){
            System.out.println("Nie znaleziono pliku");
            e.printStackTrace();
        }catch (IllegalArgumentException e){
            System.out.println("Błędny argument " + e);
            e.printStackTrace();
        }

    }

}
