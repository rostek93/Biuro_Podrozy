package Klient;


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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Oferty extends JPanel {
	
	private JLabel lblOk;
	private JLabel lblFalse;

	private JComboBox comboKraj ;
	private JComboBox comboHotel;
	private JComboBox comboTransportu;
	
	private JTextField textWyjazdDzien;
	private JTextField textWyjazdMiesiac;
	private JTextField textWyjazdRok;
	private JTextField textPowrotDzien;
	private JTextField textPowrotMiesiac;
	private JTextField textPowrotRok;
	
	private JRadioButton rdbtnAllInclusive;
	private JRadioButton rdbtnStandard;
	private JRadioButton rdbtnDowolny ;
	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	
	private JTable table;

	private DefaultTableModel model;
	
	private Connection conn = null;
	private Statement myStmt = null;
	private ResultSet myRs = null;
	private Connection conn2 = null;
	private Statement myStmt2 = null;
	private ResultSet myRs2 = null;
	private Connection conn3 = null;
	private Statement myStmt3 = null;
	private ResultSet myRs3 = null;
	
	private JTextField txtIloscrezerwacji;
	

	/**
	 * Create the panel.
	 */
	public Oferty() {
		setLayout(null);
		setBounds(0,0,653,406);
		JLabel lblZawieranieUmowyZ = new JLabel("Oferty wczas\u00F3w");
		lblZawieranieUmowyZ.setForeground(Color.BLUE);
		lblZawieranieUmowyZ.setFont(new Font("Verdana", Font.BOLD, 14));
		lblZawieranieUmowyZ.setBounds(10, 11, 330, 22);
		add(lblZawieranieUmowyZ);
		
		JButton btnNewButton = new JButton("Rezerwuj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				System.out.println(dateFormat.format(date)); 
				String query;
				String value=null;
				
				int row = table.getSelectedRow();
				if (row != -1 && Logowanie.idKlienta != null) {
				   row = table.convertRowIndexToModel(row);
				   value = (String) model.getValueAt(row, 0);
				   System.out.println(value);
				
				
				
				query = "INSERT INTO \"ZamówieniaWczasy\" (\"DataUtworzenia\", \"Status\", \"Rabat\", \"IdKlienta\", \"IdOferty\") VALUES("
						+"\'"+dateFormat.format(date)+"\', "
						+"\'Zlozone\', "
						+"\'0\', "
						+"\'"+Logowanie.idKlienta+"\', "
						+"\'"+value+"\') ";
				 System.out.println(query);
	
				if(true){
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
				lblOk.setVisible(true);}}
				else{
					lblFalse.setVisible(true);
					lblOk.setVisible(false);
					
				}
			
			}
		});
		btnNewButton.setBounds(407, 372, 113, 23);
		add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setToolTipText("jihhihiu");
		separator.setForeground(Color.YELLOW);
		separator.setBackground(Color.PINK);
		separator.setBounds(0, 42, 653, 14);
		add(separator);
		
		

		 //String[] col = { "Id","Kraj", "Hotel", "Standard", "Rodzaj_Transportu", "Data_Wyjazdu", "Data_Powrotu", "Cena" };
		 String[] col = { "Id","Kraj", "Hotel", "Standard", "Data_Wyjazdu", "Data_Powrotu", "Rodzaj_transportu", "Cena" };
		  Object[][] data = {  };
		  
		  model = new DefaultTableModel(data, col);
		
		JButton btnNewButton_1 = new JButton("Wyczysc");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//czysc();
			}
		});
		btnNewButton_1.setBounds(530, 372, 113, 23);
		add(btnNewButton_1);
		
		lblOk = new JLabel("Zamowienie zlozone");
		lblOk.setForeground(Color.GREEN);
		lblOk.setFont(new Font("Calibri", Font.BOLD, 15));
		lblOk.setIcon(new ImageIcon(Oferty.class.getResource("/javax/swing/plaf/metal/icons/Inform.gif")));
		lblOk.setBounds(402, -8, 240, 64);
		add(lblOk);
		lblOk.setVisible(false);
		
		lblFalse = new JLabel("Niepoprawne dane");
		lblFalse.setForeground(Color.RED);
		lblFalse.setFont(new Font("Calibri", Font.BOLD, 15));
		lblFalse.setIcon(new ImageIcon(Oferty.class.getResource("/javax/swing/plaf/metal/icons/Error.gif")));
		lblFalse.setBounds(402, -8, 240, 64);
		add(lblFalse);
		table = new JTable(model);
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(10, 184, 632, 184);
		add(pane);
		
		lblFalse.setVisible(false);

		//////////////////////////////////////////////////////////
		JLabel lblStandard = new JLabel("Standard:");
		lblStandard.setBounds(10, 111, 69, 14);
		add(lblStandard);
		
		JLabel lblDataWyjazdu = new JLabel("Data Wyjazdu:");
		lblDataWyjazdu.setBounds(10, 137, 83, 14);
		add(lblDataWyjazdu);
		
		JLabel lblDataPowrotu = new JLabel("Data Powrotu");
		lblDataPowrotu.setBounds(10, 159, 69, 14);
		add(lblDataPowrotu);
		
		textWyjazdDzien = new JTextField();
		textWyjazdDzien.setText("1");
		textWyjazdDzien.setBounds(121, 131, 38, 20);
		add(textWyjazdDzien);
		textWyjazdDzien.setColumns(10);
		
		JLabel label_1 = new JLabel("/");
		label_1.setBounds(164, 134, 4, 14);
		add(label_1);
		
		textWyjazdMiesiac = new JTextField();
		textWyjazdMiesiac.setText("1");
		textWyjazdMiesiac.setColumns(10);
		textWyjazdMiesiac.setBounds(169, 131, 38, 20);
		add(textWyjazdMiesiac);
		
		JLabel label_2 = new JLabel("/");
		label_2.setBounds(211, 134, 4, 14);
		add(label_2);
		
		textWyjazdRok = new JTextField();
		textWyjazdRok.setText("2015");
		textWyjazdRok.setColumns(10);
		textWyjazdRok.setBounds(221, 131, 57, 20);
		add(textWyjazdRok);
		
		textPowrotDzien = new JTextField();
		textPowrotDzien.setText("31");
		textPowrotDzien.setColumns(10);
		textPowrotDzien.setBounds(121, 153, 38, 20);
		add(textPowrotDzien);
		
		JLabel label_3 = new JLabel("/");
		label_3.setBounds(164, 156, 4, 14);
		add(label_3);
		
		textPowrotMiesiac = new JTextField();
		textPowrotMiesiac.setText("12");
		textPowrotMiesiac.setColumns(10);
		textPowrotMiesiac.setBounds(169, 153, 38, 20);
		add(textPowrotMiesiac);
		
		JLabel label_4 = new JLabel("/");
		label_4.setBounds(211, 156, 4, 14);
		add(label_4);
		
		textPowrotRok = new JTextField();
		textPowrotRok.setText("2015");
		textPowrotRok.setColumns(10);
		textPowrotRok.setBounds(221, 153, 57, 20);
		add(textPowrotRok);
		///////////////////////////////////////////////////////
		
		
		
		
		rdbtnAllInclusive = new JRadioButton("All Inclusive");
		rdbtnAllInclusive.setSelected(true);
		buttonGroup.add(rdbtnAllInclusive);
		rdbtnAllInclusive.setBounds(66, 107, 89, 23);
		add(rdbtnAllInclusive);
		
		rdbtnStandard = new JRadioButton("Standard");
		buttonGroup.add(rdbtnStandard);
		rdbtnStandard.setBounds(157, 107, 97, 23);
		add(rdbtnStandard);

		JLabel lblKraj = new JLabel("Kraj:");
		lblKraj.setBounds(10, 61, 46, 14);
		add(lblKraj);
		
		JLabel lblHotel = new JLabel("Hotel:");
		lblHotel.setBounds(10, 83, 46, 14);
		add(lblHotel);
		
		JLabel lblTransport = new JLabel("Rodzaj transportu:");
		lblTransport.setBounds(303, 58, 113, 14);
		lblTransport.setVisible(false);
		add(lblTransport);
		
		comboKraj = new JComboBox();
		comboKraj.setBounds(121, 58, 157, 20);
		add(comboKraj);
		uzupelnijListeKrajow();
		
		comboHotel = new JComboBox();
		comboHotel.setBounds(120, 80, 158, 20);
		add(comboHotel);
		
		txtIloscrezerwacji = new JTextField();
		txtIloscrezerwacji.setText("1");
		txtIloscrezerwacji.setBounds(373, 373, 24, 20);
		add(txtIloscrezerwacji);
		txtIloscrezerwacji.setVisible(false);
		txtIloscrezerwacji.setColumns(10);
		
		JLabel lblLiczbaRezerwacji = new JLabel("Liczba rezerwacji : ");
		lblLiczbaRezerwacji.setBounds(224, 376, 139, 14);
		lblLiczbaRezerwacji.setVisible(false);
		add(lblLiczbaRezerwacji);
		
		comboTransportu = new JComboBox();
		comboTransportu.setBounds(414, 55, 157, 20);
		comboTransportu.setVisible(false);
		add(comboTransportu);
		
		JButton btnSzukaj = new JButton("> Szukaj <");
		btnSzukaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/////////////////////////
				while(model.getRowCount() > 0 )
					model.removeRow(0);
				polacz();
				String standard = null;
				if(rdbtnAllInclusive.isSelected())
					standard = "ALL_INCLUSIVE";
				else if(rdbtnStandard.isSelected())
					standard = "STANDARD";
				
			//	if(rdbtnOsobaPrywatna.isSelected())
				List<String> lista = new ArrayList<String>();
				String query;
				
				if(comboHotel.getSelectedItem()!="Dowolny"){
					query = "(SELECT \"IdUmowyHotelu\" FROM \"UmowyZHotelami\" WHERE \"IdHotelu\"=( SELECT \"IdHotelu\" FROM \"Hotele\" WHERE \"Nazwa\"=\'"+comboHotel.getSelectedItem()+"\'))";
					System.out.println(query);
					try{
					myStmt = conn.createStatement();
					
					myRs = myStmt.executeQuery(query);
				
				
				while (myRs.next()) {
					String idUmowyHotelu = new String(myRs.getString(1));
					lista.add(idUmowyHotelu);
					}
					} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
				}
				query = "SELECT * FROM \"OfertaWczasów\" WHERE "
						+"  \"DataWyjazdu\">=\'"+textWyjazdRok.getText()+"-"+textWyjazdMiesiac.getText()+"-"+textWyjazdDzien.getText()+"\'"
						+" AND \"DataPowrotu\"<=\'"+textPowrotRok.getText()+"-"+textPowrotMiesiac.getText()+"-"+textPowrotDzien.getText()+"\'";
				if(!rdbtnDowolny.isSelected())
					query += " AND \"Standard\"=\'"+standard+"\'";
				if(comboKraj.getSelectedItem()!="Dowolny")
					query += " AND \"Kraj\"=\'"+comboKraj.getSelectedItem()+"\'";
				if(comboHotel.getSelectedItem()!="Dowolny"){
					query += " AND (";
					query += "\"IdUmowyHotelu\" = \'"+lista.get(0)+"\'";
					for(int i=1; i < lista.size(); i++)
					{
						query +="OR \"IdUmowyHotelu\" = \'"+lista.get(i)+"\'";
					}
					query+= ")";
					//query += " AND \"IdUmowyHotelu\"=(SELECT \"IdUmowyHotelu\" FROM \"UmowyZHotelami\" WHERE \"IdHotelu\"=( SELECT \"IdHotelu\" FROM \"Hotele\" WHERE \"Nazwa\"=\'"+comboHotel.getSelectedItem()+"\') AND ROWNUM = 1)";
				}
				System.out.println(query);
				
					try {
					myStmt = conn.createStatement();
				
						myRs = myStmt.executeQuery(query);
					
					
					while (myRs.next()) {
						String id = new String(myRs.getString(1));
						String kraj = new String(myRs.getString(2));
						String standard2 = new String(myRs.getString(3));
						String data_wyj = new String(myRs.getString(4));
						String data_pow = new String(myRs.getString(5));
						String cena = new String(myRs.getString(8));
						String queryHotel= "SELECT * FROM \"Hotele\" WHERE \"IdHotelu\"="
								+ "(SELECT \"IdHotelu\" FROM \"UmowyZHotelami\" WHERE \"IdUmowyHotelu\"='"+myRs.getString(9)+"\')";
						System.out.println(queryHotel);
						String hotel = null;
						String queryTransport= "SELECT \"Rodzaj\" FROM \"UmowyTransportoweSezonowe\" WHERE \"IdTransportu\"='"+myRs.getString(10)+"\'";
						String rodzajTransportu = null;
						System.out.println(queryTransport);
						try {
							myStmt2 = conn2.createStatement();
							myRs2 = myStmt2.executeQuery(queryHotel);
							myStmt3 = conn3.createStatement();
							myRs3 = myStmt3.executeQuery(queryTransport);
							
							if (myRs2.next()) {hotel = new String(myRs2.getString(2));}
							if (myRs3.next()) {rodzajTransportu = new String(myRs3.getString(1));}
							} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						Object[][] rowData2 = {{id,kraj, hotel, standard2, data_wyj, data_pow, rodzajTransportu, cena}};
						model.addRow(rowData2[0]);	
					}	
				
	} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			
			}});
		btnSzukaj.setBounds(284, 150, 359, 23);
		add(btnSzukaj);
		
		rdbtnDowolny = new JRadioButton("Dowolny");
		buttonGroup.add(rdbtnDowolny);
		rdbtnDowolny.setBounds(260, 107, 109, 23);
		add(rdbtnDowolny);
		
		/*comboTransport = new JComboBox();
		comboTransport.setBounds(101, 125, 138, 20);
		add(comboTransport);*/
		
		comboKraj.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent e) {
	           try {
				pobierzListeHoteli();
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
	        }
	    });

		uzupelnijRodzajTransportu();
		comboHotel.addItem("Dowolny");
	}
	
	private void uzupelnijListeKrajow(){
		comboKraj.addItem("Dowolny");
		comboKraj.addItem("Chorwacja");
		comboKraj.addItem("Egipt");
		comboKraj.addItem("Francja");
		comboKraj.addItem("Hiszpania");
		comboKraj.addItem("Polska");
		comboKraj.addItem("Portugalia");
		comboKraj.addItem("Turcja");
	}
	
	private void pobierzListeHoteli() throws SQLException{
		polacz();
		String query;
		comboHotel.removeAllItems();
		
		if(comboKraj.getSelectedItem() != "Dowolny")
			query = "select \"Nazwa\" from \"Hotele\" H WHERE H.\"Kraj\" = \'" + comboKraj.getSelectedItem() + "\'";
		else
			query = "select \"Nazwa\" from \"Hotele\" ";
			
				
		try {
			myStmt = conn.createStatement();
			myRs = myStmt.executeQuery(query);
			comboHotel.addItem("Dowolny");
			while (myRs.next()) {
				String temp = new String(myRs.getString(1));
				comboHotel.addItem(temp);
			}	
		}
		finally {
			
		}
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
		conn2 = DriverManager.getConnection(DB_URL,USER,PASS);
		conn3 = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		

	}
	
	private void uzupelnijRodzajTransportu(){
		comboTransportu.addItem("Dowolny");
		comboTransportu.addItem("Bus");
		comboTransportu.addItem("Autobus");
		comboTransportu.addItem("Samolot");
	}
	}
	
	

