package eg.edu.alexu.csd.datastructure.mailServer;

public class singlelinkedlist implements ILinkedList {
	Node head ;
	int length = 0 ;
	
	static class Node {
		Object data ;
		Node next ;
		Node(Object d){
			this.data = d ;
			next = null ;
		}
		
	}
	 
	
	public void add(int index, Object element) {
		int count = 0;
		Node temp = head ;
		Node newnode = new Node(element) ;
		if(index == 0) {
			newnode.next = head ;
			head = newnode ;
		}
		else if(index == length) {
			add(element) ;
		}
		else if (index >= length || index < 0) {
			 throw new RuntimeException();
		}
		else {
		while(temp != null) {
			if(count == index) {
				newnode.next = temp.next ;
				temp.next = newnode ;
				break ;
			}
			count++ ;
		}

	}
		length ++ ;
		 

		
	}
	
	
	public void add(Object element) {
		
		Node newnode = new Node(element) ;
		if(head == null) {
			head = newnode ;
		}
		
		Node p ; 
		p = head ;
		while( p.next != null ) {
			p=p.next ;	
		}
		p.next = newnode ;
		newnode.next = null ; 		//as it is the last node
		
		length ++ ;
	}
	public Object get(int index) {
		
		Node p = head ;
		int count = 0 ;
		
		while(p != null) {
			 
			if(index == count) {
				//System.out.println(p.data);
				return p.data ;
			}
			p=p.next ;
			count ++ ;
		}
		throw new RuntimeException();
			
		
	}
	
	public void set(int index, Object element) {
		int count=0 ;
		Node newnode= new Node(element) ;
		Node p = head ;
		if(index >= 0 && index < length ) {
			if(index == 0) {
				newnode.next = p.next ;
				head = newnode ;
			}
			else {
				while(p != null) {
					if(count == index - 1) {
						newnode.next = p.next.next ;
						p.next = newnode ; 
						break ;
					}
					p = p.next ;
					count++ ;
				}
			}
		}
		else {
			throw new RuntimeException();
		}
		
		
		
	}
	
	public void clear() {
		
		head = null ;
		length = 0;
	}
	
	
	public boolean isEmpty() {
		
		if(length == 0) {
			System.out.println(true);
			return true ;
		}
		System.out.println(false);
		return false ;
		
	}
	
	
	public void remove(int index) {
		
		Node temp = head ;
		if(index < 0 || index >= length ) {
			throw new RuntimeException();

		}
		
		if(index == 0) {
			head = temp.next ;
		}
		
		for(int i=0 ; temp != null && i < index-1 ; i++) {
			temp = temp.next ;
		}
		if(temp == null || temp.next == null) {
			return ;
		}
		temp.next = temp.next.next ;
		length -- ;
		
		
	}
	public int size() {
		//System.out.println(length);
		return length ;
		
	}
	
	public ILinkedList sublist(int fromIndex, int toIndex) {
		
		if( fromIndex < 0 || toIndex >= length || fromIndex > toIndex ) {
			throw new RuntimeException();

		}
		singlelinkedlist list = new singlelinkedlist() ;
		Node temp = head ;
		for(int i = 0 ; i <= toIndex ; i++) {
			
			if(i >= fromIndex && i <= toIndex  ) { 
				list.add(temp.data);
			}
			temp = temp.next ;
		}
		return list ;
		
		}
	public boolean contains(Object o) {
		Node temp = head ;
		if(o == null) {
			return false ;
		}
		while(temp != null) {
			if(temp.data == o) {
				System.out.println(true);
				return true ;
			}
			temp = temp.next ;
		}
		System.out.println(false);
		return false ;
	}
	
	
	public void print() {
		Node temp = head ;
		while(temp != null) {
			System.out.println(temp.data);
			temp = temp.next ;
		}
	}
		
	
	
	
	
		
		
	}
	
