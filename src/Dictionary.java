
public class Dictionary implements DictionaryADT{
	private Node[] hashmap;//EMPTY HASHMAP
	public static int numofslots;
	private int size;// number of objects in hashtable
	private Node head;//represents nodes

	public Dictionary(int capacity) {
		this.numofslots = capacity;//number of indexes
		this.hashmap = new Node[capacity];//creates table with specified indexes
		this.size = 0; //makes size 0 bevcause there are no nodes with records in them yet
		
	}
	public static int hashFunction(String key) {//hash function to convert strings to polynomial for indexes
		int hashValue = 0; 
		int stringlength = key.length();
		int numberx = 33; // x 
		for (int i = 0; i < key.length(); i++){//for loop to loop through each character
			hashValue = ( hashValue * numberx + key.charAt(i))% numofslots;
		}
		return hashValue;
		    
		    
			
	    }
			
	private int getIndex(Integer k){//function to find index 
		return k % numofslots;
		
	}
	
	@Override
	public int put(Record rec) throws DuplicatedKeyException {//use hash code from function to create index number
		int index = getIndex(hashFunction(rec.getKey()));
		Node head = hashmap[index];
		
		if (head == null) {//if no index with that string
			hashmap[index] = new Node(rec);//create new node
			Node nextNode = new Node(rec);
			
			nextNode.next = (hashmap[index]);//set linked list
			hashmap[index] = nextNode;
			size++;
			return 0;
		}
		while (head != null) {
			if (head.getValue().getKey().equals(rec.getKey())) {
				throw new DuplicatedKeyException("Duplicate Key");
			}
			head = head.getNext();//loops through list
		}
		Node nextNode2 = new Node(rec);
		
		nextNode2 .next=(hashmap[index]);
		hashmap[index] = nextNode2 ;
		size++;
		return 1;
			
		
		
		
	}

	@Override
	public void remove(String key) throws InexistentKeyException {//function to remove record from dictionary 
		int index = getIndex(hashFunction(key));//gets index 
		Node head = hashmap[index];//makes node
		Node previous = null;
		while(head!= null) {//loops through list
			if(head.key.equals(key)) { //deals with if key inside table
				break;
			}
			previous = head;//traverses linked list
			head = head.next;
		}
		if (head==null) {
			throw new InexistentKeyException("String not in dictionary");}
		size--;
		if (previous!=null) {
			previous.next = head.next;
		}
		else {
			hashmap[index] =head.next;
		}
		
		
	}
	public Record get(String key) {
		if (key ==null){
			throw new IllegalArgumentException("Key is null");
		}
		int index = getIndex(hashFunction(key));
		if (hashmap[index]!= null) {
	
		Node head = hashmap[index];
		
		while (head!= null) {
			if(head.key.equals(key)) {
				return head.getValue();// if key in dictionary
			}
			head = head.next;
		}
		}
		return null;
	}
	
	
	@Override
	public int numRecords() {
		return size;
	}
	

}
