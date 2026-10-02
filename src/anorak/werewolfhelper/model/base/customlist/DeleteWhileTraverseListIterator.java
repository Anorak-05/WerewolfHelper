package anorak.werewolfhelper.model.base.customlist;

import java.util.Iterator;
import java.util.Objects;

public class DeleteWhileTraverseListIterator<T> implements Iterator<T> {
    private DeleteWhileTraverseList<T> list;
    private int index = 0;

    public DeleteWhileTraverseListIterator(DeleteWhileTraverseList<T> list) {
        this.list = list;
    }

    void listAddedElement(int atIndex) {
        if (atIndex <= index) index++;
    }

    @Override
    public boolean hasNext() {
        boolean finalIndex = index + 1 < list.size();
        if (finalIndex) return false;

        return list.subList(index, list.size()).stream().allMatch(Objects::isNull);
    }

    @Override
    public T next() {
        return null;
    }
}