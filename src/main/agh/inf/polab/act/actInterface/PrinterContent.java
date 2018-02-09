package agh.inf.polab.act.actInterface;

import agh.inf.polab.act.elements.EditorialUnit;
import agh.inf.polab.act.Act;
import agh.inf.polab.act.ActComponent;

/**
 * Prints content of act component
 */
public class PrinterContent extends Printer {
    private  boolean printPreamble=false;

    public PrinterContent(Act act) {
        super(act);
    }

    //Specyfying printing methods

    @Override
    public void add(ActComponent actComponent){
        if(actComponent.idEditUnit.type== EditorialUnit.Root)
            printPreamble=true;

        super.add(actComponent);
    }

    @Override
    String printAct() {
        String line=act.getTitle()+lineSeparator;
        if(printPreamble && act.getPreamble()!=null)
            line+=act.getPreamble()+lineSeparator;
        return line;
    }

    @Override
    String printRoot(ActComponent actComponent) {

        String tab = actComponent.idEditUnit.type.toTabulation();
        String line="";

        if(actComponent.idEditUnit.type!=EditorialUnit.Root)
            line+=tab+actComponent.idEditUnit+lineSeparator;

        if(actComponent.getContent()!=null)
            line+=tab+actComponent.getContent()+lineSeparator;

        return line;
    }
}
