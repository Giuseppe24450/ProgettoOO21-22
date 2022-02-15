package GUI;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPasswordField;

// TODO: Auto-generated Javadoc
/**
 * The Class Registrazione.
 */
public class Registrazione extends JFrame {

	/** The content pane. */
	private JPanel contentPane;

/** The frame. */
JFrame frame;

/** The controller. */
Controller controller;

/** The text nome. */
private JTextField textNome;

/** The text cognome. */
private JTextField textCognome;

/** The text nick name. */
private JTextField textNickName;

/** The password field. */
private JPasswordField passwordField;
	
	/**
	 * Instantiates a new registrazione.
	 *
	 * @param c the controller
	 * @param frameChiamante the frame chiamante
	 */
	public Registrazione(Controller c,JFrame frameChiamante) {
		setResizable(false);
		controller=c;
		frame=this;
		passwordField = new JPasswordField();
		passwordField.setBounds(295, 258, 96, 20);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_1 = new JLabel("Cognome");
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1.setBounds(141, 202, 64, 17);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Password");
		lblNewLabel_3.setForeground(new Color(255, 0, 0));
		lblNewLabel_3.setBounds(141, 258, 75, 20);
		contentPane.add(lblNewLabel_3);
		
		textNome = new JTextField();
		textNome.setBounds(295, 174, 96, 20);
		contentPane.add(textNome);
		textNome.setColumns(10);
		
		textCognome = new JTextField();
		textCognome.setBounds(295, 200, 96, 20);
		contentPane.add(textCognome);
		textCognome.setColumns(10);
		
		textNickName = new JTextField();
		textNickName.setBounds(295, 231, 96, 20);
		contentPane.add(textNickName);
		textNickName.setColumns(10);
		
		
		JButton btnNewButton = new JButton("Annulla");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBackground(new Color(255, 140, 0));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				frameChiamante.setVisible(true);
			}
		});
		btnNewButton.setBounds(93, 307, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Registra");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBackground(new Color(255, 140, 0));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				String errore="";
				errore=new String(controller.controlloregistrazione(textNome.getText(),textCognome.getText(),textNickName.getText(),passwordField.getText()));
			if(errore.contentEquals("OK")) {
			
				JFrame frameProvaHome=new HomeUtente(controller,frame);
				frame.setVisible(false);
				frameProvaHome.setVisible(true);
			}else {
				JOptionPane.showMessageDialog(frame,errore);
			}
			}
		});
		btnNewButton_1.setBounds(356, 307, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_4 = new JLabel("Registrati!");
		lblNewLabel_4.setForeground(new Color(255, 0, 0));
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_4.setBounds(238, 11, 108, 14);
		contentPane.add(lblNewLabel_4);
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(141, 174, 64, 17);
		contentPane.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(255, 0, 0));
		
		JLabel lblImmagine = new JLabel("");
		lblImmagine.setBounds(189, 36, 175, 122);
		contentPane.add(lblImmagine);
		ImageIcon Immagineicona=new ImageIcon(this.getClass().getResource("/Image/iconaregistrazione.png"));
		lblImmagine.setIcon(Immagineicona);
		
		JLabel lblNewLabel_2 = new JLabel("NickName");
		lblNewLabel_2.setBounds(141, 233, 75, 14);
		contentPane.add(lblNewLabel_2);
		lblNewLabel_2.setForeground(Color.RED);
		
		JLabel lblNewLabel_5 = new JLabel("*");
		lblNewLabel_5.setToolTipText("1)Deve contenere almeno una lettera.\r\n2)La lunghezza maggiore di 3");
		lblNewLabel_5.setForeground(Color.RED);
		lblNewLabel_5.setBounds(205, 234, 49, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("*");
		lblNewLabel_6.setToolTipText("1)Lunghezza maggiore di 4;2)Non pi\u00F2 contenere spazi");
		lblNewLabel_6.setForeground(Color.RED);
		lblNewLabel_6.setBounds(205, 261, 49, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("*");
		lblNewLabel_7.setForeground(Color.RED);
		lblNewLabel_7.setToolTipText("1)Inserire un solo nome se si possiede pi\u00F9 di uno.\r\n2)Non pu\u00F2 contenere spazi\r\n3)Pu\u00F2 contenere solo lettere(anche accentate)");
		lblNewLabel_7.setBounds(199, 177, 24, 14);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("*");
		lblNewLabel_8.setToolTipText("1)Inserire un solo cognome se si possiede pi\u00F9 di uno.\r\n2)Non pu\u00F2 contenere spazi\r\n3)Pu\u00F2 contenere solo lettere(anche accentate)");
		lblNewLabel_8.setForeground(Color.RED);
		lblNewLabel_8.setBounds(205, 203, 49, 14);
		contentPane.add(lblNewLabel_8);
		
		
		
	}
}
