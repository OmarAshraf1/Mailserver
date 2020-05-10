package eg.edu.alexu.csd.datastructure.mailServer;

public class QueueLL implements ILinkedBased,IQueue {
	
	ILinkedList q = (ILinkedList) new singlelinkedlist();
	
	public void enqueue(Object item) {
		
		q.add(item);
	}
	
	public Object dequeue() {
		
		Object x = q.get(0) ; 
		q.remove(0);
		
		return x ;
	}
	
	public boolean isEmpty() {
		
		return q.isEmpty() ;
	}
	
	public int size() {
		
		return q.size() ;
	}
	

	

}
