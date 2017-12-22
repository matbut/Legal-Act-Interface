package agh.inf.polab;

import picocli.CommandLine;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args){
        try {
            ProgSpecyfication progSpec = new ProgSpecyfication();
            CommandLine commandLine = new CommandLine(progSpec);
            commandLine.registerConverter(IdentifiedEditorialUnit.class, s -> Converter.fromComandLineToIdEditUnit(s));
            commandLine.parse(args);

            if (progSpec.usageHelpRequested)
                commandLine.usage(System.err);

            if (progSpec.versionRequested)
                commandLine.printVersionHelp(System.err, CommandLine.Help.Ansi.AUTO);

            ActParser actParser = new ActParser(progSpec.inputFile);
            Act act = actParser.parse();

            ActInterface actInterface = new ActInterface(act);

            if(progSpec.tableOfContent){
                if(progSpec.path!=null)
                    System.out.println(actInterface.printTableOfContent(progSpec.path));
                else
                    System.out.println(actInterface.printTableOfContent());
            }
            if(progSpec.content){
                if(progSpec.path!=null)
                    System.out.println(actInterface.printContent(progSpec.path));
                else
                    System.out.println(actInterface.printContent());
            }
            if(progSpec.range!=null){
                System.out.println(actInterface.printContent(progSpec.range[0],progSpec.range[1]));
            }

        }catch(FileNotFoundException  e){
            System.out.println("File not found. "+e);
        }catch(IllegalArgumentException e) {
            System.out.println("Illegal argument. " + e);
        }catch(InputMismatchException e){
            System.out.println("InputMismatchException. "+e);
        }catch(NoSuchElementException e){
            System.out.println("NoSuchElementException. "+e);
        }
    }

}
