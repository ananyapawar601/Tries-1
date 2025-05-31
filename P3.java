class Solution {
    private TrieNode root; 

    class TrieNode{
        TrieNode[] children;
        boolean isEnd;
        public TrieNode() {
            this.children = new TrieNode[26];
        }
    }

    public void insert(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if(curr.children[ch - 'a'] == null) {
                curr.children[ch - 'a'] = new TrieNode();
            }
            curr = curr.children[ch - 'a'];
        }
        curr.isEnd = true;
    }
    public String replaceWords(List<String> dictionary, String sentence) {
        this.root = new TrieNode();
        for (String word : dictionary) {
            insert(word);
        }
        String[] strArr = sentence.split(" ");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            String currWord = strArr[i];
            if (i != 0) {
                result.append(" ");
            }
            TrieNode curr = root;
            StringBuilder replacement = new StringBuilder();
            for (int j = 0; j < currWord.length(); j++) {
                char ch = currWord.charAt(j);
                if(curr.children[ch - 'a'] == null || curr.isEnd) break;
                replacement.append(ch);
                curr = curr.children[ch - 'a'];
            }
            if (curr.isEnd) {
                result.append(replacement.toString());
            } else {
                result.append(currWord);
            }
        }
        return result.toString();
    }
}