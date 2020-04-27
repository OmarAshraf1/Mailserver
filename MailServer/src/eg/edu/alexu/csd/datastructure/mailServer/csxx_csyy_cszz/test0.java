package eg.edu.alexu.csd.datastructure.mailServer.csxx_csyy_cszz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import eg.edu.alexu.csd.datastructure.linkedList.csxx_csyy.DoubleLinkedList;
import eg.edu.alexu.csd.datastructure.stack.cs18011111.Stack;

public abstract class test0 {
	static class a{
		Date d ;
		int x ;
		
	}
	
	public static void main(String[] args) throws ParseException {
		a k = new a() ; //first object
		
		a k1 = new a() ; //2nd object
		
		DoubleLinkedList m = new DoubleLinkedList() ;
		DoubleLinkedList l = new DoubleLinkedList() ;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse("2009-12-31");
        Date date2 = sdf.parse("2010-01-31");
        k.d = date2 ;            
        k.x = 1 ;    
        l.add(k.d);    // add in date LL
        m.add(String.valueOf(k));      // add in integer and date LL
        k1.d = date1 ;
        l.add(k1.d);
        k1.x = 10 ;
        m.add(String.valueOf(k1));
        System.out.println(String.valueOf(k1));   // print before sorting
		Sort s = new Sort() ;
		s.sortdate(l,m);       //sort by date
		//System.out.println();	//print after sorting
		
		
		
	}

}
