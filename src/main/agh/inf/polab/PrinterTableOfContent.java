package agh.inf.polab;

import static agh.inf.polab.EditorialUnit.Article;

public class PrinterTableOfContent extends Printer {
    private static String rangeSign="-";
    private static String bigTab = "................................................................................";
    private static String heading = " SPIS TREŚCI ";

    @Override
    protected String printAct(Act act) {
        return act.getTitle()+lineSeparator;
    }

    @Override
    protected String printRoot(ActComponent actComponent) {
        String tab=actComponent.id.editUnitType.toTabulation();

        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(tab);
        strBuilder.append(actComponent.id.toString());

        if(actComponent.id.editUnitType == EditorialUnit.Root)
            strBuilder.append(heading);

        strBuilder.append(bigTab.substring(0,bigTab.length()-strBuilder.length()));
        strBuilder.append(getFirstArticle(actComponent).id.toString());
        strBuilder.append(rangeSign);
        strBuilder.append(getLastArticle(actComponent).id.editUnitNum);
        strBuilder.append(lineSeparator);

        if(actComponent.getContent()!=null){
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

    @Override
    protected boolean stopTraverseChilds(ActComponent child) {
        return !child.id.editUnitType.isInTableOfContent();
}

    private ActComponent getFirstArticle(ActComponent actComponent){
        while(actComponent.id.editUnitType!=EditorialUnit.Article)
            actComponent=actComponent.getFirstChild();
        return actComponent;
    }
    private ActComponent getLastArticle(ActComponent actComponent){
        while(actComponent.id.editUnitType!=EditorialUnit.Article)
            actComponent=actComponent.getLastChild();
        return actComponent;
    }
}
