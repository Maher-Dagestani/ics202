package DS;


public class SLLNode<T extends Comparable<T>> implements Comparable<SLLNode<T>> {

    public T info;
    public SLLNode<T> next;

    public SLLNode() {
      this(null,null);
    }

    public SLLNode(T e) {
      this(e,null);
    }

    public SLLNode(T e, SLLNode<T> ptr) {
      info = e; next = ptr;
    }

    @Override
    public int compareTo(SLLNode<T> node) {
      return this.info.compareTo(node.info);
    }

  }
