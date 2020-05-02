import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import eg.edu.alexu.csd.datastructure.mailServer.csxx_csyy_cszz.Mail;
import eg.edu.alexu.csd.datastructure.mailServer.csxx_csyy_cszz.MailServer;

import java.awt.Color;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JFormattedTextField;
import javax.swing.JEditorPane;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;

public class list extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					list frame = new list();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public list() {
		MailServer m =new MailServer() ;

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 674, 431);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 139, 139));
		panel.setBounds(0, 0, 658, 393);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JList b = new JList();
		b.setBounds(193, 74, 455, 308);
		panel.add(b);
				
		JComboBox comboBox = new JComboBox();    //filter
		
		comboBox.addItem("subject");
		comboBox.addItem("date");
		comboBox.addItem("sender");
		comboBox.addItem("priority");
		comboBox.addItem("null");
		comboBox.setSelectedItem(null);
		
		
		comboBox.setBounds(332, 41, 87, 22);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				///////current folder to be assigned in global variable currfold
				MailServer.filter = (String) comboBox.getSelectedItem() ;
				String filt = MailServer.filter ;         //pop up message to take if conditionals
				if(filt.equals("subject")) {
					MailServer.searchfor = "sd" ;
					//take subject name assign to global variable in MailServer class
					//for pages: for every new page intialize array of size 10
					MailServer.subject = JOptionPane.showInputDialog("Enter subject") ;
					// not now  mails = (Mail[]) m.listEmails(1) ;
				}
				else if(filt.equals("sender")) {
					MailServer.searchfor = "sd" ;

					MailServer.sender = JOptionPane.showInputDialog("Enter sender name") ;
				}
				else if(filt.equals("date")) {
					MailServer.searchfor = "sd" ;
					MailServer.fdate = JOptionPane.showInputDialog("Enter date in form dd/mm/yyyy") ;

				}
				else if(filt.equals("priority")) {
					MailServer.searchfor = "sd" ;
					MailServer.priority = Integer.parseInt(JOptionPane.showInputDialog("Enter priority number")) ;
				}
				
				
			}
		});
		panel.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();       //sort
		
		comboBox_1.addItem("date");
		comboBox_1.addItem("priority");
		comboBox_1.addItem("null");
		///if one is clicked
		comboBox_1.setSelectedItem(null);
		
		comboBox_1.setBounds(474, 41, 87, 22);
		panel.add(comboBox_1);
		
		JLabel lblNewLabel = new JLabel("Filter");
		lblNewLabel.setBounds(352, 16, 46, 14);
		lblNewLabel.setFont(new Font("Arial",Font.PLAIN,18));
		panel.add(lblNewLabel);
		JLabel lblNewLabel_1 = new JLabel("Sort");
		lblNewLabel_1.setBounds(491, 16, 46, 14);
		lblNewLabel_1.setFont(new Font("Arial",Font.PLAIN,18));
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MailServer.sort = (String) comboBox_1.getSelectedItem() ;
				if(MailServer.sort.equals("priority")) {
					MailServer.sortby = "a" ;
				}
				if(MailServer.sort.equals("date")) {
					MailServer.sortby = "a" ;
				}
				
				//mails = (Mail[]) m.listEmails(1);
				comboBox_1.setBounds(333, 41, 87, 22);
				panel.add(comboBox_1);
				
				JLabel lblNewLabel = new JLabel("Filter");
				lblNewLabel.setBounds(257, 16, 46, 14);
				lblNewLabel.setFont(new Font("Arial",Font.PLAIN,18));
				panel.add(lblNewLabel);
				JLabel lblNewLabel_1 = new JLabel("Sort");
			}
		});
		panel.add(lblNewLabel_1);
		MailServer.currfold= "inbox" ;
		
		JButton btnNewButton = new JButton("Set view");
		btnNewButton.setFont(new Font("Arial",Font.PLAIN,15));

		btnNewButton.setBounds(183, 40, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int n ;
				if(MailServer.dlf.size()>=10) {
					n=10 ;
				}
				else {
					n= MailServer.dlf.size() ;
				}
				Mail mails[] = new Mail[n]; 
				
				//JList<Mail[]> b = new JList<Mail[]>() ;
				mails=(Mail[]) m.listEmails(1) ;
				
				
				
				DefaultListModel<String> model = new DefaultListModel<>() ;
				for(int i = 0 ; i < n ; i++) {
					String senderi = mails[i].getSender() ;
					String subjecti = mails[i].getSubject() ;
					model.addElement(subjecti + "	              " + "*Sender* "+ "  " + senderi);
				}
				
				b.setModel(model);
				
				
			}
		});
		
		panel.add(btnNewButton);
		
	
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
