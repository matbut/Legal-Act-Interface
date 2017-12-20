package agh.inf.polab;

import picocli.CommandLine;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdentifiedEditorialUnit {
    public final EditorialUnit editUnitType;
    public final String editUnitNum;


    public IdentifiedEditorialUnit(EditorialUnit editUnitType, String editUnitNum) {
        this.editUnitType = editUnitType;
        this.editUnitNum = editUnitNum;
    }

    @Override
    public String toString(){
        return this.editUnitType.toString() + this.editUnitNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IdentifiedEditorialUnit)) return false;

        IdentifiedEditorialUnit that = (IdentifiedEditorialUnit) o;

        if (editUnitType != that.editUnitType) return false;
        return editUnitNum.equals(that.editUnitNum);
    }

    public static boolean is(String line){
        for (EditorialUnit editUnit : Arrays.asList(EditorialUnit.values()))
            if (Pattern.matches(editUnit.findRegex(),line))
                return true;
        return false;
    }

    public static IdentifiedEditorialUnit convert(String s) throws IllegalArgumentException{
        for(EditorialUnit findingUnit : EditorialUnit.values()){
            Pattern p = Pattern.compile(findingUnit.optionParserRegex());
            Matcher m = p.matcher(s);

            if(m.matches())
                return new IdentifiedEditorialUnit(findingUnit, m.group("id"));

        }
        throw new IllegalArgumentException("Incorrect argument: " + s);

    }
}
