package agh.inf.polab;

public class PrinterContent extends Printer {
    private  boolean printPreamble=false;

    public PrinterContent(Act act) {
        super(act);
    }

    @Override
    public void add(ActComponent actComponent){
        if(actComponent.idEditUnit.type==EditorialUnit.Root)
            printPreamble=true;

        super.add(actComponent);
    }

    @Override
    protected String printAct() {
        String line=act.getTitle()+lineSeparator;
        if(printPreamble && act.getPreamble()!=null)
            line+=act.getPreamble()+lineSeparator;
        return line;
    }

    @Override
    protected String printRoot(ActComponent actComponent) {

        String tab = actComponent.idEditUnit.type.toTabulation();
        String line="";

        if(actComponent.idEditUnit.type!=EditorialUnit.Root)
            line+=tab+actComponent.idEditUnit+lineSeparator;

        if(actComponent.getContent()!=null)
            line+=tab+actComponent.getContent()+lineSeparator;

        return line;
    }
}
