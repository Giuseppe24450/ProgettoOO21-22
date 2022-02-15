package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import java.net.MalformedURLException;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import javax.swing.border.EmptyBorder;

import Controller.Controller;
import Model.Ascolto;
import Model.Traccia;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;
// TODO: Auto-generated Javadoc

/**
 * The Class Riproduzione.
 */
public class Riproduzione extends JFrame {

	

	/** The content pane. */
	private JPanel contentPane;

/** The frame. */
JFrame frame;

/** The t. */
Timer timer=new Timer();
	
	/** The controller. */
	Controller controller;

	/** The btn termina. */
	private JButton btnTermina;
	
	/** The panel. */
	private JPanel panel;
	
	/** The lbl riproduzione. */
	private JLabel lblRiproduzione;
	
	/**
	 * Instantiates a new riproduzione.
	 *
	 * @param c the controller
	 * @param frameChiamante the frame chiamante
	 * @param ora the ora
	 * @param minuti the minuti
	 * @param secondi the secondi
	 * @param trac the traccia
	 
	 */
	public Riproduzione(Controller c,JFrame frameChiamante,int ora,int minuti,int secondi,Traccia trac) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame=this;
		controller=c;
		setBounds(100, 100, 515, 353);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.RED));
		panel.setBounds(40, 272, 426, 33);
		panel.setBackground(SystemColor.text);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Avvio");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnNewButton.setBounds(10, 5, 114, 23);
		btnNewButton.setBackground(new Color(255, 0, 255));
		panel.add(btnNewButton);
		
		btnTermina = new JButton("Termina");
		btnTermina.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnTermina.setBounds(302, 5, 114, 23);
		btnTermina.setBackground(new Color(255, 0, 0));
		btnTermina.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				frameChiamante.setVisible(true);
				frame.dispose();
			}
		});
		panel.add(btnTermina);
		
		lblRiproduzione = new JLabel("Riproduzione");
		lblRiproduzione.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblRiproduzione.setBounds(40, 128, 426, 44);
		lblRiproduzione.setBackground(new Color(255, 228, 196));
		lblRiproduzione.setHorizontalAlignment(SwingConstants.CENTER);
		lblRiproduzione.setVerticalAlignment(SwingConstants.TOP);
		contentPane.add(lblRiproduzione);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(5, -25, 659, 341);
		contentPane.add(lblNewLabel);
		ImageIcon Immagineicona=new ImageIcon(this.getClass().getResource("/Image/sfondonote.png"));
		lblNewLabel.setIcon(Immagineicona);
		btnNewButton.addMouseListener(new MouseAdapter() {
			int sec=secondi;
			int m=minuti;
			int h=ora;
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!btnNewButton.isEnabled()) {
					JOptionPane.showMessageDialog(frame,"Traccia già in riproduzione!");
				}
				
				else {
					btnNewButton.setEnabled(false);
				LocalDateTime istante =LocalDateTime.now();
				Ascolto a =new Ascolto(istante,controller.getUtente(),trac);
				controller.aggiungiascolto(a);
					timer.schedule(new TimerTask() {
						  @Override
						  public void run() {
							  if(sec==0&&m==0&&h==0) {
									frameChiamante.setVisible(true);
									frame.dispose();
									}
							  if((0<=h&&h<=9)&&(0<=m&&m<=9)&&(0<=sec&&sec<=9)) {
								lblRiproduzione.setText("0"+h+":0"+m+":0"+sec);
								}
							  if((0<=h&&h<=9)&&(m>9)&&(sec>9)) {
									
									lblRiproduzione.setText("0"+h+":"+m+":"+sec);
									}
							  if((0<=h&&h<=9)&&(0<=m&&m<=9)&&(sec>9)) {
									
									lblRiproduzione.setText("0"+h+":0"+m+":"+sec);
									}
							  if((0<=h&&h<=9)&&(m>9)&&(0<=sec&&sec<=9)) {
									
									lblRiproduzione.setText("0"+h+":"+m+":0"+sec);
									}
							  sec--;
							  
								if(sec==0&&m!=0) {
									m--;
									sec=59;
								}
								if(m==0&&sec==0&&h!=0) {
									h--;
									sec=59;
									m=59;
								}
						  }
						},1000,1000);}}
			
		});
		frame.dispose();
			
	}

}