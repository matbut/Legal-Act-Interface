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
        actualpath=actualpath.concat(actComponent.id.toString() + lineSeparator);
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
