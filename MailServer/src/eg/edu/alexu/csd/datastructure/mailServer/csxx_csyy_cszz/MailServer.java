package eg.edu.alexu.csd.datastructure.mailServer.csxx_csyy_cszz;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import eg.edu.alexu.csd.datastructure.linkedList.csxx_csyy.DoubleLinkedList;
import eg.edu.alexu.csd.datastructure.linkedList.csxx_csyy.DoubleLinkedList.Dnode;
import eg.edu.alexu.csd.datastructure.mailServer.IApp;
import eg.edu.alexu.csd.datastructure.mailServer.IContact;
import eg.edu.alexu.csd.datastructure.mailServer.IFilter;
import eg.edu.alexu.csd.datastructure.mailServer.IFolder;
import eg.edu.alexu.csd.datastructure.mailServer.ISort;

public class MailServer implements IApp  {
	public String fdate ;
	public int priority ;
	public String sender ;
	public String subject ;
	DoubleLinkedList dlf = new DoubleLinkedList();

	public void directoryCreation() throws IOException {
        File server = new File("C:\\server");
        if (!server.exists()) {
            server.mkdir();
        }
        File accounts = new File("C:\\server\\accounts");
        if (!accounts.exists()) {
            accounts.mkdir();
        }
        File emails = new File("C:\\server\\accounts\\emails.txt");
        if (!emails.exists()) {
        	emails.createNewFile();
        }
        File passwords = new File("C:\\server\\accounts\\pass.txt");
        if (!passwords.exists()) {
            passwords.createNewFile();
        }
    }
	
	public String userfold ;  //to store signed in email
    public boolean signin(String email, String password) throws IOException {
    	userfold = email ;
    	BufferedReader emailReader = new BufferedReader(new FileReader("C:\\server\\accounts\\emails.txt"));
    	BufferedReader passReader = new BufferedReader(new FileReader("C:\\server\\accounts\\pass.txt"));

    	String a = emailReader.readLine();
    	String b = passReader.readLine();
    	
    	while(a != null && b != null) {
    		if(email.equals(a)  && password.equals(b)) {
    			emailReader.close();
    			passReader.close();
    			return true;
    		}
    		else {
    			a = emailReader.readLine();
    			b = passReader.readLine();
    			continue;
    		}
    	}
    	emailReader.close();
    	passReader.close();
    	return false;
    }
	
	/**
	 * 
	 * 
	 * sign in 
	 *  
	 *  ›Ì œÌ Â⁄„· „ €Ì— static 
	 *  ⁄‘«‰ «ÕÿÂ ›Ì »«À 
	 *  » «⁄ «·sort
	 */
	
	
	
	
	
	public boolean signup(IContact contact) {
		
		//directory creation
		try {
			directoryCreation() ;
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		Contact user = (Contact) contact ;
		String name = user.email ;
		
		try (BufferedReader read = new BufferedReader(new FileReader("C:\\server" + File.separator + "accounts" + File.separator + "emails.txt"))) {
		String line = null ;
		while( (line = read.readLine()) != null) {
			if(line.equals(name)) {
				return false ;
			}
		  }
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try(PrintWriter w = new PrintWriter(new FileWriter("C:\\server" + File.separator + "accounts" + File.separator + "emails.txt",true))){
			w.println(name);
			w.close() ;
		} catch (IOException e) {
			e.printStackTrace();
		}
		try(PrintWriter w1 = new PrintWriter(new FileWriter("C:\\server" + File.separator + "accounts" + File.separator + "pass.txt",true))){
			w1.println(user.pass);
			w1.close() ;
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		(new File("C:\\server" + File.separator + "accounts" + File.separator + name)).mkdir() ;  //creating a user folder by his email 
		
	
		(new File("C:\\server" + File.separator + "accounts" + File.separator + name + File.separator + "inbox")).mkdir() ;  //creating inbox folder for the new user 
		
		(new File("C:\\server" + File.separator + "accounts" + File.separator + name + File.separator + "sent")).mkdir() ;  //creating a user folder by his email 
		
		(new File("C:\\server" + File.separator + "accounts" + File.separator + name + File.separator + "trash" )).mkdir() ;  //creating a user folder by his email 
		
		(new File("C:\\server" + File.separator + "accounts" + File.separator + name + File.separator + "/filter" )).mkdir() ;  //creating a user folder by his email 
		
		(new File("C:\\server" + File.separator + "accounts" + File.separator + name + File.separator + "drafts" )).mkdir() ;  //creating a user folder by his email 
		
		try {
			(new File("C:\\server" + File.separator + "accounts" + File.separator + name + File.separator + "data.txt" )).createNewFile() ;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  //creating a user folder by his email 
		
		
		try(PrintWriter w0 = new PrintWriter(new FileWriter("C:\\server" + File.separator + "accounts" + File.separator + name +File.separator + "data.txt",true))){
			w0.println(name);
			w0.println(user.pass);
			w0.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
		
		
		return true ;
	
	}
	
	public void setViewingOptions(IFolder folder, IFilter filter, ISort sort) {
		//if date
		Folder fold = (Folder) folder ;
		FoldertoLL fll = new FoldertoLL() ; 
		  		 //dlf  DoubleLinkedListfolder
		Mail m = new Mail() ;
		try {
			dlf = fll.foldertoLL(fold, userfold) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
		
		
		Sort sorting =new Sort() ;

		Filter filtering = new Filter() ;
		Filter ftype = (Filter) filter ;
		Sort stype = (Sort) sort ;
		
		if(ftype==null && stype !=null) {
			if(stype.type.equals("date")) {
				Dnode temp = dlf.getnode(0) ;
				DoubleLinkedList datedll = new DoubleLinkedList ();
				int count = 0 ;
				SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy") ;
				while(temp != null) {
					m = (Mail) dlf.get(count) ;
					Date date = null;
					
					try {
						date = d.parse(m.getDate());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
					datedll.add(date);
					count ++;
					temp = temp.next ; 
				}
				sorting.sortdate(datedll,dlf);   //sorted ascending
				
				datedll.reverse();   // //sorted descending
			}
			else if(stype.type.equals("priority") ) {
				sorting.sortpriority(dlf);
			}
			else if(stype.type.equals("sender") ) {
				sorting.sortsender(dlf);
			}
			else if(stype.type.equals("subject") ) {
				sorting.sortsubject(dlf);
			}
			
			
		}
		if(ftype!=null && stype ==null) {
			if(ftype.type.equals("date")) {
				Dnode temp = dlf.getnode(0) ;
				DoubleLinkedList datedll = new DoubleLinkedList ();
				int count = 0 ;
				SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy") ;
				while(temp != null) {
					m = (Mail) dlf.get(count) ;
					Date date = null;
					
					try {
						date = d.parse(m.getDate());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
					datedll.add(date);
					count ++;
					temp = temp.next ; 
				}
				sorting.sortdate(datedll,dlf);   //sorted ascending
				
				datedll.reverse();   // //sorted descending
				dlf = filtering.searchdate(dlf, fdate);
			}
		
			else if(ftype.type.equals("priority")) {
				sorting.sortpriority(dlf);
				dlf =filtering.searchpriority(dlf, priority);
				
			}
			else if(ftype.type.equals("sender")) {
				sorting.sortsender(dlf);
				dlf =filtering.searchsender(dlf, sender);
				
			}
			else if(ftype.type.equals("subject")) {
				sorting.sortsubject(dlf);
				dlf =filtering.searchsubject(dlf, subject) ;
				
			}
		}
		
	}
	

}
