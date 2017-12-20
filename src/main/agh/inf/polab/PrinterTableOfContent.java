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

        String line=tab+actComponent.id.toString();

        if(actComponent.id.editUnitType == EditorialUnit.Root)
            line+=heading;

        line+=bigTab.substring(0,bigTab.length()-line.length());
        line+=getFirstArticle(actComponent).id;
        if(!getFirstArticle(actComponent).equals(getLastArticle(actComponent)))
            line+=rangeSign+getLastArticle(actComponent).id.editUnitNum;
        line+=lineSeparator;

        if(actComponent.getContent()!=null)
            line+=tab+actComponent.getContent()+lineSeparator;

        return line;
    }

    @Override
    protected String printChild(ActComponent actComponent) {
        return "";
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
