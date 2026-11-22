import java.lang.reflect.Array;

public class LinkdList<T>{
	int size = 0;
	LinkdListNode<T> headNode;
	LinkdListNode<T> tailNode;
	/*what stuff should a linkdlist do?
	1- add items
	2- show items
	3- delete specific item (can be done by providing the index or the value, i decided to only implement the
		delete by value since i think delete by index is useless for our case
	*/



	public void addItem(T item){
		LinkdListNode<T> newNode = new LinkdListNode<T>(item);
		if(size == 0){
			headNode = newNode;
			tailNode = newNode;
		}
		else{
			tailNode.setNext(newNode);
			tailNode = tailNode.getNext();
		}
		size+=1;
	}



	public T[] listItems(Class<T> c){
		//we need this to bypass java not allowing T[]
		@SuppressWarnings("unchecked")
        	final T[] result = (T[]) Array.newInstance(c, size);

		//in case array is empty, return nothing
		if(size == 0){ return result;}

		//this code wont run unless array is not empty, so we are sure headNode exists
		LinkdListNode<T> currentNode = headNode;
		result[0] = headNode.getValue();

		//this will run when array size is bigger than 1, to add the rest
		for(int i = 1; i < size; i++){
			currentNode = currentNode.getNext();
			result[i] = currentNode.getValue();
		}
		return result;
	}


	public void delete(T item){
		//we have 3 cases, first if the linkdlist is empty:
		if(size == 0){return;}

		//secound case if the node is the first one
		LinkdListNode<T> currentNode = headNode;

		if(headNode.getValue().equals(item)){
			//if next node doesnt exist exist:
			if(headNode.isNextNull()) {
				headNode = null;
				tailNode = null;
				size -= 1;
			}
			//if it does exist
			else{
				headNode = headNode.getNext();
				size -= 1;
			}
			return;
		}



		//third case: if the node is secound to last
		else if(size>1){
			LinkdListNode<T> pastNode = headNode;
			currentNode = currentNode.getNext();
			while(currentNode.isNextNull() == false){
				if(currentNode.getValue().equals(item)){
					pastNode.setNext(currentNode.getNext());
					size -= 1;
					return;
				}
				currentNode = currentNode.getNext();
				pastNode = pastNode.getNext();
			}

			//dont forgot to check last one
			if(currentNode.getValue().equals(item)){
				pastNode.setNext(null);
				tailNode = pastNode;
				size-=1;
				return;
			}
		}
	}
}
