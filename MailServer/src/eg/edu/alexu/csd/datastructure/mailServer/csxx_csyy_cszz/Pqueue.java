package eg.edu.alexu.csd.datastructure.mailServer.csxx_csyy_cszz;

import eg.edu.alexu.csd.datastructure.linkedList.csxx_csyy.singlelinkedlist;
import eg.edu.alexu.csd.datastructure.mailServer.IPriorityQueue;

public class Pqueue implements IPriorityQueue {
	public class Pq {
		
		Object element;
		
		int key;

		
		public Pq(final Object value, final int pt) {
			// TODO Auto-generated constructor stub
			element = value;
			key = pt;
		}
	}
	
	singlelinkedlist l = new singlelinkedlist() ;
	int size=0 ;
	
	@Override
	public void insert(Object item, int key) { 
		// TODO Auto-generated method stub
				if (key < 1) {
					throw new RuntimeException();
				}
				Pq obj = new Pq(item, key);
				Pq q = new Pq(0, 0);
				if (!l.isEmpty()) {
					q = (Pq) l.get(l.size() - 1);
				}
				if (l.isEmpty()) {
					l.add(obj);
				} else if (key > q.key) {
					l.add(obj);
				} else {
					for (int i = 0; i < l.size(); i++) {
						Pq carry = (Pq) l.get(i);
						if (key < carry.key) {
							l.add(i, obj);
							break;
						}
					}
				}
	}
	@Override
	public Object removeMin() {
		Pq q = (Pq) l.get(0);
		l.remove(0);
		return q.element;
	}
	@Override
	public Object min() {
		Pq q = (Pq) l.get(0);
		return q.element;
	}
	@Override
	public boolean isEmpty() {
		
		return (l.isEmpty()) ;
	}
	@Override
	public int size() {
		return l.size() ;
	}
	

}
