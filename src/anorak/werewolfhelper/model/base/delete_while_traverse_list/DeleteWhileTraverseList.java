package anorak.werewolfhelper.model.base.delete_while_traverse_list;

import java.util.*;

public class DeleteWhileTraverseList<T> implements List<T> {
    List<T> list;
    List<Integer> removedIndices;
    DeleteWhileTraverseListIterator<T> iterator;

    public DeleteWhileTraverseList(List<T> list) {
        this.list = list;
        removedIndices = new ArrayList<>();
        iterator = new DeleteWhileTraverseListIterator<>(list);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty() || list.stream().allMatch(Objects::isNull);
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    // This list only has one Iterator!!!
    @Override
    public Iterator<T> iterator() {
        iterator.reset();
        return iterator;
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return list.toArray(a);
    }

    @Override
    public boolean add(T t) {
        list.add(t);
        iterator.listAddedElement(list.indexOf(t));
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = list.indexOf(o);
        if (index == -1) {
            return false;
        }
        return remove(index) != null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return new HashSet<>(list).containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException("Function not implemented");
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException("Function not implemented");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Function not implemented");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Function not implemented");
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public T get(int index) {
        return list.get(index);
    }

    @Override
    public T set(int index, T element) {
        return list.set(index, element);
    }

    @Override
    public void add(int index, T element) {
        list.add(index, element);
    }

    @Override
    public T remove(int index) {
        iterator.listRemovedElement(index);
        return list.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        //throw new UnsupportedOperationException("Function not implemented");
        return list.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        //throw new UnsupportedOperationException("Function not implemented");
        return list.listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Function not implemented");
    }
}