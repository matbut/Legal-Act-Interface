package agh.inf.polab.act.actInterface;

import agh.inf.polab.act.elements.EditorialUnit;
import agh.inf.polab.act.elements.IdentifiedEditorialUnit;
import agh.inf.polab.act.Act;
import agh.inf.polab.act.ActComponent;

import java.util.*;

/**
 * Search Act looking for identified editorial unit
 */
public class Searcher extends Traversal{
    private ActComponent finded;
    private String actualpath="";
    private Queue<IdentifiedEditorialUnit> path;

    //Public avaliable methods

    public Searcher(Act act) {
        super(act);
    }

    public ActComponent search(ActComponent actComponent,Collection<IdentifiedEditorialUnit> path) {
        this.path=new LinkedList<>(path);
        traverseComponent(actComponent);
        return finded;
    }

    public String getActualpath() {
        return actualpath;
    }

    //Specyfying searching methods

    @Override
    boolean stopTraverseRoot(ActComponent root) {
        return path.isEmpty();
    }

    @Override
    void processRoot(ActComponent root) {
        if(root.idEditUnit.type!= EditorialUnit.Root){
            String lineSeparator=System.getProperty("line.separator");
            actualpath+=root.idEditUnit.type.toTabulation()+root.idEditUnit+lineSeparator;
        }
    }

    @Override
    Collection<ActComponent> traversedChild(ActComponent actComponent) {
        finded=actComponent.getChilds().get(path.element()) ;
        if(finded==null)
            throw new NoSuchElementException("'"+path.element()+"' wasn't found in '"+actComponent.idEditUnit+"'.");
        if(!finded.idEditUnit.equals(path.element()))
            throw new NoSuchElementException("'"+finded.idEditUnit+"' was found in '"+actComponent.idEditUnit+"' instead of '"+path.element()+"'.");
        path.remove();
        return Collections.singleton(finded);
    }


}