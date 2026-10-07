package anorak.werewolfhelper.model.base.game_event_action_list;

import java.util.Iterator;
import java.util.List;

public class GameEventActionListIterator<T> implements Iterator<T> {
    private final List<T> list;
    private int index = 0;

    public GameEventActionListIterator(List<T> list) {
        this.list = list;
    }

    void reset() {
        index = 0;
    }

    void listAddedElement(int atIndex) {
        if (atIndex < index) index++;
    }

    void listRemovedElement(int atIndex) {
        if (atIndex <= index) index = Math.max(0, index - 1);
    }

    @Override
    public boolean hasNext() {
        return index < list.size();
    }

    @Override
    public T next() {
        return list.get(index++);
    }
}