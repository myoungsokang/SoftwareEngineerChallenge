package com.paypay.collection;

import static com.paypay.collection.Stack.empty;
import static com.paypay.collection.Stack.of;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

class StackTest {

    @Test void shouldFailHeadOfEmptyStack() {
        assertThrows(NoSuchElementException.class, () -> empty().head());
    }

    @Test void shouldHeadOfNonEmptyStack() {
        assertEquals(1, of(1).head());
    }

    @Test void shouldFailPopOfEmptyStack() {
        assertThrows(NoSuchElementException.class, () -> empty().pop());
    }

    @Test void shouldPopOfNonEmptyStack() {
        assertEquals(empty(), of(1).pop());
        assertEquals(of(1).head(), of(1, 2).pop().head());
    }

    @Test void shouldPushElements() {
        assertEquals(of(1), empty().push(1));
        assertEquals(of(1, 2, 3), empty().pushAll(1, 2, 3));
    }

    @Test void shouldReverse() {
        assertEquals(of(1, 2, 3), of(3, 2, 1).reverse());
    }
}