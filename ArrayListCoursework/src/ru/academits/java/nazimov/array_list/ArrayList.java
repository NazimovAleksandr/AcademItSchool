package ru.academits.java.nazimov.array_list;

import java.util.*;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private T[] elements;
    private int size;
    private int modCount;

    public ArrayList() {
        //noinspection unchecked
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Вместимость (" + capacity + ") не может быть отрицательной");
        }

        //noinspection unchecked
        elements = (T[]) new Object[capacity];
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Неверный индекс " + index + ", допустимые значения: от 0 до " + (size - 1));
        }
    }

    public void trimToSize() {
        if (size < elements.length) {
            elements = Arrays.copyOf(elements, size);
        }
    }

    public void ensureCapacity(int capacity) {
        if (capacity > elements.length) {
            elements = Arrays.copyOf(elements, capacity);
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
        return indexOf(element) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    @Override
    public T[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public boolean add(T element) {
        add(size, element);
        return true;
    }

    @Override
    public boolean remove(Object element) {
        int index = indexOf(element);

        if (index != -1) {
            remove(index);
            return true;
        }

        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index != size) {
            checkIndex(index);
        }

        if (c.size() == 0) {
            return false;
        }

        ++modCount;

        int collectionSize = c.size();
        ensureCapacity(collectionSize + size);

        int offsetElements = size - index;
        if (offsetElements > 0) {
            System.arraycopy(elements, index, elements, index + collectionSize, offsetElements);
        }

        int i = index;
        for (T e : c) {
            elements[i] = e;
            i++;
        }

        size += collectionSize;
        return true;
    }

    @Override
    public void clear() {
        if (size > 0) {
            ++modCount;

            Arrays.fill(elements, 0, size, null);

            size = 0;
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);

        return elements[index];
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index);

        T oldElement = elements[index];
        elements[index] = element;

        return oldElement;
    }

    @Override
    public void add(int index, T element) {
        if (index != size) {
            checkIndex(index);
        }

        if (size == 0) {
            ensureCapacity(DEFAULT_CAPACITY);
        } else if (size == elements.length) {
            ensureCapacity(size + size);
        }

        if (index != size) {
            System.arraycopy(elements, index, elements, index + 1, size - index);
        }

        elements[index] = element;

        ++modCount;
        ++size;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        ++modCount;

        T oldData = elements[index];

        System.arraycopy(elements, index + 1, elements, index, size - index - 1);

        --size;
        elements[size] = null;

        return oldData;
    }

    @Override
    public int indexOf(Object element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, elements[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object element) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(element, elements[i])) {
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
        if (size == 0) {
            return false;
        }

        if (c.size() == 0) {
            clear();
            return true;
        }

        boolean isRemoved = false;

        for (int i = 0; i < size; ++i) {
            if (!c.contains(elements[i])) {
                remove(i);

                --i;
                isRemoved = true;
            }
        }

        if (isRemoved) {
            ++modCount;
        }

        return isRemoved;
    }

    @Override
    public boolean removeAll(Collection c) {
        if (c.size() == 0) {
            return false;
        }

        boolean isRemoved = false;

        for (int i = 0; i < size; ++i) {
            while (c.contains(elements[i])) {
                remove(i);

                isRemoved = true;
            }
        }

        if (isRemoved) {
            ++modCount;
        }

        return isRemoved;
    }

    @Override
    public boolean containsAll(Collection c) {
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public <T1> T1[] toArray(T1[] array) {
        if (array.length < size) {
            //noinspection unchecked
            return (T1[]) Arrays.copyOf(elements, size, array.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(elements, 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        for (int i = 0; i < size; i++) {
            stringBuilder.append(elements[i]).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

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
            if (!hasNext()) {
                throw new NoSuchElementException("Выход за пределы коллекции (size: " + (size - 1) + "): " + currentIndex);
            }

            if (modCountBeforeIterator != modCount) {
                throw new ConcurrentModificationException("Коллекция изменилась. Во время прохода итератором по коллекции, изменения запрещены");
            }

            ++currentIndex;
            return elements[currentIndex];
        }
    }
}
