package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size = 0;
    private int modCount = 0;
    private Node<T> head;

    public void add(T value) {
        Node<T> newNode = new Node<>(value, null);
        Node<T> current = head;
        if (isEmpty()) {
            head = newNode;
        } else {
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        modCount++;
        size++;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> rsl = head;
        for (int i = 0; i < index; i++) {
            rsl = rsl.next;
        }
        return rsl.item;
    }

    public T deleteFirst() {
        if (isEmpty()) {
           throw new NoSuchElementException();
        }
        T current = head.item;
        Node<T> newNode = head.next;
        head.next = null;
        head.item = null;
        head = newNode;
        return current;
    }

    public boolean revert() {
        boolean validate = !isEmpty() && head.next != null;
        if (validate) {
            Node<T> current = head;
            while (current != null) {
                Node<T> nextNode = current.next;
                current.next = head;
                head = current;
                current = nextNode;
            }
        }
        return validate;
    }

    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            int expectedModCount = modCount;
            Node<T> current = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new IndexOutOfBoundsException();
                }
                Node<T> rsl = current;
                current = current.next;
                return rsl.item;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}