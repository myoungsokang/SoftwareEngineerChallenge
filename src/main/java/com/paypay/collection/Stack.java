package com.paypay.collection;

import java.util.NoSuchElementException;
import java.util.Objects;

public interface Stack<E> {

    static <E> Stack<E> empty() { return ImmutableEmptyStack.instance(); }

    @SuppressWarnings("unchecked")
    static <E> Stack<E> of(E... elements) {
        Objects.requireNonNull(elements, "elements is null");
        return (Stack<E>) empty().pushAll(elements);
    }

    static <E> Stack<E> of(E e) { return new ImmutableStack<>(e, empty()); }

    boolean isEmpty();

    E head();

    int length();

    Stack<E> tail();

    default Stack<E> pop() {
        if (isEmpty()) throw new NoSuchElementException("pop of empty stack");

        return tail();
    }

    default Stack<E> push(E element) {
        return new ImmutableStack<>(element, this);
    }

    @SuppressWarnings("unchecked")
    default Stack<E> pushAll(E... elements) {
        Objects.requireNonNull(elements, "elements is null");
        Stack<E> result = this;
        for (E element : elements) {
            result = result.push(element);
        }
        return result;
    }

    default Stack<E> reverse() { return reverse(empty()); }

    default Stack<E> reverse(Stack<E> accumulator) {
        return isEmpty() ? accumulator : tail().reverse(accumulator.push(head()));
    }

    class ImmutableEmptyStack<E> implements Stack<E> {

        private static final ImmutableEmptyStack<?> INSTANCE = new ImmutableEmptyStack<>();

        private ImmutableEmptyStack() {}

        @SuppressWarnings("unchecked")
        static <E> ImmutableEmptyStack<E> instance() {
            return (ImmutableEmptyStack<E>) INSTANCE;
        }

        @Override
        public E head() { throw new NoSuchElementException("head of empty " + getClass().getSimpleName()); }

        @Override
        public int length() {
            return 0;
        }

        @Override
        public Stack<E> tail() {
            throw new UnsupportedOperationException("tail of empty list");
        }

        @Override
        public boolean isEmpty() { return true; }

        @Override
        public String toString() { return "ImmutableEmptyStack{}"; }
    }
}
