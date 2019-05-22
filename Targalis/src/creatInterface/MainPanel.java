package creatInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import classe.Auteur;
import classe.Livre;
import classe.Login;
import classe.Trie;
import classe.Utilisateur;
import conectionBDD.BddCreateBookAndAuthor;
import conectionBDD.BddEtat;
import conectionBDD.BddLoginAndCreateUser;
import conectionBDD.BddRecherche;
import design.Rendu;
import fonction.CreateUser;
import fonction.CreateAuthor;
import fonction.CreateBook;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTextPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;
import java.awt.Scrollbar;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollBar;
import java.awt.event.AdjustmentListener;
import java.awt.event.FocusListener;
import java.awt.event.AdjustmentEvent;
import java.awt.Window.Type;
import javax.swing.JCheckBox;

public class MainPanel {

	private JFrame frmTargalis;
	private JTextField tfNom;
	private JTextField tfPrenom;
	private JTextField tfAdresse;
	private JTextField tfCp;
	private JTextField tfVille;
	private JTextField tfEmail;
	private JTextField tfTel;
	private JTextField tfLogin;
	private JTextField tfMdep;
	private JTextField tfTitre;
	private JTextField tfIsbn;
	private JTextField tfAuteurNom;
	private JTextField tfAuteurAnnee;

	/**
	 * Launch the application.
	 * 
	 * @param logActuel
	 */
	public static void main(Object args, Login logActuel) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPanel window = new MainPanel(logActuel);
					window.frmTargalis.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the application.
	 * 
	 * @throws ParseException
	 */
	public MainPanel(Login logActuel) throws ParseException {
		initialize(logActuel);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws ParseException
	 */
	private void initialize(Login logActuel) throws ParseException {
		frmTargalis = new JFrame();
		frmTargalis.setTitle("Targalis");
		frmTargalis.setBounds(100, 100, 878, 578);
		frmTargalis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTargalis.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 11, 862, 529);
		frmTargalis.getContentPane().add(tabbedPane);

		BddLoginAndCreateUser.connect(null, logActuel, null, null, "recupRole");

		switch (logActuel.getRole()) {

		case "administrateur":
			panCreateUtilisateur(tabbedPane);
			break;
		case "abonné":
			panRecherche(logActuel, tabbedPane);
			break;
		case "bibliothécaire":
			panCreateBookAndAuthor(logActuel, tabbedPane);
			panRecherche(logActuel, tabbedPane);
			break;

		}
		
		
	}

	private static ListCellRenderer<? super Livre> createListRenderer() {
		return new DefaultListCellRenderer() {
			private Color background = new Color(0, 100, 255, 15);
			private Color defaultBackground = (Color) UIManager.get("List.background");

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (c instanceof JLabel) {
					JLabel label = (JLabel) c;
					Livre emp = (Livre) value;
					label.setText(String.format("[%s] [%s] %s - %s", emp.getId(), emp.getExemplaire(), emp.getTitre(),
							emp.getAuteur().getNom()));
					if (!isSelected) {
						label.setBackground(index % 2 == 0 ? background : defaultBackground);
					}
				}
				return c;
			}
		};
	}

	private void panRecherche(Login logActuel, JTabbedPane tabbedPane) {
		JPanel panel = new JPanel();
		tabbedPane.addTab("recherche", null, panel, null);
		panel.setLayout(null);
		
		System.out.println(logActuel.getRole());
		
	
	
		


		JCheckBox tchAuteur = new JCheckBox("tris par auteur");
		tchAuteur.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		tchAuteur.setBounds(10, 7, 212, 23);
		panel.add(tchAuteur);

		JCheckBox tchTitre = new JCheckBox("tris par Titre");
		tchTitre.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		tchTitre.setBounds(256, 7, 199, 23);
		panel.add(tchTitre);
		
		Choice cCat = new Choice();
		cCat.setBounds(125, 54, 125, 20);
		cCat.add("");
		BddCreateBookAndAuthor.connect(null, null, null, cCat, "listeCategorie");
		panel.add(cCat);
		
		Choice cAuteur = new Choice();
		cAuteur.setBounds(407, 54, 150, 20);
		cAuteur.add("");
		BddCreateBookAndAuthor.connect(null, null, null, cAuteur, "listeAuteur");
		panel.add(cAuteur);
		
		Choice cGenre = new Choice();
		cGenre.setBounds(125, 96, 125, 20);
		cGenre.add("");
		BddCreateBookAndAuthor.connect(null, null, null, cGenre, "listeGenre");
		panel.add(cGenre);
		
		JTextField textIsbn = new JTextField();
		textIsbn.setEditable(false);
		textIsbn.setBounds(553, 166, 287, 20);
		panel.add(textIsbn);
		textIsbn.setColumns(10);
		
		JTextField textAuteur = new JTextField();
		textAuteur.setEditable(false);
		textAuteur.setColumns(10);
		textAuteur.setBounds(555, 279, 287, 20);
		panel.add(textAuteur);
		
		JTextField textEtat = new JTextField();
		textEtat.setEditable(false);
		textEtat.setColumns(10);
		textEtat.setBounds(553, 339, 287, 20);
		panel.add(textEtat);
		
		JTextField textTitre = new JTextField();
		textTitre.setEditable(false);
		textTitre.setColumns(10);
		textTitre.setBounds(553, 221, 287, 20);
		panel.add(textTitre);

		Trie trie = new Trie(false,false,"","","");
		
		ListModel<Livre> model = new DefaultListModel<Livre>();
		
		JList<Livre> listAllBook = new JList<Livre>(model);
		listAllBook.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		listAllBook.setValueIsAdjusting(true);
		listAllBook.setCellRenderer(createListRenderer());
		listAllBook.setVisible(true);
		((DefaultListModel<Livre>)listAllBook.getModel()).removeAllElements();
		BddRecherche.connect(listAllBook, trie, "listAllLivre");
		listAllBook.revalidate();
		
		listAllBook.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				
				textIsbn.setText(((Livre) listAllBook.getSelectedValue()).getISBN());
				textTitre.setText(((Livre) listAllBook.getSelectedValue()).getTitre());
				textAuteur.setText(((Livre) listAllBook.getSelectedValue()).getAuteur().getNom());
				textEtat.setText(((Livre) listAllBook.getSelectedValue()).getEtat());
				
			}
			});
		
		JScrollPane scrollPane = new JScrollPane(listAllBook);
		panel.remove(scrollPane);
		scrollPane.setBounds(10, 122, 483, 368);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setWheelScrollingEnabled(true);
		panel.add(scrollPane);
		scrollPane.revalidate();
		
		JButton btRech = new JButton("recherche");
		btRech.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btRech.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				trie.setifAuteur(tchAuteur.isSelected());
				trie.setifTitre(tchTitre.isSelected());
				trie.setAuteur(cAuteur.getSelectedItem());
				trie.setCategorie(cCat.getSelectedItem());
				trie.setGenre(cGenre.getSelectedItem());

				
				
				panel.remove(listAllBook);
				listAllBook.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				listAllBook.setValueIsAdjusting(true);
				listAllBook.setCellRenderer(createListRenderer());
				listAllBook.setVisible(true);
				((DefaultListModel<Livre>)listAllBook.getModel()).removeAllElements();
				BddRecherche.connect(listAllBook, trie, "listAllLivre");
				listAllBook.revalidate();
				
				
				panel.remove(scrollPane);
				scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scrollPane.setWheelScrollingEnabled(true);
				panel.add(scrollPane);
				scrollPane.revalidate();
				
				

			}
		});
		btRech.setBounds(615, 120, 212, 30);
		panel.add(btRech);
		
		switch (logActuel.getRole()) {

		case "administrateur":
			
			break;
		case "abonné":
			
			break;
		case "bibliothécaire":
			Choice choice = new Choice();
			choice.setBounds(708, 446, 139, 20);
			panel.add(choice);
			BddEtat.connect(logActuel,choice,0,"listeEtat");
			choice.revalidate();
			
			JButton btnModifEtat = new JButton("Modif Etat");
			btnModifEtat.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					BddEtat.connect(logActuel,choice,((Livre) listAllBook.getSelectedValue()).getId(),"ModifEtatLivre");
				}
			});
			btnModifEtat.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
			btnModifEtat.setBounds(503, 436, 150, 30);
			btnModifEtat.revalidate();
			panel.add(btnModifEtat);
			break;

		}
		
		JLabel lblCatgorie = new JLabel("cat\u00E9gorie :");
		lblCatgorie.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblCatgorie.setBounds(10, 54, 109, 20);
		panel.add(lblCatgorie);
		
		JLabel lblGenre_1 = new JLabel("genre :");
		lblGenre_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblGenre_1.setBounds(10, 85, 88, 31);
		panel.add(lblGenre_1);
		
		JLabel lblAuteur_1 = new JLabel("auteur :");
		lblAuteur_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblAuteur_1.setBounds(277, 54, 109, 20);
		panel.add(lblAuteur_1);
		
		
		
		
		JLabel lblCatgorie1 = new JLabel("cat\u00E9gorie :");
		lblCatgorie1.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblCatgorie1.setBounds(10, 54, 109, 20);
		panel.add(lblCatgorie1);
		
		JLabel lblGenre_11 = new JLabel("genre :");
		lblGenre_11.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblGenre_11.setBounds(10, 85, 88, 31);
		panel.add(lblGenre_11);
		
		JLabel lblAuteur_11 = new JLabel("auteur :");
		lblAuteur_11.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblAuteur_11.setBounds(277, 54, 109, 20);
		panel.add(lblAuteur_11);
		
		JLabel lblTitre_1 = new JLabel("Titre :");
		lblTitre_1.setBounds(497, 224, 46, 14);
		panel.add(lblTitre_1);
		
		JLabel lblIsbn_2 = new JLabel("ISBN :");
		lblIsbn_2.setBounds(497, 169, 46, 14);
		panel.add(lblIsbn_2);
		
		JLabel lblAuteur_2 = new JLabel("Auteur :");
		lblAuteur_2.setBounds(499, 282, 46, 14);
		panel.add(lblAuteur_2);
		
		JLabel lblEtat = new JLabel("Etat :");
		lblEtat.setBounds(497, 342, 46, 14);
		panel.add(lblEtat);
		
		

	}

	private void panCreateBookAndAuthor(Login logActuel, JTabbedPane tabbedPane) throws ParseException {
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("ajout livre/auteur", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel lblIsbn = new JLabel("cr\u00E9ation livre");
		lblIsbn.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblIsbn.setBounds(10, 11, 125, 28);
		panel_1.add(lblIsbn);

		JLabel lblCrationAuteur = new JLabel("cr\u00E9ation auteur");
		lblCrationAuteur.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblCrationAuteur.setBounds(425, 11, 153, 28);
		panel_1.add(lblCrationAuteur);

		JLabel lblIsbn_1 = new JLabel("ISBN :");
		lblIsbn_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblIsbn_1.setBounds(10, 63, 76, 28);
		panel_1.add(lblIsbn_1);

		JLabel lblTitre = new JLabel("Titre :");
		lblTitre.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblTitre.setBounds(10, 125, 76, 28);
		panel_1.add(lblTitre);

		JLabel lblGenre = new JLabel("Genre :");
		lblGenre.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblGenre.setBounds(10, 309, 76, 28);
		panel_1.add(lblGenre);

		JLabel lblAuteur = new JLabel("Auteur :");
		lblAuteur.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblAuteur.setBounds(10, 185, 97, 28);
		panel_1.add(lblAuteur);

		JLabel lblType = new JLabel("Type :");
		lblType.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblType.setBounds(10, 244, 76, 28);
		panel_1.add(lblType);

		tfTitre = new JTextField();
		tfTitre.setBounds(110, 133, 167, 20);
		panel_1.add(tfTitre);
		tfTitre.setColumns(10);

		tfIsbn = new JTextField();
		tfIsbn.setColumns(10);
		tfIsbn.setBounds(110, 71, 167, 20);
		panel_1.add(tfIsbn);

		Choice cCat = new Choice();
		cCat.setBounds(146, 244, 131, 20);
		BddCreateBookAndAuthor.connect(null, null, null, cCat, "listeCategorie");
		panel_1.add(cCat);

		Choice cGenre = new Choice();
		cGenre.setBounds(146, 317, 131, 20);
		BddCreateBookAndAuthor.connect(null, null, null, cGenre, "listeGenre");
		panel_1.add(cGenre);

		Choice cAuteur = new Choice();
		cAuteur.setBounds(146, 193, 131, 20);
		BddCreateBookAndAuthor.connect(null, null, null, cAuteur, "listeAuteur");
		panel_1.add(cAuteur);

		JButton btnAddBook = new JButton("ajout livre");
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Auteur auteur = new Auteur(cAuteur.getSelectedItem(), 0, 0);
				Livre livre = new Livre(tfIsbn.getText(), tfTitre.getText(), auteur, cCat.getSelectedItem(),
						cGenre.getSelectedItem(), 0, null, 0, 0);
				System.out.println(tfTitre.getText());
				System.out.println(livre.getTitre());
				System.out.println(livre.getISBN());
				CreateBook.createBook(logActuel, livre);
			}
		});
		btnAddBook.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnAddBook.setBounds(56, 414, 153, 37);
		panel_1.add(btnAddBook);

		JLabel lblNom_1 = new JLabel("Nom :");
		lblNom_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNom_1.setBounds(423, 63, 76, 28);
		panel_1.add(lblNom_1);

		JLabel lblAneDeNaissance = new JLabel("an\u00E9e de naissance :");
		lblAneDeNaissance.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblAneDeNaissance.setBounds(423, 125, 191, 28);
		panel_1.add(lblAneDeNaissance);

		tfAuteurNom = new JTextField();
		tfAuteurNom.setBounds(618, 71, 143, 20);
		panel_1.add(tfAuteurNom);
		tfAuteurNom.setColumns(10);

		MaskFormatter date = new MaskFormatter("*####");

		tfAuteurAnnee = new JFormattedTextField(date);
		tfAuteurAnnee.setColumns(10);
		tfAuteurAnnee.setBounds(618, 133, 143, 20);
		panel_1.add(tfAuteurAnnee);

		JButton btnAddAuthor = new JButton("ajout auteur");
		btnAddAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cAuteur.setVisible(false);
				Auteur auteur = new Auteur(tfAuteurNom.getText(), Integer.parseInt(tfAuteurAnnee.getText()), 0);
				CreateAuthor.createAuthor(cAuteur, panel_1, auteur);

			}
		});
		btnAddAuthor.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnAddAuthor.setBounds(546, 414, 153, 37);
		panel_1.add(btnAddAuthor);
	}

	private void panCreateUtilisateur(JTabbedPane tabbedPane) throws ParseException {
		JPanel creatUser = new JPanel();
		tabbedPane.addTab("creation user", null, creatUser, null);
		creatUser.setLayout(null);

		JLabel lblNewLabel = new JLabel("cr\u00E9ation nouveau utilisateur");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel.setBounds(295, 11, 299, 41);
		creatUser.add(lblNewLabel);

		JLabel lblNom = new JLabel("Nom* :");
		lblNom.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNom.setBounds(10, 79, 93, 28);
		creatUser.add(lblNom);

		JLabel lblPrenom = new JLabel("Prenom* :");
		lblPrenom.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblPrenom.setBounds(295, 79, 104, 28);
		creatUser.add(lblPrenom);

		JLabel lblobligatoire = new JLabel("*obligatoire");
		lblobligatoire.setBounds(10, 476, 80, 14);
		creatUser.add(lblobligatoire);

		tfNom = new JTextField();
		tfNom.setBounds(102, 87, 133, 20);
		creatUser.add(tfNom);
		tfNom.setColumns(10);

		tfPrenom = new JTextField();
		tfPrenom.setBounds(425, 87, 133, 20);
		creatUser.add(tfPrenom);
		tfPrenom.setColumns(10);

		JLabel lblAdresse = new JLabel("Adresse* :");
		lblAdresse.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblAdresse.setBounds(10, 147, 125, 28);
		creatUser.add(lblAdresse);

		JLabel lblCodePostal = new JLabel("Code postal* :");
		lblCodePostal.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblCodePostal.setBounds(10, 186, 174, 28);
		creatUser.add(lblCodePostal);

		JLabel lblVille = new JLabel("Ville* :");
		lblVille.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblVille.setBounds(359, 186, 93, 28);
		creatUser.add(lblVille);

		tfAdresse = new JTextField();
		tfAdresse.setBounds(157, 155, 600, 20);
		creatUser.add(tfAdresse);
		tfAdresse.setColumns(10);

		MaskFormatter cp = new MaskFormatter("#####");

		tfCp = new JFormattedTextField(cp);
		tfCp.setColumns(10);
		tfCp.setBounds(194, 194, 133, 20);
		creatUser.add(tfCp);

		tfVille = new JTextField();
		tfVille.setColumns(10);
		tfVille.setBounds(475, 194, 133, 20);
		creatUser.add(tfVille);

		JLabel lblEmail = new JLabel("E-mail* :");
		lblEmail.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblEmail.setBounds(10, 284, 117, 28);
		creatUser.add(lblEmail);

		JLabel lblTel = new JLabel("Tel* :");
		lblTel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblTel.setBounds(10, 346, 73, 28);
		creatUser.add(lblTel);

		JLabel lblLogin = new JLabel("Login* :");
		lblLogin.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblLogin.setBounds(425, 284, 117, 28);
		creatUser.add(lblLogin);

		JLabel lblMotDePasse = new JLabel("Mot de passe* :");
		lblMotDePasse.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblMotDePasse.setBounds(425, 346, 174, 28);
		creatUser.add(lblMotDePasse);

		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(137, 292, 225, 20);
		creatUser.add(tfEmail);

		MaskFormatter tel2 = new MaskFormatter("##-##-##-##-##");

		tfTel = new JFormattedTextField(tel2);
		tfTel.setColumns(10);
		tfTel.setBounds(137, 346, 133, 20);
		creatUser.add(tfTel);

		tfLogin = new JTextField();
		tfLogin.setColumns(10);
		tfLogin.setBounds(602, 292, 133, 20);
		creatUser.add(tfLogin);

		tfMdep = new JTextField();
		tfMdep.setColumns(10);
		tfMdep.setBounds(602, 346, 133, 20);
		creatUser.add(tfMdep);

		Choice cRole = new Choice();
		cRole.setBounds(157, 233, 170, 20);
		BddLoginAndCreateUser.connect(null, null, cRole, null, "listeRole");
		cRole.add("test");
		creatUser.add(cRole);

		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Utilisateur utilisateurRef = new Utilisateur(0, tfNom.getText(), tfPrenom.getText(),
						tfAdresse.getText(), tfCp.getText(), tfVille.getText(), cRole.getSelectedItem(),
						tfEmail.getText(), tfTel.getText(), tfLogin.getText(), tfMdep.getText());

				CreateUser.createUser(utilisateurRef, tfNom, tfPrenom, tfAdresse, tfCp, tfVille, tfEmail, tfTel,
						tfLogin, tfMdep, cRole);
			}
		});
		btnValider.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnValider.setBounds(589, 432, 133, 41);
		creatUser.add(btnValider);

		JLabel lblRle = new JLabel("R\u00F4le* :");
		lblRle.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblRle.setBounds(10, 225, 93, 28);
		creatUser.add(lblRle);
	}
}
