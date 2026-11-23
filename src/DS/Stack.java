package DS;


public class Stack<T>{

  private static class Node<T> {
    private T data;
    private Node<T> next;

    public Node(T data, Node<T> next) {
      this.data = data;
      this.next = next;
    }
  }

  private Node<T> top;
  //    private int count;

  public Stack() {
    this.top = null;
    //        this.count = 0;
  }

  public void push(T element) {
    top = new Node<>(element, top);
    //        count++;
  }

  public T pop() {
    if (isEmpty()) {
      return null;
    }
    T element = top.data;
    top = top.next;
    //        count--;
    return element;
  }

  public T peek() {
    if (isEmpty()) {
      return null;
    }
    return top.data;
  }

  public boolean isEmpty() {

    return top == null;
  }

  public int size() {
    return 0; //count;
  }
}
