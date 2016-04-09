@SuppressWarnings({"unchecked"})
public class HashTable<Key extends Comparable<Key>,Value>{
	
	/**
	 * The TABLE_SIZE is ideally a prime number
	 */
	private final int TABLE_SIZE = 5; 
	private LinkedList<Data<Key,Value>> hashtable[] ;
	
	public HashTable (){
		hashtable = (LinkedList<Data<Key,Value>>[]) new LinkedList[TABLE_SIZE];
	}
	
	/**
	 * Function inserts key,value such that linkedlist in each bucket remains sorted
	 * If the key already exists, it prints "Key already exists" and returns
	 */
	public void insert(Key key, Value value){
		int index = hash(key);
		if (hashtable[index] == null){
			hashtable[index] = new LinkedList<Data<Key,Value>>();
		}
		LinkedList<Data<Key,Value>> list = hashtable[index];
		list.insert(new Data<Key,Value>(key,value)); 
		
	}
	
	/**
	 * Function will return value if key is found, 
	 * Otherwise will return null 
	 * @param key = key
	 * @return value or null
	 */
	public Value search(Key key){
		int index = hash(key);
		if (hashtable[index] == null){
			return null;
		}
		LinkedList<Data<Key,Value>> list = hashtable[index];
		Data<Key,Value> data = new Data<Key,Value>(key,null);
		Data<Key,Value> answerData = list.search(data);
		if (answerData == null){
			System.out.print("Key not found i.e ");
			return null;
		}
		else 
			return answerData.getValue();
	}
	
	/**
	 * Searches for a key in the array of linkedlists and deletes it if found
	 * @param key
	 */
	public void delete (Key key){
		int index = hash(key);
		if (hashtable[index] == null){
			System.out.println("Key not found");
			return ;
		}
		LinkedList<Data<Key,Value>> list = hashtable[index];
		Data<Key,Value> data = new Data<Key,Value>(key,null);
		list.delete(data);
	}
	
	/**
	 * Extra utility to print the array of linkedLists
	 */
	public void displayLinkedLists(){
		for (LinkedList<Data<Key,Value>> list : hashtable){
			if (list !=null){
				LLNode<Data<Key,Value>> current = list.getHead();
				while (current!=null){
					System.out.print(current.getData().getKey()+"::"+current.getData().getValue()+" --> ");
					current = current.getNext();
				}
				System.out.println();
			}
		}
	}
	
	/**
	 * The hash function behind this HashTable. 
	 * This may not be the best hash function.
	 * @param key
	 * @return an int value i.e the index
	 */
	private int hash(Key key){
		String str = key.toString();
		int salt = 7;
		int hash = salt; 
		for (int i=0; i<str.length();i++){
			hash = hash*101 + str.charAt(i);
		}

		int index = hash % TABLE_SIZE;
		return index;
	}
}

class Data<Key extends Comparable<Key>,Value> implements Comparable<Data<Key,Value>>{
	private Key key;
	private Value value;
	
	public Data (Key key, Value value){
		this.key = key;
		this.value = value;
	}

	public int compareTo(Data<Key,Value> data) {
		return ((Comparable<Key>)key).compareTo(data.key);
	}
	
	public Key getKey(){
		return key;
	}
	public Value getValue(){
		return value;
	}
	
	
}

@SuppressWarnings("hiding")
class LLNode<Data>{
	private Data data;
	private LLNode<Data> next;
	
	LLNode(Data data){
		this.data = data;
		this.next = null;
	}
	
	public Data getData(){
		return data;
	}
	public LLNode<Data> getNext(){
		return next;
	}
	
	public void setNext(LLNode<Data> data){
		this.next=data;
	}
}

@SuppressWarnings({"hiding","unchecked"})
class LinkedList<Data> {
	
	private LLNode<Data> head;  
	
	public void insert(Data data){
		if (head==null){
			head = new LLNode<Data>(data);
			System.out.println("Key inserted");
			return;
		}
		
		LLNode<Data> current = head;
		LLNode<Data> previous = current;
		while (current != null){
			if (((Comparable<Data>)current.getData()).compareTo(data) < 0){
				if (current.getNext()==null){
					current.setNext(new LLNode<Data>(data));
					System.out.println("Key inserted");
					return;
				} else{
					previous = current;
					current = current.getNext();
				}
			} else if (((Comparable<Data>)current.getData()).compareTo(data) == 0){
				System.out.println("Key already exists");
				return;
			} else{
				if (current==head){
					current=new LLNode<Data>(data);
					current.setNext(head);
					head=current;
				} else{
					previous.setNext(new LLNode<Data>(data));
					previous.getNext().setNext(current);
				}
				System.out.println("Key inserted");
				return;
			}
		}
	}
	
	public Data search (Data data){
		LLNode<Data> current=head;
		while (current != null){
			if (((Comparable<Data>)current.getData()).compareTo(data) < 0){
				current = current.getNext();
			} else if (((Comparable<Data>)current.getData()).compareTo(data) == 0){
				return current.getData();
			} else{
				break;
			}
		}
		return null;
	}
	
	public void delete(Data data){
		if (head==null){
			System.out.println("Key not found");
			return;
		}
		LLNode<Data> current = head;
		LLNode<Data> previous = current;
		while (current != null){
			if (((Comparable<Data>)current.getData()).compareTo(data) < 0){
				previous = current;
				current = current.getNext();
			} else if (((Comparable<Data>)current.getData()).compareTo(data) == 0){
				if(current==head){
					head=head.getNext();
				} else{
					previous.setNext(current.getNext());
				}
				System.out.println("Key Deleted");
				return;
			} else{
				System.out.println("Key not found");
				return;
			}
		}
		System.out.println("Key not found");
		
	}
	
	public LLNode<Data> getHead(){
		return head;
	}
}
