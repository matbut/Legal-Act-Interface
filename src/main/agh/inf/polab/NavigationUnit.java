package agh.inf.polab;

public enum NavigationUnit implements IActUnit {
    // część
    // księga
    // tutuł,
    Section,    // dział
    Chapter;    // rozdział
    // oddział.

    @Override
    public String toString() {
        switch (this) {
            case Section:
                return "Dział ";
            case Chapter:
                return "Rozdział ";
            default:
                return super.toString();
        }
    }

    @Override
    public String findRegex() {
        return removeRegex()+".*";
    }

    @Override
    public String removeRegex() {
        switch (this) {
            case Section:
                return "^DZIAŁ \\p{Digit}*\\p{Alpha}*";
            case Chapter:
                return "^Rozdział \\p{Digit}*\\p{Alpha}*";
            default:
                return super.toString();
        }
    }

    @Override
    public NavigationUnit higher(){
        return  NavigationUnit.values()[(this.ordinal()+1)];
    }

    @Override
    public NavigationUnit lower(){
        return NavigationUnit.values()[(this.ordinal()-1)];
    }
}