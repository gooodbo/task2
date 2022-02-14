import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Tests extends Assert {

    private NewLinkedList<String> newLinkedList = new NewLinkedList<>();
    public LinkedList<String> oldLinkedList = new LinkedList<>();


    @Before
    public void addingEl(){
        newLinkedList.addAll(List.of("dfhdr","dfghdrh", "ulu", "tuy", "vbny"));
        oldLinkedList.addAll(List.of("dfhdr","dfghdrh", "ulu", "tuy", "vbny"));
    }

    @Test
    public void toStringTest(){
        assertEquals(oldLinkedList.toString(), newLinkedList.toString());
        newLinkedList.add("pop");
        assertEquals("[dfhdr, dfghdrh, ulu, tuy, vbny, pop]", newLinkedList.toString());
    }

    @Test
    public void addLastAndGetLastTest(){
        oldLinkedList.addLast("kek");
        newLinkedList.addLast("kek");
        assertEquals(oldLinkedList.getLast(), newLinkedList.getLast());
        assertEquals(oldLinkedList.getLast(), newLinkedList.getLast());
        oldLinkedList.addFirst("pup");
        newLinkedList.addFirst("pup");
        oldLinkedList.add("add");
        newLinkedList.add("add");
        assertEquals(oldLinkedList.getFirst(), newLinkedList.getFirst());
        assertEquals(oldLinkedList.get(5), newLinkedList.get(5));
        assertEquals(oldLinkedList.toString(), newLinkedList.toString());
    }

    @Test
    public void sortingTest(){
        oldLinkedList.sort(String::compareTo);
        newLinkedList.sort(String::compareTo);
        assertEquals(oldLinkedList.toString(), newLinkedList.toString());
    }

    @Test
    public void outOfBoundExeptionTest(){
        try {
            newLinkedList.add(15,"lol");
            Assert.fail("Ожидалась IndexOutOfBoundsException");
        }
        catch (IndexOutOfBoundsException thrown) {
            Assert.assertNotEquals("", thrown.getMessage());
        }
        try {
            newLinkedList.addAll(15, List.of("lol"));
            Assert.fail("Ожидалась IndexOutOfBoundsException");
        }
        catch (IndexOutOfBoundsException thrown) {
            Assert.assertNotEquals("", thrown.getMessage());
        }
        try {
            newLinkedList.remove(-1);
            Assert.fail("Ожидалась IndexOutOfBoundsException");
        }
        catch (IndexOutOfBoundsException thrown) {
            Assert.assertNotEquals("", thrown.getMessage());
        }
    }

    @Test
    public  void emptyTset(){
        assertEquals(false, newLinkedList.isEmpty());
    }

    @Test
    public void sizeTest(){
        assertEquals(oldLinkedList.size(), newLinkedList.size());
        assertEquals(5, newLinkedList.size());
    }

    @Test
    public void setTest(){
        newLinkedList.set(2, "change");
        oldLinkedList.set(2, "change");
        newLinkedList.set(4, "change2");
        oldLinkedList.set(4, "change2");
        assertEquals("change", oldLinkedList.get(2));
        assertEquals("change2", oldLinkedList.get(4));
        assertEquals(newLinkedList.get(4), oldLinkedList.get(4));
        assertEquals(newLinkedList.get(2), oldLinkedList.get(2));
    }

    @Test
    public void removeByIndexTest(){
        newLinkedList.removeLast();
        oldLinkedList.removeLast();
        newLinkedList.removeFirst();
        oldLinkedList.removeFirst();
        assertEquals(oldLinkedList.toString(), newLinkedList.toString());
        //Удаляем по индексу
        oldLinkedList.remove(2);
        newLinkedList.remove(2);
        assertEquals(oldLinkedList.toString(), newLinkedList.toString());
    }

    @Test
    public void removeByObjTest(){
        assertEquals(true, oldLinkedList.remove("ulu"));
        assertEquals(false, newLinkedList.remove("false"));
        newLinkedList.remove("ulu");
        oldLinkedList.remove("ulu");
        assertEquals(oldLinkedList.toString(),newLinkedList.toString());
    }

    @Test
    public void addAllTest(){
        oldLinkedList.addAll(4, List.of("ulu","false"));
        newLinkedList.addAll(4, List.of("ulu","false"));
        assertEquals(oldLinkedList.get(4),newLinkedList.get(4));
        assertEquals(oldLinkedList.toString(),newLinkedList.toString());
    }

}
