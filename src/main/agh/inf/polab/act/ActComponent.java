package agh.inf.polab.act;

import agh.inf.polab.act.elements.IdentifiedEditorialUnit;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents act component e.g. Article,Passagge
 */
public class ActComponent{
    public final IdentifiedEditorialUnit idEditUnit;
    private String content=null;
    private LinkedHashMap<IdentifiedEditorialUnit,ActComponent> childs = new LinkedHashMap<>();

    ActComponent(IdentifiedEditorialUnit id){
        this.idEditUnit = id;
    }

    //Avaliable public methods:

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

    //Building methods:

    void addChild(ActComponent p) {
        childs.put(p.idEditUnit,p);
    }

    void setContent(String content) {
        if(content.isEmpty())
            return;
        if(this.content == null)
            this.content=content;
        else
            this.content+=content;
    }

    void removeContent(){
        content=null;
    }
}
