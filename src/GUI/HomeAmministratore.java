package GUI;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import java.awt.SystemColor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class HomeAmministratore.
 */
public class HomeAmministratore extends JFrame {

	/** The content pane. */
	private JPanel contentPane;

/** The frame. */
JFrame frame;

/** The controller. */
Controller controller;
	
	/**
	 * Instantiates a new home amministratore.
	 *
	 * @param c the controller
	 * @param frameChiamante the frame chiamante
	 */
	public HomeAmministratore(Controller c,JFrame frameChiamante) {
		setResizable(false);
		controller=c;
		frame=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 549, 602);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon Immagineicona=new ImageIcon(this.getClass().getResource("/Image/omino2.png"));
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 255)));
		panel.setBounds(0, 38, 307, 81);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblimmagine = new JLabel("");
		lblimmagine.setBounds(-51, 0, 143, 81);
		panel.add(lblimmagine);
		lblimmagine.setIcon(Immagineicona);
		
		JButton btnUtente = new JButton("Visualizza Utente");
		btnUtente.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnUtente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame frameVisualizzaUtenti=new VisualizzaUtenti(controller,frame);
				frame.setVisible(false);
				frameVisualizzaUtenti.setVisible(true);
			}
		});
		
		btnUtente.setBounds(158, 26, 149, 23);
		panel.add(btnUtente);
		btnUtente.setBackground(new Color(0, 0, 255));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(2, 2, 2, 1, (Color) new Color(75, 0, 130)));
		panel_1.setBounds(236, 130, 299, 81);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("Visualizza Tracce");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame frameVisualizza=new VisualizzaTracce(controller,frame,"Admin");
				frame.setVisible(false);
				frameVisualizza.setVisible(true);
			}
		});
		
		btnNewButton.setBackground(new Color(255, 0, 255));
		btnNewButton.setBounds(0, 32, 139, 23);
		panel_1.add(btnNewButton);
		
		JLabel lblimmagine2 = new JLabel("");
		lblimmagine2.setBounds(169, 0, 130, 81);
		panel_1.add(lblimmagine2);
		lblimmagine2.setIcon(new ImageIcon(this.getClass().getResource("/Image/traccia.png")));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 140, 0)));
		panel_2.setBounds(0, 242, 307, 89);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblimmagine3 = new JLabel("");
		lblimmagine3.setBounds(0, 0, 120, 125);
		panel_2.add(lblimmagine3);
		lblimmagine3.setIcon(new ImageIcon(this.getClass().getResource("/Image/ascolto.png")));
		
		JButton btnNewButton_1 = new JButton("Visualizza Ascolti");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame frameAscoltoGUIAmministratore=new AscoltoGUI(controller,frame,"Admin");
				frame.setVisible(false);
				frameAscoltoGUIAmministratore.setVisible(true);	
			}
		});
		btnNewButton_1.setBackground(new Color(255, 140, 0));
		btnNewButton_1.setBounds(144, 33, 163, 23);
		panel_2.add(btnNewButton_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.RED));
		panel_3.setBounds(270, 362, 265, 74);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblimmagine4 = new JLabel("");
		lblimmagine4.setBounds(165, 0, 100, 74);
		panel_3.add(lblimmagine4);
		lblimmagine4.setIcon(new ImageIcon(this.getClass().getResource("/Image/cantante.png")));
		
		JButton btnNewButton_2 = new JButton("Visualizza Artisti");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame frameArtista=new Artista(controller,frame,"Amministratore");
				frame.setVisible(false);
				frameArtista.setVisible(true);		
			}
		});
		btnNewButton_2.setBackground(Color.RED);
		btnNewButton_2.setBounds(0, 27, 146, 23);
		panel_3.add(btnNewButton_2);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 255)));
		panel_4.setBounds(0, 447, 307, 74);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblImmagine5 = new JLabel("");
		lblImmagine5.setBounds(-11, 0, 112, 74);
		panel_4.add(lblImmagine5);
		lblImmagine5.setIcon(new ImageIcon(this.getClass().getResource("/Image/album.png")));
		
		JButton btnNewButton_3 = new JButton("Visualizza Album");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame frameAlbum=new Album(controller,frame,"Amministratore");
				frame.setVisible(false);
				frameAlbum.setVisible(true);
			}
		});
		
		btnNewButton_3.setBackground(new Color(255, 0, 255));
		btnNewButton_3.setBounds(143, 29, 164, 23);
		panel_4.add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("Benvenuto!");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel.setBounds(216, 13, 115, 14);
		contentPane.add(lblNewLabel);
		
	
	}
}
