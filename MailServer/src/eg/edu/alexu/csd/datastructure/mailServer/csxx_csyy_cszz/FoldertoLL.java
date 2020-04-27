package eg.edu.alexu.csd.datastructure.mailServer.csxx_csyy_cszz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import eg.edu.alexu.csd.datastructure.linkedList.csxx_csyy.DoubleLinkedList; 
import eg.edu.alexu.csd.datastructure.linkedList.csxx_csyy.singlelinkedlist;

public class FoldertoLL {


	public DoubleLinkedList foldertoLL(Folder folder , String userfold) throws IOException {
		DoubleLinkedList mails = new DoubleLinkedList();
		//userfold : hwa el static file ely ha5do mn el sign in 
	    BufferedReader input = new BufferedReader(new FileReader("C:\\server" + File.separator + "accounts" + File.separator + userfold + File.separator + folder + File.separator + "index.txt" ));
	    String line;
	    					// To read one mail
        while ((line = input.readLine()) != null) {
    		Mail mail = new Mail();
    		singlelinkedlist receivers = new singlelinkedlist();
    		mail.setSubject(line);	
    		mail.setDate(input.readLine());
    		mail.setSender(input.readLine());	
    		mail.priority =  input.read() ;
	        while ( !(line = input.readLine()).equals("***") ) {
	        	receivers.add(line);
	        }
	        mail.setReceivers(receivers);
	        
	        mails.add(mail);
        }
        
    	return mails; 
	}	
	
}
