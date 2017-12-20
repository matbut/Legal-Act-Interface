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

        String tab = actComponent.id.editUnitType.toTabulation();
        String line="";

        if(actComponent.id.editUnitType!=EditorialUnit.Root)
            line+=tab+actComponent.id+lineSeparator;

        if(actComponent.getContent()!=null)
            line+=tab+actComponent.getContent()+lineSeparator;

        return line;
    }

    @Override
    protected String printChild(ActComponent actComponent) {
        return "";
    }


}
