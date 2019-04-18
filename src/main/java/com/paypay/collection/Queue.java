package com.paypay.collection;

interface Queue<E> {

    Queue<E> enQueue(E e);

    Queue<E> deQueue();

    E head();

    boolean isEmpty();
}
