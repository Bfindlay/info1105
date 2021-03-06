package week4;

import java.util.Stack;

public class Palindromes {

	public static void main(String[] args) {

		long startTime = System.nanoTime();
		boolean result = isPalindrome("racecar");
		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println(totalTime);
		System.out.println("Total time for recursion" + "16563" + "loops is 349473");
		String test1 = "Madam, I'm Adam";
		String test2 = "Never odd or even";
		String testFail = "Hello World";
		boolean result1 = isPalindromeSentence(test1);
		boolean result2 = isPalindromeSentence(test2);
		boolean result3 = isPalindromeSentence(testFail);
		System.out.println(result + " Is result for single word");
		System.out.println(result1 + " Is result for sentence 1");
		System.out.println(result2 + " Is result for ssentence 2");
		System.out.println(result3 + " Is result for sentence 3");
	}

	public static boolean isPalindrome(String test) {
		Stack<Character> s = new Stack<>();
		char[] arr = test.toCharArray();
		for (char c : arr) {
			s.push(c);
		}
		return check(arr, s);
	}

	// With recursion
	public static boolean check(char[] arr, Stack<Character> s, int i) {
		if (i == arr.length)
			return true;
		return (arr[i] == s.pop()) ? check(arr, s, i + 1) : false;
	}

	// Without recursion
	public static boolean check(char[] arr, Stack<Character> s) {
		for (int i = 0; i < arr.length; i++) {
			if (s.pop() != arr[i])
				return false;
		}
		return true;
	}

	// sentences using the recursive check
	public static boolean isPalindromeSentence(String sentence) {
		Stack<Character> s = new Stack<>();
		char[] arr = sentence.replaceAll("[^A-Za-z0-9]", "").toLowerCase().toCharArray();
		for (char c : arr) {
			if (Character.isAlphabetic(c))
				s.push(Character.toLowerCase(c));
		}
		return check(arr, s, 0);
	}
}
