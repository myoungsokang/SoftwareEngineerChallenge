package com.paypay.collection;

import java.util.Objects;

public class ImmutableStack<E> implements Stack<E> {

    private final E head;
    private final Stack<E> tail;
    private final int length;

    ImmutableStack(E head, Stack<E> tail) {
        this.head = head;
        this.tail = tail;
        this.length = 1 + tail.length();
    }

    @Override
    public E head() {
        return head;
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public Stack<E> tail() {
        return tail;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImmutableStack<?> that = (ImmutableStack<?>) o;
        return length == that.length &&
                head.equals(that.head) &&
                tail.equals(that.tail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, tail, length);
    }

    @Override
    public String toString() {

        return "ImmutableStack{" +
                "head=" + head +
                ", tail=" + tail +
                ", length=" + length +
                '}';
    }
}
