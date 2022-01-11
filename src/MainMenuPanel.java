import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenuPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JButton employeesButton = new JButton("Manage Staff");
	JButton showButton = new JButton("Show Earnings");
	JButton manageButton = new JButton("Manage Items");
	
	JButton exitButton = new JButton("Exit");
	
	JPanel imagePanel = new JPanel();
	JPanel buttonsPanel = new JPanel() {
		private static final long serialVersionUID = 1L;
		@Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	        int w = getWidth();
	        int h = getHeight();
	        Color color1 = new Color(0,100,0);
	        Color color2 = Color.black;
	        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
	        g2d.setPaint(gp);
	        g2d.fillRect(0, 0, w, h);
	    }
		
		
	};
	
	JLabel user = new JLabel("user");
	
	public MainMenuPanel(){
		setBackground(Color.darkGray);
		imagePanel.setBackground(Color.darkGray);
		buttonsPanel.setBackground(Color.darkGray);
		
		setLayout(new BorderLayout());
		
		imagePanel.setLayout(new BorderLayout());
		imagePanel.setPreferredSize(new Dimension(200 ,(int)getSize().getHeight()));
		
		JLabel logo = new JLabel(new ImageIcon(new ImageIcon("logo.png").getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH)));
		
		imagePanel.add(logo, BorderLayout.CENTER);
		imagePanel.setPreferredSize(new Dimension(250,250));
		
		buttonsPanel.setPreferredSize(new Dimension(250 ,(int)getSize().getHeight()));
		
		buttonsPanel.setLayout(new GridLayout(0, 1, 0, 0));
		buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		employeesButton.setFont(MainWindow.font);
		showButton.setFont(MainWindow.font);
		manageButton.setFont(MainWindow.font);
		exitButton.setFont(MainWindow.font);
		exitButton.setForeground(Color.red);
		
		employeesButton.setUI(new MenuButtonUI());
		showButton.setUI(new MenuButtonUI());
		manageButton.setUI(new MenuButtonUI());
		exitButton.setUI(new MenuButtonUI());
		
		buttonsPanel.add(employeesButton);
		buttonsPanel.add(Box.createVerticalStrut(75));
		buttonsPanel.add(showButton);
		buttonsPanel.add(Box.createVerticalStrut(75));
		buttonsPanel.add(manageButton);
		
		showButton.addActionListener(MainWindow.eventManager);
		showButton.setActionCommand("earnings");
		
		exitButton.addActionListener(MainWindow.eventManager);
		exitButton.setActionCommand("exit");
		
		manageButton.addActionListener(MainWindow.eventManager);
		manageButton.setActionCommand("manageitems");
		
		employeesButton.addActionListener(MainWindow.eventManager);
		employeesButton.setActionCommand("employees");
		
		user.setText("<html>" + CommandExecutioner.username  + "<br> (Level: " + CommandExecutioner.loginPerm + ") </html>");
		user.setForeground(Color.white);
		user.setFont(MainWindow.font);
		

		buttonsPanel.add(Box.createVerticalGlue());
		buttonsPanel.add(user);
		buttonsPanel.add(Box.createVerticalGlue());
		buttonsPanel.add(exitButton);
		
		add(imagePanel,BorderLayout.NORTH);
		add(buttonsPanel,BorderLayout.CENTER);
		
	}
}
