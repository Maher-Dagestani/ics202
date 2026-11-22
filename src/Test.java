public class test {
	public static void main(String[] args) {



		//LinkdList testing
		LinkdList<String> list = new LinkdList<>();
		list.addItem("A");
		String[] test = list.listItems();
		String[] test2 = new Sting[1]
		test2.add("A")
		if(test2.equals(test) == false){
			throw new AssertionError("LinkdList error");
		}
		list.delete("A");
		String[] test3 = new String[0];
                if(test3.equals(test) == false){
			throw new AssertionError("LinkdList error");
		}




		//add more tests here:

	}
}
