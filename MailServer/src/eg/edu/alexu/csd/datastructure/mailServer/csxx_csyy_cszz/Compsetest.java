package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileAlreadyExistsException;

import javax.swing.JFileChooser;

import java.util.*; 


public class Compsetest {
	
	public static void directoryCreation() throws IOException {
        try {
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
        catch (Exception e) {
        	e.printStackTrace();
        }
    }

    public static boolean signin(String email, String password) throws IOException {
		try {
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
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;

    }
    
	public static boolean signup(IContact contact) /*throws IOException, FileNotFoundException*/ {		
		
		try {
			directoryCreation() ;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Contact user = (Contact) contact ;
		String email = user.email ;
		String pass = user.pass;
			
		BufferedReader emailReader = null;
		try {
			emailReader = new BufferedReader(new FileReader("C:\\server\\accounts\\emails.txt"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String line = null;
		try {
			while( (line = emailReader.readLine()) != null) {
				if(line.equals(email)) {
					emailReader.close();
					return false ;
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			emailReader.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		PrintWriter emailRegisteration = null;
		try {
			emailRegisteration = new PrintWriter(new FileWriter("C:\\server\\accounts\\emails.txt",true));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		emailRegisteration.println(email);
		emailRegisteration.close() ;		

		PrintWriter passRegistration = null;
		try {
			passRegistration = new PrintWriter(new FileWriter("C:\\server\\accounts\\pass.txt",true));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		passRegistration.println(pass);
		passRegistration.close() ;
	
		new File("C:\\server\\accounts\\" + email).mkdir(); 
		try {
			new File("C:\\server\\accounts\\" + email + "\\data.txt" ).createNewFile() ;
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
		try {
			new File("C:\\server\\accounts\\" + email + "\\inbox\\index.txt" ).createNewFile() ;
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
		new File("C:\\server\\accounts\\" + email + "\\sent").mkdir() ;  	
		try {
			new File("C:\\server\\accounts\\" + email + "\\sent\\index.txt" ).createNewFile() ;
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
		new File("C:\\server\\accounts\\" + email + "\\trash" ).mkdir() ;  	
		try {
			new File("C:\\server\\accounts\\" + email + "\\trash\\index.txt" ).createNewFile() ;
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
		new File("C:\\server\\accounts\\" + email + "\\filter" ).mkdir() ; 
		try {
			new File("C:\\server\\accounts\\" + email + "\\filter\\index.txt" ).createNewFile() ;
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
		new File("C:\\server\\accounts\\" + email + "\\drafts" ).mkdir() ;  	
		try {
			new File("C:\\server\\accounts\\" + email + "\\drafts\\index.txt" ).createNewFile() ;
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
	
		PrintWriter emailPassSaver = null;
		try {
			emailPassSaver = new PrintWriter(new FileWriter("C:\\server\\accounts\\" + email + "\\data.txt",true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		emailPassSaver.println(email);
		emailPassSaver.println(pass);	
		emailPassSaver.close();	
		
		return true;
	}
    
	 public static boolean checkReceivers1(IMail email) {
	    	
		 Mail newEmail = new Mail();
		 newEmail = (Mail)email;
		 String temp = null;
		 int existedReceivers = 0;
		 int numOfReceivers = newEmail.getReceivers().size();
		 for(int i=0; i<numOfReceivers; i++) {
			 temp = (String) newEmail.getReceivers().dequeue(); 
			 newEmail.getReceivers().enqueue(temp);
			 if (new File("C:\\server\\accounts\\" + temp).exists()) {
				 existedReceivers++;
			 }
		 }
		 if (existedReceivers == numOfReceivers) {
			 return true;
		 }
		 else {
			 return false; 
		 }
	 }
	
	 public static ArrayList<String> checkReceivers2(IMail email) {
	    	
		 Mail newEmail = new Mail();
		 newEmail = (Mail)email;
		 
		 ArrayList<String> notExistedReceivers =new ArrayList<String>();
		
		 String temp = null;
		 int numOfReceivers = newEmail.getReceivers().size();
		 
		 for(int i=0; i<numOfReceivers; i++) {	
			 temp = (String) newEmail.getReceivers().dequeue(); 
			 if (!new File("C:\\server\\accounts\\" + temp).exists()) {
				 notExistedReceivers.add(temp);
			 }
			 else {
				 newEmail.getReceivers().enqueue(temp);
			 }
		 }
		 return notExistedReceivers;

	 }
	
    public static boolean compose(IMail email) /*throws IOException, FileNotFoundException, FileAlreadyExistsException, DirectoryNotEmptyException, UnsupportedOperationException, NoSuchFileException*/ {
    	
    	Mail newEmail = new Mail();
    	newEmail = (Mail)email;
    	
    	if (new File ("C:\\server\\accounts\\" + newEmail.getSender() + "\\sent\\" + newEmail.getSubject()).exists()) {
    		System.out.println("Your Subject is Dublicated");
    		return false;
    	}
    	
    	PrintWriter emailWriter1 = null;
		try {
			emailWriter1 = new PrintWriter(new FileWriter("C:\\server\\accounts\\" + newEmail.getSender() + "\\sent\\index.txt",true));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	emailWriter1.println(newEmail.getSubject());
    	emailWriter1.println(newEmail.getDate());
    	emailWriter1.println(newEmail.getSender());
    	
    	int numOfReceivers = newEmail.getReceivers().size();
    	QueueLL getTempReceivers1 = new QueueLL();
    	QueueLL getTempReceivers2 = new QueueLL();
    	String temp;
    	for (int i=0; i<numOfReceivers; i++) {
    		temp = (String) newEmail.getReceivers().dequeue();
    		getTempReceivers1.enqueue(temp);
    		getTempReceivers2.enqueue(temp);
    		newEmail.getReceivers().enqueue(temp);
    	}
    
    	emailWriter1.println(numOfReceivers);
    	for (int i=0; i<numOfReceivers; i++) {
    		emailWriter1.println(newEmail.getReceivers().dequeue());
    	}
    	
    	int numOfAttachments = newEmail.getAttachments().size();
    	emailWriter1.println(numOfAttachments);
    	for(int i=0; i<numOfAttachments; i++) {
    		emailWriter1.println(newEmail.getAttachments().get(i));
    	}
    	
    	emailWriter1.println("***");
    	emailWriter1.close();    	

    	new File ("C:\\server\\accounts\\" + newEmail.getSender() + "\\sent\\" + newEmail.getSubject()).mkdir();
    	try {
			new File ("C:\\server\\accounts\\" + newEmail.getSender() + "\\sent\\" + newEmail.getSubject() + "\\emailBody.txt").createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	PrintWriter emailContentWriter1 = null;
		try {
			emailContentWriter1 = new PrintWriter(new FileWriter("C:\\server\\accounts\\" + newEmail.getSender() + "\\sent\\" + newEmail.getSubject() + "\\emailBody.txt",true));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	emailContentWriter1.println(newEmail.getEmailBody());
    	emailContentWriter1.close();
    	
    	for (int i=0; i<numOfAttachments; i++) {
        	String attachments = (String) newEmail.getAttachments().get(i);
        	
        	String[] spliter = attachments.split("\\n");
        	String attachmentPath = spliter[0];
        	String attachmentName = spliter[1];
        	
            File source = new File(attachmentPath);
            File dest = new File("C:\\server\\accounts\\" + newEmail.getSender() + "\\sent\\" + newEmail.getSubject() + "\\" + attachmentName);
            try {
				Files.copy(source.toPath(), dest.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	
    	for (int i=0; i<numOfReceivers; i++) {
    		
    		String tempReceiver = null;
    		String currentReceiver = null;
    		currentReceiver = (String) getTempReceivers1.dequeue();
    		getTempReceivers1.enqueue(currentReceiver);
        	
    		PrintWriter emailWriter2 = null;
			try {
				emailWriter2 = new PrintWriter(new FileWriter("C:\\server\\accounts\\" + currentReceiver + "\\inbox\\index.txt",true));
			} catch (IOException e) {
				e.printStackTrace();
			}
        	
        	emailWriter2.println(newEmail.getSubject());
        	emailWriter2.println(newEmail.getDate());
        	emailWriter2.println(newEmail.getSender());
        	
        	
           	emailWriter2.println(numOfReceivers);
        	for (int j=0; j<numOfReceivers; j++) {
        		tempReceiver = (String) getTempReceivers2.dequeue();
        		getTempReceivers2.enqueue(tempReceiver);
        		emailWriter2.println(tempReceiver);
        	}
        	
        	emailWriter2.println(numOfAttachments);
        	for(int k=0; k<numOfAttachments; k++) {
        		emailWriter2.println(newEmail.getAttachments().get(k));
        	}
        	
        	emailWriter2.println("***");
        	emailWriter2.close();    	

    		
        	new File ("C:\\server\\accounts\\" + currentReceiver + "\\inbox\\" + newEmail.getSubject()).mkdir();
        	try {
				new File ("C:\\server\\accounts\\" + currentReceiver + "\\inbox\\" + newEmail.getSubject() + "\\emailBody.txt").createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
        	
        	PrintWriter emailContentWriter2 = null;
			try {
				emailContentWriter2 = new PrintWriter(new FileWriter("C:\\server\\accounts\\" + currentReceiver + "\\inbox\\" + newEmail.getSubject() + "\\emailBody.txt",true));
			} catch (IOException e) {
				e.printStackTrace();
			}
        	emailContentWriter2.println(newEmail.getEmailBody());
        	emailContentWriter2.close();
        	
        	for (int l=0; l<numOfAttachments; l++) {
            	String attachments = (String) newEmail.getAttachments().get(l);
            	String[] spliter = attachments.split("\\n");
            	String attachmentPath = spliter[0];
            	String attachmentName = spliter[1];
            	
            	File source = new File(attachmentPath);
            	File dest = new File("C:\\server\\accounts\\" + currentReceiver + "\\inbox\\" + newEmail.getSubject() + "\\" + attachmentName);
            	try {
					Files.copy(source.toPath(), dest.toPath());
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
    	}    	
    	return true;
    }

	public static boolean specialCompose (IMail email) {
		Mail newEmail = new Mail();
    	newEmail = (Mail)email;
    	
    	if (new File ("C:\\server\\accounts\\" + newEmail.getSender() + "\\drafts\\" + newEmail.getSubject()).exists()) {
    		System.out.println("Your Subject is Dublicated");
    		return false;
    	}
    	
    	PrintWriter emailWriter1 = null;
		try {
			emailWriter1 = new PrintWriter(new FileWriter("C:\\server\\accounts\\" + newEmail.getSender() + "\\drafts\\index.txt",true));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	emailWriter1.println(newEmail.getSubject());
    	emailWriter1.println(newEmail.getDate());
    	emailWriter1.println(newEmail.getSender());
    	
    	int numOfReceivers = newEmail.getReceivers().size();
    	emailWriter1.println(numOfReceivers);
    	for (int i=0; i<numOfReceivers; i++) {
    		emailWriter1.println(newEmail.getReceivers().dequeue());
    	}
    	
    	int numOfAttachments = newEmail.getAttachments().size();
    	emailWriter1.println(numOfAttachments);
    	for(int i=0; i<numOfAttachments; i++) {
    		emailWriter1.println(newEmail.getAttachments().get(i));
    	}
    	
    	emailWriter1.println("***");
    	emailWriter1.close();    	

		
		return true;
	}
    
	public static void main(String[] args) throws IOException, FileNotFoundException, FileAlreadyExistsException, DirectoryNotEmptyException, UnsupportedOperationException, NoSuchFileException {
		
		directoryCreation();
		System.out.println(signin("ahmed","12345678"));
		
		Mail newEmail = new Mail();
		
		newEmail.setSubject("Education Managment Crises in Corona (Covoid 19)");
		newEmail.setDate("17/12/2000");
		newEmail.setSender("ahmed");
		newEmail.setEmailBody("Assigning students in my university is very unfair because most of our professors don't estimate well our situation..\n They think we are very qualified to enter exams as they see themselves present us a considered effort   ");
		QueueLL receivers = new QueueLL();
		receivers.enqueue("Suarez");
		receivers.enqueue("Cr7");
		receivers.enqueue("messi");
		receivers.enqueue("Xavi");
		newEmail.setReceivers(receivers);

		singlelinkedlist attachments = new singlelinkedlist();
		JFileChooser attachmentChooser = new JFileChooser();
    	int check = attachmentChooser.showOpenDialog(null);
    	String attachmentPath = null;
    	String attachmentName = null;
    	if(check == JFileChooser.APPROVE_OPTION) {
    		attachmentPath = attachmentChooser.getSelectedFile().getAbsolutePath();
    		attachmentName = attachmentChooser.getSelectedFile().getName();
    		attachments.add(attachmentPath + "\n" + attachmentName);
    	}
		newEmail.setAttachments(attachments);
		
		System.out.println(checkReceivers1(newEmail));
		
		for (String obj:checkReceivers2(newEmail)) {	
			System.out.println(obj);
		}
		

		System.out.println(compose(newEmail));
	
		

	}
	
}
