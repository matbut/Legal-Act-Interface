package agh.inf.polab;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Converter {




    public static IdentifiedEditorialUnit fromComandLineToIdEditUnit(String s) throws IllegalArgumentException {
        for (EditorialUnit findingUnit : EditorialUnit.values()) {
            Pattern p = Pattern.compile(findingUnit.optionParserRegex());
            Matcher m = p.matcher(s);

            if (m.matches())
                return new IdentifiedEditorialUnit(findingUnit, m.group("id"));

        }
        throw new IllegalArgumentException("Incorrect argument: " + s);
    }
}
