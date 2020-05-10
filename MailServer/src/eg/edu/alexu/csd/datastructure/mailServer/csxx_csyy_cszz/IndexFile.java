package eg.edu.alexu.csd.datastructure.mailServer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IndexFile {

	DoubleLinkedList mails = new DoubleLinkedList();

	public DoubleLinkedList readSet() throws IOException {
		
	    BufferedReader input = new BufferedReader(new FileReader("W:\\index.txt"));
	    String line;

	    while ((line = input.readLine()) != null) {
    	
	    	Mail mail = new Mail();
    		QueueLL receivers = new QueueLL();
    		
    		mail.setSubject(line);	
    		mail.setDate(input.readLine());
    		mail.setSender(input.readLine());	
    		
    		for(int i=0; i<receivers.size(); i++) {
    			line = input.readLine();
	        	receivers.enqueue(line);
    		}

	        mail.setReceivers(receivers);
	        mails.add(mail);
        }
        input.close();
        
    	return mails;
	}	
	
}
