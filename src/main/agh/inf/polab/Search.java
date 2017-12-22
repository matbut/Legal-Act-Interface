package agh.inf.polab;

import java.util.*;

public abstract class Search extends DepthTraversal{
    protected String lineSeparator=System.getProperty("line.separator");
    protected Iterator<IdentifiedEditorialUnit> path;
    protected ActComponent finded;
    protected String actualpath;

    protected ActComponent search(Act act,Collection<IdentifiedEditorialUnit> path) {
        this.path=path.iterator();
        traverseDeeplyAct(act);
        return finded;
    }
    @Override
    protected boolean stopTraverseRoot(ActComponent root) {
        return !path.hasNext();
    }

    @Override
    protected void processAct(Act act) {
        actualpath="";
    }

    @Override
    protected void processRoot(ActComponent actComponent) {
        actualpath=actualpath.concat(actComponent.idEditUnit + lineSeparator);
    }

    @Override
    protected ActComponent traversedChild(ActComponent actComponent) {
        IdentifiedEditorialUnit first = path.next();
        finded=actComponent.getChildrens().get(first.id) ;
        if(finded==null)
            throw new NoSuchElementException("'"+first+"' wasn't found in '"+actComponent.idEditUnit+"'. Got there by '"+actualpath+"'");
        if(!finded.idEditUnit.equals(first))
            throw new NoSuchElementException("'"+finded.idEditUnit+"' was found in '"+actComponent.idEditUnit+"' instead of '"+first+"'. Got there by '"+actualpath+"'");
        return finded;
    }
}
