public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque d = new LinkedListDeque();
        for (int i = 0, length = word.length(); i < length; i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    public boolean isPalindrome(String word) {
        Deque d = this.wordToDeque(word);
        return this.isPalindromeHelper(d);
    }

    private boolean isPalindromeHelper(Deque d) {
        if (d.size() <= 1) {
            return true;
        }
        return d.removeFirst() == d.removeLast() && isPalindromeHelper(d);
    }
}
