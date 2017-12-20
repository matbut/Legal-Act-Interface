package agh.inf.polab;

public abstract class Printer extends BreadthwiseTraversal {
    private StringBuilder strBuilder;
    protected String lineSeparator=System.getProperty("line.separator");

    public String print(Act act){
        strBuilder=new StringBuilder();
        traverseBreadthwise(act);
        return strBuilder.toString();
    }

    @Override
    protected void processAct(Act act) {
        strBuilder.append((printAct(act)));
    }
    @Override
    protected void processRoot(ActComponent actComponent) {
        strBuilder.append(printRoot(actComponent));
    }
    @Override
    protected void processChild(ActComponent child) {
        strBuilder.append(printChild(child));
    }
    @Override
    protected boolean stopTraverseRoot(ActComponent root) {
        return false;
    }
    @Override
    protected boolean stopTraverseChilds(ActComponent child) {
        return false;
    }

    protected abstract String printAct(Act act);
    protected abstract String printRoot(ActComponent actComponent);
    protected abstract String printChild(ActComponent actComponent);
    @Override
    protected ActComponent startTraverse(Act act) {
        return act.getRoot();
    }

}
