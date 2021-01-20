package ru.academits.java.nazimov.hash_table;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    @SuppressWarnings("FieldCanBeLocal")
    private final int DEFAULT_LENGTH = 20;

    private final ArrayList<T>[] arrayLists;
    private int size;
    private int modCount;

    public HashTable() {
        //noinspection unchecked
        arrayLists = new ArrayList[DEFAULT_LENGTH];
    }

    public HashTable(int arrayLength) {
        //noinspection unchecked
        arrayLists = new ArrayList[arrayLength];
    }

    private int getElementIndexInArray(Object element) {
        if (element == null) {
            return 0;
        }

        int hashCode = element.hashCode();

        return Math.abs(hashCode % arrayLists.length);
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

        return arrayLists[index].contains(element);
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    @Override
    public Object[] toArray() {
        ArrayList<T> array = new ArrayList<>();

        for (ArrayList<T> e : arrayLists) {
            for (T t : e) {
                if (t != null) {
                    array.add(t);
                }
            }
        }

        return array.toArray();
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

        if (arrayLists[index] == null) {
            arrayLists[index] = new ArrayList<>(Collections.singletonList(element));

            size++;
            modCount++;
            return true;
        }

        arrayLists[index].add(element);

        size++;
        modCount++;
        return true;
    }

    @Override
    public boolean remove(Object element) {
        int index = getElementIndexInArray(element);

        if (arrayLists[index].remove(element)) {
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

        for (Object e : c) {
            //noinspection unchecked
            add((T) e);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.size() == 0) {
            return false;
        }

        int index;
        boolean isRemoved = false;

        for (Object e : c) {
            index = getElementIndexInArray(e);

            if (arrayLists[index].removeAll(c)) {
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
    public boolean retainAll(Collection<?> c) {
        if (c.size() == 0) {
            clear();
            return true;
        }

        boolean isRemoved = false;

        for (ArrayList<T> e : arrayLists) {
            if (e.retainAll(c)) {
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
        Arrays.fill(arrayLists, null);
        size = 0;
        modCount++;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[").append(System.lineSeparator());

        if (size > 0) {
            for (ArrayList<T> e : arrayLists) {

                if (e != null) {
                    stringBuilder.append("[");

                    for (T d : e) {
                        stringBuilder.append(d).append(", ");
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
        private int elementIndex = -1;
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

            while (arrayIndex < arrayLists.length) {
                if (arrayLists[arrayIndex] != null && elementIndex < arrayLists[arrayIndex].size() - 1) {
                    elementIndex++;

                    element = arrayLists[arrayIndex].get(elementIndex);

                    break;
                }

                elementIndex = -1;
                arrayIndex++;
            }

            return element;
        }
    }
}
