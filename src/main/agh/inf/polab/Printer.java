package agh.inf.polab;

import java.util.Collection;

public abstract class Printer extends Traversal {
    private StringBuilder strBuilder;
    protected String lineSeparator=System.getProperty("line.separator");

    public Printer(Act act){
        super(act);
        strBuilder=new StringBuilder(printAct());
    }

    public void add(ActComponent actComponent){
        traverseComponent(actComponent);
    }
    public String get(){
        return strBuilder.toString();
    }

    @Override
    protected boolean stopTraverseRoot(ActComponent root) {
        return false;
    }
    @Override
    protected void processRoot(ActComponent root) {
        strBuilder.append(printRoot(root));
    }
    @Override
    protected Collection<ActComponent> traversedChild(ActComponent actComponent) {
        return actComponent.getChilds().values();
    }

    protected abstract String printAct();
    protected abstract String printRoot(ActComponent actComponent);
}
