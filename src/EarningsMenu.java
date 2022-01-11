import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Timer;

public class EarningsMenu extends JFrame implements ActionListener{


	private static final long serialVersionUID = 1L;

	 JList tableitemlist;
	 DefaultListModel tableitemmodel;
	 

	 
	 JPanel innerPanel = new JPanel();

	 JButton clearButton = new JButton("Clear Earnings");
	 JButton closeButton = new JButton("Close");
	 
	 Timer timer = new Timer(100, this);
	 
	 float totalPrice = 0;
	 
	 JLabel totalLabel = new JLabel("Total Earning: $0");
	 
	public EarningsMenu() {
		setSize(500,500);
		setTitle("Earnings Menu");
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getSize().width) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLayout(new BorderLayout());

		clearButton.addActionListener(this);
		closeButton.addActionListener(this);
		

		tableitemmodel = new DefaultListModel();
	    tableitemlist = new JList(tableitemmodel);
	    JScrollPane pane2 = new JScrollPane(tableitemlist);
	    
	    
	    pane2.setPreferredSize(new Dimension(400, 250));
	    updateLists();
	    
	    innerPanel.setLayout(new GridLayout(0, 1, 100, 0));
	    innerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	    
	    innerPanel.add(totalLabel);
	    innerPanel.add(Box.createVerticalStrut(10));
	    innerPanel.add(clearButton);
	    innerPanel.add(Box.createVerticalStrut(5));
	    innerPanel.add(closeButton);
	    
	   // add(pane, BorderLayout.WEST);
	    
	    
	    
	    add(innerPanel, BorderLayout.SOUTH);
	    
	    add(pane2, BorderLayout.CENTER);
		setVisible(true);
	}
	
	private void updateLists() {
		tableitemmodel.clear();

		System.err.println( CommandExecutioner.earnings);
	    String[] earnings = CommandExecutioner.earnings.split(CommandExecutioner.lSep);
	    //System.out.println(CommandExecutioner.items);

	    totalPrice = 0;
	    for (int i = 0; i < earnings.length; i++) {
	    	String[] e = earnings[i].split(CommandExecutioner.iSep);
	    	if(e.length > 1) {
	    		tableitemmodel.addElement(e[1] + " - " + e[0]);
		    	totalPrice += Integer.parseInt(e[0]);
	    	}
	    	
	    	
		}

	    tableitemlist.setSelectedIndex(0);
	    
	    totalLabel.setText("Total price: $" + totalPrice);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(clearButton)) {
			
			CommandExecutioner.sendQueue.add("clearearning" + CommandExecutioner.cSep + MainWindow.eventManager.selectedTable.id);
			CommandExecutioner.sendQueue.add("getearnings");
			timer.start();
			
		}
		if(e.getSource().equals(closeButton)) {
			
			setVisible(false);
		}
		if(e.getSource().equals(timer)) {
			updateLists();
			timer.stop();
		}
		
	}

}
