package creatInterface;
import java.awt.Choice;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JTextField;

import classe.Login;
import conectionBDD.BddLoginAndCreateUser;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class main {

	private JFrame frameLogin;
	private JTextField tfLogin;
	private JTextField tfMdep;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			private main window;
			public void run() {
				try {
					this.window = new main();
					window.frameLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public main() {
		initialize();
	}
	private void initialize() {
		frameLogin = new JFrame();
		frameLogin.setTitle("Login - Targalis");
		frameLogin.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 13));
		frameLogin.setBounds(100, 100, 621, 402);
		frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameLogin.getContentPane().setLayout(null);

		
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(233, 31, 78, 37);
		frameLogin.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("login :");
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(22, 145, 78, 37);
		frameLogin.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("mot de passe :");
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(22, 206, 139, 25);
		frameLogin.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("si vous avez un probl\u00E8me de connexion");
		lblNewLabel_3.setBounds(10, 321, 432, 14);
		frameLogin.getContentPane().add(lblNewLabel_3);
		
		JLabel lblVousDevezAller = new JLabel("vous devez prendre contact avec votre administrateur r\u00E9seau");
		lblVousDevezAller.setBounds(10, 344, 526, 14);
		frameLogin.getContentPane().add(lblVousDevezAller);
		
		tfLogin = new JTextField();
		tfLogin.setBounds(205, 157, 139, 20);
		frameLogin.getContentPane().add(tfLogin);
		tfLogin.setColumns(10);
		
		tfMdep = new JPasswordField();
		tfMdep.setBounds(205, 212, 139, 20);
		frameLogin.getContentPane().add(tfMdep);
		tfMdep.setColumns(10);
		
		JButton btnConect = new JButton("connexion");
		btnConect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				Login logActuelle = new Login(0,tfLogin.getText(),tfMdep.getText());			
				BddLoginAndCreateUser.connect(null,logActuelle,null,frameLogin,"conect");
			}
		});
		btnConect.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnConect.setBounds(383, 178, 178, 37);
		frameLogin.getContentPane().add(btnConect);
	}

	

	
}
