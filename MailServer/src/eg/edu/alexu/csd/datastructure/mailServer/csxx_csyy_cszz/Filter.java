package eg.edu.alexu.csd.datastructure.mailServer.csxx_csyy_cszz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import eg.edu.alexu.csd.datastructure.linkedList.csxx_csyy.DoubleLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.IFilter;
import eg.edu.alexu.csd.datastructure.stack.cs18011111.Stack;

public class Filter implements IFilter {
	
	public String type ;

	/**
	 * Method to search for sender
	 * @param tosearch
	 * @param sender
	 * @return Dll of mails of targeted sender
	 */
	public DoubleLinkedList searchsender(DoubleLinkedList tosearch , String sender) {
		
		DoubleLinkedList result = new DoubleLinkedList() ;    //DLL stores the result 
		Mail m = new Mail() ;    // to access Mail objects 
		Stack s = new Stack() ;
		int l = 0 ;
		int r = tosearch.size() - 1 ;
		int mid = l + (r-l) / 2 ;
		s.push(tosearch.get(mid));
		
		while(!s.isEmpty() && l <= r) {
			m = (Mail) s.pop() ;
			if(m.getSender().equals(sender)) {
				result.add(m);
				mid = l + (r-l) / 2 ;
				int mid2 = mid ;
				if(mid-1 >= 0) {
					while((mid-1) >= 0) {
						Mail m2 = (Mail) tosearch.get(mid-1) ;
						if(m2.getSender().equals(sender)) {
							result.add(m2);
							mid-- ;
						}
						else {
							break ;
						}
					}
					
				}
				mid = mid2 ;
				if(mid + 1 <= r) {
					while(mid+1 <= r) {
						Mail m3 = (Mail) tosearch.get(mid+1) ;
						if(m3.getSender().equals(sender)) {
							result.add(m3);
							mid++ ;
						}
						else {
							break ;
						}
					}
					
				}
				break ;
				
			}
			else if(l <= r ) {
				if(m.getSender().compareTo(sender) > 0) {
					r = mid - 1 ;
					mid = l + (r-l) / 2 ;
					s.push(tosearch.get(mid));
				}
				else {
					l = mid + 1 ;
					mid =  l + (r-l) / 2 ;
					s.push(tosearch.get(mid));
				}
			}
			else {
				break ;
			}
			
		}
		return result ;
	}
	/**
	 * Method to search for date
	 * @param tosearch
	 * @param date
	 * @return Dll of mails of targeted dates
	 */
	public DoubleLinkedList searchdate(DoubleLinkedList tosearch , String date) {
		DoubleLinkedList result = new DoubleLinkedList() ;    //DLL stores the result 
		Mail m = new Mail() ;    // to access Mail objects 
		Stack s = new Stack() ;
		int l = 0 ;
		int r = tosearch.size() - 1 ;
		int mid = l + (r-l) / 2 ;
		s.push(tosearch.get(mid));
		// to be used to compare dates
		SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy") ;
		Date dsearch = null , dtarget = null ;
		try {
			dtarget = d.parse(date) ;
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		while(!s.isEmpty() && l <= r) {
			m = (Mail) s.pop() ;
			try {
				dsearch = d.parse(m.getDate()) ;
			} catch (ParseException e) {
				e.printStackTrace();
			} 
			if(m.getDate().equals(date)) {
				result.add(m);
				mid = l + (r-l) / 2 ;
				int mid2 = mid ;
				if(mid-1 >= 0) {
					while((mid-1) >= 0) {
						Mail m2 = (Mail) tosearch.get(mid-1) ;
						if(m2.getDate().equals(date)) {
							result.add(m2);
							mid-- ;
						}
						else {
							break ;
						}
					}
					
				}
				mid = mid2 ;
				if(mid + 1 <= r) {
					while(mid+1 <= r) {
						Mail m3 = (Mail) tosearch.get(mid+1) ;
						if(m3.getDate().equals(date)) {
							result.add(m3);
							mid++ ;
						}
						else {
							break ;
						}
					}
					
				}
				break ;
				
			}
			
			else if(l <= r ) {
				if(dsearch.compareTo(dtarget) > 0) {
					r = mid - 1 ;
					mid = l + (r-l) / 2 ;
					s.push(tosearch.get(mid));
				}
				else {
					l = mid + 1 ;
					mid =  l + (r-l) / 2 ;
					s.push(tosearch.get(mid));
				}
			}
			else {
				break ;
			}
			
		}
		return result ;
		
	}
	/**
	 * Method to search subjects
	 * @param tosearch
	 * @param subject
	 * @return Dll of mails of targeted subjects
	 */
	public DoubleLinkedList searchsubject(DoubleLinkedList tosearch , String subject) {
		
		DoubleLinkedList result = new DoubleLinkedList() ;    //DLL stores the result 
		Mail m = new Mail() ;    // to access Mail objects 
		Stack s = new Stack() ;
		int l = 0 ;
		int r = tosearch.size() - 1 ;
		int mid = l + (r-l) / 2 ;
		s.push(tosearch.get(mid));
		
		while(!s.isEmpty() && l <= r) {
			m = (Mail) s.pop() ;
			if(m.getSubject().equals(subject)) {
				result.add(m);
				mid = l + (r-l) / 2 ;
				int mid2 = mid ;
				if(mid-1 >= 0) {
					while((mid-1) >= 0) {
						Mail m2 = (Mail) tosearch.get(mid-1) ;
						if(m2.getSubject().equals(subject)) {
							result.add(m2);
							mid-- ;
						}
						else {
							break ;
						}
					}
					
				}
				mid = mid2 ;
				if(mid + 1 <= r) {
					while(mid+1 <= r) {
						Mail m3 = (Mail) tosearch.get(mid+1) ;
						if(m3.getSubject().equals(subject)) {
							result.add(m3);
							mid++ ;
						}
						else {
							break ;
						}
					}
					
				}
				break ;
				
			}
			else if(l <= r ) {
				if(m.getSubject().compareTo(subject) > 0) {
					r = mid - 1 ;
					mid = l + (r-l) / 2 ;
					s.push(tosearch.get(mid));
				}
				else {
					l = mid + 1 ;
					mid =  l + (r-l) / 2 ;
					s.push(tosearch.get(mid));
				}
			}
			else {
				break ;
			}
			
		}
		return result ;
	}
	/**
	 * Method to search specific priority
	 * @param tosearch
	 * @param priority
	 * @return Dll of mails of targeted priority
	 */
	public DoubleLinkedList searchpriority(DoubleLinkedList tosearch , int priority) {
		
		DoubleLinkedList result = new DoubleLinkedList() ;    //DLL stores the result 
		Mail m = new Mail() ;    // to access Mail objects 
		Stack s = new Stack() ;
		int l = 0 ;
		int r = tosearch.size() - 1 ;
		int mid = l + (r-l) / 2 ;
		s.push(tosearch.get(mid));
		
		while(!s.isEmpty() && l <= r) {
			m = (Mail) s.pop() ;
			if(m.priority == priority) {
				result.add(m);
				mid = l + (r-l) / 2 ;
				int mid2 = mid ;
				if(mid-1 >= 0) {
					while((mid-1) >= 0) {
						Mail m2 = (Mail) tosearch.get(mid-1) ;
						if(m2.priority == priority) {
							result.add(m2);
							mid-- ;
						}
						else {
							break ;
						}
					}
					
				}
				mid = mid2 ;
				if(mid + 1 <= r) {
					while(mid+1 <= r) {
						Mail m3 = (Mail) tosearch.get(mid+1) ;
						if(m3.priority == priority) {
							result.add(m3);
							mid++ ;
						}
						else {
							break ;
						}
					}
					
				}
				break ;
				
			}
			else if(l <= r ) {
				if(m.priority > priority) {
					r = mid - 1 ;
					mid = l + (r-l) / 2 ;
					s.push(tosearch.get(mid));
				}
				else {
					l = mid + 1 ;
					mid =  l + (r-l) / 2 ;
					s.push(tosearch.get(mid));
				}
			}
			else {
				break ;
			}
			
		}
		return result ;
	}
	
	

}