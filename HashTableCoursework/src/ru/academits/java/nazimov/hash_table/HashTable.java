package ru.academits.java.nazimov.hash_table;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private final int DEFAULT_ELEMENT_COUNT = 1;

    private final T[][] table;
    private int count;
    private int modCount;

    public HashTable() {
        int DEFAULT_SIZE = 100;

        //noinspection unchecked
        table = (T[][]) new Object[DEFAULT_SIZE][DEFAULT_ELEMENT_COUNT];
    }

    public HashTable(int size) {
        //noinspection unchecked
        table = (T[][]) new Object[size][DEFAULT_ELEMENT_COUNT];
    }

    private int getElementHashCode(Object element) {
        int hashCode = element.hashCode();

        if (hashCode < 0 || hashCode > table.length - 1) {
            hashCode = Math.abs(hashCode % table.length);
        }

        return hashCode;
    }

    private void grow(int index) {
        //noinspection unchecked
        T[] temp = (T[]) new Object[table[index].length];

        System.arraycopy(table[index], 0, temp, 0, temp.length);

        //noinspection unchecked
        table[index] = (T[]) new Object[table[index].length + 1];

        System.arraycopy(temp, 0, table[index], 0, temp.length);
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public boolean contains(Object element) {
        int hashCode = getElementHashCode(element);
        int elementCount = table[hashCode].length;

        for (int i = 0; i < elementCount; i++) {
            if (table[hashCode][i].equals(element)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    @Override
    public Object[] toArray() {
        ArrayList<T> arrayList = new ArrayList<>();

        for (T[] e : table) {
            for (T t : e) {
                if (t != null) {
                    arrayList.add(t);
                }
            }
        }

        return arrayList.toArray();
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
        int hashCode = getElementHashCode(element);

        if (table[hashCode][0] == null) {
            table[hashCode][0] = element;
        } else {
            grow(hashCode);

            table[hashCode][table[hashCode].length - 1] = element;
        }

        count++;
        modCount++;
        return true;
    }

    @Override
    public boolean remove(Object element) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (Objects.equals(element, table[i][j])) {
                    table[i][j] = null;

                    count--;
                    modCount++;
                    return true;
                }
            }
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
            if (e != null) {
                //noinspection unchecked
                add((T) e);
            }
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.size() == 0) {
            return false;
        }

        boolean isRemoved = false;

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (table[i][j] != null && c.contains(table[i][j])) {
                    table[i][j] = null;
                    isRemoved = true;
                    count--;
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
        if (c.size() == 0) {
            clear();
            return true;
        }

        boolean isRemoved = false;

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (table[i][j] != null && !c.contains(table[i][j])) {
                    table[i][j] = null;
                    isRemoved = true;
                    count--;
                }
            }
        }

        if (isRemoved) {
            modCount++;
        }

        return isRemoved;
    }

    @Override
    public void clear() {
        Arrays.fill(table, null);
        count = 0;
        modCount++;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < table.length; i++) {
            stringBuilder.append("[").append(i).append("][");

            for (int j = 0; j < table[i].length; j++) {
                stringBuilder.append(table[i][j]).append(", ");
            }

            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
            stringBuilder.append("]").append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    private class MyListIterator implements Iterator<T> {
        private int elementsPassed = 0;
        private int externalIndex = 0;
        private int internalIndex = -1;
        private final int modCountBeforeIterator = modCount;

        @Override
        public boolean hasNext() {
            return elementsPassed < count;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Выход за пределы коллекции (size: " + (count - 1) + "): " + elementsPassed);
            } else if (modCountBeforeIterator != modCount) {
                throw new ConcurrentModificationException("Коллекция изменилась. Во время прохода итератором по коллекции, изменения запрещены");
            }

            internalIndex++;
            elementsPassed++;

            while (externalIndex < table.length) {
                while (internalIndex < table[externalIndex].length) {
                    if (table[externalIndex][internalIndex] != null) {

                        return table[externalIndex][internalIndex];
                    }

                    internalIndex++;
                }

                internalIndex = 0;
                externalIndex++;
            }

            return null;
        }
    }
}
