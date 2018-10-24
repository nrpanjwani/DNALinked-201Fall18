
public class LinkStrand implements IDnaStrand{
	private class Node {
		String info;
		Node next;
		public Node(String s) {
		   info = s;
		   next = null;
		}
	}
	private Node myFirst,myLast;
	private long mySize;
	private int myAppends;
	
	// Constructor with String parameter and next field
	public LinkStrand(String s) {
		initialize(s);
	}
	
	// Default constructor
	public LinkStrand() {
		this("");
	}
	
	@Override
	public long size() {
		// Returns number of base pairs in LinkStrand
		return mySize;
	}

	@Override
	public void initialize(String source) {
		// Initializes new LinkStrand object as Node and assigns to myFirst
		myFirst = new Node(source);
		myLast = myFirst;
		mySize = myFirst.info.length();
	}

	@Override
	public IDnaStrand getInstance(String source) {
		// Returns a new instance of LinkStrand
		return new LinkStrand(source);
	}

	@Override
	public IDnaStrand append(String dna) {
		// Converts dna parameter to new Node and assigns it
		// as the next field of the last Node in LinkStrand,
		// reassigning myLast as the newly appended Node
		// Returns the LinkStrand in question
		myLast.next = new Node(dna);
		myLast = myLast.next;
		mySize += myLast.info.length();
		myAppends += 1;
		return this;
	}

	@Override
	public IDnaStrand reverse() {
		// Creates and returns new LinkStrand
		// with nucleotides in reverse order
		Node node = myFirst;
		LinkStrand revStrand = new LinkStrand();
		while(node.next != null) {
			StringBuilder copy = new StringBuilder(node.info);
			copy.reverse();
			Node myNext = null; // Initialize myNext as null
			if(revStrand != null) { // Null revStrand will not have myFirst field
				myNext = revStrand.myFirst;
			}
			revStrand.myFirst = new Node(copy.toString());
			myFirst.next = myNext; // Default next field is null, uses stored value
			node = node.next;
		}
		return revStrand;
	}

	@Override
	public int getAppendCount() {
		return myAppends;
	}

	@Override
	public char charAt(int index) { 
		int myIndex = index;
		if (index >= this.size()) {
			throw new IndexOutOfBoundsException();
		}
		Node list = myFirst;
		
		int count = -1;
		int dex = 0;
		while (count + list.info.length() < myIndex) {
			count += list.info.length();
			list = list.next;
		}
		dex = myIndex - count;
		if (count == -1) {
			dex = myIndex;
		}
		int myLocalIndex = dex;
		Node myCurrent = list;
		return myCurrent.info.charAt(myLocalIndex);
	}
	
	@Override
	public String toString() {
		// Uses linked list principle to create and return
		// String using StringBuilder object and its append
		// method
		Node list = myFirst;
		StringBuilder tempString = new StringBuilder();
		while(list.next != null) {
			tempString.append(list.info);
			list = list.next;
		}
		return tempString.toString();
	}
	
}
