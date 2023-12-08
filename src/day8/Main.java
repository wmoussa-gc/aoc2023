package day8;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.file.Files.readAllLines;

public class Main {
    private final static String FILENAME = "src/" + Main.class.getPackageName() + "/input.txt";

    public static void main(String[] args) throws IOException {
        part1();
        part2();
    }

    private static void part1() throws IOException {
        List<String> lines = readAllLines(Path.of(FILENAME));
        Map<String, Node> nodes = new HashMap<>();

        for (String s : lines.subList(2, lines.size())) {
            Node temp = new Node(s.split("=")[0].trim(), null, null);
            nodes.put(s.split("=")[0].trim(), temp);
        }

        for (String s : lines.subList(2, lines.size())) {
            String name = s.split("=")[0].trim();
            String clean = s.split("=")[1].trim().replaceAll("[()]", "");
            String left = clean.split(",")[0].trim();
            String right = clean.split(",")[1].trim();

            nodes.get(name).left = nodes.get(left).name;
            nodes.get(name).right = nodes.get(right).name;
        }

        int steps = 0;
        String[] instructions = lines.get(0).split("");
        String next = "AAA";

        while (!next.equals("ZZZ")) {
            for (String instruction : instructions) {
                if (instruction.equals("L")) {
                    next = nodes.get(next).left;
                } else {
                    next = nodes.get(next).right;
                }
                steps++;
                if (next.equals("ZZZ")) break;
            }
        }
        System.out.println(steps);
    }

    /**
     * Find each node ending with A compute the steps to reach the first node ending with Z.
     * Find the LCM of each node's steps since it is going to keep cycling until all ends up with Z as a common denomination
     */
    private static void part2() throws IOException {
        List<String> lines = readAllLines(Path.of(FILENAME));
        Map<String, Node> nodes = new HashMap<>();

        for (String s : lines.subList(2, lines.size())) {
            Node temp = new Node(s.split("=")[0].trim(), null, null);
            nodes.put(s.split("=")[0].trim(), temp);
        }

        for (String s : lines.subList(2, lines.size())) {
            String name = s.split("=")[0].trim();
            String clean = s.split("=")[1].trim().replaceAll("[()]", "");
            String left = clean.split(",")[0].trim();
            String right = clean.split(",")[1].trim();

            nodes.get(name).left = nodes.get(left).name;
            nodes.get(name).right = nodes.get(right).name;
        }

        String[] instructions = lines.get(0).split("");

        List<Integer> steps = new ArrayList<>();
        for (String nodeName : nodes.keySet()) {
            if (nodeName.split("")[2].equals("A")) {
                steps.add(computeSteps(nodeName, nodes, instructions));
            }
        }

        long lcm = lcmCalculation(steps.get(0), steps.get(1));
        for (int i = 2; i < steps.size(); i++) {
            lcm = lcmCalculation(lcm, steps.get(i));
        }
        System.out.println(lcm);
    }

    private static int computeSteps(String next, Map<String, Node> nodes, String[] instructions) {
        int steps = 0;
        while (!next.split("")[2].equals("Z")) {
            for (String instruction : instructions) {
                if (instruction.equals("L")) {
                    next = nodes.get(next).left;
                } else {
                    next = nodes.get(next).right;
                }
                steps++;
                if (next.split("")[2].equals("Z")) break;
            }
        }
        return steps;
    }

    private static long lcmCalculation(long n1, long n2) {
        long temp, i = 2, res;
        res = Math.max(n1, n2);
        temp = res;
        while (res % n1 != 0 || res % n2 != 0) {
            res = temp * i;
            i++;
        }
        return res;
    }

    private static class Node {
        String name;
        String left;
        String right;

        public Node(String name, String left, String right) {
            this.name = name;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "name=" + name +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }


}
