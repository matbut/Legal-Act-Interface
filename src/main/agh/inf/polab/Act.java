package agh.inf.polab;

import java.util.LinkedList;

public class Act {

    private String title=null;
    private String preamble=null;

    private ActComponent root =null;
    private ActComponent articles=new ActComponent(new IdentifiedEditorialUnit(EditorialUnit.Root,""));

    public void addArticle(ActComponent actComp) {
        articles.addChild(actComp);
    }
    public void setRoot(ActComponent actComponent){
        this.root =actComponent;
    }
    public void setPreable(String preable) {
        this.preamble=preable;
    }
    public void setTitle(String firstLines) {
        this.title = firstLines;
    }

    public void printTableOfContent(){
        printTitle();
        root.printTableOfContent();
    }
    public void printAll(){
        printTitle();
        printPreamble();
        root.printAll();
    }
    public void printTitle(){
        if(title!=null)
            System.out.println(title);
    }
    public void printPreamble(){
        if(preamble!=null)
            System.out.println(preamble);
    }

    public ActComponent search(LinkedList<IdentifiedEditorialUnit> path){
        return articles.search(path);
    }

}
