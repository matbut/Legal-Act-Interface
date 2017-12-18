package agh.inf.polab;

import java.util.*;

public class ActComponent{
    public final IdentifiedEditorialUnit id;

    private String content=null;
    private LinkedHashMap<String,ActComponent> lowers = new LinkedHashMap<>();

    public ActComponent(IdentifiedEditorialUnit id){
        this.id = id;
    }

    public void addChild(ActComponent p) {
        lowers.put(p.id.editUnitNum,p);
    }
    public void addContent(String line) {
        if(line.isEmpty())
            return;

        if(this.content==null)
            this.content = line;
        else
            this.content=this.content.concat(line);
    }

    public String getContent(){
        return this.content;
    }
    public LinkedHashMap<String, ActComponent> getChildrens() {
        return lowers;
    }
    public ActComponent getFirstChild(){
        return lowers.entrySet().iterator().next().getValue();
    }
    public ActComponent getLastChild(){
        Iterator<Map.Entry<String, ActComponent>> iterator=lowers.entrySet().iterator();
        ActComponent lastElement=null;
        while (iterator.hasNext()) {
            lastElement = iterator.next().getValue();
        }
        return lastElement;

    }

    public void removeContent(){
        this.content=null;
    }
}
