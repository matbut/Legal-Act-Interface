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
        if(this.content==null)
            this.content = line;
        else
            this.content=this.content+line;
    }

    public String getContent(){
        return this.content;
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

    public LinkedHashMap<String, ActComponent> getChildrens() {
        return lowers;
    }
}
