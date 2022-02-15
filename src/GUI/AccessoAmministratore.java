package GUI;



import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


// TODO: Auto-generated Javadoc
/**
 * The Class AccessoAmministratore.
 */
public class AccessoAmministratore extends JFrame {

	/** The content pane. */
	private JPanel contentPane;
	
	/** The text codice fiscale. */
	private JTextField textCodiceFiscale;
	
	/** The password field. */
	private JPasswordField passwordField;

/** The controller. */
Controller controller;

/** The frame. */
JFrame frame;
	
	/**
	 * Instantiates a new accesso amministratore.
	 *
	 * @param c the controller
	 */
	public AccessoAmministratore(Controller c) {
		 
		
		controller=c;
		frame=this;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 459);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);
		panel.setBounds(143, 37, 289, 273);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblImmagine = new JLabel("");
		lblImmagine.setBounds(10, 11, 269, 198);
		panel.add(lblImmagine);
		ImageIcon Immagineicona=new ImageIcon(this.getClass().getResource("/Image/admin.png"));
		lblImmagine.setIcon(Immagineicona);
		
		JLabel lblNewLabel_1 = new JLabel("CodiceFiscale");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 223, 80, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblNewLabel_2.setBounds(10, 248, 49, 14);
		panel.add(lblNewLabel_2);
		
		textCodiceFiscale = new JTextField();
		textCodiceFiscale.setBounds(100, 220, 96, 20);
		panel.add(textCodiceFiscale);
		textCodiceFiscale.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(100, 245, 96, 20);
		panel.add(passwordField);
		
		JLabel lblNewLabel = new JLabel("Accesso");
		lblNewLabel.setBounds(257, 11, 79, 14);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel.setForeground(Color.RED);
		
		JButton btnNewButton = new JButton("Accedi");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 11));
		
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String CodF=new String(textCodiceFiscale .getText());
				if(CodF.length()!=16) {
					JOptionPane.showMessageDialog(frame,"Codice Fiscale errato!");
				}
				else {
				String messaggio=controller.consentireaccessoAdmin(textCodiceFiscale.getText(),passwordField.getText());
				if(messaggio.contentEquals("accesso consentito")) {
				JFrame frameHomeAmministratore=new HomeAmministratore(controller,frame);
				frame.setVisible(false);
				frameHomeAmministratore.setVisible(true);}
				else {
				JOptionPane.showMessageDialog(frame,messaggio);}
			}}
		});
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setBounds(247, 343, 89, 23);
		contentPane.add(btnNewButton);
	}
}
