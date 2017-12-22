package agh.inf.polab;

import java.util.*;

public class Searcher extends Traversal{
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
        if(root.idEditUnit.type!=EditorialUnit.Root){
            String lineSeparator=System.getProperty("line.separator");
            actualpath+=root.idEditUnit.type.toTabulation()+root.idEditUnit+lineSeparator;
        }
    }

    @Override
    protected Collection<ActComponent> traversedChild(ActComponent actComponent) {
        finded=actComponent.getChilds().get(path.element()) ;
        if(finded==null)
            throw new NoSuchElementException("'"+path.element()+"' wasn't found in '"+actComponent.idEditUnit+"'.");
        if(!finded.idEditUnit.equals(path.element()))
            throw new NoSuchElementException("'"+finded.idEditUnit+"' was found in '"+actComponent.idEditUnit+"' instead of '"+path.element()+"'.");
        path.remove();
        return Collections.singleton(finded);
    }

    public String getActualpath() {
        return actualpath;
    }
}