package agh.inf.polab.act.elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Reperesnts specyfic Editorial Unit - ediorial unit with id. E.g. Chapter IV, Article 5a
 */

public class IdentifiedEditorialUnit{
    public final EditorialUnit type;
    public final String id;

    public IdentifiedEditorialUnit(EditorialUnit type, String id) {
        this.type = type;
        this.id = id;
    }

    @Override
    public String toString(){
        return this.type.toString() + ' ' + this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IdentifiedEditorialUnit)) return false;

        IdentifiedEditorialUnit that = (IdentifiedEditorialUnit) o;

        return type == that.type && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public static boolean is(String line){
        for (EditorialUnit editUnit : EditorialUnit.values())
            if (Pattern.matches(editUnit.findRegex(),line))
                return true;
        return false;
    }

    public static IdentifiedEditorialUnit convert(String s) throws IllegalArgumentException{
        for(EditorialUnit findingUnit : EditorialUnit.values()){
            Pattern p = Pattern.compile(findingUnit.findRegex());
            Matcher m = p.matcher(s);

            if(m.matches())
                return new IdentifiedEditorialUnit(findingUnit, m.group("id"));

        }
        throw new IllegalArgumentException("Incorrect argument: " + s);
    }

}
