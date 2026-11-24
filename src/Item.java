public class Item {
  private String id, name;


  public Item(String id, String  name){
    this.id = id;
    this.name = name;
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
  punlic String toString(){
    return this.id + ":" +  this.name;
  }
}
