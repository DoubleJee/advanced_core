package datastructure.tree.multi;


import datastructure.AssertTool;

public class TrieTest {

    public static void main(String[] args) {
        Trie<Integer> trie = new Trie<>();
        trie.add("cat", 1);
        trie.add("dog", 2);
        trie.add("catalog", 3);
        trie.add("cast", 4);
        trie.add("巩振振", 5);
        AssertTool.test(trie.size() == 5);
        AssertTool.test(trie.startWith("do"));
        AssertTool.test(trie.startWith("c"));
        AssertTool.test(trie.startWith("ca"));
        AssertTool.test(trie.startWith("cat"));
        AssertTool.test(trie.startWith("cata"));
        AssertTool.test(!trie.startWith("haha"));
        AssertTool.test(trie.get("巩振振") == 5);
        AssertTool.test(trie.remove("cat") == 1);
        AssertTool.test(trie.remove("catalog") == 3);
        AssertTool.test(trie.remove("cast") == 4);
        AssertTool.test(trie.size() == 2);

        AssertTool.test(trie.startWith("巩"));
        AssertTool.test(trie.startWith("do"));
        AssertTool.test(!trie.startWith("c"));
        AssertTool.test(trie.contains("dog"));




    }
}
