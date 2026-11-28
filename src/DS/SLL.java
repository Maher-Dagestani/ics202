package DS;

public class SLL<T extends Comparable<T>> {
  private SLLNode<T> head, tail;

  public SLL() {
    head = tail = null;
  }
  public boolean isEmpty() {
    return head == null;
  }

  public SLLNode<T> getHead() { return this.head; }
  public SLLNode<T> getTail() { return this.tail; }

  public void addToHead(T e) {

    SLLNode<T> newNode = new SLLNode<T>(e);

    if (isEmpty() ){
      tail = newNode;
      head = newNode;
    }
    else {
      newNode.next = head;
      head = newNode;
    }

  }

  public void addToTail(T e) {

    SLLNode<T> newNode = new SLLNode<>(e);

    if (isEmpty()) {
      head = newNode;
      tail = newNode;
    }
    else {
      tail.next = newNode;
      tail =  newNode;
    }

  }



  public T deleteFromHead() { // delete the head and return its info;

    if (isEmpty())
      return null;

    T e = head.info;

    if (head == tail)       // if only one node on the list;
    head = tail = null;
    else
      head = head.next;

    return e;
  }


  public T deleteFromTail() {          // delete the tail and return its info;

    if (isEmpty())
      return null;

    T el = tail.info;

    if (head == tail)               // if only one node in the list;
    head = tail = null;

    else {                          // if more than one node in the list,

      SLLNode<T> tmp = head;      // find the predecessor of tail;

      while (tmp.next != tail)
        tmp = tmp.next;

      tail = tmp;                  // the predecessor of tail becomes tail;
      tail.next = null;
    }

    return el;
  }

  public boolean delete(T e) {  // delete the node with an element e;
    if (isEmpty()) return false;
    // Case 1: e at the head
    if (e.equals(head.info)) { // e is in the head node;
      head = head.next;
      // only one node in the list;
      if (head == null)
        head = tail = null;
    }
    else {                    // e is in a none-head node;
      SLLNode<T> prev = head, tmp = head.next;
      while( (tmp != null) && (!tmp.info.equals(e))){
        prev = tmp;
        tmp = tmp.next;
      }

      if (tmp == null)
        return  false;
      // if e  was found;
      prev.next = tmp.next;
      if (tmp == tail) // if e  is in the last node;
      tail = prev;
    }

    return true;
  }

  @Override
  public String toString() {
    if(isEmpty())
      return "[ ]";
    String str = "[ ";
    SLLNode<T> tmp = head;
    while(tmp != null){
      str += tmp.info + "  ";
      tmp = tmp.next;
    }
    return str+"]";
  }

  public boolean find(T e) {       //;
    if(isEmpty())
      return false;
    SLLNode<T> tmp = head;
    while(tmp != null){
      if(tmp.info.equals(e))
        return true;
      tmp = tmp.next;
    }
    return false;
  }

  /* -------- Example: -------- */

  public int size(){

    if(head == null)
      return 0;

    int count = 0;
    SLLNode<T> p = head;
    while(p != null) {
      count++;
      p = p.next;
    }

    return count;
  }

  //  Please write the methods here:


  /* -------- Task 1: A-------- */
  public T get(int idx) {
    if (isEmpty() || idx < 0 || idx >= size()) return null;
    if (idx == size() - 1) return tail.info;

    int i = 0;
    SLLNode<T> temp = head;
    while (temp.next != null) {
      if (i == idx) return temp.info;
      temp = temp.next;
      i++;
    }
    return null;
  }


  /* -------- Task 1: B-------- */
  public boolean insert(int idx, T newE) {
    if (idx < 0 || idx > size()) return false;
    SLLNode<T> e = new SLLNode<T>(newE);
    if (idx == 0) {
      e.next = head;
      head = e;
      return true;
    } else if (idx == size()) {
      tail.next = e;
      tail = e;
      return true;
    } else {
      SLLNode<T> temp = head;
      int i = 0;
      while (temp.next != null) {
        if (i == idx-1) {
          e.next = temp.next;
          temp.next = e;
          return true;
        }
        temp = temp.next;
        i++;
      }
    }
    return false;
  }

  /* -------- Task 1: C-------- */
  public boolean delete(int idx) {
    if (idx < 0 || idx >= size() || isEmpty()) return false;
    if (idx == 0) {
      head = head.next;
      return true;
    } else {
      SLLNode<T> temp = head;
      int i = 0;
      while (temp.next != null) {
        if (temp.next.next == null) tail = temp.next;
        if (i == idx-1) {
          temp.next = temp.next.next;
          return true;
        }
        temp = temp.next;
        i++;
      }
    }
    return false;
  }

  

  public boolean equals(SLL<T> list) {
    SLLNode<T> a = list.head;
    SLLNode<T> b = head;

    while (a.next != null && b.next != null) {
      if (a.info != b.info) return false;
      a = a.next;
      b = b.next;
    }
    return a.next == b.next;
  }

  public Object[] findMiddle() {
    Object[] result = (Object[]) new Object[2];
    SLLNode<T> mid = head;
    SLLNode<T> iterator = head.next;

    while (iterator != null && iterator.next != null) {
      iterator = iterator.next.next;
      mid = mid.next;
    }

    result[0] = mid.info;
    if (iterator != null) result[1] = mid.next.info;

    return result;
  }

  private SLLNode<T> getMiddle(SLLNode<T> head) {
    SLLNode<T> slow = head;
    SLLNode<T> fast = head.next;

    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }

  private SLLNode<T> sorted(SLLNode<T> a, SLLNode<T> b) {
    if (a == null) return b;
    if (b == null) return a;

    SLLNode<T> result;

    if (a.compareTo(b) <= 0) {
      result = a;
      result.next = sorted(a.next, b);
    } else {
      result = b;
      result.next = sorted(a, b.next);
    }

    return result;
  }

  public void sort() {
    this.head = sortHelper(this.head);
  }

  public SLLNode<T> sortHelper(SLLNode<T> node) {
    if (node == null || node.next == null)
      return node;

    SLLNode<T> mid1 = getMiddle(node);
    SLLNode<T> mid2 = mid1.next;

    mid1.next = null;

    SLLNode<T> left = sortHelper(node);
    SLLNode<T> right = sortHelper(mid2);

    return sorted(left, right);
  }


}
