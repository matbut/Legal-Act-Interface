package agh.inf.polab;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public abstract class Printer extends BreadthwiseTraversal {
    private StringBuilder strBuilder=new StringBuilder();
    protected String lineSeparator=System.getProperty("line.separator");

    public String print(Act act){
        traverseBreadthwise(act);
        return strBuilder.toString();
    }

    @Override
    protected void processAct(Act act) {
        strBuilder.append((printAct(act)));
    }
    @Override
    protected void processRoot(ActComponent actComponent) {
        strBuilder.append(printRoot(actComponent));
    }
    @Override
    protected boolean stopTraverseRoot(ActComponent root) {
        return false;
    }
    @Override
    protected boolean stopTraverseChilds(ActComponent child) {
        return false;
    }

    protected abstract String printAct(Act act);
    protected abstract String printRoot(ActComponent actComponent);
    @Override
    protected ActComponent startTraverse(Act act) {
        return act.getRoot();
    }

}
