package stack;

public class Main {

	
	public static void main(String[] args) {
		
		Stack<Integer> stack = new Stack<Integer>(4);

		stack.push(700);
		stack.push(20);

		stack.push(710);

		stack.push(100);
		
		System.out.println(stack.getIndexBigElement());

		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());

	}
}


