package DS;

import java.util.Iterator;
import java.util.LinkedList;

class Hashmap<K, V> {

  enum Mode {
    CHAINING,
    LINEAR_PROBING,
    QUADRATIC_PROBING,
    DOUBLE_HASHING
  }

  private int size;
  private Mode mode;

  private LinkedList<Pair<K, V>>[] arr1;
  private Pair<K, V>[] arr2;

  public Hashmap(int size, Mode mode) {
    this.size = size;
    this.mode = mode;
    if (mode == Mode.CHAINING) {
      this.arr1 = new LinkedList[size];
    } else {
      this.arr2 = new Pair[size];
    }
  }

  private int hash(K key) {
    return (key.hashCode() & 0x7fffffff) % size;
  }

  private int hash2(K key) {
    return 7 - ((key.hashCode() & 0x7fffffff) % 7);
  }

  public int getIndex(K key, int i) {
    int idx = 0;
    if (mode == Mode.LINEAR_PROBING)
      idx = (hash(key)+i)%size;
    else if (mode == Mode.QUADRATIC_PROBING)
      idx = (hash(key)+i*i)%size;
    else if (mode == Mode.DOUBLE_HASHING)
      idx = (hash(key) + i * hash2(key)) % size;
    return idx;
  }


  public void put(K key, V value) {
    if (mode == Mode.CHAINING) {
      int idx = hash(key);
      if (arr1[idx] == null) {
        arr1[idx] = new LinkedList<Pair<K, V>>();
        arr1[idx].add(new Pair<K, V>(key, value));
      } else {
        boolean isUpdate = false;
        for (Pair<K, V> p : arr1[idx]) {
          if (p.getKey().equals(key)) {
            p.setVal(value);
            isUpdate = true;
            break;
          }
        }
        if (!isUpdate) {
          arr1[idx].add(new Pair<K, V>(key, value));
        }
      }
    } else {
      int firstDeleted = -1;
      for (int i = 0; i < size; i++) {
        int idx = getIndex(key, i);
        if (arr2[idx] == null) {
          int target = (firstDeleted != -1) ? firstDeleted : idx;
          arr2[target] = new Pair<K, V>(key, value, 'O');
          return;
        }
        if (arr2[idx].getStatus() == 'D') {
          if (firstDeleted == -1) firstDeleted = idx;
        } else if (arr2[idx].getKey().equals(key)) {
          arr2[idx].setVal(value);
          return;
        }
      }
      if (firstDeleted != -1)
        arr2[firstDeleted] = new Pair<K, V>(key, value, 'O');
      else
        throw new IllegalStateException("Hash table is full");
    }
  }

  // Search key
  public V get(K key) {
    if (mode == Mode.CHAINING) {
      int idx = hash(key);
      if (arr1[idx] != null) {
        for (Pair<K, V> p : arr1[idx]) {
          if (p.getKey().equals(key)) return p.getVal();
        }
      }
    } else {
      for (int i = 0; i < size; i++) {
        int idx = getIndex(key, i);
        if (arr2[idx] == null) break;
        if (arr2[idx].getStatus() == 'O' && arr2[idx].getKey().equals(key))
          return arr2[idx].getVal();
      }
    }


    return null;
  }

  // Delete key
  public void remove(K key) {
    if (mode == Mode.CHAINING) {
      int idx = hash(key);
      if (arr1[idx] != null) {
        for (Pair<K, V> p : arr1[idx]) {
          if (p.getKey().equals(key)) {
            arr1[idx].remove(p);
            break;
          }
        }
      }
    } else {
      for (int i = 0; i < size; i++) {
        int idx = getIndex(key, i);
        if (arr2[idx] == null) break;
        if (arr2[idx].getStatus() == 'O' && arr2[idx].getKey().equals(key)) {
          arr2[idx].setStatus('D');
          return;
        }
      }
    }
  }



  // Display table
  public void display() {
    System.out.println("\nMode: " + mode);
    for (int i = 0; i < size; i++) {
      System.out.print(i + " -> ");
      if (mode == Mode.CHAINING) {
        if (arr1[i] != null)
          for (Pair<K, V> p : arr1[i])
            System.out.print("(" + p.getKey() + "," + p.getVal() + ") ");
      } else {
        if (arr2[i] == null) System.out.print("EMPTY");
        else if (arr2[i].getStatus() == 'D') System.out.print("DELETED");
        else System.out.print("OCCUPIED(" + arr2[i].getKey() + "," + arr2[i].getVal() + ")");
      }
      System.out.println();
    }
  }

}
