package agh.inf.polab.act.actInterface;

import agh.inf.polab.act.Act;
import agh.inf.polab.act.ActComponent;

import java.util.Collection;

/**
 * Act printer
 */
public abstract class Printer extends Traversal {
    private StringBuilder strBuilder;
    String lineSeparator=System.getProperty("line.separator");

    Printer(Act act){
        super(act);
        strBuilder=new StringBuilder(printAct());
    }

    //Public avaliable actions

    public void addText(String text){
        strBuilder.append(text);
    }

    public void add(ActComponent actComponent){
        traverseComponent(actComponent);
    }

    public String get(){
        return strBuilder.toString();
    }

    //Printer components

    abstract String printAct();

    abstract String printRoot(ActComponent actComponent);

    @Override
    boolean stopTraverseRoot(ActComponent root) {
        return false;
    }

    @Override
    void processRoot(ActComponent root) {
        strBuilder.append(printRoot(root));
    }

    @Override
    Collection<ActComponent> traversedChild(ActComponent actComponent) {
        return actComponent.getChilds().values();
    }
}
