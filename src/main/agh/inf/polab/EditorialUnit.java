package agh.inf.polab;


public enum EditorialUnit implements IHasRegex{
    Root,           //

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
            line=line+"  ";
        return line;
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
                return "art. ";
            case Passagge:
                return "ust. ";
            case Point:
                return "pkt. ";
            case Letter:
                return "lit. ";
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
                return "(?!DZIAŁ .*\\b)(?<id>[A-ZĘÓĄŚŁŻŹĆŃ ,]{5,})";
            case Article:
                return "Art\\. (?<id>\\d+\\p{Lower}*)\\. *";
            case Passagge:
                //Ustęp oznacza się cyframi arabskimi z kropką bez nawiasu
                return "(?<id>\\d+\\p{Lower}*)\\. *";
            case Point:
                //Punkt oznacza się cyframi arabskimi z nawiasem z prawej strony
                return "(?<id>\\d+\\p{Lower}*)\\) +";
            case Letter:
                //Wyliczenie w obrębie punktów (tzw. litery) oznacza się małymi literami alfabetu łacińskiego,
                //z wyłączeniem liter właściwych tylko językowi polskiemu (ą, ć, ę, ł, ń, ó, ś, ż, ź), z nawiasem z prawej strony
                return "(?<id>\\p{Lower}+)\\) +";
            default:
                return super.toString();
        }
    }

    @Override
    public String findRegex(){
        if (this==Branch)
            return this.removeRegex();
        return "^"+this.removeRegex()+".*";
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
}