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
            commandLine.registerConverter(IdentifiedEditorialUnit.class, s -> IdentifiedEditorialUnit.convert2(s));

            commandLine.parse(args);


            assert  app.tableOfContent;
            assert  app.inputFile != null;

            if (app.usageHelpRequested) {
                CommandLine.usage(new OptionParser(),System.err);
            }

            ActParser actParser = new ActParser(app.inputFile);
            Act act = actParser.parse();

            if(app.tableOfContent){
                Printer printer= new PrinterTableOfContent();
                System.out.println(printer.print(act));
            }
            if(app.content){
                Printer printer = new PrinterAll();
                System.out.println(printer.print(act));
            }
            if(app.path!=null){
                SearchContent searchContent=new SearchContent();
                System.out.println(searchContent.search(act,app.path).idEditUnit);
            }
            if(app.range!=null){
                PrinterRange printer = new PrinterRange();
                System.out.println(printer.print(act,app.range.getFirst(),app.range.getLast()));
            }


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
