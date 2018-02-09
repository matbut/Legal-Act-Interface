package agh.inf.polab.act;

import agh.inf.polab.act.elements.EditorialUnit;
import agh.inf.polab.act.elements.IdentifiedEditorialUnit;

/**
 * Represents Legal Act.
 */
public class Act {
    private String title=null;
    private String preamble=null;
    private String journalOfLaws=null;

    private ActComponent root =null;
    private ActComponent articles=new ActComponent(new IdentifiedEditorialUnit(EditorialUnit.Root,""));

    // Public getters:

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

    public String getJournalOfLaws() {
        return journalOfLaws;
    }

    //package-private Adders and setters:

    void addArticle(ActComponent actComp) {
        articles.addChild(actComp);
    }

    void setRoot(ActComponent root){
        this.root = root;
    }

    void setPreable(String preable) {
        this.preamble=preable;
    }

    void setTitle(String title) {
        this.title = title;
    }

    void setJournalOfLaws(String journalOfLaws) {
        this.journalOfLaws = journalOfLaws;
    }

}
