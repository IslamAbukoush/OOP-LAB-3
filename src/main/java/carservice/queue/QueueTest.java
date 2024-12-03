package carservice.queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {
    @Test
    void testArrayQueue() {
        Queue<Integer> queue = new ArrayQueue<>();
        assertTrue(queue.isEmpty());
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals(3, queue.size());
        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.size());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    void testLinkedListQueue() {
        Queue<String> queue = new LinkedListQueue<>();
        assertTrue(queue.isEmpty());
        queue.enqueue("A");
        queue.enqueue("B");
        assertEquals(2, queue.size());
        assertEquals("A", queue.dequeue());
        assertEquals("B", queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    void testCircularQueue() {
        Queue<Integer> queue = new CircularQueue<>(3);
        assertTrue(queue.isEmpty());
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals(3, queue.size());
        assertThrows(IllegalStateException.class, () -> queue.enqueue(4));
        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
        queue.enqueue(4);
        assertEquals(3, queue.dequeue());
        assertEquals(4, queue.dequeue());
        assertTrue(queue.isEmpty());
    }
}

