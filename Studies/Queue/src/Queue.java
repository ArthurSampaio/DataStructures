import java.util.Scanner;

class Queue {
	
	private int head; 
	private int tail; 
	private int capacity;
	private int[] q; //queue
	
	public Queue(int size){
		
		head = -1;
		tail = -1;
		capacity = size; 
		q = new int[size];
	}
	
	private boolean isEmpty(){
		
		return head==-1 && tail ==-1; 
	}
	
	private boolean isFull(){
		
		return head == (tail+1)%capacity;
	}
	

	public boolean add(int n){
		
		if(isFull()) return false; 
		
		if(isEmpty()){
			this.head=0;
			this.tail=0;
			q[0] = n;


			return true;
			
		}else{
			this.tail = ((this.tail + 1) % capacity);
			q[tail] = n;


			return true;
		}
		
	}
	
	public boolean remove(){
	
		if(isEmpty()) return false;
		if(tail == head){
			head = -1;
			tail = -1;
			
			return true;
		}else{
			this.head = ((this.head + 1) % capacity);
		
			return true;
		}
		
	}
	
	
	public String print(){
		if(isEmpty()) return "empty";
		String out="";
		if(tail == head) out+=q[head];
		else{
			int aux = head;
			while(aux != tail){
				out += q[aux] + " ";
				aux = (aux+1)%capacity;
			}
			out+= q[tail];
		}
		return out;
		
	}
	
	public int element(){
		return q[head]; 
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
				
		Queue q = new Queue(size);
		
		String in = "";
		in = sc.nextLine();
		while(!in.equals("end")){
			
			
			String[] words = in.split(" ")
					;
			if(words[0].equalsIgnoreCase("add")){
				if(!q.add(Integer.parseInt(words[1]))){
					System.out.println("full");
				}
			}
			
			if(words[0].equalsIgnoreCase("remove")){
				boolean b = q.remove();
				if(!b) System.out.println("empty");
			}
			
			if(words[0].equalsIgnoreCase("print"))
					System.out.println(q.print());
			
			if(words[0].equalsIgnoreCase("element")){
				if(q.element()!=-1)
					System.out.println(q.element());
				else
					System.out.println("empty");

			}
			
			in = sc.nextLine();
			
		}
		
		
	}
	

}
