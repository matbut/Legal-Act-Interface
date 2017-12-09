package agh.inf.polab;

public enum EditorialUnit implements IActUnit {
    Root,            // ustawa
    Article,        // artykuł
    Passagge,       // ustęp
    Point,          // punkt
    Letter;        // litera

    public String toTabulatedString() {
        String line="";
        for(int i=0;i<this.ordinal();i++)
            line=line+"  ";
        return line+this.toString();
    }

    @Override
    public String toString() {
        switch (this) {
            case Root:
                return "";
            case Article:
                return "art. ";
            //case Paragraph:
            //    return "Paragraf";
            case Passagge:
                return "ust. ";
            case Point:
                return "pkt. ";
            case Letter:
                return "lit. ";
            //case Indent:
            //    return "tiret";
            //case DoubleIndent:
            //    return "podwójne tiret";
            default:
                return super.toString();
        }
    }

    @Override
    public String removeRegex() {
        switch (this) {
            case Root:
                return "Root";
            case Article:
                return "Art\\. (\\p{Digit}+\\p{Lower}*)\\.";
            case Passagge:
                //Ustęp oznacza się cyframi arabskimi z kropką bez nawiasu
                return "(\\p{Digit}+\\p{Lower}*)\\.";
            case Point:
                //Punkt oznacza się cyframi arabskimi z nawiasem z prawej strony
                return "(\\p{Digit}{1,2}?\\p{Lower}*)\\)";
            case Letter:
                //Wyliczenie w obrębie punktów (tzw. litery) oznacza się małymi literami alfabetu łacińskiego,
                //z wyłączeniem liter właściwych tylko językowi polskiemu (ą, ć, ę, ł, ń, ó, ś, ż, ź), z nawiasem z prawej strony
                return "(\\p{Lower}{1,3})\\).";
            default:
                return super.toString();
        }
    }

    @Override
    public String findRegex(){
        return "^"+this.removeRegex()+".*";
    }

    @Override
    public EditorialUnit higher(){
        if(this.ordinal() > 0 )
            return  EditorialUnit.values()[(this.ordinal()-1)];
        else
            return null;
    }

    public EditorialUnit[] lowers(){
        if(this!=Article) {
            if(this.lower()==null){
                return null;
            }
            EditorialUnit[] lowers = {this.lower()};
            return lowers;
        }
        EditorialUnit[] lowers = {EditorialUnit.Passagge,EditorialUnit.Point};
        return lowers;
    }

    @Override
    public EditorialUnit lower(){
        if (this.ordinal() < EditorialUnit.values().length-1)
            return EditorialUnit.values()[(this.ordinal()+1)];
        else
            return null;
    }
}