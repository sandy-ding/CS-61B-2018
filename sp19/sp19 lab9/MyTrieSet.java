import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyTrieSet implements TrieSet61B {
    private Node root;

    private class Node {
        public char c;
        public boolean isKey;
        public Map map;

        public Node() {
            this.isKey = false;
            this.map = new HashMap();
        }

        public Node(char c, boolean isKey) {
            this.c = c;
            this.isKey = isKey;
            this.map = new HashMap();
        }
    }

    public MyTrieSet() {
        this.root = new Node();
    }

    @Override
    public void clear() {
        this.root = new Node();
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    @Override
    public boolean contains(String key) {
        if (key == null || key.length() < 1) {
            return false;
        }

        Node curr = root;
        for (int i = 0, length = key.length(); i < length; i++) {
            char c = key.charAt(i);
            if (curr.map.containsKey(c)) {
                curr = (Node) curr.map.get(c);
            } else {
                return false;
            }
        }
        return curr.isKey;
    }

    /** Inserts string KEY into Trie */
    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }

        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(c, false));
            }
            curr = (Node) curr.map.get(c);
        }
        curr.isKey = true;
    }

    /** Returns a list of all words that start with PREFIX */
    @Override
    public List<String> keysWithPrefix(String prefix) {
        if (prefix == null || prefix.length() < 1) {
            return null;
        }
        List list = new LinkedList();
        Node curr = root;
        for (int i = 0, n = prefix.length(); i < n; i++) {
            char c = prefix.charAt(i);
            if (curr.map.containsKey(c)) {
                curr = (Node) curr.map.get(c);
            } else {
                return null;
            }
        }
        return helper(prefix, curr, list);

    }

    private List<String> helper(String s, Node node, List list) {
        if (node.isKey) {
            list.add(s);
        }
        if (node.map.keySet().isEmpty()) {
            return list;
        }
        for (Object ch : node.map.keySet()) {
            char c = (char) ch;
            Node n = (Node) node.map.get(c);
            helper(s+c, n, list);
        }
        return list;
    }

    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }
}
