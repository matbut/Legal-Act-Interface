package agh.inf.polab.act.actInterface;

import agh.inf.polab.act.elements.EditorialUnit;
import agh.inf.polab.act.Act;
import agh.inf.polab.act.ActComponent;

import java.util.Collection;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Prints table of content of act component
 */
public class PrinterTableOfContent extends Printer {

    public PrinterTableOfContent(Act act) {
        super(act);
    }

    //Specyfying printing methods

    @Override
    String printAct() {
        return act.getTitle()+lineSeparator;
    }

    @Override
    String printRoot(ActComponent actComponent) {
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
    Collection<ActComponent> traversedChild(ActComponent actComponent) {
        Stream<ActComponent> childs = actComponent.getChilds().values().stream();
        return childs.filter(p -> p.idEditUnit.type!=EditorialUnit.Article).collect(Collectors.toList());
    }

    // Aditional methods for printing range.

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
