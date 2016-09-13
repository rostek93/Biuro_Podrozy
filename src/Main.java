import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.awt.Toolkit;


public class Main extends JFrame {

	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
		
			    	//frame.pack();
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
	public Main() {
		//setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\_Dane\\projekt\\Biuro_Podrozy\\src\\biuro.jpg"));
		setTitle("Biuro Podr\u00F3zy");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 429, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	/**	try {
			contentPane.add(new JLabel((Icon) new ImageIcon(ImageIO.read(new File("C:\\_Dane\\projekt\\Biuro_Podrozy\\src\\biuro.jpg")))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	JPanel panel = new JPanel();

		
		
	JLabel lblNewLabel = new JLabel("New label");
		
	
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 653, 21);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Widok");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNowaOferta = new JMenuItem("Pracownik");
		mntmNowaOferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Pracownik framePracownik = new Pracownik();
				framePracownik.setVisible(true);
				
			}
		});
		mnNewMenu.add(mntmNowaOferta);
		
		JMenuItem mntmUmowaZHotelem = new JMenuItem("Klient");
		mntmUmowaZHotelem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
			}
		});
		mnNewMenu.add(mntmUmowaZHotelem);
		
		JMenu mnNewMenu_1 = new JMenu("Inne");
		menuBar.add(mnNewMenu_1);
		

				
		JMenuItem mntmClose = new JMenuItem("Close");
		mntmClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();	
			}
		});
		mnNewMenu_1.add(mntmClose);
		
		JButton btnKlient = new JButton("Klient");
		btnKlient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Klient frameKlient = new Klient();
				frameKlient.setVisible(true);
				setVisible(false);
				
			}
		});
		btnKlient.setBounds(57, 120, 147, 23);
		contentPane.add(btnKlient);
		
		JButton btnPracownik = new JButton("Pracownik");
		btnPracownik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Pracownik framePracownik = new Pracownik();
				framePracownik.setVisible(true);
				setVisible(false);
				
				
			}
		});
		btnPracownik.setBounds(226, 120, 168, 23);
		contentPane.add(btnPracownik);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("biuro.jpg"));
		lblNewLabel_1.setBounds(0, 11, 448, 240);
		contentPane.add(lblNewLabel_1);
		
	
	
		
		

		

		

	
	}
}
