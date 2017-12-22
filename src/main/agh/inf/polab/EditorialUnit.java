package agh.inf.polab;

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
        StringBuilder strBuilder=new StringBuilder();
        for(int i=0;i<this.ordinal();i++)
            strBuilder.append("  ");
        return strBuilder.toString();
    }

    @Override
    public String toString() {
        switch (this) {
            case Root:
                return "Ustawa";
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
    public String optionParserRegex() {
        switch (this) {
            case Root:
                return "Mateusz Buta"; // :)
            case Section:
                return "^dz\\.(?<id>\\d*\\p{Alpha}*)";
            case Chapter:
                return "^roz\\.(?<id>\\d*\\p{Alpha}*)";
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
    public EditorialUnit[] lowers(){
        switch (this){
            case Root:
                return new EditorialUnit[] {Section,Chapter,Article};
            case Section:
                return new EditorialUnit[] {Chapter,Article};
            case Chapter:
                return new EditorialUnit[] {Branch,Article};
            case Article:
                return new EditorialUnit[] {Passagge,Point};
            case Letter:
                return null;
            default:{
                return new EditorialUnit[] {lower()};
            }
        }
    }

    public boolean isLastOne(){
        return this.lowers()==null;
    }
    public boolean isInLowers(EditorialUnit parent){
        if(parent.isLastOne())
            return false;

        for(EditorialUnit findingUnit : parent.lowers())
            if(this==findingUnit)
                return  true;
        return false;
    }
}