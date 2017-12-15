package agh.inf.polab;

public abstract class ActTraversal {

    protected void traverseAct(Act act){
        processAct(act);
        traverseAllActComponent(startTraverse(act));
    }
    private void traverseAllActComponent(ActComponent actComponent){
        if(stopTraverseRoot(actComponent))
            return;
        processRoot(actComponent);
        for (ActComponent child : actComponent.getChildrens().values()){

            if(!stopTraverseChilds(child)){
                processChild(child);
                traverseAllActComponent(child);
            }
        }
    }

    protected void traverseSpecyficAct(Act act){
        processAct(act);
        traverseSpecyficActComponent(startTraverse(act));
    }
    protected void traverseSpecyficActComponent(ActComponent actComponent){
        if(stopTraverseRoot(actComponent))
            return;
        processRoot(actComponent);
        traverseSpecyficActComponent(traversedChild(actComponent));
    }

    protected abstract ActComponent startTraverse(Act act);
    protected abstract boolean stopTraverseRoot(ActComponent root);
    protected abstract boolean stopTraverseChilds(ActComponent child);
    protected abstract void processAct(Act act);
    protected abstract void processRoot(ActComponent actComponent);
    protected abstract void processChild(ActComponent children);
    protected abstract ActComponent traversedChild(ActComponent actComponent);
}