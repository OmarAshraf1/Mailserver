package eg.edu.alexu.csd.datastructure.mailServer;

public class Mail implements IMail {
	
	// @param email should contain all the information needed
	// sender, list of receivers, list of attachments, email body, ...
	
	private String subject;
	private String	sender;
	private QueueLL receivers = new QueueLL();
	private	singlelinkedlist attachments = new singlelinkedlist();
	private String emailBody;
	private String date;
	
	
	public Mail() {
	}
	
	public void setSender(String s) {
		sender = s;
	}
	
	public void setSubject(String s) {
		subject = s;
	}
	
	public void setDate(String d) {
		date = d;
	}
	
	public void setReceivers(QueueLL sll) {
		receivers = sll;
	}
	
	public void setAttachments(singlelinkedlist sll) {
		attachments = sll;
	}
	
	public void setEmailBody(String str) {
		emailBody = str;
	}
	
	
	
	public String getSender() {
		return sender;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public String getDate() {
		return date;
	}
	
	public QueueLL getReceivers() {
		return receivers;
	}
	
	public singlelinkedlist getAttachments() {
		return attachments;
	}
	
	public String getEmailBody() {
		return emailBody;
	}
	
}
