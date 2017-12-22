package agh.inf.polab;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class ActComponent{

    public final IdentifiedEditorialUnit idEditUnit;
    private String content=null;
    private LinkedHashMap<IdentifiedEditorialUnit,ActComponent> childs = new LinkedHashMap<>();

    public ActComponent(IdentifiedEditorialUnit id){
        this.idEditUnit = id;
    }

    public void addChild(ActComponent p) {
        childs.put(p.idEditUnit,p);
    }
    public void setContent(String content) {
        if(content.isEmpty())
            return;
        this.content=content;
    }

    public String getContent(){
        return content;
    }
    public LinkedHashMap<IdentifiedEditorialUnit, ActComponent> getChilds() {
        return childs;
    }
    public ActComponent getFirstChild(){
        return childs.entrySet().iterator().next().getValue();
    }
    public ActComponent getLastChild(){
        Iterator<Map.Entry<IdentifiedEditorialUnit, ActComponent>> iterator= childs.entrySet().iterator();
        ActComponent lastElement=null;
        while (iterator.hasNext()) {
            lastElement = iterator.next().getValue();
        }
        return lastElement;

    }

    public void removeContent(){
        content=null;
    }
}
