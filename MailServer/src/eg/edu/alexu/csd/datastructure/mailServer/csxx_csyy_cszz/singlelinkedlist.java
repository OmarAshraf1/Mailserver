package eg.edu.alexu.csd.datastructure.linkedList.csxx_csyy;


import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList ;


public class singlelinkedlist implements ILinkedList {
	public Node head ;
	int length = 0 ;
	
	    public static class Node {
	    	Object data ;
	    public Node next ;
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
			return true ;
		}
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
				return true ;
			}
			temp = temp.next ;
		}
		return false ;
	}
	
	
	public void print() {
		Node temp = head ;
		while(temp != null) {
			System.out.println(temp.data);
			temp = temp.next ;
		}
	}
	/**
	 * Method to return a node to be used in MailServer 	
	 * @param index
	 * @return Node
	 */	
	public Node getnode(int index) {
		
		Node p = head ;
		int count = 0 ;
		
		 while(p != null) {
			 
			if(index == count) {
				return p ;
					}
			p=p.next ;
			count ++ ;
	}
		 throw new RuntimeException();
			
		
	}
	
	/**
	 * Method to swap nodes used in Mail server project
	 * @param l
	 * @param start
	 * @param end
	 */
	
	public void swapnodes(singlelinkedlist l , int start ,int end) {
		if(start == end) {
			return ;
		}
		
		Object temp = l.get(start);
		l.getnode(start).data = l.get(end) ;
		l.getnode(end).data = temp ;
		
	}
	public Node reverse(Node head) {
		if(head == null) { 
            return head; 
        } 
  
        if(head.next == null) { 
            return head; 
        } 
  
        Node newHeadNode = reverse(head.next); 
  
        head.next.next = head; 
        head.next = null; 
  
        return newHeadNode; 
    } 
    
	

	
	
	
		
		
	}
	
	


