package eg.edu.alexu.csd.datastructure.mailServer.csxx_csyy_cszz;

import java.io.IOException;
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
		MailServer m = new MailServer() ;
		DoubleLinkedList d = new DoubleLinkedList() ;

		
		Folder folder =new Folder(); 
		folder.foldername = "inbox" ;
		//DoubleLinkedList d = new DoubleLinkedList() ;
		FoldertoLL ff = new FoldertoLL() ;
		try {
			MailServer.dlf=ff.foldertoLL(folder, "ashraf_koms") ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Filter f = new Filter();
		MailServer.subject = "abc" ;
		Sort s = new Sort() ;  				//kalam hlw 2
		f.type = "subject" ;
		MailServer.sortby = null ;
		MailServer.userfold = "ashraf_koms" ;
		
		
		//Mail mail = (Mail) m.dlf.get();
		//System.out.println(mail.getSender());
		
		MailServer.currfold = "inbox" ;
		MailServer.sort =null ;
	
		MailServer.filter = "subject" ; 
		m.searchfor = "j" ;
		
		
		
		
		Mail ma = new Mail() ;
		ma.setSubject("zbc") ;
		ma.setDate("20/11/2018");
		ma.setSender("omar");
		ma.priority = 1 ;
		Mail ma1 = new Mail() ;
		ma1.setSubject("adds") ;
		ma1.setDate("22/11/2019");
		ma1.setSender("hamo");
		ma1.priority = 2 ;  
		Mail ma2 = new Mail() ;
		ma2.setSubject("addd") ;
		ma2.setDate("22/11/2018");
		ma2.setSender("fada2i");
		ma2.priority = 1 ;
		d.add(ma);
		d.add(ma1);
		d.add(ma2);
		
		//Filter f = new Filter() ;
		//s.sortpriority(d);
		//d=f.searchpriority(d, 1) ;
		//d.swapnodes(d, 0, 1);
		
		//m.setViewingOptions(folder, f, s);

		//m.setViewingOptions(folder, f, null);
		
		Mail mailss[] = (Mail[]) m.listEmails(1);

			
		//Mail mm = (Mail) d.get(0) ;
		
			
		System.out.println(mailss[0].getSender()); 
		
		
			
		
		
		
		
		
		/**
		m.filter = "sender" ;
		m.sort = null ;                 //kalam hlw rakm 1
		m.sender = "omar" ;
		m.setViewingOptions(folder, , null);
		**/
		
		
		
		
	}

}
