package agh.inf.polab;

public abstract class BreadthwiseTraversal {

    protected void traverseBreadthwise(Act act){
        processAct(act);
        traverseBreadthwiseComponent(startTraverse(act));
    }
    private void traverseBreadthwiseComponent(ActComponent actComponent){
        if(stopTraverseRoot(actComponent))
            return;
        processRoot(actComponent);
        for (ActComponent child : actComponent.getChildrens().values()){
            if(!stopTraverseChilds(child)){
                processChild(child);
                traverseBreadthwiseComponent(child);
            }
        }
    }

    protected abstract ActComponent startTraverse(Act act);
    protected abstract boolean stopTraverseRoot(ActComponent root);
    protected abstract boolean stopTraverseChilds(ActComponent child);
    protected abstract void processAct(Act act);
    protected abstract void processRoot(ActComponent actComponent);
    protected abstract void processChild(ActComponent children);

}