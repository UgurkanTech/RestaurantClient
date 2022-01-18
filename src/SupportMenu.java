import java.awt.BorderLayout;
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

public class SupportMenu extends JFrame implements ActionListener{


	
	private static final long serialVersionUID = 1L;

	
	 
	 JPanel innerPanel = new JPanel();

	 JButton closeButton = new JButton("Close");
	 
	 Timer timer = new Timer(100, this);
	 
	public SupportMenu() {
		setSize(500,250);
		setTitle("Support Menu");
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getSize().width) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLayout(new BorderLayout());
		setResizable(false);

		closeButton.addActionListener(this);
	    innerPanel.setLayout(new GridLayout(0, 1, 100, 0));
	    innerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	
	    innerPanel.add(Box.createVerticalStrut(25));
	    innerPanel.add(new JLabel("If any problem occurs, try restarting the application."));
	    innerPanel.add(Box.createVerticalStrut(25));
	    innerPanel.add(new JLabel("For detailed technical support: "));
	    innerPanel.add(new JLabel("E-mail: ugurkan.hosgor@bilgiedu.net"));

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

	}

}
