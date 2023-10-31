package edu.hw3;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public final class Task2 {
    private Task2() {
    }

    public static List<String> clusterize(String input) {
        List<String> output = new LinkedList<>();
        Stack<Character> stack = new Stack<>();
        StringBuilder cluster = new StringBuilder();
        for (char character : input.toCharArray()) {
            cluster.append(character);
            if (character == ')' && stack.peek() == '(') {
                stack.pop();
            } else {
                stack.push(character);
            }
            if (stack.empty()) {
                output.add(cluster.toString());
                cluster.delete(0, cluster.length());
            }
        }
        return output;
    }
}
