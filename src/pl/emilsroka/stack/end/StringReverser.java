package pl.emilsroka.stack.end;

import java.util.Stack;

public class StringReverser {
    public static String reverse(String text){
        if (text == null)
            throw new IllegalArgumentException("");

        Stack<Character> stack = new Stack<>();
        for(var character : text.toCharArray())
            stack.push(character);

        var builder = new StringBuilder();
        while(!stack.empty())
            builder.append(stack.pop());

        return builder.toString();
    }
}
