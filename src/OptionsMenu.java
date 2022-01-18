import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class OptionsMenu extends JFrame implements ActionListener{


	
	private static final long serialVersionUID = 1L;

	
	 
	 JPanel innerPanel = new JPanel();

	 JButton muteButton = new JButton("Sounds ON/OFF");
	 JButton closeButton = new JButton("Close");
	 
	 JLabel soundsJLabel;
	 
	 Timer timer = new Timer(100, this);
	 
	 public static boolean sounds = true;
	 
	public OptionsMenu() {
		setSize(300,250);
		setTitle("Options Menu");
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getSize().width) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLayout(new BorderLayout());
		setResizable(false);
		
		soundsJLabel = new JLabel("Sounds are enabled: " + sounds);
		if(sounds)
			soundsJLabel.setForeground(Color.green);
		else 
			soundsJLabel.setForeground(Color.red);
	
		muteButton.addActionListener(this);
		closeButton.addActionListener(this);
	    innerPanel.setLayout(new GridLayout(0, 1, 100, 0));
	    innerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	
	    innerPanel.add(Box.createVerticalStrut(25));
	    innerPanel.add(soundsJLabel);
	    innerPanel.add(Box.createVerticalStrut(25));
	    innerPanel.add(muteButton);

	    innerPanel.add(Box.createVerticalStrut(25));
	    innerPanel.add(closeButton);
	    

	    add(innerPanel, BorderLayout.CENTER);
	 
		setVisible(true);
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(closeButton)) {
			setVisible(false);
			
		}
		if (e.getSource().equals(muteButton)) {
			sounds = !sounds;
			soundsJLabel.setText("Sounds are enabled: " + sounds);
			if(sounds)
				soundsJLabel.setForeground(Color.green);
			else 
				soundsJLabel.setForeground(Color.red);
		
		}

	}

}
