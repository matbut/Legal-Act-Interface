package agh.inf.polab;

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
        String tab=actComponent.idEditUnit.type.toTabulation();

        String line=tab+actComponent.idEditUnit.toString();

        if(actComponent.idEditUnit.type == EditorialUnit.Root)
            line+=heading;

        line+=bigTab.substring(0,bigTab.length()-line.length());
        line+=getFirstArticle(actComponent).idEditUnit;

        if(!getFirstArticle(actComponent).equals(getLastArticle(actComponent)))
            line+=rangeSign+getLastArticle(actComponent).idEditUnit.id;
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
        return !child.idEditUnit.type.isInTableOfContent();
}

    private ActComponent getFirstArticle(ActComponent actComponent){
        while(actComponent.idEditUnit.type !=EditorialUnit.Article)
            actComponent=actComponent.getFirstChild();
        return actComponent;
    }
    private ActComponent getLastArticle(ActComponent actComponent){
        while(actComponent.idEditUnit.type !=EditorialUnit.Article)
            actComponent=actComponent.getLastChild();
        return actComponent;
    }
}
