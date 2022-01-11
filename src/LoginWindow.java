import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;

public class LoginWindow extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	public Timer timer = new Timer(250, this);
	
	static ImageIcon loginIcon = new ImageIcon(new ImageIcon("login.png").getImage().getScaledInstance(75, 75, Image.SCALE_FAST));
	
	JButton loginButton;
	
	JTextField userText;
	JPasswordField passwordText;
	
	public LoginWindow() {
		setSize(350, 150);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Login to RMS");
		setResizable(false);
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getSize().width) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
		setLayout(null);

		JLabel userLabel = new JLabel("User");
		userLabel.setBounds(100, 10, 80, 25);
		add(userLabel);

		userText = new JTextField(32);
		userText.setText("admin");
		userText.setBounds(160, 10, 165, 25);
		add(userText);

		JLabel passwordLabel = new JLabel("Password");
		
		passwordLabel.setBounds(100, 40, 80, 25);
		add(passwordLabel);
		passwordText = new JPasswordField(32);
		passwordText.setBounds(160, 40, 165, 25);
		passwordText.setText("admin");
		add(passwordText);

		loginButton = new JButton("Login");
		loginButton.setBounds(100, 80, 220, 25);
		loginButton.addActionListener(this);
		add(loginButton);
		
		JLabel connectUcon = new JLabel(loginIcon);
		connectUcon.setBounds(0, 0, 100, 100);
		add(connectUcon);
		
		
		setVisible(true);
		
	}
	boolean loginResult = false;
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(loginButton)) {
			timer.start();
			CommandExecutioner.login(userText.getText(), passwordText.getText());
		}
		
		if (e.getSource().equals(timer)) {
			if (CommandExecutioner.loginResult) {
				setVisible(false);
				timer.stop();
				new MainWindow();
				
			}
			else {
				if (CommandExecutioner.loginErr) {
					setTitle("Login - Invalid User!");
				}
			}
		}
		
	}
}
