package anorak.werewolfhelper.model.base.game_event_action_list;

import anorak.werewolfhelper.model.actions.base.IGameEventAction;

import java.util.*;

public class GameEventActionList implements Iterable<IGameEventAction> {
    List<IGameEventAction> list;
    List<Integer> removedIndices;
    GameEventActionListIterator<IGameEventAction> iterator;

    public GameEventActionList(List<IGameEventAction> list) {
        this.list = list;
        removedIndices = new ArrayList<>();
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

    public boolean addAll(Collection<? extends IGameEventAction> c) {
        throw new UnsupportedOperationException("Function not implemented");
    }

    public boolean addAll(int index, Collection<? extends IGameEventAction> c) {
        throw new UnsupportedOperationException("Function not implemented");
    }

    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Function not implemented");
    }

    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Function not implemented");
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

    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    public ListIterator<IGameEventAction> listIterator() {
        //throw new UnsupportedOperationException("Function not implemented");
        return list.listIterator();
    }

    public ListIterator<IGameEventAction> listIterator(int index) {
        //throw new UnsupportedOperationException("Function not implemented");
        return list.listIterator(index);
    }

    public List<IGameEventAction> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Function not implemented");
    }
}