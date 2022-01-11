import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class ConnectWindow extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	static ImageIcon connectIcon = new ImageIcon(new ImageIcon("connect.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
	
	public Timer timer = new Timer(250, this);
	
	JTextField ipText;
	JTextField portText;
	JButton loginButton;
	
	ClientTCP client;
	
	public ConnectWindow() {
		setSize(350, 150);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Connect to RMS Server");
		setResizable(false);
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getSize().width) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
		setLayout(null);

		JLabel ipLabel = new JLabel("IP");
		ipLabel.setBounds(100, 10, 80, 25);
		add(ipLabel);

		ipText = new JTextField(32);
		ipText.setText("localhost");
		ipText.setBounds(150, 10, 175, 25);
		add(ipText);

		JLabel portLabel = new JLabel("Port");
		
		portLabel.setBounds(100, 40, 80, 25);
		add(portLabel);
		portText = new JTextField(32);
		portText.setText("5555");
		portText.setBounds(150, 40, 175, 25);
		add(portText);

		loginButton = new JButton("Connect");
		loginButton.setBounds(100, 80, 220, 25);
		loginButton.setActionCommand("connect");
		loginButton.addActionListener(this);
		add(loginButton);
		
		JLabel connectUcon = new JLabel(connectIcon);
		connectUcon.setBounds(0, 0, 100, 100);
		add(connectUcon);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(loginButton)) {
			client = new ClientTCP(ipText.getText(), Integer.parseInt(portText.getText()));
			timer.start();
		}
		
		if (e.getSource().equals(timer)) {
			if (client != null && ClientTCP.connected) {
				setVisible(false);
				timer.stop();
				if(!LoginWindow.loginwindow)
					new LoginWindow();
				
			}
		}
		
	}

}
