package rs.ac.bg.fon.ai.dodatna.momcilovic.uros.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import rs.ac.bg.fon.ai.dodatna.momcilovic.uros.util.Sistem;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class MenjacnicaGUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblIzValuteZemlje;
	private JLabel lblUValutuZemlje;
	private JLabel lblIznos;
	private JLabel lblIznos_1;
	private JTextField textFieldIz;
	private JTextField textFieldU;
	private JComboBox comboBoxIz;
	private JComboBox comboBoxU;
	private JButton btnKonvertuj;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sistem.ucitajUlistu();
					MenjacnicaGUI frame = new MenjacnicaGUI();
					frame.setVisible(true);
					
					frame.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							Sistem.upisiKonUFajl();
							System.exit(0);
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	

	/**
	 * Create the frame.
	 */
	public MenjacnicaGUI() {
		initGUI();
	}
	private void initGUI() {
		setTitle("Menjacnica");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblIzValuteZemlje());
		contentPane.add(getLblUValutuZemlje());
		contentPane.add(getLblIznos());
		contentPane.add(getLblIznos_1());
		contentPane.add(getTextFieldIz());
		contentPane.add(getTextFieldU());
		contentPane.add(getComboBoxIz());
		contentPane.add(getComboBoxU());
		contentPane.add(getBtnKonvertuj());
	}
	private JLabel getLblIzValuteZemlje() {
		if (lblIzValuteZemlje == null) {
			lblIzValuteZemlje = new JLabel("Iz valute zemlje:");
			lblIzValuteZemlje.setBounds(55, 49, 112, 16);
		}
		return lblIzValuteZemlje;
	}
	private JLabel getLblUValutuZemlje() {
		if (lblUValutuZemlje == null) {
			lblUValutuZemlje = new JLabel("U valutu zemlje:");
			lblUValutuZemlje.setBounds(255, 49, 112, 16);
		}
		return lblUValutuZemlje;
	}
	private JLabel getLblIznos() {
		if (lblIznos == null) {
			lblIznos = new JLabel("Iznos:");
			lblIznos.setBounds(55, 139, 56, 16);
		}
		return lblIznos;
	}
	private JLabel getLblIznos_1() {
		if (lblIznos_1 == null) {
			lblIznos_1 = new JLabel("Iznos:");
			lblIznos_1.setBounds(255, 139, 56, 16);
		}
		return lblIznos_1;
	}
	private JTextField getTextFieldIz() {
		if (textFieldIz == null) {
			textFieldIz = new JTextField();
			textFieldIz.setBounds(55, 168, 116, 22);
			textFieldIz.setColumns(10);
		}
		return textFieldIz;
	}
	private JTextField getTextFieldU() {
		if (textFieldU == null) {
			textFieldU = new JTextField();
			textFieldU.setEditable(false);
			textFieldU.setBounds(255, 168, 116, 22);
			textFieldU.setColumns(10);
		}
		return textFieldU;
	}
	JComboBox getComboBoxIz() {
		if (comboBoxIz == null) {
			comboBoxIz = new JComboBox();
			comboBoxIz.setModel(new DefaultComboBoxModel(Sistem.izvuciImenaZemalja()));
			comboBoxIz.setBounds(55, 93, 112, 22);
		}
		return comboBoxIz;
	}
	JComboBox getComboBoxU() {
		if (comboBoxU == null) {
			comboBoxU = new JComboBox();
			comboBoxU.setModel(new DefaultComboBoxModel(Sistem.izvuciImenaZemalja()));
			comboBoxU.setBounds(255, 93, 112, 22);
		}
		return comboBoxU;
	}
	
	private JButton getBtnKonvertuj() {
		if (btnKonvertuj == null) {
			btnKonvertuj = new JButton("Konvertuj");
			btnKonvertuj.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					izracunajKurs();
				}
			});
			btnKonvertuj.setBounds(152, 215, 97, 25);
		}
		return btnKonvertuj;
	}
	
	private void izracunajKurs(){ 
		double iznos = Double.parseDouble(textFieldIz.getText());
		
		if(iznos <= 0){
			JOptionPane.showMessageDialog(contentPane, "Nepravilno unet iznos!", "Greska!", JOptionPane.ERROR_MESSAGE);
		}
		
		double kurs = Sistem.kurs(comboBoxIz.getSelectedItem().toString(), comboBoxU.getSelectedItem().toString());
		
		if(kurs <= 0){
			JOptionPane.showMessageDialog(contentPane, "Ne postoji kurs za unete drzave!", "Greska!",JOptionPane.ERROR_MESSAGE);
		}
		
		
		textFieldU.setText("" + iznos * kurs);
		
		Sistem.konverzija(comboBoxIz.getSelectedItem().toString(), comboBoxU.getSelectedItem().toString(), kurs);
	}
	
	
	
}
