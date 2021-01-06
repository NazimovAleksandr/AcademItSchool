package ru.academits.java.nazimov.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class List<T> {
    private ListItem<T> head;
    private int count;

    public List() {
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Индекс " + index + " за пределами допустимых значений (от 0, до " + (count - 1) + ")");
        }
    }

    private ListItem<T> getListItem(int index) {
        ListItem<T> item = head;

        for (int i = 0; i < count; i++) {
            if (i == index) {
                break;
            }

            item = item.getNext();
        }

        return item;
    }

    public int getCount() {
        return count;
    }

    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException("Список пуст, нельзя получить несуществующий элемент");
        }

        return head.getData();
    }

    public T get(int index) {
        checkIndex(index);
        return getListItem(index).getData();
    }

    public T set(int index, T data) {
        checkIndex(index);

        ListItem<T> item = getListItem(index);
        T oldData = item.getData();

        item.setData(data);

        return oldData;
    }

    public T remove(int index) {
        checkIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        ListItem<T> previousItem = getListItem(index - 1);
        ListItem<T> currentItem = previousItem.getNext();

        T data = currentItem.getData();

        previousItem.setNext(currentItem.getNext());
        count--;

        return data;
    }

    public void addFirst(T data) {
        head = new ListItem<>(data, head);
        count++;
    }

    public void add(int index, T data) {
        if (index != count) {
            checkIndex(index);
        }

        if (index == 0) {
            addFirst(data);

            return;
        }

        ListItem<T> previousItem = getListItem(index - 1);
        ListItem<T> currentItem = previousItem.getNext();

        ListItem<T> item = new ListItem<>(data, currentItem);

        previousItem.setNext(item);
        count++;
    }

    public void add(T data) {
        add(count, data);
    }

    public boolean remove(T data) {
        if (head == null) {
            return false;
        }

        if (Objects.equals(data, head.getData())) {
            head = head.getNext();
            count--;

            return true;
        }

        ListItem<T> previousItem = head;
        ListItem<T> item = head.getNext();

        for (int i = 1; i < count; i++) {
            if (Objects.equals(data, item.getData())) {
                previousItem.setNext(item.getNext());

                count--;
                return true;
            }

            previousItem = item;
            item = item.getNext();
        }

        return false;
    }

    public T removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("Список пуст, нельзя удалить несуществующий элемент");
        }

        T data = head.getData();
        head = head.getNext();
        count--;

        return data;
    }

    public void revert() {
        if (count <= 1) {
            return;
        }

        ListItem<T> previousItem = head;
        ListItem<T> currentItem = previousItem.getNext();
        ListItem<T> nextItem = currentItem.getNext();

        for (int i = 1; i < count; i++) {
            if (nextItem == null) {
                head = currentItem;
                head.setNext(previousItem);

                break;
            }

            currentItem.setNext(previousItem);
            previousItem = currentItem;
            currentItem = nextItem;
            nextItem = nextItem.getNext();
        }
    }

    public List<T> copy() {
        List<T> copy = new List<>();

        if (head == null) {
            return copy;
        }

        copy.head = new ListItem<>(head.getData());
        copy.count = count;

        ListItem<T> previousItem = copy.head;

        ListItem<T> currentItem = head.getNext();

        for (int i = 1; i < count; i++) {
            ListItem<T> copyItem = new ListItem<>(currentItem.getData());

            previousItem.setNext(copyItem);
            previousItem = copyItem;

            currentItem = currentItem.getNext();
        }

        return copy;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        ListItem<T> item = head;

        for (int i = 0; i < count; i++) {
            stringBuilder.append(item.getData());
            item = item.getNext();

            if (i != count - 1) {
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
