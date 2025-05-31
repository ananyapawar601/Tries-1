/*
Time Complexity: O(N * L)
Space Complexity: O(W) where W = sum of lengths of all words
 */

 class Solution {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    private void insert(TrieNode root, String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            if (curr.children[c - 'a'] == null) curr.children[c - 'a'] = new TrieNode();
            curr = curr.children[c - 'a'];
        }
        curr.isEnd = true;
    }
    public String longestWord(String[] words) {
        TrieNode root = new TrieNode();

        for (String word : words) {
            insert(root, word);
        }

        Queue<TrieNode> q = new LinkedList<>();
        Queue<String> strQ = new LinkedList<>();
        q.add(root);
        strQ.add("");

        String result = "";

        while(!q.isEmpty()) {
            TrieNode curr = q.poll();
            String currStr = strQ.poll();

            for (int i = 25; i >= 0; i--) {
                TrieNode next = curr.children[i];
                if (next != null && next.isEnd) {
                    char c = (char) ('a' + i);
                    q.add(next);
                    strQ.add(currStr + c);
                }
            }
            result = currStr;
        }
        return result;
    }
}