package aoc.y2020;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static aoc.common.InputHandler.*;

public class Day07 {
	private static HashMap<String, Node> nodes = generateTree();

	public static void main(String[] args) {
		System.out.println("Part 1: " + partOne());
		System.out.println("Part 2: " + partTwo());
	}

	static int partOne() {
		Node gold = nodes.get("shiny gold");

		Set<Node> visited = new HashSet<>();
		Stack<Node> stack = new Stack<>();

		stack.addAll(gold.parents);

		while (!stack.isEmpty()) {
			Node current = stack.pop();

			if (visited.contains(current))
				continue;

			stack.addAll(current.parents);
			visited.add(current);
		}
		return visited.size();
	}

	static int partTwo() {
		return nodes.get("shiny gold").computeChildren();
	}

	private static HashMap<String, Node> generateTree() {
		nodes = new HashMap<>();
		Pattern parent = Pattern.compile("(.+) bags contain (.+)\\.");
		Pattern child = Pattern.compile("(\\d+?) (.+?) bags?");

		String[] lines = asStringArray(2020,7);

		for (String line : lines) {
			Matcher matcher = parent.matcher(line);
			matcher.matches();
			Node parentNode = nodes.computeIfAbsent(matcher.group(1), Node::new);

			String children = matcher.group(2);

			for (String c : children.split(", ")) {
				if (c.equals("no other bags"))
					break;

				Matcher childMatcher = child.matcher(c);
				childMatcher.matches();
				int n = Integer.parseInt(childMatcher.group(1));
				Node childNode = nodes.computeIfAbsent(childMatcher.group(2), Node::new);
				childNode.parents.add(parentNode);
				parentNode.children.put(childNode, n);
			}
		}
		return nodes;
	}

	static class Node {
		public String bag;
		public Set<Node> parents;
		public Map<Node, Integer> children;

		public Node(String bag) {
			this.bag = bag;
			parents = new HashSet<>();
			children = new HashMap<>();
		}

		public int computeChildren() {
			int sum = 0;

			for (Map.Entry<Node, Integer> entry : children.entrySet()) {
				int nbr = entry.getValue();
				sum += nbr * (entry.getKey().computeChildren() + 1);
			}
			return sum;
		}
	}
}
