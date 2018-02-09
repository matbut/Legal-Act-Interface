package agh.inf.polab;

import agh.inf.polab.act.Act;
import agh.inf.polab.act.ActInterface;
import agh.inf.polab.act.ActParser;
import agh.inf.polab.act.elements.IdentifiedEditorialUnit;
import picocli.CommandLine;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args){
        try {
            ProgSpecyfication progSpec = new ProgSpecyfication();
            CommandLine commandLine = new CommandLine(progSpec);
            commandLine.registerConverter(IdentifiedEditorialUnit.class, s -> ProgSpecyfication.fromComandLineToIdEditUnit(s));
            commandLine.parse(args);

            if (progSpec.isUsageHelpRequested())
                commandLine.usage(System.err);

            if (progSpec.isVersionRequested())
                commandLine.printVersionHelp(System.err, CommandLine.Help.Ansi.AUTO);

            progSpec.check();

            ActParser actParser = new ActParser(progSpec.getInputFile());
            Act act = actParser.parse();

            ActInterface actInterface = new ActInterface(act);

            if(progSpec.isTableOfContent()){
                if(progSpec.getPath()!=null)
                    System.out.println(actInterface.printTableOfContent(progSpec.getPath()));
                else
                    System.out.println(actInterface.printTableOfContent());
            }
            if(progSpec.isContent()){
                if(progSpec.getPath()!=null)
                    System.out.println(actInterface.printContent(progSpec.getPath()));
                else
                    System.out.println(actInterface.printContent());
            }
            if(progSpec.getRange()!=null){
                System.out.println(actInterface.printContent(progSpec.getRange()[0],progSpec.getRange()[1]));
            }

        }catch(FileNotFoundException  e){
            System.out.println("File not found. "+e.getMessage());
        }catch(IllegalArgumentException e) {
            System.out.println("Illegal argument. "+e.getMessage());
        }catch(InputMismatchException e){
            System.out.println("Input mismatch. "+e.getMessage());
        }catch(NoSuchElementException e){
            System.out.println("NoSuch element. "+e.getMessage());
        }catch(CommandLine.ParameterException e){
            System.out.println(e.getMessage());
        }
    }

}
