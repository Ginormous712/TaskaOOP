package Tasks.task6;

import java.util.concurrent.atomic.AtomicReference;

public class MichaelScottQueue<T> {
    private static class Node<T> {
        final T value;
        AtomicReference<Node<T>> next;

        Node(T value) {
            this.value = value;
            this.next = new AtomicReference<>(null);
        }
    }

    private final AtomicReference<Node<T>> head;
    private final AtomicReference<Node<T>> tail;

    public MichaelScottQueue() {
        Node<T> dummyNode = new Node<>(null);
        this.head = new AtomicReference<>(dummyNode);
        this.tail = new AtomicReference<>(dummyNode);
    }

    public void enqueue(T value) {
        Node<T> newNode = new Node<>(value);

        while (true) {
            Node<T> last = tail.get();
            Node<T> next = last.next.get();

            if (last == tail.get()) {
                if (next == null) {
                    if (last.next.compareAndSet(next, newNode)) {
                        tail.compareAndSet(last, newNode);
                        return;
                    }
                } else {
                    tail.compareAndSet(last, next);
                }
            }
        }
    }

    public T dequeue() {
        while (true) {
            Node<T> first = head.get();
            Node<T> last = tail.get();
            Node<T> next = first.next.get();

            if (first == head.get()) {
                if (first == last) {
                    if (next == null) {
                        return null; // Queue is empty
                    }
                    tail.compareAndSet(last, next);
                } else {
                    T value = next.value;
                    if (head.compareAndSet(first, next)) {
                        return value;
                    }
                }
            }
        }
    }
}

