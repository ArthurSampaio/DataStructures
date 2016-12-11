package stack;

public class Main {

	
	public static void main(String[] args) {
		
		Stack<Integer> stack = new Stack<Integer>(4);

		stack.push(1010);
		stack.push(500);

		stack.push(950);

		stack.push(900);
		
		System.out.println(stack.getIndexBigElement()-1);
		stack.reverseStack(stack.getIndexBigElement()+ (-1));
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());

	}
}


