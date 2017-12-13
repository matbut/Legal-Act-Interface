package agh.inf.polab;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Act {

    private String title;

    //private LinkedHashMap<String,ActComponent> articles = new LinkedHashMap<>();

    private ActComponent articles=new ActComponent(new IdentifiedEditorialUnit(EditorialUnit.Root,""));

    private ActComponent actComponent;

    private String preamble=null;

    public void addComponents(ActComponent actComponent){
        this.actComponent=actComponent;
    }

    public void setTitle(String firstLines) {
        this.title = firstLines;
    }

    public void printTableOfContent(){
        printTitle();
        actComponent.printTableOfContent();
    }
    public void printAll(){
        printTitle();
        printPreamble();
        actComponent.printAll();
    }
    public void printTitle(){
        if(title!=null)
            System.out.println(title);
    }
    public void printPreamble(){
        if(preamble!=null)
            System.out.println(preamble);
    }

    public void addPreable(String line) {
        if(this.preamble==null)
            this.preamble = line;
        else
            this.preamble=this.preamble+line;
    }

    public void addArticle(ActComponent actComp) {
        articles.addChild(actComp);
    }

    public ActComponent search(LinkedList<IdentifiedEditorialUnit> path){
        return articles.search(path);
    }
}
