import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.Component;

import javax.swing.JSeparator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class AddTransportContract extends JPanel {
	private JTextField textRok;
	private JTextField textCena;
	private JTextField textStart;
	private JTextField textEnd;
	private JTextField textZakres;
	private JLabel lblOk;
	private JLabel lblFalse;
	
	private JLabel lblPoczatekTrasy;
	private JLabel lblKoniecTrasy;
	private JComboBox comboRodzaj;
	private JComboBox comboFirma;
	
	private Connection conn = null;
	private Statement myStmt = null;
	private ResultSet myRs = null;
	

	/**
	 * Create the panel.
	 */
	public AddTransportContract() {
		setLayout(null);
		setBounds(0,0,653,406);
		
		JLabel label = new JLabel("Dodawanie umowy z firm¹ transportow¹");
		label.setForeground(Color.BLUE);
		label.setFont(new Font("Verdana", Font.BOLD, 14));
		label.setBounds(10, 11, 330, 22);
		add(label);
		
		comboFirma = new JComboBox();
		comboFirma.setBounds(10, 80, 436, 20);
		add(comboFirma);
		
		textRok = new JTextField();
		textRok.setColumns(10);
		textRok.setBounds(109, 129, 113, 20);
		add(textRok);
		
		textCena = new JTextField();
		textCena.setColumns(10);
		textCena.setBounds(109, 154, 113, 20);
		add(textCena);
		
		JButton buttonCommit = new JButton("Wprowad\u017A");
		buttonCommit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				polacz();
				String query = "INSERT INTO  \"UmowyTransportoweSezonowe\""
						+ "(\"IdFirmyTransportowej\",\"Rodzaj\", \"RokUmowy\",\"ZakresDzialania\", \"PoczatekTrasy\",\"KoniecTrasy\",\"Cena\")"
						+ " VALUES ("
						+"(SELECT \"IdFirmyTransportowej\" FROM \"FirmyTransportowe\" ft WHERE ft.\"Nazwa\" = \'"+comboFirma.getSelectedItem()+"\') , \'"
						+comboRodzaj.getSelectedItem()+"\' , \'"
						+textRok.getText()+"\' , \'"
						+textZakres.getText()+"\' , \'"
						+textStart.getText()+"\' , \'"
						+textEnd.getText()+"\' , \'"
						+textCena.getText()+"\' )";		
				System.out.println(query);
				if(sprawdzPoprawnosc()){
				try {
					myStmt = conn.createStatement();
					myRs = myStmt.executeQuery(query);
					query = "commit";
					myStmt = conn.createStatement();
					myRs = myStmt.executeQuery(query);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {
					
				}
				
				lblOk.setVisible(true);}
				else
					lblFalse.setVisible(true);
			}
		});
		buttonCommit.setBounds(495, 372, 113, 23);
		add(buttonCommit);
		
		JLabel lblFirma = new JLabel("Firma");
		lblFirma.setBounds(149, 55, 46, 14);
		add(lblFirma);
		
		JLabel label_4 = new JLabel("Rok");
		label_4.setBounds(10, 132, 46, 14);
		add(label_4);
		
		JLabel label_5 = new JLabel("Cena");
		label_5.setBounds(10, 157, 46, 14);
		add(label_5);
		
		JButton buttonCancel = new JButton("Anuluj");
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				czysc();
				lblOk.setVisible(false);
			}
		});
		buttonCancel.setBounds(372, 372, 113, 23);
		add(buttonCancel);
		
		JButton button_5 = new JButton("Wczytaj");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					pobierzDane();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_5.setBounds(10, 372, 89, 23);
		add(button_5);
		
		JSeparator separator = new JSeparator();
		separator.setToolTipText("jihhihiu");
		separator.setForeground(Color.YELLOW);
		separator.setBackground(Color.PINK);
		separator.setBounds(0, 44, 653, 14);
		add(separator);
		
		textStart = new JTextField();
		textStart.setColumns(10);
		textStart.setBounds(109, 242, 147, 20);
		add(textStart);
		
		textEnd = new JTextField();
		textEnd.setColumns(10);
		textEnd.setBounds(109, 267, 147, 20);
		add(textEnd);
		
		lblPoczatekTrasy = new JLabel("Poczatek trasy:");
		lblPoczatekTrasy.setBounds(10, 245, 99, 14);
		add(lblPoczatekTrasy);
		
		lblKoniecTrasy = new JLabel("Koniec Trasy:");
		lblKoniecTrasy.setBounds(10, 270, 89, 14);
		add(lblKoniecTrasy);
		
		comboRodzaj = new JComboBox();
		comboRodzaj.setBounds(109, 183, 113, 20);
		add(comboRodzaj);
		comboRodzaj.addItem("Bus");
		comboRodzaj.addItem("Autokar");
		comboRodzaj.addItem("Samolot");
		
		JLabel lblRodzaj = new JLabel("Rodzaj");
		lblRodzaj.setBounds(10, 186, 46, 14);
		add(lblRodzaj);
		
		textZakres = new JTextField();
		textZakres.setColumns(10);
		textZakres.setBounds(109, 214, 147, 20);
		add(textZakres);
		
		JLabel lblZakresDziaania = new JLabel("Zakres dzia\u0142ania:");
		lblZakresDziaania.setBounds(10, 217, 99, 14);
		add(lblZakresDziaania);
		
		lblOk = new JLabel("Oferta dodana do bazy danych");
		lblOk.setForeground(Color.GREEN);
		lblOk.setFont(new Font("Calibri", Font.BOLD, 15));
		lblOk.setIcon(new ImageIcon(AddOffer.class.getResource("/javax/swing/plaf/metal/icons/Inform.gif")));
		lblOk.setBounds(372, 297, 240, 64);
		add(lblOk);
		lblOk.setVisible(false);
		
		lblFalse = new JLabel("Niepoprawne dane");
		lblFalse.setForeground(Color.RED);
		lblFalse.setFont(new Font("Calibri", Font.BOLD, 15));
		lblFalse.setIcon(new ImageIcon(AddOffer.class.getResource("/javax/swing/plaf/metal/icons/Error.gif")));
		lblFalse.setBounds(372, 297, 240, 64);
		add(lblFalse);
		lblFalse.setVisible(false);


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
	
	public void pobierzDane() throws Exception{
		
		polacz();
		comboFirma.removeAllItems();
		
		try {
			myStmt = conn.createStatement();
			myRs = myStmt.executeQuery("select * from \"FirmyTransportowe\"");
			
			while (myRs.next()) {
				String temp = new String(myRs.getString(3));
				comboFirma.addItem(temp);
			}		
		}
		finally {
			
		}
	}
	
	public Boolean sprawdzPoprawnosc()
	{
 if (textRok.getText().equals(""))
			return false;
		else if (textZakres.getText().equals(""))
			return false;
		else if (textStart.getText().equals(""))
			return false;
		else if (textEnd.getText().equals(""))
			return false;
		else if (textCena.getText().equals(""))
			return false;
		else if (comboFirma.getSelectedIndex() == -1)
			return false;
		else if (comboRodzaj.getSelectedIndex() == -1)
			return false;
		else 
			return true;
	}
	
	public void czysc(){
		
		textRok.setText("");
		textZakres.setText("");
		textStart.setText("");;
		textEnd.setText("");
		textCena.setText("");	
		comboFirma.removeAllItems();
		lblOk.setVisible(false);
		lblFalse.setVisible(false);
	}
	
	
}
