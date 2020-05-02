import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import eg.edu.alexu.csd.datastructure.mailServer.csxx_csyy_cszz.Contact;
import eg.edu.alexu.csd.datastructure.mailServer.csxx_csyy_cszz.MailServer;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Signup extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton btnNewButton;
	private JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signup frame = new Signup();
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
	public Signup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 139, 139));
		panel.setBounds(0, 0, 454, 358);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(150, 105, 243, 39);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(150, 169, 243, 39);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(150, 231, 243, 39);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(39, 102, 86, 39);
		lblNewLabel.setFont(new Font("Arial",Font.PLAIN,20));

		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(39, 169, 101, 39);
		lblNewLabel_1.setFont(new Font("Arial",Font.PLAIN,20));

		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setBounds(39, 228, 86, 39);
		lblNewLabel_2.setFont(new Font("Arial",Font.PLAIN,20));

		panel.add(lblNewLabel_2);
		
		btnNewButton = new JButton("Sign up");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField.getText() ;
				String pass = textField_1.getText() ;
				String email = textField_2.getText() ;
				MailServer m = new MailServer() ;
				Contact cont = new Contact() ;
				cont.name = name ;
				cont.pass = pass ;
				cont.email = email ;
				if(m.signup(cont)) {
					JOptionPane.showMessageDialog(null, "Signed in successfully");
					

				}
			}
		});
		
		btnNewButton.setBounds(150, 298, 116, 33);
		btnNewButton.setFont(new Font("Arial",Font.PLAIN,20)); 

		panel.add(btnNewButton);
		
		lblNewLabel_3 = new JLabel("Sign up");
		lblNewLabel_3.setBounds(160, 28, 204, 39);
		lblNewLabel_3.setFont(new Font("Arial",Font.BOLD,30));
		panel.add(lblNewLabel_3);
	}

}
