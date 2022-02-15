package GUI;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;

import javax.swing.JPasswordField;

// TODO: Auto-generated Javadoc
/**
 * The Class Accesso.
 */
public class AccessoUtente extends JFrame {

	/** The content pane. */
	private JPanel contentPane;
	
	/** The controller. */
	private Controller controller;
	
	/** The frame. */
	JFrame frame;
	
	/** The text nick name. */
	private JTextField textNickName;
	
	/** The password field. */
	private JPasswordField passwordField;
	

	/**
	 * Instantiates a new accesso.
	 *
	 * @param c the controller
	 */
	public AccessoUtente(Controller c) {
		setResizable(false);
		controller=c;
		frame = this;
		frame.setVisible(true);
	
	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 6, 6));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Accedi");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnNewButton.setBackground(new Color(255, 140, 0));
		btnNewButton.setForeground(UIManager.getColor("ToggleButton.foreground"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String errore=new String(controller.validit‡NickNamePassword(textNickName.getText(),passwordField.getText()));
				if(errore.contentEquals("OK")) {
				String messaggio= new String(controller.accessoutente(textNickName.getText(),passwordField.getText()));
				if(messaggio.contentEquals("OK")) {
					JFrame frameProvaHome=new HomeUtente(controller,frame);
					frame.setVisible(false);
					frameProvaHome.setVisible(true);
					}
				else {
					if(messaggio.contentEquals("password errata")) {
					JOptionPane.showMessageDialog(frame,"Inserire in maniera corretta la password.");}
					else {
						
						JOptionPane.showMessageDialog(frame,""+messaggio+"");
					}
				}}
				else {
					JOptionPane.showMessageDialog(frame,errore);
				}
			}
		});
		btnNewButton.setBounds(458, 327, 119, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Registrati");
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame frameRegistrazione=new Registrazione(controller,frame);
				frame.setVisible(false);
				frameRegistrazione.setVisible(true);
			}
		});
		btnNewButton_1.setBackground(new Color(255, 140, 0));
		btnNewButton_1.setForeground(UIManager.getColor("TextArea.caretForeground"));
		btnNewButton_1.setBounds(135, 327, 119, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Non sei registrato?");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(135, 314, 119, 14);
		contentPane.add(lblNewLabel_1);
		
		ImageIcon Immagineicona=new ImageIcon(this.getClass().getResource("/Image/omino2.png"));
		
		JPanel panel = new JPanel();
		panel.setBounds(131, 11, 446, 292);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Password");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblNewLabel_3.setBounds(125, 223, 69, 17);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(new Color(255, 0, 0));
		passwordField = new JPasswordField();
		passwordField.setBounds(211, 221, 96, 20);
		panel.add(passwordField);
		JLabel lblNewLabel = new JLabel("NickName");
		lblNewLabel.setLabelFor(this);
		
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblNewLabel.setBounds(125, 193, 78, 14);
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 51, 0));
		
		textNickName = new JTextField();
		textNickName.setBounds(211, 190, 96, 20);
		panel.add(textNickName);
		textNickName.setHorizontalAlignment(SwingConstants.CENTER);
		textNickName.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Accesso!");
		lblNewLabel_2.setBounds(193, 0, 141, 21);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setBackground(Color.RED);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		
		JLabel lblimmagine = new JLabel("");
		lblimmagine.setBounds(134, 24, 200, 158);
		panel.add(lblimmagine);
		lblimmagine.setIcon(Immagineicona);
		
	
		
	}
}
