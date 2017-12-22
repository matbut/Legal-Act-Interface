package agh.inf.polab;


import java.util.*;

public class Searcher extends Traversal{
    private String lineSeparator=System.getProperty("line.separator");
    private Iterator<IdentifiedEditorialUnit> path;
    private ActComponent finded;
    private String actualpath="";

    public Searcher(Act act) {
        super(act);
    }

    public ActComponent search(ActComponent actComponent,Collection<IdentifiedEditorialUnit> path) {
        this.path=path.iterator();
        traverseComponent(actComponent);
        return finded;
    }

    @Override
    protected boolean stopTraverseRoot(ActComponent root) {
        return !path.hasNext();
    }

    @Override
    protected void processRoot(ActComponent root) {
        actualpath=actualpath.concat(root.idEditUnit + lineSeparator);
    }

    @Override
    protected Collection<ActComponent> traversedChild(ActComponent actComponent) {
        IdentifiedEditorialUnit first = path.next();
        finded=actComponent.getChilds().get(first) ;
        if(finded==null)
            throw new NoSuchElementException("'"+first+"' wasn't found in '"+actComponent.idEditUnit+"'. Got there by "+actualpath+"");
        if(!finded.idEditUnit.equals(first))
            throw new NoSuchElementException("'"+finded.idEditUnit+"' was found in '"+actComponent.idEditUnit+"' instead of '"+first+"'. Got there by "+actualpath+"");
        return Collections.singleton(finded);
    }
}