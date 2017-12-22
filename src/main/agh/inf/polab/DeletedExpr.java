package agh.inf.polab;

import java.util.Arrays;
import java.util.regex.Pattern;

public enum DeletedExpr {
    sejm,
    data,
    rubbish,
    removedtext,
    empty,
    removedArticle;


    public String removeRegex() {
        switch (this) {
            case data:
                return "^\\d{4}-\\d{2}-\\d{2}$";
            case sejm:
                return "^©Kancelaria Sejmu.*";
            case rubbish:
                return "^.?$";
            case removedtext:
                return "\\(pominięt.*\\)";
            case empty:
                return "";
            case removedArticle:
                return "^Art\\. \\d+\\p{Lower}*–\\d+\\p{Lower}*\\..*";
            default:
                return super.toString();
        }
    }
    public String findRegex(){ return removeRegex() ;}

    public static boolean is(String line){
        for (DeletedExpr delete : Arrays.asList(DeletedExpr.values()))
            if (Pattern.matches(delete.findRegex(),line))
                return true;
        return false;
    }
}
