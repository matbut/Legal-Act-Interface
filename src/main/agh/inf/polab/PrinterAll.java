package agh.inf.polab;

public class PrinterAll extends ActPrinter{

    @Override
    protected String printAct(Act act) {
        return act.getTitle()+lineSeparator+act.getPreamble()+lineSeparator;
    }

    @Override
    protected String printRoot(ActComponent actComponent) {
        String line="";
        if(actComponent.id.editUnitType!=EditorialUnit.Root)
            line=line.concat(actComponent.id.toString()+lineSeparator);
        if(actComponent.getContent()!=null)
            line=line.concat(actComponent.getContent()+lineSeparator);
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
