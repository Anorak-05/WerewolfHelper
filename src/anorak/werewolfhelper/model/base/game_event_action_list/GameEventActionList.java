package anorak.werewolfhelper.model.base.game_event_action_list;

import anorak.werewolfhelper.model.actions.base.IGameEventAction;

import java.util.*;

public class GameEventActionList implements Iterable<IGameEventAction> {
    private final List<IGameEventAction> list;
    private final GameEventActionListIterator<IGameEventAction> iterator;

    public GameEventActionList(List<IGameEventAction> list) {
        this.list = list;
        iterator = new GameEventActionListIterator<>(list);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty() || list.stream().allMatch(Objects::isNull);
    }

    public boolean contains(Object o) {
        return list.contains(o);
    }

    // This list only has one Iterator!!!
    public Iterator<IGameEventAction> iterator() {
        iterator.reset();
        return iterator;
    }

    public Object[] toArray() {
        return list.toArray();
    }

    public boolean add(IGameEventAction t) {
        int index = 0;
        for (IGameEventAction action : list) {
            if (t.getPriority() <= action.getPriority()) {
                break;
            }
            index++;
        }
        list.add(index, t);
        iterator.listAddedElement(index);
        return true;
    }

    public boolean remove(Object o) {
        int index = list.indexOf(o);
        if (index == -1) {
            return false;
        }
        return remove(index) != null;
    }

    public boolean containsAll(Collection<?> c) {
        return new HashSet<>(list).containsAll(c);
    }

    public void clear() {
        list.clear();
    }

    public IGameEventAction get(int index) {
        return list.get(index);
    }

    public IGameEventAction set(int index, IGameEventAction element) {
        return list.set(index, element);
    }

    public void add(int index, IGameEventAction element) {
        list.add(index, element);
    }

    public IGameEventAction remove(int index) {
        iterator.listRemovedElement(index);
        return list.remove(index);
    }
}