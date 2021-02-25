class linkedList{

	node begin = null;
	node end = null;
	
	public void traverse(){
		node tempNode = begin;
		while(tempNode != null){
			System.out.println(tempNode.payLoad);
			tempNode = tempNode.nextNode;
		}
	}
	
	public void push(String s){
		if(begin == null){
			begin = new node(s);
			end = begin;
		}
		else{
			node newNode = new node(s);
			end.nextNode = newNode;
			end = newNode;
		}
	}
	
	class node{
		String payLoad;
		node nextNode = null;
		public node(String s){
			payLoad = s;
			nextNode = null;
		}
		
		public node(){
			payLoad = "";
			nextNode = null;
		}
	}
}