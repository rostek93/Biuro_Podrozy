import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
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


public class AddHotelContract extends JPanel {
	private JTextField textRok;
	private JTextField textCena;
	private JTextField textIlosc;
	private JComboBox comboBox;
	private JComboBox comboRodzaj ;
	private JLabel lblOk;
	private JLabel lblFalse;

	private JTable table;

	private DefaultTableModel model;
	
	private Connection conn = null;
	private Statement myStmt = null;
	private ResultSet myRs = null;
	
	private Main root;
	
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
		List<String> list = new ArrayList<>();
		comboBox.removeAllItems();
		
		try {
			myStmt = conn.createStatement();
			myRs = myStmt.executeQuery("select * from \"Hotele\"");
			
			while (myRs.next()) {
				String temp = new String(myRs.getString(2));
				comboBox.addItem(temp);
				list.add(temp);
			}		
		}
		finally {
			
		}
	}
	
	public void wyswietlPokoje() throws Exception{
		polacz();
		List<String> list = new ArrayList<>();
		comboRodzaj.removeAllItems();
		try {
			myStmt = conn.createStatement();
			myRs = myStmt.executeQuery("select * from \"Pokoje\" WHERE \"IdHotelu\"=(SELECT \"IdHotelu\" FROM \"Hotele\" h WHERE h.\"Nazwa\" =' " 
			+ comboBox.getSelectedItem() + "\')");
			
			while (myRs.next()) {
				String temp = new String(myRs.getString(3));
				comboRodzaj.addItem(temp);
				list.add(temp);
			}		
		}
		finally {
			
		}
	}
	
	public void czysc(){
		comboRodzaj.removeAllItems();
		comboBox.removeAllItems();
		
		textRok.setText("");
		textCena.setText("");
		textIlosc.setText("");
		while(model.getRowCount() > 0 )
			for(int i=0; i< model.getRowCount(); i++ )
				model.removeRow(i);	
		
		comboBox.setEnabled(true);
	
		textRok.setEnabled(true);
		textCena.setEnabled(true);
		lblOk.setVisible(false);
		
	}
	
	public Boolean sprawdzPoprawnoscHotelu()
	{
		if (textRok.getText().equals(""))
			return false;
		else if (textCena.getText().equals(""))
			return false;
		else if (comboBox.getSelectedIndex() == -1)
			return false;
		else 
			return true;
	}
	public Boolean sprawdzPoprawnoscPokoju()
	{
		if (textRok.getText().equals(""))
			return false;
		else if (textCena.getText().equals(""))
			return false;
		else if (textIlosc.getText().equals(""))
			return false;
		else if (comboRodzaj.getSelectedIndex() == -1)
			return false;
		else if (comboBox.getSelectedIndex() == -1)
			return false;
		else if(model.getRowCount() == 0)
			return false;
		else 
			return true;
	}
	

	/**
	 * Create the panel.
	 */
	public AddHotelContract() {

		setLayout(null);
		setBounds(0,0,653,406);
		JLabel lblZawieranieUmowyZ = new JLabel("Zawieranie umowy z hotelem");
		lblZawieranieUmowyZ.setForeground(Color.BLUE);
		lblZawieranieUmowyZ.setFont(new Font("Verdana", Font.BOLD, 14));
		lblZawieranieUmowyZ.setBounds(10, 11, 330, 22);
		add(lblZawieranieUmowyZ);
		
		comboBox = new JComboBox();
		comboBox.setBounds(10, 79, 230, 20);
		add(comboBox);
		
		textRok = new JTextField();
		textRok.setBounds(250, 79, 86, 20);
		add(textRok);
		textRok.setColumns(10);
		
		textCena = new JTextField();
		textCena.setBounds(346, 79, 86, 20);
		add(textCena);
		textCena.setColumns(10);
		
		JButton btnNewButton = new JButton("Wprowad\u017A");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				while(model.getRowCount() > 0 ){
					
					String query = "INSERT INTO  \"DostepnePokoje\" "
							+ "(\"IdUmowyHotelu\", \"IdPokoju\",\"IloscZarezerwowanych\")"
							+ "VALUES (\'"
							/**Pobranie IdUmowyHotelu */
							+"(SELECT \"IdUmowyHotelu\" FROM \"UmowyZHotelami\" uh WHERE uh.\"IdHotelu\" =' "
							+"(SELECT \"IdHotelu\" FROM \"Hotel\" h WHERE h.\"Nazwa\" =' " + comboBox.getSelectedItem() + "\')\'), \'"
							/**IdPokoju*/
							+"(SELECT \"IdPokoju\" FROM \"Pokoje\" WHERE \"Standard\"=\'"+  model.getValueAt(0, 0)+"\' "
							+"AND \"IdHotelu=\'"
							+"(SELECT \"IdHotelu\" FROM \"Hotel\" h WHERE h.\"Nazwa\" =' " + comboBox.getSelectedItem() + "\')\'), \'"		
							+model.getValueAt(0, 1)+ "\'), \'0\')";	
					
					try {
					myStmt = conn.createStatement();
					myRs = myStmt.executeQuery(query);
					query = "commit";
					myStmt = conn.createStatement();
					myRs = myStmt.executeQuery(query);
					} catch (SQLException e) {	e.printStackTrace();}
					model.removeRow(0);
				
				}
					lblOk.setVisible(true);}
				catch (Exception ex)	{lblFalse.setVisible(true);}
			}
		});
		btnNewButton.setBounds(495, 372, 113, 23);
		add(btnNewButton);
		
		JLabel lblHotel = new JLabel("Hotel");
		lblHotel.setBounds(90, 54, 46, 14);
		add(lblHotel);
		
		JLabel lblRok = new JLabel("Rok");
		lblRok.setBounds(279, 54, 46, 14);
		add(lblRok);
		
		JLabel lblCena = new JLabel("Cena");
		lblCena.setBounds(367, 54, 46, 14);
		add(lblCena);
		
		JSeparator separator = new JSeparator();
		separator.setToolTipText("jihhihiu");
		separator.setForeground(Color.YELLOW);
		separator.setBackground(Color.PINK);
		separator.setBounds(0, 44, 653, 14);
		add(separator);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 111, 319, 215);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblDodawaniePokoi = new JLabel("Pokoje:");
		lblDodawaniePokoi.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblDodawaniePokoi.setBounds(10, 11, 55, 17);
		panel.add(lblDodawaniePokoi);
		
		textIlosc = new JTextField();
		textIlosc.setBounds(155, 54, 55, 20);
		panel.add(textIlosc);
		textIlosc.setColumns(10);
		
		comboRodzaj = new JComboBox();
		comboRodzaj.setBounds(10, 54, 135, 20);
		panel.add(comboRodzaj);
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  Object[][] rowData2 = {{comboRodzaj.getSelectedItem(), textIlosc.getText()}};
				  model.addRow(rowData2[0]);	
			}
		});
		btnDodaj.setBounds(220, 53, 89, 23);
		panel.add(btnDodaj);
		
		JLabel lblRodzaj = new JLabel("Rodzaj");
		lblRodzaj.setBounds(49, 39, 46, 14);
		panel.add(lblRodzaj);
		
		JLabel lblIlo = new JLabel("ilo\u015B\u0107");
		lblIlo.setBounds(171, 39, 46, 14);
		panel.add(lblIlo);
		
		JButton btnUsu = new JButton("Usu\u0144");
		btnUsu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRowCount() > 0)
				model.removeRow(table.getSelectedRow());
			}
		});
		btnUsu.setBounds(220, 78, 89, 23);
		panel.add(btnUsu);
		
		

		 String[] col = { "Rodzaj", "ilosc" };
		  Object[][] data = {  };
		  
		  model = new DefaultTableModel(data, col);
		  table = new JTable(model);
		  JScrollPane pane = new JScrollPane(table);

		  pane.setBounds(10, 85, 200, 119);
		  
		  panel.add(pane);

		
		JButton btnZatwierdz = new JButton("Zatwierdz");
		btnZatwierdz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/**comboBox.setEnabled(false);
				textId.setEnabled(false);
				textRok.setEnabled(false);
				textCena.setEnabled(false);
*/
				String query = "INSERT INTO  \"UmowyZHotelami\" "
						+ "(\"IdHotelu\", \"RokUmowy\",\"Cena\")"
						+ "VALUES ("
						+"(SELECT \"IdHotelu\" FROM \"Hotele\" h WHERE h.\"Nazwa\" ='" + comboBox.getSelectedItem() + "\'), \'"
						+textRok.getText()+ "\', \'"
						+textCena.getText()+"\')";
				//System.out.println(query);
				
				//System.out.println(sprawdzPoprawnoscHotelu());
				if(sprawdzPoprawnoscHotelu()){
					try {
					myStmt = conn.createStatement();
					myRs = myStmt.executeQuery(query);
					query = "commit";
					myStmt = conn.createStatement();
					myRs = myStmt.executeQuery(query);
					//System.out.println(query);
					
					wyswietlPokoje();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		btnZatwierdz.setBounds(442, 78, 113, 22);
		add(btnZatwierdz);
		
		JButton btnNewButton_1 = new JButton("Anuluj");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			czysc();
			}
		});
		btnNewButton_1.setBounds(372, 372, 113, 23);
		add(btnNewButton_1);
		
		JButton btnWczytaj = new JButton("Wczytaj");
		btnWczytaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					pobierzDane();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnWczytaj.setBounds(10, 372, 89, 23);
		add(btnWczytaj);
		
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
}
