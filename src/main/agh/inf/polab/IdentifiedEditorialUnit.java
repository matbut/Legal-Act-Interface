package agh.inf.polab;

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
}
