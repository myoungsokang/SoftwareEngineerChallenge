package com.paypay.collection;

import static com.paypay.collection.ImmutableQueue.empty;
import static com.paypay.collection.ImmutableQueue.of;
import static com.paypay.collection.ImmutableQueue.ofAll;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

class QueueTest {

    @Test void shouldFailHeadOfEmpty() {
        assertThrows(NoSuchElementException.class, () -> empty().head());
    }

    @Test void shouldReturnHeadOfNonEmpty() {
        assertEquals(1, of(1).head());
    }

    @Test void shouldFailDequeueOfEmpty() {
        assertThrows(NoSuchElementException.class, () -> empty().deQueue());
    }

    @Test void shouldDequeueOfNonEmpty() {
        ImmutableQueue<Integer> expected = ofAll(1, 2);
        ImmutableQueue<Integer> actual = ofAll(0, 1, 2).deQueue();

        assertAll("head",
                () -> assertEquals(expected.head(), actual.head()),
                () -> assertEquals(expected.deQueue().head(), actual.deQueue().head()));
    }
}