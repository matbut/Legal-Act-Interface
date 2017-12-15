package agh.inf.polab;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class SearchContent extends Search {

    @Override
    protected ActComponent startTraverse(Act act) {
        return act.getArticles();
    }
}
