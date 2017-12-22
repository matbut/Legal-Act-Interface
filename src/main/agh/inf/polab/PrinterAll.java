package agh.inf.polab;

public class PrinterAll extends Printer {


    @Override
    protected String printAct(Act act) {
        String line=act.getTitle()+lineSeparator;
        if(act.getPreamble()!=null)
            line+=act.getPreamble()+lineSeparator;

        return line;
    }

    @Override
    protected String printRoot(ActComponent actComponent) {

        String tab = actComponent.idEditUnit.type.toTabulation();
        String line="";

        if(actComponent.idEditUnit.type !=EditorialUnit.Root)
            line+=tab+actComponent.idEditUnit+lineSeparator;

        if(actComponent.getContent()!=null)
            line+=tab+actComponent.getContent()+lineSeparator;

        return line;
    }
}
