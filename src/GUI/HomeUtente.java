package GUI;



import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;

import javax.swing.JLabel;

import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JEditorPane;

// TODO: Auto-generated Javadoc
/**
 * The Class ProvaHome.
 */
public class HomeUtente extends JFrame {

	/** The content pane. */
	private JPanel contentPane;

/** The controller. */
Controller controller;
	
	/** The frame. */
	JFrame frame;
	
	/**
	 * Instantiates a new prova home.
	 *
	 * @param c the controller
	 * @param frameChiamante the frame chiamante
	 */
	public HomeUtente(Controller c,JFrame frameChiamante) {
		setResizable(false);
		frame=this;
		controller=c;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 635, 488);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 250, 240));
		panel_1.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 215, 0)));
		panel_1.setBounds(163, 210, 108, 230);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Album");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblNewLabel_1.setBounds(33, 5, 43, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblImmagine2 = new JLabel("");
		lblImmagine2.setBounds(-15, 33, 135, 104);
		panel_1.add(lblImmagine2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 245, 238));
		panel_2.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
		panel_2.setBounds(383, 210, 97, 230);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Artisti");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblNewLabel_2.setBounds(31, 5, 43, 14);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblImmagine3 = new JLabel("");
		lblImmagine3.setBounds(0, 30, 113, 100);
		panel_2.add(lblImmagine3);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 245, 238));
		panel_3.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 255)));
		panel_3.setBounds(514, 55, 97, 230);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("History Ascolti");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblNewLabel_3.setBounds(13, 5, 84, 14);
		panel_3.add(lblNewLabel_3);
		
		JLabel lblImmagine4 = new JLabel("");
		lblImmagine4.setBounds(0, 30, 97, 103);
		panel_3.add(lblImmagine4);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 245, 238));
		panel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(75, 0, 130)));
		panel.setBounds(10, 55, 108, 230);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tracce");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblNewLabel.setBounds(31, 5, 33, 14);
		panel.add(lblNewLabel);
		
		JLabel lblImmagine = new JLabel("");
		lblImmagine.setBounds(-52, 31, 160, 106);
		panel.add(lblImmagine);
		
		
		JButton btnVisualizzaTracce = new JButton("Visualizza");
		btnVisualizzaTracce.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame frameVisualizza=new VisualizzaTracce(controller,frame,"Utente");
				frame.setVisible(false);
				frameVisualizza.setVisible(true);
			}
		});
		btnVisualizzaTracce.setBackground(new Color(199, 21, 133));
		btnVisualizzaTracce.setBounds(0, 148, 108, 23);
		panel.add(btnVisualizzaTracce);
		
		JButton btnPreferiti = new JButton("Preferiti");
		btnPreferiti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPreferiti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame framePreferiti=new Preferiti(controller,frame);
				frame.setVisible(false);
				framePreferiti.setVisible(true);
			}
		});
		btnPreferiti.setBackground(new Color(199, 21, 133));
		btnPreferiti.setBounds(0, 183, 108, 23);
		panel.add(btnPreferiti);
		ImageIcon Immagineicona=new ImageIcon(this.getClass().getResource("/Image/traccia.png"));
		lblImmagine.setIcon(Immagineicona);
		ImageIcon Immagineicona2=new ImageIcon(this.getClass().getResource("/Image/album.png"));
		lblImmagine2.setIcon(Immagineicona2);
		ImageIcon Immagineicona3=new ImageIcon(this.getClass().getResource("/Image/cantante.png"));
		lblImmagine3.setIcon(Immagineicona3);
		ImageIcon Immagineicona4=new ImageIcon(this.getClass().getResource("/Image/ascolto.png"));
		lblImmagine4.setIcon(Immagineicona4);
		
		JButton btnStorico = new JButton("Storico");
		btnStorico.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame frameAscoltoGUI=new AscoltoGUI(controller,frame,"Utente");
				frame.setVisible(false);
				frameAscoltoGUI.setVisible(true);	
			}
		});
		btnStorico.setBackground(new Color(0, 0, 255));
		btnStorico.setBounds(0, 165, 97, 23);
		panel_3.add(btnStorico);
		JButton btnVisualizzArtista = new JButton("Visualizza");
		btnVisualizzArtista.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame frameArtista=new Artista(controller,frame,"Utente");
				frame.setVisible(false);
				frameArtista.setVisible(true);		
			}
		});
		btnVisualizzArtista.setBackground(new Color(255, 0, 0));
		btnVisualizzArtista.setBounds(0, 167, 97, 23);
		panel_2.add(btnVisualizzArtista);
		
		JButton btnVisualizzAlbum = new JButton("Visualizza");
		btnVisualizzAlbum.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame frameAlbum=new Album(controller,frame,"Utente");
				frame.setVisible(false);
				frameAlbum.setVisible(true);
			}
		});
		
		btnVisualizzAlbum.setBackground(new Color(255, 215, 0));
		btnVisualizzAlbum.setForeground(new Color(0, 0, 0));
		btnVisualizzAlbum.setBounds(0, 167, 108, 23);
		panel_1.add(btnVisualizzAlbum);
		
		JLabel lblNewLabel_4 = new JLabel("Benvenuto!");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblNewLabel_4.setBounds(243, 0, 138, 25);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("\"La musica pu\u00F2 nominare l\u2019innominabile e comunicare l\u2019inconoscibile.\"");
		lblNewLabel_5.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_5.setBounds(80, 11, 475, 41);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblImmagine5 = new JLabel("");
		lblImmagine5.setBounds(163, -92, 548, 514);
		contentPane.add(lblImmagine5);
		ImageIcon Immagineicona5=new ImageIcon(this.getClass().getResource("/Image/Musimmagine.png"));
		lblImmagine5.setIcon(Immagineicona5);
		
	
		
	}
}
