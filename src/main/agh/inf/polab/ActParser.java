package agh.inf.polab;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActParser {
    private PreParser preParser;
    private Act act=new Act();

    public ActParser(String fileName) throws FileNotFoundException {
        preParser=new PreParser(fileName);
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

    private void parseFirstLines() throws InputMismatchException {
        String DzU="Dz\\.U\\. \\d{4} Nr \\d+ poz\\. \\d+.*";
        StringBuilder strbuilder=new StringBuilder();

        if(!preParser.endOfFile()) {
            strbuilder.append(preParser.getLine());
            preParser.clearLine();
        }
        if(Pattern.matches(DzU,preParser.getLine()) && !preParser.endOfFile()){
            strbuilder.append("\n");
            strbuilder.append((preParser.getLine()));
            preParser.clearLine();
        }

        for(int i=0;i<2 && !preParser.endOfFile();i++) {
            strbuilder.append("\n");
            strbuilder.append((preParser.getLine()));
            preParser.clearLine();
        }
        if(preParser.endOfFile())
            throw new InputMismatchException("To short file ");
        act.setTitle(strbuilder.toString());
    }
    private void SearchUnit(ActComponent actComp){

        getContent(actComp);
        FindUnits(actComp);

        if(actComp.id.editUnitType==EditorialUnit.Article){
            act.addArticle(actComp);
        }

    }
    private void getContent(ActComponent actComp){

        StringBuilder strbuilder=new StringBuilder();
        while(!preParser.endOfFile() && !preParser.isEditorialUnit()) {
            strbuilder.append(preParser.getLine());
            preParser.clearLine();
        }
        actComp.setContent(strbuilder.toString());
    }
    private void FindUnits(ActComponent actComp){
        if(actComp.id.editUnitType.isLastOne())
            return;

        boolean restart=true;
        while(restart) {
            Iterator<EditorialUnit> iterator = Arrays.asList(actComp.id.editUnitType.lowers()).iterator();

            restart = false;
            while (iterator.hasNext() && !restart) {

                EditorialUnit findingUnit = iterator.next();

                Pattern p = Pattern.compile(findingUnit.findRegex());
                Matcher m = p.matcher(preParser.getLine());

                while (m.matches()) {
                    restart = true;
                    preParser.clearLine(findingUnit.removeRegex());

                    IdentifiedEditorialUnit id = new IdentifiedEditorialUnit(findingUnit, m.group("id"));

                    ActComponent newActComp = new ActComponent(id);
                    actComp.addChild(newActComp);

                    //wyjatek dla wczytywania tytułu rodzialu()
                    if (findingUnit == EditorialUnit.Chapter && !preParser.endOfFile()) {
                        newActComp.setContent(preParser.getLine());
                        preParser.clearLine();
                    }
                    SearchUnit(newActComp);
                    m = p.matcher(preParser.getLine());
                }
            }
        }
    }
}


