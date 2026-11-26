public class LinkdListNode<T>{
	private T value;
	private LinkdListNode<T> next;




	public LinkdListNode(T value){
		this.value = value;
	}
	public void setNext(LinkdListNode<T>o next){
		this.next = next;
	}
	public T getValue(){
		return this.value;
	}
	public boolean isNextNull(){
		if (next == null) {return true;}
		else{return false;}
	}
	public LinkdListNode<T> getNext() {
		return this.next;
	}

}
