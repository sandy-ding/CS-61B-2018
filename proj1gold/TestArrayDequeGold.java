import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testAddFirst() {

        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();
        boolean test = true;

        while (test) {
            double testChoice = StdRandom.uniform();
            if (testChoice < 0.25) {
                int numberToAdd = (int) StdRandom.uniform(0, 100);
                sad1.addFirst(numberToAdd);
                ads1.addFirst(numberToAdd);
                assertEquals("Oh noooo!\nThis is bad:\n   Random number " + sad1
                        + " not equal to " + ads1 + "!", ads1, sad1);
            } else if (testChoice < 0.5) {
                int numberToAdd = (int) StdRandom.uniform(0, 100);
                sad1.addLast(numberToAdd);
                ads1.addLast(numberToAdd);
                assertEquals("Oh noooo!\nThis is bad:\n   Random number " + sad1
                        + " not equal to " + ads1 + "!", ads1, sad1);
            } else if (testChoice < 0.75 && !sad1.isEmpty()) {
                assertEquals("Oh noooo!\nThis is bad:\n   Random number " + sad1
                        + " not equal to " + ads1 + "!", ads1.removeFirst(), sad1.removeFirst());
            } else if (!sad1.isEmpty()) {
                assertEquals("Oh noooo!\nThis is bad:\n   Random number " + sad1
                        + " not equal to " + ads1 + "!", ads1.removeLast(), sad1.removeLast());
            }
        }

        sad1.printDeque();
    }

}
