package agh.inf.polab;

import java.util.Arrays;
import java.util.Collection;


public enum EditorialUnit{
    Root,

    Section,        // dział
    Chapter,        // rozdział
    Branch,         // oddział
    Article,        // artykuł
    Passagge,       // ustęp
    Point,          // punkt
    Letter;         // litera

    public String toString() {
        switch (this) {
            case Root:
                return "";
            case Section:
                return "Dział";
            case Chapter:
                return "Rozdział";
            case Branch:
                return "Oddział";
            case Article:
                return "Art.";
            case Passagge:
                return "Ust.";
            case Point:
                return "Pkt.";
            case Letter:
                return "Lit.";
            default:
                return super.toString();
        }
    }
    public String toTabulation() {
        StringBuilder strBuilder=new StringBuilder();
        for(int i=0;i<this.ordinal();i++)
            strBuilder.append("  ");
        return strBuilder.toString();
    }

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
    public String findRegex(){
        if (this==Branch)
            return this.removeRegex();
        return this.removeRegex()+".*";
    }
    public String optionParserRegex() {
        switch (this) {
            case Root:
                return "Mateusz Buta"; // :)
            case Section:
                return "^dz\\.(?<id>\\d*[IVXLC]*)";
            case Chapter:
                return "^roz\\.(?<id>\\d*[IVXLC]*)";
            case Branch:
                return "^oddz\\.(?<id>[A-ZĘÓĄŚŁŻŹĆŃ ,]{5,})";
            case Article:
                return "^art\\.(?<id>\\d+\\p{Lower}*)";
            case Passagge:
                return "^ust\\.(?<id>\\d+\\p{Lower}*)";
            case Point:
                return "^pkt\\.(?<id>\\d+\\p{Lower}*)";
            case Letter:
                return "^lit\\.(?<id>\\p{Lower}+)";
            default:
                return super.toString();
        }
    }

    public EditorialUnit higher(){
        if(this.ordinal() > 0 )
            return  EditorialUnit.values()[(this.ordinal()-1)];
        else
            return null;
    }
    public EditorialUnit lower(){
        if (this.ordinal() < EditorialUnit.values().length-1)
            return EditorialUnit.values()[(this.ordinal()+1)];
        else
            return null;
    }
    public Collection<EditorialUnit> lowers(){
        switch (this){
            case Root:
                return Arrays.asList(Section,Chapter,Article);
            case Section:
                return Arrays.asList(Chapter,Article);
            case Chapter:
                return  Arrays.asList(Branch,Article);
            case Article:
                return  Arrays.asList(Passagge,Point);
            case Letter:
                return null;
            default:{
                return  Arrays.asList(lower());
            }
        }
    }

    public boolean isLastOne(){
        return this.lowers()==null;
    }
    public boolean isInLowers(EditorialUnit parent) {
        return !parent.isLastOne() && parent.lowers().contains(this);
    }
}