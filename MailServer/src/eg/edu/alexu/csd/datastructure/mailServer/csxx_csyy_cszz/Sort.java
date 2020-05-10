package eg.edu.alexu.csd.datastructure.mailServer.csxx_csyy_cszz;

import java.util.Date;

import eg.edu.alexu.csd.datastructure.linkedList.csxx_csyy.DoubleLinkedList;
import eg.edu.alexu.csd.datastructure.linkedList.csxx_csyy.DoubleLinkedList.Dnode;
import eg.edu.alexu.csd.datastructure.mailServer.ISort;
import eg.edu.alexu.csd.datastructure.stack.cs18011111.Stack;

public class Sort implements ISort {

	public String type;

	// public String type = "";

	// end = size - 1
	// start = 0 ;

	class pair {
		private final int x;
		private final int y;

		pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getx() {
			return x;
		}

		public int gety() {
			return y;
		}
	}

	/**
	 * partition date used in quick sort l is DLL of date only storing Date class
	 * 
	 * @param l
	 * @param start
	 * @param end
	 * @return pindex
	 */
	public static int partitiondate(DoubleLinkedList l, DoubleLinkedList m, int start, int end) {

		Date pivot = (Date) l.get(end);
		int pindex = start;
		Dnode temp = l.getnode(0);
		int count = start;
		while (temp.next != null) {
			Date comp = (Date) l.get(count);
			if (comp.compareTo(pivot) < 0 || comp.compareTo(pivot) == 0) {
				m.swapnodes(m, count, pindex); // to sort the whole email,we should swap the mail Dll
				pindex++;
			}
			count++;
			temp = temp.next;
		}
		m.swapnodes(m, pindex, end);
		return pindex;
	}

	public static int partitionpriority(DoubleLinkedList l, int start, int end) {
		Mail m = (Mail) l.get(end);
		int pivot = m.priority;
		int pindex = start;
		Dnode temp = l.getnode(0);
		int count = start;
		while (temp.next != null) {

			Mail m1 = (Mail) l.get(count);
			if (m1.priority <= pivot) {
				l.swapnodes(l, count, pindex); // to sort the whole email,we should swap the mail Dll
				pindex++;
			}
			count++;
			temp = temp.next;
		}
		l.swapnodes(l, pindex, end);
		return pindex;
	}

	public void sortdate(DoubleLinkedList a, DoubleLinkedList m) {
		int start = 0;
		int end = a.size() - 1;
		Stack s = new Stack();
		s.push(new pair(start, end));
		while (!s.isEmpty()) {
			pair p = (pair) s.peek();
			start = p.getx();
			end = p.gety();
			s.pop();
			int pivot = partitiondate(a, m, start, end);
			if (pivot - 1 > start) {
				s.push(new pair(start, pivot - 1));
			}
			if (pivot + 1 < end) {
				s.push(new pair(pivot + 1, end));
			}
		}
	}

	public void sortpriority(DoubleLinkedList a) {
		int start = 0;
		int end = a.size() - 1;
		Stack s = new Stack();
		s.push(new pair(start, end));
		while (!s.isEmpty()) {
			pair p = (pair) s.peek();
			start = p.getx();
			end = p.gety();
			s.pop();
			int pivot = partitionpriority(a, start, end);
			if (pivot - 1 > start) {
				s.push(new pair(start, pivot - 1));
			}
			if (pivot + 1 < end) {
				s.push(new pair(pivot + 1, end));
			}
		}
	}

	public static int partitionsender(DoubleLinkedList l, int start, int end) {
		Mail m = (Mail) l.get(end);
		String pivot = m.getSender();
		int pindex = start;
		Dnode temp = l.getnode(0);
		int count = start;
		while (temp.next != null) {

			Mail m1 = (Mail) l.get(count);
			if (m1.getSender().equals(pivot) || m1.getSender().compareTo(pivot) < 0) {
				l.swapnodes(l, count, pindex); // to sort the whole email,we should swap the mail Dll
				pindex++;
			}
			count++;
			temp = temp.next;
		}
		l.swapnodes(l, pindex, end);
		return pindex;
	}

	public void sortsender(DoubleLinkedList a) {
		int start = 0;
		int end = a.size() - 1;
		Stack s = new Stack();
		s.push(new pair(start, end));
		while (!s.isEmpty()) {
			pair p = (pair) s.peek();
			start = p.getx();
			end = p.gety();
			s.pop();
			int pivot = partitionsender(a, start, end);
			if (pivot - 1 > start) {
				s.push(new pair(start, pivot - 1));
			}
			if (pivot + 1 < end) {
				s.push(new pair(pivot + 1, end));
			}
		}
	}

	public static int partitionsubject(DoubleLinkedList l, int start, int end) {
		Mail m = (Mail) l.get(end);
		String pivot = m.getSubject();
		int pindex = start;
		Dnode temp = l.getnode(0);
		int count = start;
		while (temp.next != null) {

			Mail m1 = (Mail) l.get(count);
			if (m1.getSubject().equals(pivot) || m1.getSubject().compareTo(pivot) < 0) {
				l.swapnodes(l, count, pindex); // to sort the whole email,we should swap the mail Dll
				pindex++;
			}
			count++;
			temp = temp.next;
		}
		l.swapnodes(l, pindex, end);
		return pindex;
	}

	public void sortsubject(DoubleLinkedList a) {
		int start = 0;
		int end = a.size() - 1;
		Stack s = new Stack();
		s.push(new pair(start, end));
		while (!s.isEmpty()) {
			pair p = (pair) s.peek();
			start = p.getx();
			end = p.gety();
			s.pop();
			int pivot = partitionsubject(a, start, end);
			if (pivot - 1 > start) {
				s.push(new pair(start, pivot - 1));
			}
			if (pivot + 1 < end) {
				s.push(new pair(pivot + 1, end));
			} else {
				break;
			}
		}
	}

}
