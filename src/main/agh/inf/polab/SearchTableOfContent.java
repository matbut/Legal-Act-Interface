package agh.inf.polab;

import static agh.inf.polab.EditorialUnit.Article;

public class SearchTableOfContent extends Search{

    @Override
    protected ActComponent startTraverse(Act act) {
        return act.getRoot();
    }

    @Override
    protected boolean stopTraverseRoot(ActComponent root) {
        return super.stopTraverseRoot(root) || !root.id.editUnitType.isInTableOfContent();
    }

}
