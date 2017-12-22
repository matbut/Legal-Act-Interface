package agh.inf.polab;

public class Act {

    private String title=null;
    private String preamble=null;
    private String journalOfLaws=null;

    private ActComponent root =null;
    private ActComponent articles=new ActComponent(new IdentifiedEditorialUnit(EditorialUnit.Root,""));

    public void addArticle(ActComponent actComp) {
        articles.addChild(actComp);
    }
    public void setRoot(ActComponent root){
        this.root = root;
    }
    public void setPreable(String preable) {
        this.preamble=preable;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setJournalOfLaws(String journalOfLaws) {
        this.journalOfLaws = journalOfLaws;
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
    public String getJournalOfLaws() {
        return journalOfLaws;
    }

}
