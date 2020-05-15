package datastructure.list.stack;

import datastructure.AssertTool;

import java.util.HashMap;
import java.util.Map;

public class StackTest {

    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        System.out.println(stack.top());
        System.out.println(stack.pop());
        System.out.println(stack);

    }

    static class LeeTest{
        public static void main(String[] args) {
            String str = "{([])}";
            AssertTool.test(verification2(str));
        }


        public static boolean verification(String str){
            Stack<Character> characterStack = new Stack<>();
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (c == '(' || c == '[' || c == '{'){
                    characterStack.push(c);
                } else {
                    if (characterStack.isEmpty()) return false;

                    char leftChar = characterStack.pop();
                    if (leftChar == '(' && c != ')'){
                        return false;
                    }
                    if (leftChar == '[' && c != ']'){
                        return false;
                    }
                    if (leftChar == '{' && c != '}'){
                        return false;
                    }
                }
            }


            return characterStack.isEmpty();
        }

        static Map<Character,Character> characterMap = new HashMap<>();
        static {
            characterMap.put('(',')');
            characterMap.put('[',']');
            characterMap.put('{','}');
        }
        public static boolean verification2(String str){

            Stack<Character> characterStack = new Stack<>();
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (characterMap.containsKey(c)){
                    characterStack.push(c);
                } else {
                    if (characterStack.isEmpty()) return false;

                    char leftChar = characterStack.pop();
                    if (characterMap.get(leftChar) != c){
                        return false;
                    }

                }
            }


            return characterStack.isEmpty();
        }

    }
}
