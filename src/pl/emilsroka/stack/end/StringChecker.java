package pl.emilsroka.stack.end;

import java.util.*;

public class StringChecker {
    private static Set<Character> openingBrackets = new HashSet(Arrays.asList('(', '<', '[', '{'));
    private static Map<Character, Character> bracketPairs = Map.of(
            ')', '(',
            '>', '<',
            ']', '[',
            '}', '{'
    );

    public static boolean isBalanced(String expression){
        if(expression == null)
            throw new IllegalArgumentException();

        Stack<Character> stack = new Stack<>();

        for(var letter : expression.toCharArray()){
            if(openingBrackets.contains(letter)) {
                stack.push(letter);
            } else if(bracketPairs.containsKey(letter)) {
                if( stack.isEmpty() ) return false;
                if( bracketPairs.get(letter) != stack.pop()) return false;
            }
        }

        return stack.isEmpty();
    }
}
