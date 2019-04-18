package com.paypay.collection;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ImmutableQueue<E> implements Queue<E> {

    private static final ImmutableQueue<?> EMPTY = new ImmutableQueue<>(Stack.empty(), Stack.empty());

    private final Stack<E> front;
    private final Stack<E> rear;

    @SuppressWarnings("unchecked")
    public static <E> ImmutableQueue<E> empty() {
        return (ImmutableQueue<E>) EMPTY;
    }

    @SuppressWarnings("unchecked")
    public static <E> ImmutableQueue<E> of(E element) {
        return ofAll(element);
    }

    @SuppressWarnings("unchecked")
    public static <E> ImmutableQueue<E> ofAll(E... elements) {
        return (ImmutableQueue<E>) empty().enQueueAll(elements);
    }

    private ImmutableQueue(Stack<E> front, Stack<E> rear) {
        final boolean frontIsEmpty = front.isEmpty();
        this.front = frontIsEmpty ? rear.reverse() : front;
        this.rear = frontIsEmpty ? front : rear;
    }

    @Override
    public ImmutableQueue<E> deQueue() {
        if (isEmpty())
            throw new NoSuchElementException("dequeue of empty " + getClass().getSimpleName());

        return new ImmutableQueue<>(front.tail(), rear);
    }

    @Override
    public ImmutableQueue<E> enQueue(E element) {
        return new ImmutableQueue<>(front, rear.push(element));
    }

    @SafeVarargs
    public final ImmutableQueue<E> enQueueAll(E... elements) {
        Objects.requireNonNull(elements, "elements is null");
        ImmutableQueue<E> result = this;
        for (E element : elements) {
            result = result.enQueue(element);
        }
        return result;
    }

    @Override
    public E head() {
        if (isEmpty())
            throw new NoSuchElementException("head of empty " + getClass().getSimpleName());

        return front.head();
    }

    @Override
    public boolean isEmpty() { return front.isEmpty(); }
}
