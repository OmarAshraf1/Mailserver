import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextField;

import eg.edu.alexu.csd.datastructure.mailServer.csxx_csyy_cszz.MailServer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class login {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(new Color(0, 139, 139));
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 139, 139));
		panel.setForeground(new Color(0, 139, 139));
		panel.setBounds(0, 0, 468, 375);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(159, 125, 249, 32);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(159, 193, 249, 32);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(58, 125, 91, 32);
		lblNewLabel.setFont(new Font("Arial",Font.PLAIN,20));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(58, 193, 91, 32);   
		
		lblNewLabel_1.setFont(new Font("Arial",Font.PLAIN,20));
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Sign in");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField.getText() ;
				String pass = textField_1.getText() ;
				MailServer m = new MailServer() ;
				try {
					if(m.signin(name, pass)) {
						JOptionPane.showMessageDialog(null, "Username and password is correct");
						frame.dispose();
						list l = new list() ;
						l.setVisible(true);
						//view mails
					}
					else {
						
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(159, 258, 104, 32);
		btnNewButton.setFont(new Font("Arial",Font.PLAIN,20));
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Sign up");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Signup s = new Signup() ;
				s.setVisible(true);
				
			}
		});
		btnNewButton_1.setBounds(296, 258, 112, 32);
		btnNewButton_1.setFont(new Font("Arial",Font.PLAIN,20));
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("Log in");
		lblNewLabel_2.setBounds(189, 33, 187, 45);
		lblNewLabel_2.setFont(new Font("Arial",Font.PLAIN,30));

		panel.add(lblNewLabel_2);
		frame.setBounds(100, 100, 484, 413);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
