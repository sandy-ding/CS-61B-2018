import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testAddFirst() {

        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();
        String message = "";
        boolean test = true;

        while (test) {
            double testChoice = StdRandom.uniform();
            if (testChoice < 0.25) {
                Integer numberToAdd = StdRandom.uniform(0, 100);
                sad1.addFirst(numberToAdd);
                ads1.addFirst(numberToAdd);
                message += "addFirst(" + numberToAdd + ")\n";
                assertEquals(message, ads1.get(0), sad1.get(0));
            } else if (testChoice < 0.5) {
                Integer numberToAdd = StdRandom.uniform(0, 100);
                sad1.addLast(numberToAdd);
                ads1.addLast(numberToAdd);
                message += "addLast(" + numberToAdd + ")\n";
                assertEquals(message, ads1.getLast(), sad1.get(sad1.size()-1));
            } else if (testChoice < 0.75 && !sad1.isEmpty()) {
                message += "removeFirst()\n";
                assertEquals(message, ads1.removeFirst(), sad1.removeFirst());
            } else if (!sad1.isEmpty()) {
                message += "removeLast()\n";
                assertEquals(message, ads1.removeLast(), sad1.removeLast());
            }
    }

}
