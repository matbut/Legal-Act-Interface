package agh.inf.polab;



public class SearchTableOfContent extends Search{

    @Override
    protected ActComponent startTraverse(Act act) {
        return act.getRoot();
    }

    @Override
    protected boolean stopTraverseRoot(ActComponent root) {
        return super.stopTraverseRoot(root) || !root.idEditUnit.type.isInTableOfContent();
    }

}
