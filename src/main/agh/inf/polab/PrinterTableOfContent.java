package agh.inf.polab;

import static agh.inf.polab.EditorialUnit.Article;

public class PrinterTableOfContent extends Printer {
    private static String rangeSign="-";
    private static String bigTab = "................................................................................";

    @Override
    protected String printAct(Act act) {
        return act.getTitle()+lineSeparator;
    }

    @Override
    protected String printRoot(ActComponent actComponent) {
        String tab=actComponent.id.editUnitType.toTabulation();
        String line=tab + actComponent.id.toString();

        if(actComponent.id.editUnitType == EditorialUnit.Root)
            line=" SPIS TREŚCI";

        line=line.concat(bigTab.substring(0,bigTab.length()-line.length()));
        line=line.concat(getFirstArticle(actComponent).id.toString()+rangeSign+getLastArticle(actComponent).id.editUnitNum+lineSeparator);

        if(actComponent.getContent()!=null)
            line=line.concat(tab + actComponent.getContent() + lineSeparator);

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
