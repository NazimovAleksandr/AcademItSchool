package ru.academits.java.nazimov.array_list;

import java.util.*;

public class ArrayListMyImplementation<T> implements List<T> {
    private T[] arrayList;
    private int size = 0;
    private int modCount = 0;
    private final int DEFAULT_CAPACITY = 10;

    public ArrayListMyImplementation() {
        //noinspection unchecked
        arrayList = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayListMyImplementation(int size) {
        //noinspection unchecked
        arrayList = (T[]) new Object[size];
    }

    private void grow(int capacity) {
        arrayList = Arrays.copyOf(arrayList, capacity);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Интекс " + index + " за пределами длины " + size);
        }
    }

    private int getIndex(T element) {
        for (int i = 0; i < size; i++) {
            if (arrayList[i] != null && arrayList[i].equals(element)) {
                return i;
            }
        }

        return -1;
    }

    public void trimToSize() {
        ++modCount;

        if (size < arrayList.length) {
            arrayList = Arrays.copyOf(arrayList, size);
        }
    }

    public void ensureCapacity(int capacity) {
        if (capacity > arrayList.length && capacity > DEFAULT_CAPACITY) {
            ++modCount;
            grow(capacity);
        }
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
        //noinspection unchecked
        return getIndex((T) element) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    @Override
    public T[] toArray() {
        return Arrays.copyOf(arrayList, size);
    }

    @Override
    public boolean add(T element) {
        add(size, element);
        return true;
    }

    @Override
    public boolean remove(Object element) {
        ++modCount;
        //noinspection unchecked
        int index = getIndex((T) element);

        if (index != -1) {
            System.arraycopy(arrayList, index + 1, arrayList, index, size - index);

            arrayList[size] = null;
            --size;
            return true;
        }

        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        ++modCount;
        Object[] collection = c.toArray();
        int collectionSize = collection.length;

        if (collectionSize == 0) {
            return false;
        }

        if (collectionSize + size > arrayList.length) {
            grow(collectionSize + size);
        }

        Object[] arrayList = this.arrayList;
        System.arraycopy(collection, 0, arrayList, size, collectionSize);

        size += collectionSize;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        ++modCount;
        checkIndex(index);

        Object[] collection = c.toArray();
        int collectionSize = collection.length;

        if (collectionSize == 0) {
            return false;
        }

        if (collectionSize + size > arrayList.length) {
            grow(collectionSize + size);
        }

        int offsetElements = size - index;
        if (offsetElements > 0) {
            System.arraycopy(arrayList, index, arrayList, index + collectionSize, offsetElements);
        }

        Object[] arrayList = this.arrayList;
        System.arraycopy(collection, 0, arrayList, index, collectionSize);

        size += collectionSize;
        return true;
    }

    @Override
    public void clear() {
        ++modCount;

        for (int i = 0; i < size; i++) {
            arrayList[i] = null;
        }

        size = 0;
    }

    @Override
    public T get(int index) {
        checkIndex(index);

        return arrayList[index];
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index);
        ++modCount;

        T oldElement = arrayList[index];
        arrayList[index] = element;

        return oldElement;
    }

    @Override
    public void add(int index, T element) {
        if (index != size) {
            checkIndex(index);
        }

        if (size == arrayList.length) {
            grow(size + DEFAULT_CAPACITY);
        }

        if (index == size) {
            arrayList[index] = element;
        } else {
            for (int i = size; i >= index; i--) {
                arrayList[i + 1] = arrayList[i];

                if (i == index) {
                    arrayList[i] = element;
                }
            }
        }

        ++modCount;
        ++size;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        ++modCount;

        T oldData = arrayList[index];

        System.arraycopy(arrayList, index + 1, arrayList, index, size - index - 1);

        --size;
        return oldData;
    }

    @Override
    public int indexOf(Object element) {
        //noinspection unchecked
        return getIndex((T) element);
    }

    @Override
    public int lastIndexOf(Object element) {
        for (int i = size - 1; i >= 0; i--) {
            if (arrayList[i] != null && arrayList[i].equals(element)) {
                return i;
            }
        }

        return -1;
    }

    @Override // Без реализации
    public ListIterator<T> listIterator() {
        //noinspection ConstantConditions
        return null;
    }

    @Override // Без реализации
    public ListIterator<T> listIterator(int index) {
        //noinspection ConstantConditions
        return null;
    }

    @Override // Без реализации
    public List<T> subList(int fromIndex, int toIndex) {
        //noinspection ConstantConditions
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        ++modCount;

        Object[] collection = c.toArray();

        if (collection.length == 0) {
            return false;
        }

        boolean remove = false;

        for (int i = 0; i < size; ++i) {
            int deletion = 1;

            for (Object e : collection) {
                if (arrayList[i] != null && arrayList[i].equals(e)) {
                    deletion = 0;
                    break;
                }
            }

            if (arrayList[i] != null && deletion == 1) {
                System.arraycopy(arrayList, i + 1, arrayList, i, size - i - 1);
                remove = true;
                --size;
            }

            i -= deletion;
        }

        return remove;
    }

    @Override
    public boolean removeAll(Collection c) {
        ++modCount;

        boolean remove = false;
        Object[] collection = c.toArray();

        if (collection.length == 0) {
            return false;
        }

        for (int i = 0; i < size; ++i) {
            int deletion = 0;

            for (Object e : collection) {
                if (arrayList[i] != null && arrayList[i].equals(e)) {
                    System.arraycopy(arrayList, i + 1, arrayList, i, size - i - 1);
                    --size;

                    remove = true;
                    deletion = 1;
                }
            }

            i -= deletion;
        }

        return remove;
    }

    @Override
    public boolean containsAll(Collection c) {
        Object[] collection = c.toArray();

        if (size == 0 && collection.length != 0) {
            return false;
        }

        int coincidencesCount = 0;

        for (int i = 0; i < size; i++) {
            for (Object e : collection) {
                if (arrayList[i] != null && arrayList[i].equals(e)) {
                    coincidencesCount++;
                    break;
                }
            }
        }

        return coincidencesCount == collection.length;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T[] toArray(Object[] array) {
        if (array.length < size) {
            return (T[]) Arrays.copyOf(arrayList, size, array.getClass());
        }

        System.arraycopy(arrayList, 0, array, 0, array.length);
        return (T[]) array;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        for (int i = 0; i < size; i++) {
            stringBuilder.append(arrayList[i]).append(", ");
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private class MyListIterator implements Iterator<T> {
        private int currentIndex = -1;
        private final int modCountBeforeIterator = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public T next() {
            if (currentIndex >= size) {
                throw new NoSuchElementException("Выход за пледелы коллекции (size: " + (size - 1) + "): " + currentIndex);
            }

            if (modCountBeforeIterator != modCount) {
                throw new ConcurrentModificationException("Во время прохода по коллекции итератором, изменения запрещены");
            }

            ++currentIndex;
            return arrayList[currentIndex];
        }
    }
}
