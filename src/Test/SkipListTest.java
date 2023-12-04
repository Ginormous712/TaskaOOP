package Test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Tasks.task5.SkipList;

class SkipListTest {
    @Test
    void testInsert() {
        SkipList skipList = new SkipList();

        skipList.insert(3);
        assertTrue(skipList.search(3));
        assertFalse(skipList.search(5));
        assertFalse(skipList.search(2));
    }

    @Test
    void testDelete() {
        SkipList skipList = new SkipList();

        skipList.insert(3);
        assertTrue(skipList.search(3));

        skipList.delete(3);
        assertFalse(skipList.search(3));
    }

    @Test
    void testSearch() {
        SkipList skipList = new SkipList();

        skipList.insert(3);
        assertTrue(skipList.search(3));
        assertFalse(skipList.search(5));
        assertFalse(skipList.search(2));
    }
}
