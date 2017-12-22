package agh.inf.polab;

import java.util.Collection;

public abstract class Traversal {
    protected Act act;

    public Traversal(Act act) {
        this.act = act;
    }

    protected void traverseComponent(ActComponent actComponent){
        if(stopTraverseRoot(actComponent))
            return;
        processRoot(actComponent);
        for (ActComponent child : traversedChild(actComponent)){
            traverseComponent(child);
        }
    }
    protected abstract boolean stopTraverseRoot(ActComponent root);
    protected abstract void processRoot(ActComponent root);
    protected abstract Collection<ActComponent> traversedChild(ActComponent actComponent);
}