import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JSeparator;
import javax.swing.JList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import java.awt.FlowLayout;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class Klienci extends JPanel {
	private JLabel lblOk;
	private JLabel lblFalse;

	private JTable table;

	private DefaultTableModel model;
	
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
		
		if(table.getSelectedRow() != -1){
		
		String query = "SELECT * FROM \"Klienci\" k WHERE k.\"IdKlienta\" = '" + model.getValueAt(table.getSelectedRow(), 0) + "\'" ;
		
		
		try {
			myStmt = conn.createStatement();
			myRs = myStmt.executeQuery(query);
			
			while (myRs.next()) {
				String temp = new String(myRs.getString(2));
				
				txtId.setText(myRs.getString(1));
				textImie.setText(myRs.getString(2));
				textNazwisko.setText(myRs.getString(3));
				textAdres.setText(myRs.getString(4));
				textMiasto.setText(myRs.getString(5));
				textPoczta.setText(myRs.getString(6));
				textTelefon.setText(myRs.getString(7));
				textMail.setText(myRs.getString(8));
				
				if(!(myRs.getString(10) == null)){
					textFirma.setText(myRs.getString(10));
					textFirma.setVisible(true);
					lblNazwa.setVisible(true);
					rdbtnFirma.setSelected(true);
					lblPesel.setText("NIP:");
					textNumer.setText(myRs.getString(11));
				}else{
					textFirma.setText("");
					textFirma.setVisible(false);
					lblNazwa.setVisible(false);
					rdbtnOsobaPrywatna.setSelected(true);
					lblPesel.setText("PESEL:");
					textNumer.setText(myRs.getString(9));
				}
			}		
		}
		finally {
			
		}}
	}
	
	public void wyswietlKlientow() throws Exception{
		polacz();
		while(model.getRowCount() > 0 )
			for(int i=0; i< model.getRowCount(); i++ )
				model.removeRow(i);	
		
		try {
			myStmt = conn.createStatement();
			myRs = myStmt.executeQuery("select * from \"Klienci\"");
			
			while (myRs.next()) {
				String Nazwisko = new String(myRs.getString(3));
				String Imie = new String(myRs.getString(2));
				String Id = new String(myRs.getString(1));
				Object[][] rowData2 = {{Id,Nazwisko, Imie}};
				model.addRow(rowData2[0]);	
			}		
		}
		finally {}
	}
	
	public void czysc(){
		txtId.setText("");
		textImie.setText("");
		textNazwisko.setText("");
		textMiasto.setText("");
		textAdres.setText("");
		textTelefon.setText("");
		textPoczta.setText("");
		textNumer.setText("");
		textFirma.setText("");
		textMail.setText("");
		lblOk.setVisible(false);
		lblFalse.setVisible(false);
	}
	
	public void zablokuj(Boolean yes){
		if(yes==true){
		txtId.setEnabled(false);
		textImie.setEnabled(false);
		textNazwisko.setEnabled(false);
		textMiasto.setEnabled(false);
		textAdres.setEnabled(false);
		textTelefon.setEnabled(false);
		textPoczta.setEnabled(false);
		textNumer.setEnabled(false);
		textFirma.setEnabled(false);
		textMail.setEnabled(false);
		rdbtnFirma.setEnabled(false);
		rdbtnOsobaPrywatna.setEnabled(false);
		}
		else{
		txtId.setEnabled(false);
		textImie.setEnabled(true);
		textNazwisko.setEnabled(true);
		textMiasto.setEnabled(true);
		textAdres.setEnabled(true);
		textTelefon.setEnabled(true);
		textPoczta.setEnabled(true);
		textNumer.setEnabled(true);
		textFirma.setEnabled(true);
		textMail.setEnabled(true);
		rdbtnFirma.setEnabled(true);
		rdbtnOsobaPrywatna.setEnabled(true);}
		
	}
	
	public Boolean sprawdzPoprawnosc()
	{
		if(	txtId.getText().equals(""))
			return false;
		else if (textImie.getText().equals(""))
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
	

	/**
	 * Create the panel.
	 */
	public Klienci() {

		setLayout(null);
		setBounds(0,0,653,406);
		JLabel lblZawieranieUmowyZ = new JLabel("Baza Klient\u00F3w");
		lblZawieranieUmowyZ.setForeground(Color.BLUE);
		lblZawieranieUmowyZ.setFont(new Font("Verdana", Font.BOLD, 14));
		lblZawieranieUmowyZ.setBounds(10, 11, 330, 22);
		add(lblZawieranieUmowyZ);
		
		JButton btnNewButton = new JButton("Zastosuj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query;
				if(rdbtnOsobaPrywatna.isSelected())
					query = "UPDATE \"Klienci\" SET "
						+"\"Imiê\" = \'"+textImie.getText()+"\', "
						+"\"Nazwisko\" = \'"+textNazwisko.getText()+"\', "
						+"\"Miasto\" = \'"+textMiasto.getText()+"\', "
						+"\"Adres\" = \'"+textAdres.getText()+"\', "
						+"\"KodPocztowy\" = \'"+textPoczta.getText()+"\', "
						+"\"e-mail\" = \'"+textMail.getText()+"\', "
						+"\"NazwaFirmy\" = \'\', "
						+"\"NIP\" = \'\', "
						+"\"PESEL\" = \'"+textNumer.getText()+"\'"
						+"WHERE \"IdKlienta\" = \'"+txtId.getText()+"\'";
				else
					query = "UPDATE \"Klienci\" SET "
							+"\"Imiê\" = \'"+textImie.getText()+"\', "
							+"\"Nazwisko\" = \'"+textNazwisko.getText()+"\', "
							+"\"Miasto\" = \'"+textMiasto.getText()+"\', "
							+"\"Adres\" = \'"+textAdres.getText()+"\', "
							+"\"KodPocztowy\" = \'"+textPoczta.getText()+"\', "
							+"\"e-mail\" = \'"+textMail.getText()+"\', "
							+"\"NIP\" = \'"+textNumer.getText()+"\', "
							+"\"NazwaFirmy\" = \'"+textFirma.getText()+"\', "
							+"\"PESEL\" = \'\' "
							+"WHERE \"IdKlienta\" = \'"+txtId.getText()+"\'";
					
	
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
				lblFalse.setVisible(false);
				lblOk.setVisible(true);}
				else{
					lblFalse.setVisible(true);
					lblOk.setVisible(false);
					
				}
			
			}
		});
		btnNewButton.setBounds(495, 372, 113, 23);
		add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setToolTipText("jihhihiu");
		separator.setForeground(Color.YELLOW);
		separator.setBackground(Color.PINK);
		separator.setBounds(0, 42, 653, 14);
		add(separator);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 55, 319, 340);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblDodawaniePokoi = new JLabel("Przegl\u0105daj:");
		lblDodawaniePokoi.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblDodawaniePokoi.setBounds(10, 11, 79, 17);
		panel.add(lblDodawaniePokoi);
		
		JButton btnDodaj = new JButton("Modyfikuj");
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					pobierzDane();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				zablokuj(false);
				
			}
		});
		btnDodaj.setBounds(220, 81, 89, 23);
		panel.add(btnDodaj);
		
		JButton btnUsu = new JButton("Usu\u0144");
		btnUsu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRowCount() > 0){
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Czy napewno chcesz usun¹æ klienta z bazy danych?","Warning",dialogButton);
				if(dialogResult == JOptionPane.YES_OPTION){
					
					String query = "DELETE FROM \"Klienci\" WHERE \"IdKlienta\" = \'" + model.getValueAt(table.getSelectedRow(), 0) + "\'" ;
					try {
						myStmt = conn.createStatement();
				
					myRs = myStmt.executeQuery(query);
					query = "commit";
					myStmt = conn.createStatement();
					myRs = myStmt.executeQuery(query);
					wyswietlKlientow();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}	}

			}
		});
		btnUsu.setBounds(220, 104, 89, 23);
		panel.add(btnUsu);
		
		

		 String[] col = { "Id","Nazwisko", "Imie" };
		  Object[][] data = {  };
		  
		  model = new DefaultTableModel(data, col);
		  table = new JTable(model);
		  JScrollPane pane = new JScrollPane(table);

		  pane.setBounds(10, 36, 200, 293);
		  
		  panel.add(pane);
		  
		  JButton btnWczytaj = new JButton("Wczytaj");
		  btnWczytaj.setBounds(220, 59, 89, 23);
		  btnWczytaj.addActionListener(new ActionListener() {
			  	public void actionPerformed(ActionEvent arg0) {
			  		try {
						pobierzDane();
						zablokuj(true);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			  	}
			  });
		  panel.add(btnWczytaj);
		  
		  JButton btnZaaduj = new JButton("Za\u0142aduj");
		  btnZaaduj.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		try {
					wyswietlKlientow();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  	}
		  });
		  btnZaaduj.setBounds(220, 36, 89, 23);
		  panel.add(btnZaaduj);
		
		JButton btnNewButton_1 = new JButton("Anuluj");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			czysc();
			}
		});
		btnNewButton_1.setBounds(372, 372, 113, 23);
		add(btnNewButton_1);
		
		lblOk = new JLabel("Zaktualizowano dane");
		lblOk.setForeground(Color.GREEN);
		lblOk.setFont(new Font("Calibri", Font.BOLD, 15));
		lblOk.setIcon(new ImageIcon(AddOffer.class.getResource("/javax/swing/plaf/metal/icons/Inform.gif")));
		lblOk.setBounds(402, -8, 240, 64);
		add(lblOk);
		lblOk.setVisible(false);
		
		lblFalse = new JLabel("Niepoprawne dane");
		lblFalse.setForeground(Color.RED);
		lblFalse.setFont(new Font("Calibri", Font.BOLD, 15));
		lblFalse.setIcon(new ImageIcon(AddOffer.class.getResource("/javax/swing/plaf/metal/icons/Error.gif")));
		lblFalse.setBounds(402, -8, 240, 64);
		add(lblFalse);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(339, 55, 304, 310);
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
		lblId.setBounds(10, 35, 22, 14);
		panel_1.add(lblId);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setText("Id");
		txtId.setBounds(33, 32, 54, 20);
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
		textFirma.setBounds(124, 32, 152, 20);
		panel_1.add(textFirma);
		textFirma.setColumns(10);
		
		lblNazwa = new JLabel("Nazwa");
		lblNazwa.setBounds(176, 14, 46, 14);
		panel_1.add(lblNazwa);
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

	}
}
