import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.ImageIcon;


public class AddOffer extends JPanel {
	private JTextField textWyjazdDzien;
	private JTextField textWyjazdMiesiac;
	private JTextField textWyjazdRok;
	private JTextField textPowrotDzien;
	private JTextField textPowrotMiesiac;
	private JTextField textPowrotRok;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox comboKraj ;
	private JComboBox comboHotel;
	private JComboBox comboTransport;
	private JComboBox comboHotelUmowa;
	private JComboBox comboTransportUmowa;
	private JRadioButton rdbtnAllInclusive;
	private JRadioButton rdbtnStandard;
	private JLabel lblOk;
	private JLabel lblFalse;
	
	private Connection conn = null;
	private Statement myStmt = null;
	private ResultSet myRs = null;
	
	private JTextField textIloscMiejsc;
	private JTextField textCena;
	
	private String IdUmowyHotelu;
	private String IdHotelu;
	private String IdUmowyTransportu;
	private String IdTransportu;
	

	/**
	 * Create the panel.
	 */
	public AddOffer() {
		setLayout(null);
		setBounds(0,0,653,406);
		
		JLabel label = new JLabel("Tworzenie nowej oferty");
		label.setForeground(Color.BLUE);
		label.setFont(new Font("Verdana", Font.BOLD, 14));
		label.setBounds(10, 11, 330, 22);
		add(label);
		
		JSeparator separator = new JSeparator();
		separator.setToolTipText("jihhihiu");
		separator.setForeground(Color.YELLOW);
		separator.setBackground(Color.PINK);
		separator.setBounds(0, 44, 653, 14);
		add(separator);
		
		JLabel lblKraj = new JLabel("Kraj:");
		lblKraj.setBounds(10, 84, 46, 14);
		add(lblKraj);
		
		JLabel lblStandard = new JLabel("Standard:");
		lblStandard.setBounds(10, 159, 69, 14);
		add(lblStandard);
		
		JLabel lblDataWyjazdu = new JLabel("Data Wyjazdu:");
		lblDataWyjazdu.setBounds(10, 210, 83, 14);
		add(lblDataWyjazdu);
		
		JLabel lblDataPowrotu = new JLabel("Data Powrotu");
		lblDataPowrotu.setBounds(10, 235, 69, 14);
		add(lblDataPowrotu);
		
		JLabel lblHotel = new JLabel("Hotel:");
		lblHotel.setBounds(10, 106, 46, 14);
		add(lblHotel);
		
		JLabel lblTransport = new JLabel("Transport:");
		lblTransport.setBounds(10, 131, 69, 14);
		add(lblTransport);
		
		comboKraj = new JComboBox();


		comboKraj.setBounds(101, 81, 138, 20);
		add(comboKraj);
		uzupelnijListeKrajow();
		
		comboHotel = new JComboBox();
		comboHotel.setBounds(101, 103, 138, 20);
		add(comboHotel);
		
		comboTransport = new JComboBox();
		comboTransport.setBounds(101, 125, 138, 20);
		add(comboTransport);
		
		rdbtnAllInclusive = new JRadioButton("All Inclusive");
		rdbtnAllInclusive.setSelected(true);
		buttonGroup.add(rdbtnAllInclusive);
		rdbtnAllInclusive.setBounds(101, 155, 89, 23);
		add(rdbtnAllInclusive);
		
		rdbtnStandard = new JRadioButton("Standard");
		buttonGroup.add(rdbtnStandard);
		rdbtnStandard.setBounds(193, 155, 109, 23);
		add(rdbtnStandard);
		
		textWyjazdDzien = new JTextField();
		textWyjazdDzien.setBounds(100, 207, 38, 20);
		add(textWyjazdDzien);
		textWyjazdDzien.setColumns(10);
		
		JLabel label_1 = new JLabel("/");
		label_1.setBounds(143, 210, 4, 14);
		add(label_1);
		
		textWyjazdMiesiac = new JTextField();
		textWyjazdMiesiac.setColumns(10);
		textWyjazdMiesiac.setBounds(148, 207, 38, 20);
		add(textWyjazdMiesiac);
		
		JLabel label_2 = new JLabel("/");
		label_2.setBounds(190, 210, 4, 14);
		add(label_2);
		
		textWyjazdRok = new JTextField();
		textWyjazdRok.setColumns(10);
		textWyjazdRok.setBounds(200, 207, 57, 20);
		add(textWyjazdRok);
		
		textPowrotDzien = new JTextField();
		textPowrotDzien.setColumns(10);
		textPowrotDzien.setBounds(100, 229, 38, 20);
		add(textPowrotDzien);
		
		JLabel label_3 = new JLabel("/");
		label_3.setBounds(143, 232, 4, 14);
		add(label_3);
		
		textPowrotMiesiac = new JTextField();
		textPowrotMiesiac.setColumns(10);
		textPowrotMiesiac.setBounds(148, 229, 38, 20);
		add(textPowrotMiesiac);
		
		JLabel label_4 = new JLabel("/");
		label_4.setBounds(190, 232, 4, 14);
		add(label_4);
		
		textPowrotRok = new JTextField();
		textPowrotRok.setColumns(10);
		textPowrotRok.setBounds(200, 229, 57, 20);
		add(textPowrotRok);
		
		JButton btnSzukajUmowy = new JButton("Szukaj umowy");
		btnSzukajUmowy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboHotel.getSelectedIndex() != -1){
				comboHotelUmowa.removeAllItems();
				String query = "SELECT * FROM \"UmowyZHotelami\" uh WHERE uh.\"IdHotelu\" IN (SELECT \"IdHotelu\" FROM \"Hotele\" h WHERE h.\"Nazwa\" = \'" + comboHotel.getSelectedItem() + "\')";
						
				try {
					myStmt = conn.createStatement();
					myRs = myStmt.executeQuery(query);
			
					while (myRs.next()) {
						String temp = new String(myRs.getString(3));
						IdUmowyHotelu = new String(myRs.getString(1));
						IdHotelu = new String(myRs.getString(2));
						comboHotelUmowa.addItem(temp);
					}	
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {
					
				}
				if(comboHotelUmowa.getItemCount() == 0)
					comboHotelUmowa.addItem("Brak umowy w bazie");
			}}
		});
		btnSzukajUmowy.setBounds(249, 103, 145, 23);
		add(btnSzukajUmowy);
		
		JButton button = new JButton("Szukaj umowy");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(comboTransport.getSelectedIndex() != -1){
				comboTransportUmowa.removeAllItems();
				String query = "SELECT * FROM \"UmowyTransportoweSezonowe\" ut WHERE ut.\"IdFirmyTransportowej\" IN (SELECT \"IdFirmyTransportowej\" FROM \"FirmyTransportowe\" f WHERE f.\"Nazwa\" = \'" + comboTransport.getSelectedItem() + "\')";
				//System.out.println(query);
				try {
					myStmt = conn.createStatement();
					myRs = myStmt.executeQuery(query);
			
					while (myRs.next()) {
						String temp = new String(myRs.getString(3));
						IdUmowyTransportu = new String(myRs.getString(1));
						IdTransportu = new String(myRs.getString(2));
						comboTransportUmowa.addItem(temp);
					}	
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {
					
				}
				if(comboTransportUmowa.getItemCount() == 0)
					comboTransportUmowa.addItem("Brak umowy w bazie");
			
			}}
		});
		button.setBounds(249, 125, 145, 23);
		add(button);
		
		comboHotelUmowa = new JComboBox();
		comboHotelUmowa.setBounds(404, 103, 162, 20);
		add(comboHotelUmowa);
		
		comboTransportUmowa = new JComboBox();
		comboTransportUmowa.setBounds(404, 128, 162, 20);
		add(comboTransportUmowa);
		
		JButton button_1 = new JButton("Wprowad\u017A");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String standard;
				if(rdbtnAllInclusive.isSelected())
					standard="ALL_INCLUSIVE";
				else
					standard="STANDARD";
				
				String query = "INSERT INTO  \"OfertaWczasów\" "
						+ "(\"Kraj\", \"Standard\",\"DataWyjazdu\",\"DataPowrotu\", \"LiczbaMiejsc\",\"DokonanychRezerwacji\","
						+ "\"Cena\", \"IdUmowyHotelu\",\"IdTransportu\")"
						+ "VALUES (\'"
						+comboKraj.getSelectedItem()+"\', \'"
						+standard+"\', \'"
						+textWyjazdRok.getText()+"-"+textWyjazdMiesiac.getText()+"-"+textWyjazdDzien.getText()+"\', \'"
						+textPowrotRok.getText()+"-"+textPowrotMiesiac.getText()+"-"+textPowrotDzien.getText()+"\', \'"
						+textIloscMiejsc.getText()+"\', \'"
						+"0"+"\', \'"
						+textCena.getText()+"\', \'"
						+IdUmowyHotelu+"\', \'"
						+IdUmowyTransportu+"\' )";

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
				lblOk.setVisible(true);
				lblFalse.setVisible(false);}
				else
				{
					lblFalse.setVisible(true);
					lblOk.setVisible(false);
					
				}
				
			}
		});
		button_1.setBounds(495, 372, 113, 23);
		add(button_1);
		
		JButton button_2 = new JButton("Anuluj");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				czysc();
			}
		});
		button_2.setBounds(372, 372, 113, 23);
		add(button_2);
		
		textIloscMiejsc = new JTextField();
		textIloscMiejsc.setBounds(100, 179, 86, 20);
		add(textIloscMiejsc);
		textIloscMiejsc.setColumns(10);
		
		JLabel lblLiczbaMiejsc = new JLabel("Liczba miejsc:");
		lblLiczbaMiejsc.setBounds(10, 184, 83, 14);
		add(lblLiczbaMiejsc);
		
		JLabel lblCenaosobe = new JLabel("Cena /osobe:");
		lblCenaosobe.setBounds(10, 265, 83, 14);
		add(lblCenaosobe);
		
		textCena = new JTextField();
		textCena.setColumns(10);
		textCena.setBounds(100, 260, 86, 20);
		add(textCena);
		
		lblOk = new JLabel("Oferta dodana do bazy danych");
		lblOk.setForeground(Color.GREEN);
		lblOk.setFont(new Font("Calibri", Font.BOLD, 15));
		lblOk.setIcon(new ImageIcon(AddOffer.class.getResource("/javax/swing/plaf/metal/icons/Inform.gif")));
		lblOk.setBounds(10, 331, 240, 64);
		add(lblOk);
		
		lblFalse = new JLabel("Niepoprawne dane");
		lblFalse.setForeground(Color.RED);
		lblFalse.setFont(new Font("Calibri", Font.BOLD, 15));
		lblFalse.setIcon(new ImageIcon(AddOffer.class.getResource("/javax/swing/plaf/metal/icons/Error.gif")));
		lblFalse.setBounds(10, 331, 240, 64);
		add(lblFalse);
		lblFalse.setVisible(false);
		
		comboKraj.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent e) {
	           try {
				pobierzDane();
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
	        }
	    });

	}
	
	private void uzupelnijListeKrajow(){
		comboKraj.addItem("Chorwacja");
		comboKraj.addItem("Egipt");
		comboKraj.addItem("Francja");
		comboKraj.addItem("Hiszpania");
		comboKraj.addItem("Polska");
		comboKraj.addItem("Portugalia");
		comboKraj.addItem("Turcja");
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
	
		comboHotel.removeAllItems();
		comboTransport.removeAllItems();
		String query = "select * from \"Hotele\" H WHERE H.\"Kraj\" = \'" + comboKraj.getSelectedItem() + "\'";
				
		try {
			myStmt = conn.createStatement();
			myRs = myStmt.executeQuery(query);
	
			while (myRs.next()) {
				String temp = new String(myRs.getString(2));
				comboHotel.addItem(temp);
			}	
			
			query = "select * from \"FirmyTransportowe\"";
			myStmt = conn.createStatement();
			myRs = myStmt.executeQuery(query);
	
			while (myRs.next()) {
				String temp = new String(myRs.getString(3));
				comboTransport.addItem(temp);
			}		
			
		}
		finally {
			
		}
	}

	private Boolean sprawdzPoprawnosc(){
		if (textWyjazdRok.getText().equals(""))
			return false;
		else if (textWyjazdMiesiac.getText().equals(""))
			return false;
		else if (textWyjazdDzien.getText().equals(""))
			return false;
		else if (textPowrotRok.getText().equals(""))
			return false;
		else if (textPowrotMiesiac.getText().equals(""))
			return false;
		else if (textPowrotDzien.getText().equals(""))
			return false;
		else if (textIloscMiejsc.getText().equals(""))
			return false;
		else if (textCena.getText().equals(""))
			return false;
		else if (comboHotel.getSelectedIndex() == -1)
			return false;
		else if (comboTransport.getSelectedIndex() == -1)
			return false;
		else if (comboHotelUmowa.getSelectedIndex() == -1)
			return false;
		else if (comboTransportUmowa.getSelectedIndex() == -1)
			return false;
		else 
			return true;
	}
	
	public void czysc(){
	
		textWyjazdRok.setText("");
		textWyjazdMiesiac.setText("");
		textWyjazdDzien.setText("");
		textPowrotRok.setText("");
		textPowrotMiesiac.setText("");
		textPowrotDzien.setText("");
		textIloscMiejsc.setText("");
		textCena.setText("");
		comboHotel.removeAllItems();
		comboTransport.removeAllItems();
		comboHotelUmowa.removeAllItems();
		comboTransportUmowa.removeAllItems();
		lblOk.setVisible(false);
		lblFalse.setVisible(false);
	}
}
