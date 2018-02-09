package agh.inf.polab.act;

import agh.inf.polab.act.elements.IdentifiedEditorialUnit;
import agh.inf.polab.act.actInterface.PrinterContent;
import agh.inf.polab.act.actInterface.PrinterTableOfContent;
import agh.inf.polab.act.actInterface.Searcher;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * Collects all possible act actions
 */
public class ActInterface {
    private final Act act;

    public ActInterface(Act act) {
        this.act = act;
    }

    public String printContent(ActComponent actComponent){
        PrinterContent printerContent=new PrinterContent(act);
        printerContent.add(actComponent);
        return printerContent.get();
    }

    public String printContent(){
        return printContent(act.getRoot());
    }

    public String printContent(IdentifiedEditorialUnit startIdEditUnit, IdentifiedEditorialUnit endIdEditUnit){

        Searcher searcher=new Searcher(act);
        searcher.search(act.getArticles(),Collections.singleton(startIdEditUnit));
        searcher.search(act.getArticles(),Collections.singleton(endIdEditUnit));

        PrinterContent printerContent=new PrinterContent(act);
        Iterator<ActComponent> iterator=act.getArticles().getChilds().values().iterator();

        ActComponent temp=iterator.next();
        while (iterator.hasNext() && !temp.idEditUnit.equals(startIdEditUnit) && !temp.idEditUnit.equals(endIdEditUnit)){
            temp=iterator.next();
        }
        if(temp.idEditUnit.equals(endIdEditUnit))
            throw new IllegalArgumentException("'"+startIdEditUnit+"' is not before '"+endIdEditUnit+"'");

        while (iterator.hasNext() && !temp.idEditUnit.equals(endIdEditUnit)){
            printerContent.add(temp);
            temp=iterator.next();
        }
        if(temp.idEditUnit.equals(endIdEditUnit))
            printerContent.add(temp);

        return printerContent.get();
    }

    public String printContent(Collection<IdentifiedEditorialUnit> path){


        Searcher searcher = new Searcher(act);
        ActComponent actComponent=searcher.search(act.getArticles(),path);

        PrinterContent printerContent=new PrinterContent(act);
        printerContent.addText(searcher.getActualpath());
        printerContent.add(actComponent);
        return printerContent.get();
    }

    public String printTableOfContent(ActComponent actComponent){
        PrinterTableOfContent printerTableOfContent=new PrinterTableOfContent(act);
        printerTableOfContent.add(actComponent);
        return printerTableOfContent.get();
    }

    public String printTableOfContent(){
        return printTableOfContent(act.getRoot());
    }

    public String printTableOfContent(Collection<IdentifiedEditorialUnit> path){
        Searcher searcher = new Searcher(act);
        ActComponent actComponent=searcher.search(act.getRoot(),path);
        return printTableOfContent(actComponent);
    }
}

