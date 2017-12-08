package agh.inf.polab;

public class ProgSpecyfication {
    private String fileName;
    private boolean PrintTableOfContents=false;
    private boolean PrintContentOfPath=false;
    private EditorialUnit[] path=null;


    public ProgSpecyfication(String[] args) throws IllegalArgumentException{
        if(args.length==0) {
            System.out.println("Help: Allowed arguments:");
            // spisu treści ustawy
            // spis treści działu o numerze

            // wyświetlanie specyficznych elementów składowych artykułu
            // treści artykułu o określonym numerze lub zakresu artykułów

            // -a - Print All;
            // -t - Print Table Of Contents;
            // -p - Print Content Of Path;


            return;
        }

        if(args.length<2)
            throw new IllegalArgumentException("Too less arguments");

        this.fileName=args[1];


        /*
        if(args.length<3){
            if(args[1].equals("-a")){
                this.PrintAll=true;
                return;
            }
            if(args[1].equals("-t")){
                this.PrintTableOfContents=true;
                return;
            }
            if(args[1].equals("-p"))
                throw new IllegalArgumentException("Lack of path");
            throw new IllegalArgumentException("Incorrect second argument");
        }

        if(!args[1].equals("-p"))
            throw new IllegalArgumentException("Incorrect second argument");

        this.path= new EditorialUnit[args.length-2];
        for(int i=2;i<args.length;i++){
            EditorialUnit editUnit=EditorialUnit.fromString(args[i]);
            if(editUnit!=null)
                this.path[i-2]=editUnit;
            else
                throw new IllegalArgumentException("Incorrect "+i+" argument");
        }
        */

    }

    public String getFileName() {
        return fileName;
    }



}

