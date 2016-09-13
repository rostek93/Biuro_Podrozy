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


public class Pracownik extends JFrame {

	private JPanel contentPane;
	public JPanel panel_1;
	public JPanel offer;
	public JPanel transportContract;
	public JPanel hotelContract;
	public JPanel bazaKlientow;
	


	/**
	 * Create the frame.
	 */
	public Pracownik() {
		setTitle("Biuro Podr\u00F3zy [Pracownik]");
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
		
		JMenu mnNewMenu = new JMenu("Planowanie");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNowaOferta = new JMenuItem("Nowa oferta");
		mntmNowaOferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				offer.setVisible(true);	
				hotelContract.setVisible(false);
				transportContract.setVisible(false);
				bazaKlientow.setVisible(false);
				((AddOffer) offer).czysc();
				
			}
		});
		mnNewMenu.add(mntmNowaOferta);
		
		JMenuItem mntmUmowaZHotelem = new JMenuItem("Umowa z hotelem");
		mntmUmowaZHotelem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hotelContract.setVisible(true);
				transportContract.setVisible(false);
				offer.setVisible(false);	
				bazaKlientow.setVisible(false);
				((AddHotelContract) hotelContract).czysc();
				
			}
		});
		mnNewMenu.add(mntmUmowaZHotelem);
		
		
		JMenuItem mntmUmowaZFirm = new JMenuItem("Umowa z firm\u0105");
		mntmUmowaZFirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hotelContract.setVisible(false);
				offer.setVisible(false);
				transportContract.setVisible(true);
				bazaKlientow.setVisible(false);
				((AddTransportContract) transportContract).czysc();
		
				
			}
		});
		mnNewMenu.add(mntmUmowaZFirm);
		
		JMenu mnWyszukiwanie = new JMenu("Wyszukiwanie");
		menuBar.add(mnWyszukiwanie);
		
		JMenuItem mntmSzukajOferty = new JMenuItem("Baza ofert");
		mnWyszukiwanie.add(mntmSzukajOferty);
		
		JMenuItem mntmBazaKlientw = new JMenuItem("Baza klient\u00F3w");
		mntmBazaKlientw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bazaKlientow.setVisible(true);
				offer.setVisible(false);	
				hotelContract.setVisible(false);
				transportContract.setVisible(false);
				((Klienci) bazaKlientow).czysc();
			}
		});
		mnWyszukiwanie.add(mntmBazaKlientw);
		
		JMenu mnNewMenu_1 = new JMenu("Inne");
		menuBar.add(mnNewMenu_1);
		

				
		JMenuItem mntmClose = new JMenuItem("Zamknij");
		mntmClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();	
			}
		});
		
		JMenuItem mntmPrzeczTryb = new JMenuItem("Prze\u0142\u0105cz tryb");
		mntmPrzeczTryb.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		Klient frame = new Klient();
		frame.setVisible(true);
		dispose();	
			}
			});
		mnNewMenu_1.add(mntmPrzeczTryb);
		mnNewMenu_1.add(mntmClose);
		
		
		offer =  new AddOffer();
		offer.setBounds(0, 21, 653, 403);
		offer.setVisible(false);
		contentPane.add(offer);
		
		transportContract =  new AddTransportContract();
		transportContract.setBounds(0, 21, 653, 403);
		transportContract.setVisible(false);
		contentPane.add(transportContract);
		
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
