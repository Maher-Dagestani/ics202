package DS;

class Pair<K, V> {
  private K k;
  private V v;
  private char status;

  public Pair(K k, V v) {
    this.k = k;
    this.v = v;
  }

  public Pair(K k, V v, char status) {
    this.k = k;
    this.v = v;
    this.status = status;
  }

  public K getKey() { return this.k; }
  public V getVal() { return this.v; }
  public char getStatus() { return this.status; }

  public void setKey(K k) { this.k = k; }
  public void setVal(V v) { this.v = v; }
  public void setStatus(char status) { this.status = status; }
}
