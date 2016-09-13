import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Klient.Logowanie;
import Klient.Oferty;


public class Klient extends JFrame {

	private JPanel contentPane;
	
	public JPanel panel_1;
	public JPanel logowanie;
	public JPanel oferty;
	public JPanel hotelContract;
	public JPanel bazaKlientow;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Klient frame = new Klient();
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
	public Klient() {
		setTitle("Biuro Podr\u00F3zy [Klient]");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 659, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	JPanel panel = new JPanel();

		
		
	JLabel lblNewLabel = new JLabel("New label");
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 653, 21);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Informacje");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmMojeDane = new JMenuItem("Moje Dane");
		mntmMojeDane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logowanie.setVisible(true);	
				hotelContract.setVisible(false);
				oferty.setVisible(false);
				bazaKlientow.setVisible(false);
				//((AddOffer) logowanie).czysc();
				
			}
		});
		
		JMenuItem mntmZaloguj = new JMenuItem("Zaloguj");
		mntmZaloguj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logowanie.setVisible(true);
				oferty.setVisible(false);
				//offer.setVisible(false);	
				bazaKlientow.setVisible(false);
				//((AddHotelContract) logowanie).czysc();
				
			}
		});
		mnNewMenu.add(mntmZaloguj);
		mnNewMenu.add(mntmMojeDane);
		
		JMenu mnWyszukiwanie = new JMenu("Wyszukiwanie");
		menuBar.add(mnWyszukiwanie);
		
		JMenuItem mntmSzukajOferty = new JMenuItem("Oferty wczasow");
		mntmSzukajOferty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				oferty.setVisible(true);
				logowanie.setVisible(false);
				//offer.setVisible(false);	
				bazaKlientow.setVisible(false);
				//((AddHotelContract) logowanie).czysc();
			}
		});
		mnWyszukiwanie.add(mntmSzukajOferty);
		
		JMenu mnNewMenu_1 = new JMenu("Inne");
		menuBar.add(mnNewMenu_1);
		

				
		JMenuItem mntmClose = new JMenuItem("Zamknij");
		mntmClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();	
			}
		});
		
		JMenuItem mntmPrzelacztryb = new JMenuItem("Przelacz tryb");
		mntmPrzelacztryb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Pracownik framePracownik = new Pracownik();
				framePracownik.setVisible(true);
				dispose();	
				
			}
		});
		mnNewMenu_1.add(mntmPrzelacztryb);
		mnNewMenu_1.add(mntmClose);
		
		
		logowanie =  new Logowanie();
		logowanie.setBounds(0, 21, 653, 403);
		logowanie.setVisible(true);
		contentPane.add(logowanie);
		
		oferty =  new Oferty();
		oferty.setBounds(0, 21, 653, 403);
		oferty.setVisible(false);
		contentPane.add(oferty);
		
		hotelContract =  new AddHotelContract();
		hotelContract.setBounds(0, 21, 653, 403);
		hotelContract.setVisible(false);
		contentPane.add(hotelContract);
		
		bazaKlientow =  new Klienci();
		bazaKlientow.setBounds(0, 21, 653, 403);
		bazaKlientow.setVisible(false);
		contentPane.add(bazaKlientow);
		
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 21, 653, 403);
		contentPane.add(panel_1);
		panel_1.setVisible(false);
		
	}
}
