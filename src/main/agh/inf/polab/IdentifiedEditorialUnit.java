package agh.inf.polab;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdentifiedEditorialUnit{
    public final EditorialUnit type;
    public final String id;


    public IdentifiedEditorialUnit(EditorialUnit type, String editUnitNum) {
        this.type = type;
        this.id = editUnitNum;
    }

    @Override
    public String toString(){
        return this.type.toString() + this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IdentifiedEditorialUnit)) return false;

        IdentifiedEditorialUnit that = (IdentifiedEditorialUnit) o;

        if (type != that.type) return false;
        return id.equals(that.id);
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

    public static IdentifiedEditorialUnit convert2(String s) throws IllegalArgumentException{
        for(EditorialUnit findingUnit : EditorialUnit.values()){
            Pattern p = Pattern.compile(findingUnit.optionParserRegex());
            Matcher m = p.matcher(s);

            if(m.matches())
                return new IdentifiedEditorialUnit(findingUnit, m.group("id"));

        }
        throw new IllegalArgumentException("Incorrect argument: " + s);
    }

}
