public class HashTableTester {
	public static void main(String[] args) {
		
		//Key type is now Integer, Value type is now String
		//Key should be a type that implements Comparable Interface
		HashTable<Integer, String> myTable = new HashTable<Integer, String>() ; 
		
		//Inserting Key, Value Pairs
		myTable.insert(10, "This is number 10");
		myTable.insert(9, "This is number 9");
		myTable.insert(7, "This is number 7");
		myTable.insert(2, "This is number 2");
		myTable.insert(3, "This is number 3");
		myTable.insert(1, "This is number 1");
		myTable.insert(0, "This is number 0");
		myTable.insert(4, "This is number 4");
		myTable.insert(5, "This is number 5");
		myTable.insert(6, "This is number 6");
		myTable.insert(8, "This is number 8");
		System.out.println();
		
		//Searching Known Keys and print the corresponding Value
		for (int i =0; i<=10; i++){
			System.out.println(myTable.search(i));
		}
		System.out.println();
		
		//Searching Unknown Key
		System.out.println(myTable.search(18));
		System.out.println();
		
		//Inserting a key that is already inserted
		myTable.insert(8, "Inserting again"); //Will give the message "Key already exists"
		System.out.println();
		
		//Printing All LinkedLists
		//LinkedLists are sorted with respect to their keys
		myTable.displayLinkedLists();
		System.out.println();
		
		//Deleting all keys
		myTable.delete(0);
		myTable.delete(5);
		myTable.delete(10);
		myTable.delete(3);
		myTable.delete(3); //Should say "Key not found" as we deleted this key in the last step
		System.out.println();
		
		//Printing All LinkedLists
		myTable.displayLinkedLists();
		System.out.println();
		
		//Using strings as keys here
		HashTable<String, Integer> myTable2 = new HashTable<String, Integer>() ;
		myTable2.insert("A", 1);
		myTable2.insert("C", 3);
		myTable2.insert("B", 2);
		myTable2.insert("ABCD", 6);
		myTable2.insert("DE", 8);
		myTable2.insert("ABC", 44);
		myTable2.insert("GH", 7);
		myTable2.delete("D"); //not found
		System.out.println();
		
		myTable2.displayLinkedLists();

	}
	


}
