/** Performs some basic linked list tests. */
public class ArrayDequeTest {
	
	/* Utility method for printing out empty checks. */
	public static boolean checkEmpty(boolean expected, boolean actual) {
		if (expected != actual) {
			System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Utility method for printing out empty checks. */
	public static boolean checkSize(int expected, int actual) {
		if (expected != actual) {
			System.out.println("size() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Prints a nice message based on whether a test passed. 
	 * The \n means newline. */
	public static void printTestStatus(boolean passed) {
		if (passed) {
			System.out.println("Test passed!\n");
		} else {
			System.out.println("Test failed!\n");
		}
	}

	/** Adds a few things to the list, checking isEmpty() and size() are correct, 
	  * finally printing the results. 
	  *
	  * && is the "and" operation. */
	public static void addIsEmptySizeTest() {
		System.out.println("Running add/isEmpty/Size test.");

		ArrayDeque<String> ad = new ArrayDeque<String>();

		boolean passed = checkEmpty(true, ad.isEmpty());

		ad.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
		passed = checkSize(1, ad.size()) && passed;
		passed = checkEmpty(false, ad.isEmpty()) && passed;

		ad.addLast("middle");
		passed = checkSize(2, ad.size()) && passed;

		ad.addLast("back");
		passed = checkSize(3, ad.size()) && passed;

		System.out.println("Printing out deque: ");
		ad.printDeque();

		printTestStatus(passed);

	}

	/** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
	public static void addRemoveTest() {

		System.out.println("Running add/remove test.");

		ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
		// should be empty 
		boolean passed = checkEmpty(true, ad.isEmpty());

		ad.addFirst(10);
		// should not be empty 
		passed = checkEmpty(false, ad.isEmpty()) && passed;

		ad.removeFirst();
		// should be empty 
		passed = checkEmpty(true, ad.isEmpty()) && passed;

		ad.addLast(10);
		ad.removeLast();
		passed = checkEmpty(true, ad.isEmpty()) && passed;

		printTestStatus(passed);

	}

	/** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
	public static void resizeTest() {

		System.out.println("Running resize test.");

		ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
		// should be empty
		boolean passed = checkEmpty(true, ad.isEmpty());

		ad.addFirst(8);
		ad.addFirst(7);
		ad.addFirst(6);
		ad.addFirst(5);
		ad.addFirst(4);
		ad.addFirst(3);
		ad.addFirst(2);
		ad.addFirst(1);
		// should not be empty
		passed = checkSize(8, ad.size()) && passed;

		ad.addLast(9);
		ad.addLast(10);
		ad.addLast(11);
		ad.addLast(12);
		ad.addLast(13);
		ad.addLast(14);
		ad.addLast(15);
		ad.addLast(16);
		ad.addLast(17);
		ad.addLast(18);
		passed = checkSize(18, ad.size()) && passed;

		ad.removeLast();
		ad.removeLast();
		ad.removeLast();
		ad.removeLast();
		ad.removeLast();
		ad.removeLast();
		ad.removeLast();
		ad.removeLast();
		ad.removeLast();
		ad.removeLast();
		ad.removeLast();
		ad.removeLast();
		passed = 1 == ad.removeFirst() && passed;

		printTestStatus(passed);

	}

	public static void main(String[] args) {
		System.out.println("Running tests.\n");
		addIsEmptySizeTest();
		addRemoveTest();
		resizeTest();
	}
}
