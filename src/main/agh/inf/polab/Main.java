package agh.inf.polab;

import picocli.CommandLine;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args){
        try {


            OptionParser app = new OptionParser();
            CommandLine commandLine = new CommandLine(app);
            commandLine.registerConverter(IdentifiedEditorialUnit.class, s -> IdentifiedEditorialUnit.convert(s));

            commandLine.parse(args);


            assert  app.tableOfContent;
            assert  app.inputFile != null;

            if (app.usageHelpRequested) {
                CommandLine.usage(new OptionParser(),System.err);
                return;
            }

            ActParser actParser = new ActParser(app.inputFile);

            Act act = actParser.parse();

            PrinterAll printer = new PrinterAll();
            System.out.println(printer.print(act));

            PrinterTableOfContent tprinter= new PrinterTableOfContent();
            System.out.println(tprinter.print(act));

            /*
            LinkedList<IdentifiedEditorialUnit> path = new LinkedList<>();
            path.add(new IdentifiedEditorialUnit(EditorialUnit.Article,"4"));
            path.add(new IdentifiedEditorialUnit(EditorialUnit.Passagge,"1"));
            */

            //SearchContent searchContent = new SearchContent();
            //ActComponent actComponent=searchContent.search(act,app.path);
            //System.out.println(printer.print(act,actComponent));

            /*
            LinkedList<IdentifiedEditorialUnit> path2 = new LinkedList<>();
            path2.add(new IdentifiedEditorialUnit(EditorialUnit.Chapter,"II"));
            */

        }catch(FileNotFoundException  e){
            System.out.println("File not found");
            e.printStackTrace();

        }catch(IllegalArgumentException e) {
            System.out.println("Illegal argument " + e);
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
