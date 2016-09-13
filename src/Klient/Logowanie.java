package Klient;

//import AddOffer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Logowanie extends JPanel {
	private JTextField textIdKlienta;
	private JTextField textMailKlienta;
	private JTextField textStart;
	private JTextField textEnd;
	private JTextField textZakres;
	private JLabel lblOk;
	private JLabel lblFalse;
	private JPanel panel_1 ;
	
	private JLabel lblPoczatekTrasy;
	private JLabel lblKoniecTrasy;
	private JComboBox comboFirma;
	
	private Connection conn = null;
	private Statement myStmt = null;
	private ResultSet myRs = null;
	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtId;
	private JTextField textImie;
	private JTextField textNazwisko;
	private JTextField textMiasto;
	private JTextField textAdres;
	private JTextField textTelefon;
	private JTextField textPoczta;
	private JTextField textMail;
	private JTextField textNumer;
	private JTextField textFirma;
	
	private JRadioButton rdbtnOsobaPrywatna;
	private JRadioButton rdbtnFirma;
	private JLabel lblPesel ;
	private JLabel lblNazwa;
	private JButton btnUtwrz;
	private JButton btnWyczysc;
	public JLabel lblZawieranieUmowyZ;
	public static String idKlienta;
	

	/**
	 * Create the panel.
	 */
	
	public Logowanie() {

		setLayout(null);
		setBounds(0,0,653,406);
		lblZawieranieUmowyZ = new JLabel("Panel logowania");
		lblZawieranieUmowyZ.setForeground(Color.BLUE);
		lblZawieranieUmowyZ.setFont(new Font("Verdana", Font.BOLD, 14));
		lblZawieranieUmowyZ.setBounds(10, 11, 330, 22);
		add(lblZawieranieUmowyZ);
		
		textIdKlienta = new JTextField();
		textIdKlienta.setBounds(92, 124, 176, 20);

		add(textIdKlienta);
		textIdKlienta.setColumns(10);
		
		textMailKlienta = new JTextField();
		textMailKlienta.setBounds(92, 155, 176, 20);
		add(textMailKlienta);
		textMailKlienta.setColumns(10);
		
		JLabel lblHotel = new JLabel("e-mail:");
		lblHotel.setBounds(10, 158, 46, 14);
		add(lblHotel);
		
		JLabel lblRok = new JLabel("Nr klienta:");
		lblRok.setBounds(10, 127, 84, 14);
		add(lblRok);
		
		JSeparator separator = new JSeparator();
		separator.setToolTipText("jihhihiu");
		separator.setForeground(Color.YELLOW);
		separator.setBackground(Color.PINK);
		separator.setBounds(0, 44, 653, 14);
		add(separator);
		
		



		
		
		
		JButton btnZaloguj = new JButton("Zaloguj");
		btnZaloguj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					zaloguj(textIdKlienta.getText(), textMailKlienta.getText());
		
			}
		});
		btnZaloguj.setBounds(155, 196, 113, 23);
		add(btnZaloguj);
		

		
		lblOk = new JLabel("Oferta dodana do bazy danych");
		lblOk.setForeground(Color.GREEN);
		lblOk.setFont(new Font("Calibri", Font.BOLD, 15));
		lblOk.setIcon(new ImageIcon(Logowanie.class.getResource("/javax/swing/plaf/metal/icons/Inform.gif")));
		lblOk.setBounds(399, -8, 240, 64);
		add(lblOk);
		lblOk.setVisible(false);
		
		lblFalse = new JLabel("Niepoprawne dane");
		lblFalse.setForeground(Color.RED);
		lblFalse.setFont(new Font("Calibri", Font.BOLD, 15));
		lblFalse.setIcon(new ImageIcon(Logowanie.class.getResource("/javax/swing/plaf/metal/icons/Error.gif")));
		lblFalse.setBounds(399, -8, 240, 64);
		add(lblFalse);
		
		JButton btnUtwrzKonto = new JButton("Utw\u00F3rz konto");
		btnUtwrzKonto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.setVisible(true);
			}
		});
		btnUtwrzKonto.setBounds(10, 197, 135, 22);
		add(btnUtwrzKonto);
		lblFalse.setVisible(false);
		
		
		
		
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(314, 69, 304, 310);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblDane = new JLabel("Dane:");
		lblDane.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblDane.setBounds(10, 11, 79, 17);
		panel_1.add(lblDane);
		
		rdbtnOsobaPrywatna = new JRadioButton("osoba prywatna");

		buttonGroup.add(rdbtnOsobaPrywatna);
		rdbtnOsobaPrywatna.setSelected(true);
		rdbtnOsobaPrywatna.setBounds(10, 59, 140, 23);
		panel_1.add(rdbtnOsobaPrywatna);
		
		rdbtnFirma = new JRadioButton("firma");
		buttonGroup.add(rdbtnFirma);
		rdbtnFirma.setBounds(152, 59, 109, 23);
		panel_1.add(rdbtnFirma);
		
		JLabel lblId = new JLabel("Id :");
		lblId.setVisible(false);
		lblId.setBounds(112, 14, 22, 14);
		panel_1.add(lblId);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setText("Id");
		txtId.setVisible(false);
		txtId.setBounds(132, 11, 54, 20);
		panel_1.add(txtId);
		txtId.setColumns(10);
		
		JLabel lblImi = new JLabel("Imi\u0119:");
		lblImi.setBounds(10, 92, 42, 14);
		panel_1.add(lblImi);
		
		textImie = new JTextField();
		textImie.setColumns(10);
		textImie.setBounds(83, 89, 152, 20);
		panel_1.add(textImie);
		
		JLabel lblNazwisko = new JLabel("Nazwisko:");
		lblNazwisko.setBounds(10, 114, 63, 14);
		panel_1.add(lblNazwisko);
		
		textNazwisko = new JTextField();
		textNazwisko.setColumns(10);
		textNazwisko.setBounds(83, 111, 152, 20);
		panel_1.add(textNazwisko);
		
		JLabel lblMiasto = new JLabel("Miasto:");
		lblMiasto.setBounds(10, 183, 63, 14);
		panel_1.add(lblMiasto);
		
		textMiasto = new JTextField();
		textMiasto.setColumns(10);
		textMiasto.setBounds(83, 180, 152, 20);
		panel_1.add(textMiasto);
		
		textAdres = new JTextField();
		textAdres.setColumns(10);
		textAdres.setBounds(83, 158, 152, 20);
		panel_1.add(textAdres);
		
		JLabel lblAdres = new JLabel("Adres:");
		lblAdres.setBounds(10, 161, 42, 14);
		panel_1.add(lblAdres);
		
		JLabel lblTelefon = new JLabel("Telefon");
		lblTelefon.setBounds(10, 229, 63, 14);
		panel_1.add(lblTelefon);
		
		textTelefon = new JTextField();
		textTelefon.setColumns(10);
		textTelefon.setBounds(83, 226, 92, 20);
		panel_1.add(textTelefon);
		
		textPoczta = new JTextField();
		textPoczta.setColumns(10);
		textPoczta.setBounds(83, 204, 92, 20);
		panel_1.add(textPoczta);
		
		JLabel lblKodPocztowy = new JLabel("Kod Pocztowy:");
		lblKodPocztowy.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblKodPocztowy.setBounds(10, 207, 79, 14);
		panel_1.add(lblKodPocztowy);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(10, 253, 63, 14);
		panel_1.add(lblEmail);
		
		textMail = new JTextField();
		textMail.setColumns(10);
		textMail.setBounds(83, 250, 152, 20);
		panel_1.add(textMail);
		
		lblPesel = new JLabel("PESEL:");
		lblPesel.setBounds(10, 134, 63, 14);
		panel_1.add(lblPesel);
		
		textNumer = new JTextField();
		textNumer.setColumns(10);
		textNumer.setBounds(83, 134, 152, 20);
		panel_1.add(textNumer);
		
		textFirma = new JTextField();
		textFirma.setBounds(83, 32, 152, 20);
		panel_1.add(textFirma);
		textFirma.setColumns(10);
		
		lblNazwa = new JLabel("Nazwa");
		lblNazwa.setBounds(10, 38, 46, 14);
		panel_1.add(lblNazwa);
		
		btnUtwrz = new JButton("Utw\u00F3rz");
		btnUtwrz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
	dodajKlienta();
				
				
				
				
				
			}
		});
		btnUtwrz.setBounds(187, 276, 89, 23);
		panel_1.add(btnUtwrz);
		
		btnWyczysc = new JButton("Wyczysc");
		btnWyczysc.setBounds(93, 276, 89, 23);
		btnWyczysc.setVisible(false);
		panel_1.add(btnWyczysc);
		lblNazwa.setVisible(false);
		textFirma.setVisible(false);
		
		lblFalse.setVisible(false);
		
		rdbtnOsobaPrywatna.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rdbtnOsobaPrywatna.isSelected())
				{
					lblPesel.setText("PESEL"); 
					lblNazwa.setVisible(false);
					textFirma.setVisible(false);
				}
				else
				{
					lblPesel.setText("NIP"); 
					lblNazwa.setVisible(true);
					textFirma.setVisible(true);
				}
			}
		});
panel_1.setVisible(false);
	}

	
protected void zaloguj(String id, String mail)  {
		polacz();
		String imie = null;
		String query = "SELECT \"Imiê\" FROM \"Klienci\" WHERE"
		+ "\"IdKlienta\"=\'"+textIdKlienta.getText()+"\' AND "
		+ " \"e-mail\"=\'"+textMailKlienta.getText()+"\'";
		System.out.println(query);
		try {
			myStmt = conn.createStatement();
			myRs = myStmt.executeQuery(query);
			while (myRs.next()) {
					imie=myRs.getString(1);
			}	
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Brak takiego klienta");
		}
		idKlienta = textIdKlienta.getText();
		lblZawieranieUmowyZ.setText("Witaj "+imie);
		
	}


public void polacz(){
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String USER = "C##DAMIAN";  
		String PASS = "damian";  
		String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
		try {
			
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		

	}

public Boolean sprawdzPoprawnosc()
{

	if (textImie.getText().equals(""))
		return false;
	else if (textNazwisko.getText().equals(""))
		return false;
	else if (textMiasto.getText().equals(""))
		return false;
	else if (textAdres.getText().equals(""))
		return false;
	else if (textPoczta.getText().equals(""))
		return false;
	else if (textNumer.getText().equals(""))
		return false;
	else if(rdbtnFirma.isSelected() && textFirma.getText().equals(""))
		return false;
	else 
		return true;
}

public void dodajKlienta() {
	polacz();
	String query;
	if(rdbtnOsobaPrywatna.isSelected())
		query = "INSERT INTO \"Klienci\" "
				+ "(\"Imiê\", \"Nazwisko\", \"Miasto\", \"Adres\", \"KodPocztowy\", \"e-mail\", \"NazwaFirmy\", \"NIP\", \"PESEL\", \"Telefon\" )"
				+ " VALUES ("
				+"\'"+textImie.getText()+"\', "
				+"\'"+textNazwisko.getText()+"\', "
				+"\'"+textMiasto.getText()+"\', "
				+"\'"+textAdres.getText()+"\', "
				+"\'"+textPoczta.getText()+"\', "
				+"\'"+textMail.getText()+"\', "
				+"\'\', "
				+"\'\', "
				+"\'"+textNumer.getText()+"\', "
				+"\'"+textTelefon.getText()+"\')";
			
	else
		query = "INSERT INTO \"Klienci\""
				+ "(\"Imiê\", \"Nazwisko\", \"Miasto\", \"Adres\", \"KodPocztowy\", \"e-mail\", \"NazwaFirmy\", \"NIP\", \"PESEL\" , \"Telefon\" )"
				+ " VALUES ("
				+"\'"+textImie.getText()+"\', "
				+"\'"+textNazwisko.getText()+"\', "
				+"\'"+textMiasto.getText()+"\', "
				+"\'"+textAdres.getText()+"\', "
				+"\'"+textPoczta.getText()+"\', "
				+"\'"+textMail.getText()+"\', "
				+" \'"+textFirma.getText()+"\', "
				+"\'"+textNumer.getText()+"\', "
				+"\'\' ,"
				+"\'"+textTelefon.getText()+"\')";
				System.out.println(query);
	if(sprawdzPoprawnosc()){
	try {
		System.out.println("Sprawdzilem");
		myStmt = conn.createStatement();
		myRs = myStmt.executeQuery(query);
		query = "commit";
		System.out.println(query);
		myStmt = conn.createStatement();
		myRs = myStmt.executeQuery(query);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("Wyjebalem SIe");
	}
	finally {
		
	}
	lblFalse.setVisible(false);
	try {
		lblOk.setText("Twoje ID : " + pobierzNoweId());
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	lblOk.setVisible(true);}
	else{
		lblFalse.setVisible(true);
		lblOk.setVisible(false);
		
	}

}

public String pobierzNoweId() throws SQLException{
	String query;
	query = "SELECT \"IdKlienta\" FROM \"Klienci\" WHERE"
			+ "\"Imiê\"=\'"+textImie.getText()+"\' AND "
			+ " \"Nazwisko\"=\'"+textNazwisko.getText()+"\'";
	System.out.println(query);
	myStmt = conn.createStatement();
	myRs = myStmt.executeQuery(query);
	while (myRs.next()) {
		idKlienta=myRs.getString(1);
	}
	
	return idKlienta;
	
}
}
