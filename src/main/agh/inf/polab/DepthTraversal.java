package agh.inf.polab;

public abstract class DepthTraversal {
    protected void traverseDeeplyAct(Act act){
        processAct(act);
        traverseDeeplyActComponent(startTraverse(act));
    }
    protected void traverseDeeplyActComponent(ActComponent actComponent){
        if(stopTraverseRoot(actComponent))
            return;
        processRoot(actComponent);
        traverseDeeplyActComponent(traversedChild(actComponent));
    }

    protected abstract ActComponent startTraverse(Act act);
    protected abstract boolean stopTraverseRoot(ActComponent root);
    protected abstract void processAct(Act act);
    protected abstract void processRoot(ActComponent actComponent);
    protected abstract ActComponent traversedChild(ActComponent actComponent);
}
