package agh.inf.polab;

public class PrinterRange extends Printer{
    private boolean start=false;
    private boolean end=false;
    private IdentifiedEditorialUnit startIdEditUnit;
    private IdentifiedEditorialUnit endIdEditUnit;

    @Override
    protected String printAct(Act act) {
        return "  Zakres "+startIdEditUnit+"-"+endIdEditUnit+lineSeparator;
    }

    public String print(Act act, IdentifiedEditorialUnit startIdEditUnit, IdentifiedEditorialUnit endIdEditUnit){
        this.startIdEditUnit=startIdEditUnit;
        this.endIdEditUnit=endIdEditUnit;
        return super.print(act);
    }
    @Override
    protected void processRoot(ActComponent actComponent) {
        if(actComponent.idEditUnit.equals(startIdEditUnit))
            start=true;
        if(actComponent.idEditUnit.equals(endIdEditUnit)) {
            end = true;
        }super.processRoot(actComponent);
    }
    @Override
    protected String printRoot(ActComponent actComponent) {
        if(start && !end){
            String tab = actComponent.idEditUnit.type.toTabulation();
            String line="";

            if(actComponent.idEditUnit.type!=EditorialUnit.Root)
                line+=tab+actComponent.idEditUnit+lineSeparator;

            if(actComponent.getContent()!=null)
                line+=tab+actComponent.getContent()+lineSeparator;

            return line;
        }else
            return "";
    }

}
