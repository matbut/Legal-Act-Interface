package agh.inf.polab;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public abstract class Search extends DepthTraversal{
    protected String lineSeparator=System.getProperty("line.separator");
    protected LinkedList<IdentifiedEditorialUnit> path;
    protected ActComponent finded;
    protected String actualpath;

    protected ActComponent search(Act act,LinkedList<IdentifiedEditorialUnit> path) {
        this.path=path;
        traverseDeeplyAct(act);
        return finded;
    }
    @Override
    protected boolean stopTraverseRoot(ActComponent root) {
        if(path.size()==0){
            return true;
        }
        return false;
    }

    @Override
    protected void processAct(Act act) {
        actualpath="";
    }

    @Override
    protected void processRoot(ActComponent actComponent) {
        actualpath=actualpath.concat(actComponent.idEditUnit.toString() + lineSeparator);
    }

    @Override
    protected ActComponent traversedChild(ActComponent actComponent) {
        finded=actComponent.getChildrens().get(path.getFirst().type) ;
        if(finded==null)
            throw new NoSuchElementException("'" + path.getFirst().toString() + "' wasn't found in '" + actComponent.idEditUnit.toString() + "'");
        if(!finded.idEditUnit.equals(path.getFirst()))
            throw new NoSuchElementException("'" + finded.idEditUnit.toString() + "' was found in '" + actComponent.idEditUnit.toString() + "' instead of '" + path.getFirst().toString() + "'");
        path.removeFirst();
        return finded;
    }
}
