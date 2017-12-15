package agh.inf.polab;

public abstract class Printer extends BreadthwiseTraversal {
    private String line;
    protected String lineSeparator=System.getProperty("line.separator");

    public String print(Act act){
        line="";
        traverseBreadthwise(act);
        return line;
    }

    @Override
    protected void processAct(Act act) {
        line=line.concat(printAct(act));
    }
    @Override
    protected void processRoot(ActComponent actComponent) {
        line=line.concat(printRoot(actComponent));
    }
    @Override
    protected void processChild(ActComponent child) {
        line=line.concat(printChild(child));
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


}
