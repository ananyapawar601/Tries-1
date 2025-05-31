/*
Time Complexity:
Insertion: O(N), where N is the length of the word being inserted. 
This is because we need to go through each letter of the word to either find its corresponding node or create a new node as needed.
Search: O(N), where N is the length of the word being searched for. During a Trie search, we traverse each letter of the word starting from the root, 
checking if the current node has a child node at the position of the next letter. This continues until we either reach the end of the word or find a missing letter node.
Prefix Search: O(N), where N is the length of the prefix being searched for. Just like in word search, we go through each letter of the prefix to find its corresponding node.


Space Complexity: O(N) where N is the total number of characters across all unique words inserted into the Trie. For each character in a word, a new node might need to be created, resulting in space usage that is proportional to the total number of characters.
 */

 class Node {
    /* Array to store links to child nodes,
    each index represents a letter */
    private Node[] links;
    /* Flag indicating if the node
    marks the end of a word */
    private boolean flag;

    // Constructor
    public Node() {
        links = new Node[26];
        flag = false;
    }

    /* Check if the node contains
    a specific key (letter) */
    public boolean containsKey(char ch) {
        return links[ch - 'a'] != null;
    }

    /* Insert a new node with a specific
    key (letter) into the Trie */
    public void put(char ch, Node node) {
        links[ch - 'a'] = node;
    }

    /* Get the node with a specific
    key (letter) from the Trie */
    public Node get(char ch) {
        return links[ch - 'a'];
    }

    /* Set the current node
    as the end of a word */
    public void setEnd() {
        flag = true;
    }

    /* Check if the current node
    marks the end of a word */
    public boolean isEnd() {
        return flag;
    }
}

class Trie {
    private Node root;

    /* Constructor to initialize the
    Trie with an empty root node */
    public Trie() {
        root = new Node();
    }

    /* Inserts a word into the Trie
    Time Complexity O(len), where len
    is the length of the word */
    public void insert(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            if (!node.containsKey(word.charAt(i))) {
                /* Create a new node for
                the letter if not present */
                node.put(word.charAt(i), new Node());
            }
            // Move to the next node
            node = node.get(word.charAt(i));
        }
        // Mark the end of the word
        node.setEnd();
    }

    /* Returns if the word
    is in the trie */
    public boolean search(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            if (!node.containsKey(word.charAt(i))) {
                /* If a letter is not found,
                the word is not in the Trie */
                return false;
            }
            // Move to the next node
            node = node.get(word.charAt(i));
        }
        /* Check if the last node
        marks the end of a word */
        return node.isEnd();
    }

    /* Returns if there is any word in the
    trie that starts with the given prefix */
    public boolean startsWith(String prefix) {
        Node node = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (!node.containsKey(prefix.charAt(i))) {
                /* If a letter is not found,
                there is no word with the
                given prefix */
                return false;
            }
            // Move to the next node
            node = node.get(prefix.charAt(i));
        }
        // The prefix is found in the Trie
        return true;
    }
}