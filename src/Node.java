
public class Node {
	public String key;
	public Record value;
	public Node next;
	
	public Node(Record value) {
		this.key = value.getKey();
		this.value = value;
	}
	public Record getValue() {
		return value;
	}
	public Node getNext() {
		return this.next;
	}
	
}
