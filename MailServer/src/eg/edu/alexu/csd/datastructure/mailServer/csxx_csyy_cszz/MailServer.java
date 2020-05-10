package eg.edu.alexu.csd.datastructure.mailServer.csxx_csyy_cszz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import eg.edu.alexu.csd.datastructure.linkedList.csxx_csyy.DoubleLinkedList;
import eg.edu.alexu.csd.datastructure.linkedList.csxx_csyy.DoubleLinkedList.Dnode;
import eg.edu.alexu.csd.datastructure.mailServer.IApp;
import eg.edu.alexu.csd.datastructure.mailServer.IContact;
import eg.edu.alexu.csd.datastructure.mailServer.IFilter;
import eg.edu.alexu.csd.datastructure.mailServer.IFolder;
import eg.edu.alexu.csd.datastructure.mailServer.IMail;
import eg.edu.alexu.csd.datastructure.mailServer.ISort;

public class MailServer implements IApp {
	public static String fdate;
	public static int priority;
	public static String sender;
	public static String subject;
	public static DoubleLinkedList dlf = new DoubleLinkedList();
	public static String currfold;
	public static String sort;
	public static String filter;
	public static String sortby = null;
	public static String searchfor = null;

	public static String test; //

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

	public static String userfold; // to store signed in email

	public boolean signin(String email, String password) throws IOException {
		userfold = email;
		BufferedReader emailReader = new BufferedReader(new FileReader("C:\\server\\accounts\\emails.txt"));
		BufferedReader passReader = new BufferedReader(new FileReader("C:\\server\\accounts\\pass.txt"));

		String a = emailReader.readLine();
		String b = passReader.readLine();

		while (a != null && b != null) {
			if (email.equals(a) && password.equals(b)) {
				emailReader.close();
				passReader.close();
				return true;
			} else {
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
	 * في دي هعمل متغير static عشان احطه في باث بتاع الsort
	 */
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

	public void setViewingOptions(IFolder folder, IFilter filter, ISort sort) {

		// if date
		dlf.clear();
		Folder fold = (Folder) folder;
		FoldertoLL fll = new FoldertoLL();
		// dlf DoubleLinkedListfolder
		Mail m = new Mail();
		try {
			dlf = fll.foldertoLL(fold, userfold);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Filter ftype = (Filter) filter;
		Sort stype = (Sort) sort;
		Sort sorting = new Sort();
		Filter filtering = new Filter();

		if (filter == null && sort != null) {
			if (stype.type.equals("date")) {
				Dnode temp = dlf.getnode(0);
				DoubleLinkedList datedll = new DoubleLinkedList();
				int count = 0;
				SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
				while (temp.next != null) {
					m = (Mail) dlf.get(count);
					Date date = null;

					try {
						date = d.parse(m.getDate());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					datedll.add(date);
					count++;
					temp = temp.next;
				}
				sorting.sortdate(datedll, dlf); // sorted ascending

				datedll.reverse(); // //sorted descending
			} else if (stype.type.equals("priority")) {
				sorting.sortpriority(dlf);
				dlf.reverse();
			} else if (stype.type.equals("sender")) {
				sorting.sortsender(dlf);
			} else if (stype.type.equals("subject")) {
				sorting.sortsubject(dlf);
			}

		}
		if (filter != null && sort == null) {
			if (ftype.type.equals("date")) {
				Dnode temp = dlf.getnode(0);
				DoubleLinkedList datedll = new DoubleLinkedList();
				int count = 0;
				SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
				while (temp.next != null) {
					m = (Mail) dlf.get(count);
					Date date = null;

					try {
						date = d.parse(m.getDate());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					datedll.add(date);
					count++;
					temp = temp.next;
				}
				sorting.sortdate(datedll, dlf); // sorted ascending

				datedll.reverse(); // //sorted descending
				dlf = filtering.searchdate(dlf, fdate);
			}

			else if (ftype.type.equals("priority")) {
				// test = ftype.type ;
				sorting.sortpriority(dlf);
				dlf = filtering.searchpriority(dlf, priority);

			} else if (ftype.type.equals("sender")) {

				sorting.sortsender(dlf);
				dlf = filtering.searchsender(dlf, sender);

			} else if (ftype.type.equals("subject")) {

				sorting.sortsubject(dlf);
				dlf = filtering.searchsubject(dlf, subject);

			}
		}

	}

	public IMail[] listEmails(int page) {

		Folder f = new Folder();
		Sort s = new Sort();
		Filter filt = new Filter();
		f.foldername = currfold;

		if (searchfor == null) {
			filt = null;
		} else {
			filt.type = filter;
		}
		if (sortby == null) {
			s = null;
		} else {
			s.type = sort;
		}

		setViewingOptions(f, filt, s); // change dlf
		int n;
		if (dlf.size() >= 10) {
			n = 10;
		} else {
			n = dlf.size();
		}
		Mail mails[] = new Mail[n];
		int i = page * 10 - 10;
		for (int j = 0; j < n; j++) {

			mails[j] = (Mail) dlf.get(i);
			i++;
		}
		return mails;
	}
	
	
	public void deleteEmails(ILinkedList mails) {
		
		Folder f = new Folder("trash") ;
		moveEmails(mails, (IFolder)f);
		
	}

	
	public void moveEmails(ILinkedList mails, IFolder des) {
		
		DoubleLinkedList m = (DoubleLinkedList) mails;
		Folder desName = (Folder) des;
		
	try {
		// The indexFile of the des folder
		File newIndexFile = new File("C:\\server" + File.separator + "accounts" + File.separator + userfold + File.separator + desName.foldername + File.separator + "index.txt");									
		PrintWriter write = new PrintWriter(new FileWriter(newIndexFile,true));

		for (int j = 0 ; j < m.size() ; j++) {
			Mail mail = ((Mail) m.get(j));
			try {
				// The indexFile of the source folder
				File file = new File("C:\\server" + File.separator + "accounts" + File.separator + userfold + File.separator + folder + File.separator + "index.txt");
				BufferedReader input = new BufferedReader(new FileReader(file));	// Read from the index file
				File tempFile = new File("myTempFile.txt");									// Make temp file
			    PrintWriter writer = new PrintWriter(new FileWriter(tempFile));
			    String line;
				
			    // 1- Search for the mail in the indexFile and delete its information
				String subject = mail.getSubject();			
				while ( (line = input.readLine()) != null ){
					if ( subject.equals(line) ) {								
				        while ( !(line = input.readLine()).equals("***") ) {
				        	continue;	
				        }
					}
					else {
						writer.println(line);
					}
				}
				input.close();
				writer.close();
									//Delete the original file
				file.delete();
										//Rename the new file to the filename the original file had
				tempFile.renameTo(file);
				
				// 2- Move the folder
			    File sourceFolder = new File("C:\\server" + File.separator + "accounts" + File.separator + userfold + File.separator + folder + File.separator + subject);
			    File mailFolder = new File("C:\\server" + File.separator + "accounts" + File.separator + userfold + File.separator + desName.foldername + File.separator + subject);
		        if (!mailFolder.exists()) 
		        	mailFolder.mkdir();
			    moveDir(sourceFolder.toPath(), mailFolder.toPath());
			    sourceFolder.delete();
			    
			    
			    
			    // 3- Write the mail information in the indexFile which in the des folder
				write.println(mail.getSubject());
				write.println(mail.getDate());
				write.println(mail.getSender());
				singlelinkedlist receivers = mail.getReceivers();
				for (int i = 0 ; i < receivers.size() ; i++)
					write.println(receivers.get(i));
				
				write.println("***");

								
			} 
			catch (IOException e) {
				System.out.println("Erorr!!");
			}
		}
    	}catch (IOException e) {
		System.out.println("Erorr!!");
		}
	
	}

	
	private boolean moveDir(Path src, Path dest) {
		if (src.toFile().isDirectory()) {
			for (File file : src.toFile().listFiles()) {
				moveDir(file.toPath(), dest.resolve(src.relativize(file.toPath())));
			}
		}

		try {
			Files.move(src, dest, StandardCopyOption.REPLACE_EXISTING);
			return true;
		} catch (IOException e) {
			return false;
		}
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
}
