package agh.inf.polab;


import picocli.CommandLine;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum EditorialUnit implements IHasRegex{
    Root,

    Section,        // dział
    Chapter,        // rozdział
    Branch,         // oddział
    Article,        // artykuł
    Passagge,       // ustęp
    Point,          // punkt
    Letter;         // litera

    public String toTabulation() {
        String line="";
        for(int i=0;i<this.ordinal();i++)
            line+="  ";
        return line;
    }

    public boolean isInTableOfContent(){
        return this.ordinal()<EditorialUnit.Article.ordinal();
    }

    @Override
    public String toString() {
        switch (this) {
            case Root:
                return "";
            case Section:
                return "dział ";
            case Chapter:
                return "rozdział ";
            case Branch:
                return "oddział ";
            case Article:
                return "art.";
            case Passagge:
                return "ust.";
            case Point:
                return "pkt.";
            case Letter:
                return "lit.";
            default:
                return super.toString();
        }
    }

    @Override
    public String removeRegex() {
        switch (this) {
            case Root:
                return "Mateusz Buta";
            case Section:
                return "^DZIAŁ (?<id>\\d*\\p{Alpha}*)";
            case Chapter:
                return "^Rozdział (?<id>\\d*\\p{Alpha}*)";
            case Branch:
                return "(?!DZIAŁ .*\\b)^(?<id>[A-ZĘÓĄŚŁŻŹĆŃ ,]{5,})";
            case Article:
                return "^Art\\. (?<id>\\d+\\p{Lower}*)\\. *";
            case Passagge:
                return "^(?<id>\\d+\\p{Lower}*)\\. *";   //Ustęp oznacza się cyframi arabskimi z kropką bez nawiasu
            case Point:
                return "^(?<id>\\d+\\p{Lower}*)\\) +";   //Punkt oznacza się cyframi arabskimi z nawiasem z prawej strony
            case Letter:
                return "^(?<id>\\p{Lower}+)\\) +";   //Wyliczenie w obrębie punktów (tzw. litery) oznacza się małymi literami alfabetu łacińskiego,
                                                    //z wyłączeniem liter właściwych tylko językowi polskiemu (ą, ć, ę, ł, ń, ó, ś, ż, ź), z nawiasem z prawej strony
            default:
                return super.toString();
        }
    }

    @Override
    public String findRegex(){
        if (this==Branch)
            return this.removeRegex();
        return this.removeRegex()+".*";
    }

    public EditorialUnit higher(){
        if(this.ordinal() > 0 )
            return  EditorialUnit.values()[(this.ordinal()-1)];
        else
            return null;
    }
    public EditorialUnit[] lowers(){
        switch (this){
            case Root:{
                EditorialUnit[] lowers = {Section,Chapter,Article};
                return lowers;
            }
            case Section:{
                EditorialUnit[] lowers = {Chapter,Article};
                return lowers;
            }
            case Chapter:{
                EditorialUnit[] lowers = {Branch,Article};
                return lowers;
            }
            case Article:{
                EditorialUnit[] lowers = {Passagge,Point};
                return lowers;
            }
            case Letter:
                return null;
            default:{
                EditorialUnit[] lowers = {lower()};
                return lowers;
            }
        }
    }
    public EditorialUnit lower(){
        if (this.ordinal() < EditorialUnit.values().length-1)
            return EditorialUnit.values()[(this.ordinal()+1)];
        else
            return null;
    }
    public boolean isLastOne(){
        return this.lowers()==null;
    }

    public String optionParserRegex() {
        switch (this) {
            case Root:
                return "Mateusz Buta";
            case Section:
                return "^dz\\.(?<id>\\d*\\p{Alpha}*)";
            case Chapter:
                return "^roz\\.(?<id>\\d*\\p{Alpha}*)";
            case Branch:
                return "^oddz\\.(?<id>[A-ZĘÓĄŚŁŻŹĆŃ ,]{5,})";
            case Article:
                return "^art\\.(?<id>\\d+\\p{Lower}*)";
            case Passagge:
                return "^ust\\.(?<id>\\d+\\p{Lower}*)";   //Ustęp oznacza się cyframi arabskimi z kropką bez nawiasu
            case Point:
                return "^pkt\\.(?<id>\\d+\\p{Lower}*)";   //Punkt oznacza się cyframi arabskimi z nawiasem z prawej strony
            case Letter:
                return "^lit\\.(?<id>\\p{Lower}+)";   //Wyliczenie w obrębie punktów (tzw. litery) oznacza się małymi literami alfabetu łacińskiego,
            //z wyłączeniem liter właściwych tylko językowi polskiemu (ą, ć, ę, ł, ń, ó, ś, ż, ź), z nawiasem z prawej strony
            default:
                return super.toString();
        }
    }


    public boolean isInLowers(ActComponent actComponent){
        if(this.isLastOne())
            return false;

        for(EditorialUnit findingUnit : this.lowers())
            if(actComponent.equals(findingUnit)
                return  true;
        return false;
    }

    public static boolean is(String s){
        for(EditorialUnit findingUnit : EditorialUnit.values())
            if(Pattern.matches(findingUnit.findRegex(),s))
                return true;
        return false;
    }

    public static EditorialUnit convert(String s) throws IllegalArgumentException{
        for(EditorialUnit findingUnit : EditorialUnit.values())
            if(Pattern.matches(findingUnit.findRegex(),s))
                return findingUnit;

        throw new IllegalArgumentException("Incorrect argument: " + s);
    }
}