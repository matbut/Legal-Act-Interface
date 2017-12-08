package agh.inf.polab;

import java.util.LinkedHashMap;

public class ActComponent{

    public final EditorialUnit editUnitType;
    public final String editUnitNum;

    private String content=null;
    private LinkedHashMap<String,ActComponent> lowers = new LinkedHashMap<>();

    public ActComponent(EditorialUnit editUnitType, String editUnitNum){
        this.editUnitType = editUnitType;
        this.editUnitNum = editUnitNum;
    }

    public String getContent(){
        return this.content;
    }

    public void PrintAll() {
        System.out.println(this.editUnitType.toString()+this.editUnitNum);

        if (this.getContent()!=null)
            System.out.println(this.getContent());

        for(ActComponent actComponent : lowers.values())
            actComponent.PrintAll();
    }
    public void PrintTableOfContent(){
        System.out.println(this.editUnitType.toTabulatedString()+this.editUnitNum);

        for(ActComponent actComponent : lowers.values())
            actComponent.PrintTableOfContent();
    }

    public void addChild(ActComponent p) {
        lowers.put(p.editUnitNum,p);
    }

    public ActComponent removeChild(String key) {
        return lowers.remove(key);
    }

    public void addContent(String line) {
        if(this.content==null)
            this.content = line;
        else
            this.content=this.content+line;
    }
}
