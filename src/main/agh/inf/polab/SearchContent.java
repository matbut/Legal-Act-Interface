package agh.inf.polab;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class SearchContent extends ActTraversal {
    protected String lineSeparator=System.getProperty("line.separator");
    private LinkedList<IdentifiedEditorialUnit> path;
    private ActComponent finded;
    private String actualpath;

    public ActComponent search(Act act,LinkedList<IdentifiedEditorialUnit> path) {
        this.path=path;
        traverseSpecyficAct(act);
        return finded;
    }

    @Override
    protected void processAct(Act act) {
        actualpath="Start";
    }
    @Override
    protected ActComponent startTraverse(Act act) {
        return act.getArticles();
    }

    @Override
    protected boolean stopTraverseRoot(ActComponent root) {
        if(path.size()==0){
            return true;
        }
        return false;
    }
    @Override
    protected void processRoot(ActComponent actComponent) throws NoSuchElementException {
        actualpath=actualpath.concat(actComponent.id.toString() + lineSeparator);

    }
    @Override
    protected boolean stopTraverseChilds(ActComponent child) {
        return false;
    }
    @Override
    protected void processChild(ActComponent children) {
    }

    @Override
    protected ActComponent traversedChild(ActComponent actComponent) {
        finded=actComponent.getChildrens().get(path.getFirst().editUnitNum) ;
        if(finded==null)
            throw new NoSuchElementException("'" + path.getFirst().toString() + "' wasn't found in '" + actComponent.id.toString() + "'");
        if(!finded.id.equals(path.getFirst()))
            throw new NoSuchElementException("'" + finded.id.toString() + "' was found in '" + actComponent.id.toString() + "' instead of '" + path.getFirst().toString() + "'");
        path.removeFirst();
        return finded;
    }
}
