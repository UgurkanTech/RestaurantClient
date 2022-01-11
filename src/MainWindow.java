import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	static Font font = new Font("Louis George Café", Font.PLAIN, 25);
	
	static ImageIcon woodIcon = new ImageIcon(new ImageIcon("wood.png").getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
	
	static final int sizeX = 1600;
	static final int sizeY = 900;
	
	static float tableSizeX = 0;
	static float tableSizeY = 0;
	
	public static TablePanel tablePanel;
	public static MainMenuPanel mainMenuPanel;
	public static TableMenuPanel tableMenuPanel;
	public static EventManager eventManager;
	
	public MainWindow(){
		setSize(sizeX, sizeY);
		setTitle("Restauran Management System");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getSize().width) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
		setVisible(true);
		setLayout(new BorderLayout());
		setIconImage(new ImageIcon("icon.png").getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH));
		
		try {
		     GraphicsEnvironment ge = 
		     GraphicsEnvironment.getLocalGraphicsEnvironment();
		     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Louis George Cafe.ttf")));
		} catch (IOException|FontFormatException e) { System.err.println("Font Loading Error!");}
		
		eventManager = new EventManager();
		tablePanel = new TablePanel();
		mainMenuPanel = new MainMenuPanel();
		tableMenuPanel = new TableMenuPanel();
		
		add(tablePanel, BorderLayout.CENTER);
		add(mainMenuPanel, BorderLayout.EAST);
		add(tableMenuPanel, BorderLayout.WEST);
		
		
		revalidate();
		pack();
		
		tableSizeX = tablePanel.getWidth();
		tableSizeY = tablePanel.getHeight();
		
	}
	
}
