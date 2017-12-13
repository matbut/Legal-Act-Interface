package agh.inf.polab;

import java.util.*;

public class ActComponent{

    public final IdentifiedEditorialUnit id;

    private String content=null;
    private LinkedHashMap<String,ActComponent> lowers = new LinkedHashMap<>();

    public ActComponent(IdentifiedEditorialUnit id){
        this.id = id;
    }

    public String getContent(){
        return this.content;
    }
    public void addContent(String line) {
        if(this.content==null)
            this.content = line;
        else
            this.content=this.content+line;
    }
    public void removeContent(){
        this.content=null;
    }

    public ActComponent search(LinkedList<IdentifiedEditorialUnit> path){

        if(this.lowers.containsKey(path.getFirst().editUnitNum)) {
            ActComponent finded=this.lowers.get(path.getFirst().editUnitNum);
            if (finded.id.equals(path.getFirst())) {
                path.removeFirst();
                if (path.size()==0)
                    return finded;
                return finded.search(path);
            }
        }
        return null;

    }

    public void printAll() {
        System.out.println(this.id.editUnitType.toTabulation() + this.id.editUnitType.toString()+this.id.editUnitNum);

        if (this.getContent()!=null)
            System.out.println(this.id.editUnitType.toTabulation() + getContent());

        for(ActComponent actComponent : lowers.values())
            actComponent.printAll();
    }
    public void printTableOfContent(){
        if(this.id.editUnitType.isInTableOfContent()) {
            System.out.println(this.id.editUnitType.toTabulation() + this.id.toString());

            if (this.getContent()!=null)
                System.out.println(this.id.editUnitType.toTabulation() + getContent());

            for (ActComponent actComponent : lowers.values())
                actComponent.printTableOfContent();
        }
    }

    public void addChild(ActComponent p) {
        lowers.put(p.id.editUnitNum,p);
    }
    public ActComponent removeChild(String key) {
        return lowers.remove(key);
    }


}
