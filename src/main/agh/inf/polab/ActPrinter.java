package agh.inf.polab;

public abstract class ActPrinter extends ActTraversal{
    private String line;
    protected String lineSeparator=System.getProperty("line.separator");

    public String print(Act act){
        line="";
        traverseAct(act);
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

    @Override
    protected ActComponent traversedChild(ActComponent actComponent) {
        return null;
    }
}
