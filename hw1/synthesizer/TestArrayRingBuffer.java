package synthesizer;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import edu.princeton.cs.algs4.StdOut;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void testIterable() {
        ArrayRingBuffer arb = new ArrayRingBuffer(4);
        int expected = 1;
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        for (Object t : arb) {
            assertEquals(expected, t);
            expected += 1;
        }
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
