package agh.inf.polab;

import java.util.Collection;

import java.util.stream.Collectors;
import java.util.stream.Stream;


public class PrinterTableOfContent extends Printer {

    public PrinterTableOfContent(Act act) {
        super(act);
    }

    @Override
    protected String printAct() {
        return act.getTitle()+lineSeparator;
    }

    @Override
    protected String printRoot(ActComponent actComponent) {
        String rangeSign="–";
        String bigTab = ".......................................................................................";
        String heading = " SPIS TREŚCI ";
        String tab=actComponent.idEditUnit.type.toTabulation();

        String line="";
        if(actComponent.idEditUnit.type == EditorialUnit.Root)
            line+=heading;
        else
            line+=tab+actComponent.idEditUnit.toString();

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
    protected Collection<ActComponent> traversedChild(ActComponent actComponent) {
        Stream<ActComponent> childs = actComponent.getChilds().values().stream();
        return childs.filter(p -> p.idEditUnit.type!=EditorialUnit.Article).collect(Collectors.toList());
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
