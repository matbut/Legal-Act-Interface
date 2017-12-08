package agh.inf.polab;

import java.util.Arrays;
import java.util.regex.Pattern;

public enum DeletedExpr implements IHasRegex{
    sejm,
    data,
    rubbish;

    /*
    public static boolean is(String line){
        for (DeletedExpr delete : Arrays.asList(DeletedExpr.values()))
            if (Pattern.matches(delete.findRegex(),line))
                return true;
        return false;
    }
    */

    @Override
    public String findRegex() {
        switch (this) {
            case data:
                return "^\\d{4}-\\d{2}-\\d{2}$";
            case sejm:
                return "^©Kancelaria Sejmu.*";
            case rubbish:
                return "^.?$";
            default:
                return super.toString();
        }
    }

    @Override
    public String removeRegex(){ return findRegex();}

    @Override
    public String toString() {
        switch (this) {
            case data:
                return "YYYY-MM-DD";
            case sejm:
                return "©Kancelaria Sejmu";
            case rubbish:
                return "?";
            default:
                return super.toString();
        }
    }
}
