package agh.inf.polab;

import java.util.*;

public class Searcher extends Traversal{
    private String lineSeparator=System.getProperty("line.separator");
    private ActComponent finded;
    private String actualpath="";
    private Queue<IdentifiedEditorialUnit> path;

    public Searcher(Act act) {
        super(act);
    }

    public ActComponent search(ActComponent actComponent,Collection<IdentifiedEditorialUnit> path) {
        this.path=new LinkedList<>(path);
        traverseComponent(actComponent);
        return finded;
    }

    @Override
    protected boolean stopTraverseRoot(ActComponent root) {
        return path.isEmpty();
    }

    @Override
    protected void processRoot(ActComponent root) {
        actualpath=actualpath.concat(root.idEditUnit + lineSeparator);
    }

    @Override
    protected Collection<ActComponent> traversedChild(ActComponent actComponent) {
        finded=actComponent.getChilds().get(path.element()) ;
        if(finded==null)
            throw new NoSuchElementException("'"+path.element()+"' wasn't found in '"+actComponent.idEditUnit+"'. Got there by "+actualpath+"");
        if(!finded.idEditUnit.equals(path.element()))
            throw new NoSuchElementException("'"+finded.idEditUnit+"' was found in '"+actComponent.idEditUnit+"' instead of '"+path.element()+"'. Got there by "+actualpath+"");
        path.remove();
        return Collections.singleton(finded);
    }
}