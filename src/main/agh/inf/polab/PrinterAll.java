package agh.inf.polab;

public class PrinterAll extends Printer {

    @Override
    protected String printAct(Act act) {
        return act.getTitle()+lineSeparator+act.getPreamble()+lineSeparator;
    }

    @Override
    protected String printRoot(ActComponent actComponent) {
        String line="";
        String tab = actComponent.id.editUnitType.toTabulation();
        if(actComponent.id.editUnitType!=EditorialUnit.Root)
            line=line.concat(tab + actComponent.id.toString()+lineSeparator);
        if(actComponent.getContent()!=null)
            line=line.concat(tab + actComponent.getContent()+lineSeparator);
        return line;
    }

    @Override
    protected String printChild(ActComponent actComponent) {
        return "";
    }

    @Override
    protected ActComponent startTraverse(Act act) {
        return act.getRoot();
    }
}
