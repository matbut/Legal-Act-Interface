package agh.inf.polab.act.actInterface;

import agh.inf.polab.act.Act;
import agh.inf.polab.act.ActComponent;

import java.util.Collection;

/**
 * Universal recursive act traversal
 */

public abstract class Traversal {
    protected Act act;

    Traversal(Act act) {
        this.act = act;
    }

    void traverseComponent(ActComponent actComponent){
        if(stopTraverseRoot(actComponent))
            return;
        processRoot(actComponent);
        for (ActComponent child : traversedChild(actComponent)){
            traverseComponent(child);
        }
    }

    abstract boolean stopTraverseRoot(ActComponent root);

    abstract void processRoot(ActComponent root);

    abstract Collection<ActComponent> traversedChild(ActComponent actComponent);
}