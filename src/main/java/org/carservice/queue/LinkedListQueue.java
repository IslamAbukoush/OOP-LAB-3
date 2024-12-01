package org.carservice.queue;

import java.util.LinkedList;

public class LinkedListQueue<T> implements Queue<T> {
    private final LinkedList<T> items;

    public LinkedListQueue() {
        items = new LinkedList<>();
    }

    @Override
    public void enqueue(T item) {
        items.addLast(item);
    }

    @Override
    public T dequeue() {
        if (isEmpty()) throw new IllegalStateException("Queue is empty");
        return items.removeFirst();
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public int size() {
        return items.size();
    }
}
