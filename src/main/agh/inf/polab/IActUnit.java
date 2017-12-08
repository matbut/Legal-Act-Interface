package agh.inf.polab;

public interface IActUnit extends IHasRegex{
        String toString();
        IActUnit higher();
        IActUnit lower();
}
