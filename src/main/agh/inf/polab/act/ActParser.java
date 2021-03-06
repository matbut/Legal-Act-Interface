package agh.inf.polab.act;

import agh.inf.polab.act.elements.EditorialUnit;
import agh.inf.polab.act.elements.IdentifiedEditorialUnit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.regex.Pattern;

/**
 * Parse act from text file by using preParser.
 */
public class ActParser {
    private PreParser preParser;
    private Act act=new Act();

    //Avaliable public methods:

    public ActParser(File file) throws FileNotFoundException {
        preParser=new PreParser(file);
    }

    public Act parse() throws InputMismatchException {
        parseFirstLines();

        ActComponent root=new ActComponent(new IdentifiedEditorialUnit(EditorialUnit.Root,""));
        SearchUnit(root);

        if(!preParser.endOfFile())
            throw new InputMismatchException("Illegal statement before " + preParser.getLine());

        act.setRoot(root);
        act.setPreable(root.getContent());
        root.removeContent();
        return act;
    }

    //private methods, parts of parser algorithm:

    private void parseFirstLines() throws InputMismatchException {
        String DzU="Dz\\.U\\. \\d{4} Nr \\d+ poz\\. \\d+.*";

        if(Pattern.matches(DzU,preParser.getLine()) && !preParser.endOfFile()){
            act.setJournalOfLaws(preParser.getLine());
            preParser.clearLine();
        }
        String line="";
        for(int i=0;i<3 && !preParser.endOfFile();i++) {
            line+="\n"+preParser.getLine();
            preParser.clearLine();
        }
        if(preParser.endOfFile())
            throw new InputMismatchException("To short file ");
        act.setTitle(line);
    }

    private void SearchUnit(ActComponent actComp){

        if(actComp.idEditUnit.type ==EditorialUnit.Article){
            act.addArticle(actComp);
        }

        getContent(actComp);
        FindUnits(actComp);

    }

    private void getContent(ActComponent actComp){

        StringBuilder strbuilder=new StringBuilder();
        while(!preParser.endOfFile() && !IdentifiedEditorialUnit.is(preParser.getLine())) {
            strbuilder.append(preParser.getLine());
            preParser.clearLine();
        }
        actComp.setContent(strbuilder.toString());
    }

    private void FindUnits(ActComponent actComp) {

        if(actComp.idEditUnit.type.isLastOne())
            return;

        while(IdentifiedEditorialUnit.is(preParser.getLine()) &&
            (IdentifiedEditorialUnit.convert(preParser.getLine()).type.isInLowers(actComp.idEditUnit.type))){

            IdentifiedEditorialUnit idEditUnit = IdentifiedEditorialUnit.convert(preParser.getLine());

            preParser.clearLine(idEditUnit.type.removeRegex());
            ActComponent newActComp = new ActComponent(idEditUnit);
            actComp.addChild(newActComp);

            //Po rozdziale konieczne jest wczytanie jego tytułu, aby nie pomylić go z oddziałem
            if (idEditUnit.type == EditorialUnit.Chapter && !preParser.endOfFile()) {
                newActComp.setContent(preParser.getLine());
                preParser.clearLine();
            }
            SearchUnit(newActComp);
        }
    }
}


