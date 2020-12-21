package ru.academits.java.nazimov.array_list;

import java.util.*;

public class ArrayList<T> implements List<T> {
    private T[] elements;
    private int size;
    private int modCount;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayList() {
        //noinspection unchecked
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Вместимость не может быть отрицательной или равняться нулю: " + capacity);
        }

        //noinspection unchecked
        elements = (T[]) new Object[capacity];
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Неверный индекс " + index
                    + ", допустимые значения: от 0 до " + (size - 1));
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

        //noinspection unchecked
        ArrayList<T> collection = (ArrayList<T>) c;
        int collectionSize = c.size();

        ++modCount;

        if (collectionSize + size > elements.length) {
            ensureCapacity(collectionSize + size);
        }

        int offsetElements = size - index;
        if (offsetElements > 0) {
            System.arraycopy(elements, index, elements, index + collectionSize, offsetElements);
        }

        System.arraycopy(collection.elements, 0, elements, index, collectionSize);

        size += collectionSize;
        return true;
    }

    @Override
    public void clear() {
        if (size > 0) {
            ++modCount;

            for (int i = 0; i < size; i++) {
                elements[i] = null;
            }

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
        ++modCount;

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
        if (element != null) {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object element) {
        if (element != null) {
            for (int i = size - 1; i >= 0; i--) {
                if (element.equals(elements[i])) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (elements[i] == null) {
                    return i;
                }
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
        if (c.size() == 0) {
            return true;
        }

        boolean remove = false;

        for (int i = 0; i < size; ++i) {
            if (!c.contains(elements[i])) {
                remove = true;

                System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                --size;
                --i;
            }
        }

        if (remove) {
            ++modCount;
        }

        return remove;
    }

    @Override
    public boolean removeAll(Collection c) {
        if (c.size() == 0) {
            return false;
        }

        boolean remove = false;

        for (Object e : c) {
            while (contains(e)) {
                int index = indexOf(e);

                if (index != 1) {
                    System.arraycopy(elements, index + 1, elements, index, size - index - 1);
                    --size;

                    remove = true;
                }
            }
        }

        if (remove) {
            ++modCount;
        }

        return remove;
    }

    @Override
    public boolean containsAll(Collection c) {
        if (size == 0 && c.size() != 0) {
            return false;
        }

        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object[] toArray(Object[] array) {
        if (array.length < size) {
            return Arrays.copyOf(elements, size, array.getClass());
        }

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
                throw new NoSuchElementException("Выход за пледелы коллекции (size: " + (size - 1) + "): " + currentIndex);
            }

            if (modCountBeforeIterator != modCount) {
                throw new ConcurrentModificationException("Во время прохода по коллекции итератором, изменения запрещены");
            }

            ++currentIndex;
            return elements[currentIndex];
        }
    }
}
