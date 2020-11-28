package ru.academits.java.nazimov.list;

import java.util.Objects;

public class List<T> {
    private ListItem<T> head;
    private int count;

    public List() {
    }

    public int getCount() {
        return count;
    }

    public T getHeadData() {
        return head.getData();
    }

    public T get(int index) {
        if (index >= count || index < 0) {
            throw new IllegalArgumentException("Неверный индекс");
        }

        ListItem<T> item = head;
        T data = head.getData();

        for (int i = 0; i < count; i++) {
            if (i == index) {
                break;
            }

            item = item.getNext();
            data = item.getData();
        }

        return data;
    }

    public T set(T element, int index) {
        if (index >= count || index < 0) {
            throw new IllegalArgumentException("Неверный индекс");
        }

        ListItem<T> item = head;
        T data = head.getData();

        for (int i = 0; i < count; i++) {
            if (i == index) {
                item.setData(element);
                break;
            }

            item = item.getNext();
            data = item.getData();
        }

        return data;
    }

    public T remove(int index) {
        if (index >= count || index < 0) {
            throw new IllegalArgumentException("Неверный индекс");
        }

        T data = head.getData();

        if (index == 0) {
            head = head.getNext();
            count--;

            return data;
        }

        ListItem<T> prevItem = head;
        ListItem<T> currentItem = prevItem.getNext();

        data = currentItem.getData();

        for (int i = 1; i < count; i++) {
            if (i == index) {
                prevItem.setNext(currentItem.getNext());
                count--;

                break;
            }

            prevItem = currentItem;
            currentItem = currentItem.getNext();
            data = currentItem.getData();
        }

        return data;
    }

    public void addToBeginning(T element) {
        head = new ListItem<>(element, head);
        count++;
    }

    public void add(T element, int index) {
        if (index > count || index < 0) {
            throw new IllegalArgumentException("Неверный индекс");
        }

        if (index == 0) {
            addToBeginning(element);

            return;
        }

        ListItem<T> item = new ListItem<>(element);

        ListItem<T> prevItem = head;
        ListItem<T> currentItem = prevItem.getNext();

        for (int i = 1; i <= count; i++) {
            if (i == index) {
                prevItem.setNext(item);
                item.setNext(currentItem);
                count++;

                break;
            }

            prevItem = currentItem;
            currentItem = currentItem.getNext();
        }
    }

    public void add(T element) {
        ListItem<T> item = new ListItem<>(element);

        if (head == null) {
            head = item;
            count++;

            return;
        }

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (p.getNext() == null) {
                p.setNext(item);
                count++;

                break;
            }
        }
    }

    public boolean remove(T element) {
        T data = head.getData();
        ListItem<T> item = head;

        for (int i = 0; i < count; i++) {
            if (data.equals(element)) {
                remove(i);

                return true;
            }

            if (i != count - 1) {
                item = item.getNext();
                data = item.getData();
            }
        }

        return false;
    }

    public T removeFirstItem() {
        T data = head.getData();
        head = head.getNext();
        count--;

        return data;
    }

    public void revert() {
        ListItem<T> prevItem = head;
        ListItem<T> currentItem = prevItem.getNext();
        ListItem<T> nextItem = currentItem.getNext();

        for (int i = 1; i < count - 1; i++) {
            currentItem.setNext(prevItem);
            prevItem = currentItem;
            currentItem = nextItem;
            nextItem = nextItem.getNext();

            if (nextItem == null) {
                head = currentItem;
                head.setNext(prevItem);
            }
        }
    }

    public List<T> copy() {
        List<T> copy = new List<>();
        ListItem<T> item = head;

        for (int i = 0; i < count; i++) {
            copy.add(item.getData(), i);
            item = item.getNext();
        }

        return copy;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        ListItem<T> item = head.getNext();

        for (int i = 1; i < count; i++) {
            stringBuilder.append(item.toString());
            item = item.getNext();
        }

        return "{ List: " + System.lineSeparator() + head + stringBuilder.toString() + "}";
    }
}
