package objects;

public class Item implements Comparable<Item> {
  private String id, name;


  public Item(String id, String  name){
    this.id = id;
    this.name = name;
  }

  public Item(String id){
    this(id, id);
  }

  public String getId(){
    return this.id;
  }

  public boolean isEqual(Item item2){
    if(item2.getId().equals(this.id)){
      return true;
    }
    else{
      return false;
    }
  }

  @Override
  public boolean equals(Object obj){
    return this.name.equals(((Item) obj).name);
  }

  @Override
  public int compareTo(Item other){
    return this.name.compareTo(other.name);
  }

  @Override
  public String toString(){
    return this.id;
  }

  public String getName(){
    return this.name;
  }
}
