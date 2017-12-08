package agh.inf.polab;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args){
        try {

            ProgSpecyfication progSpec = new ProgSpecyfication(args);
            ActParser actParser = new ActParser(progSpec.getFileName());

            ActComponent root = actParser.parse();

            root.PrintTableOfContent();

        }catch(FileNotFoundException e){
            System.out.println("Nie znaleziono pliku");
            e.printStackTrace();
        }

    }

}
