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

    public ActComponent searchInArticles(LinkedList<IdentifiedEditorialUnit> path){
        return articles.search(path);
    }
    public ActComponent searchInTableOfContent(LinkedList<IdentifiedEditorialUnit> path){
        return root.search(path);
    }

    public String getTitle() {
        return title;
    }
    public String getPreamble() {
        return preamble;
    }
    public ActComponent getArticles() {
        return articles;
    }
    public ActComponent getRoot() {
        return root;
    }
}
