package ru.academits.java.nazimov.hash_table;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private final static int DEFAULT_LENGTH = 20;

    private final ArrayList<T>[] lists;
    private int size;
    private int modCount;

    public HashTable() {
        //noinspection unchecked
        lists = new ArrayList[DEFAULT_LENGTH];
    }

    public HashTable(int arrayLength) {
        if (arrayLength <= 0) {
            throw new IllegalArgumentException("Размер таблици " + arrayLength + " не может равняться нулю и быть отрицательным");
        }
        //noinspection unchecked
        lists = new ArrayList[arrayLength];
    }

    private int getElementIndexInArray(Object element) {
        if (element == null) {
            return 0;
        }

        int hashCode = element.hashCode();

        return Math.abs(hashCode % lists.length);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object element) {
        int index = getElementIndexInArray(element);

        return lists[index].contains(element);
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;

        for (T e: this) {
            array[i] = e;
            i++;
        }

        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        //noinspection unchecked
        T1[] array = (T1[]) toArray();

        if (array.length > a.length) {
            //noinspection unchecked
            return (T1[]) Arrays.copyOf(array, array.length, a.getClass());
        }

        System.arraycopy(array, 0, a, 0, array.length);

        if (a.length > array.length) {
            a[array.length] = null;
        }

        return array;
    }


    @Override
    public boolean add(T element) {
        int index = getElementIndexInArray(element);

        if (lists[index] == null) {
            lists[index] = new ArrayList<>();
        }

        lists[index].add(element);

        size++;
        modCount++;
        return true;
    }

    @Override
    public boolean remove(Object element) {
        if (size == 0) {
            return false;
        }

        int index = getElementIndexInArray(element);

        if (lists[index].remove(element)) {
            size--;
            modCount++;

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c.size() == 0) {
            return false;
        }

        for (T e : c) {
            add(e);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.size() == 0 || size == 0) {
            return false;
        }

        boolean isRemoved = false;
        int index;

        for (Object e : c) {
            index = getElementIndexInArray(e);

            for (int i = 0; i < lists[index].size(); i++) {
                //noinspection SuspiciousMethodCalls
                if (lists[index].remove(e)) {
                    isRemoved = true;
                    size--;
                }
            }
        }

        if (isRemoved) {
            modCount++;
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (size == 0) {
            return false;
        }

        if (c.size() == 0) {
            clear();
            return true;
        }

        boolean isRemoved = false;

        for (ArrayList<T> e : lists) {
            if (!c.contains(e)) {
                isRemoved = true;
                size--;
            }
        }

        if (isRemoved) {
            modCount++;
        }

        return isRemoved;
    }

    @Override
    public void clear() {
        Arrays.fill(lists, null);
        size = 0;
        modCount++;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[").append(System.lineSeparator());

        if (size > 0) {
            for (ArrayList<T> list : lists) {
                if (list != null) {
                    stringBuilder.append("[");

                    for (T e : list) {
                        stringBuilder.append(e).append(", ");
                    }

                    char symbol = stringBuilder.charAt(stringBuilder.length() - 1);

                    if (Character.toString(symbol).equals(" ")) {
                        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
                    }

                    stringBuilder.append("]").append(System.lineSeparator());
                } else {
                    stringBuilder.append("null").append(System.lineSeparator());
                }
            }
        }

        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    private class MyListIterator implements Iterator<T> {
        private int elementsPassed = 0;
        private int arrayIndex = 0;
        private int listIndex = -1;
        private final int modCountBeforeIterator = modCount;

        @Override
        public boolean hasNext() {
            return elementsPassed < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Выход за пределы коллекции (size: " + (size - 1) + "): " + elementsPassed);
            }

            if (modCountBeforeIterator != modCount) {
                throw new ConcurrentModificationException("Коллекция изменилась. Во время прохода итератором по коллекции, изменения запрещены");
            }

            elementsPassed++;
            T element = null;

            while (arrayIndex < lists.length) {
                if (lists[arrayIndex] != null && listIndex < lists[arrayIndex].size() - 1) {
                    listIndex++;

                    element = lists[arrayIndex].get(listIndex);

                    break;
                }

                listIndex = -1;
                arrayIndex++;
            }

            return element;
        }
    }
}
