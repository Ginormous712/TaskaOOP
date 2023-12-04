package Tasks.task5;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Node {
    int key;
    int value;
    Node[] next;
    Lock lock;

    public Node(int key, int level) {
        this.key = key;
        this.value = key; // For simplicity, using key as value
        this.next = new Node[level + 1];
        this.lock = new ReentrantLock();
    }
}

public class SkipList {
    private static final double PROBABILITY = 0.5;
    private Node head;
    private int maxLevel;

    private static final int MAX_LEVEL = 32;

    public SkipList() {
        head = new Node(Integer.MIN_VALUE, 0);
        maxLevel = 0;
    }

    private Random random = new Random();

    private int randomLevel() {
        int level = 0;
        while (random.nextDouble() < PROBABILITY && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }

    public void insert(int key) {
        int level = randomLevel();
        Node[] update = new Node[Math.max(maxLevel + 1, level + 1)];
        Node current = head;

        for (int i = maxLevel; i >= 0; i--) {
            while (current.next[i] != null && current.next[i].key < key) {
                current = current.next[i];
            }
            if (i <= level) {
                update[i] = current;
            }
        }

        Node newNode = new Node(key, level);

        if (level > maxLevel) {
            for (int i = maxLevel + 1; i <= level; i++) {
                update[i] = head;
            }
            maxLevel = level;
        }

        for (int i = 0; i <= level; i++) {
            newNode.next[i] = update[i].next[i];
            update[i].next[i] = newNode;
        }
    }


    public void delete(int key) {
        Node[] update = new Node[maxLevel + 1];
        Node current = head;

        for (int i = maxLevel; i >= 0; i--) {
            while (current.next[i] != null && current.next[i].key < key) {
                current = current.next[i];
            }
            update[i] = current;
        }

        if (current.next[0] != null && current.next[0].key == key) {
            for (int i = 0; i <= maxLevel && update[i].next[i] == current.next[i]; i++) {
                update[i].next[i] = current.next[i].next[i];
            }

            while (maxLevel > 0 && head.next[maxLevel] == null) {
                maxLevel--;
            }
        }
    }


    public boolean search(int key) {
        Node current = head;

        for (int i = maxLevel; i >= 0; i--) {
            while (current.next[i] != null && current.next[i].key < key) {
                current = current.next[i];
            }
        }

        return current.next[0] != null && current.next[0].key == key;
    }
}

