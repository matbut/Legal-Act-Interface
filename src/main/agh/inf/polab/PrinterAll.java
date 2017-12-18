package agh.inf.polab;

public class PrinterAll extends Printer {

    @Override
    protected String printAct(Act act) {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(act.getTitle());
        strBuilder.append(lineSeparator);
        if(act.getPreamble()!=null){
            strBuilder.append(act.getPreamble());
            strBuilder.append(lineSeparator);
        }
        return strBuilder.toString();
    }

    @Override
    protected String printRoot(ActComponent actComponent) {
        StringBuilder strBuilder = new StringBuilder();
        String tab = actComponent.id.editUnitType.toTabulation();

        if(actComponent.id.editUnitType!=EditorialUnit.Root){
            strBuilder.append(tab);
            strBuilder.append(actComponent.id.toString());
            strBuilder.append(lineSeparator);
        }
        if(actComponent.getContent()!=null) {
            strBuilder.append(tab);
            strBuilder.append(actComponent.getContent());
            strBuilder.append(lineSeparator);
        }
        return strBuilder.toString();
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
