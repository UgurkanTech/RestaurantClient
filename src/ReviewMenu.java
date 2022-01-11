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

public class ReviewMenu extends JFrame implements ActionListener{

	class ItemPair{
		public ItemPair(String item, String count) {
			this.item = item;
			this.count = count;
	
		}
		String item;
		String count;
		
	}
	
	private static final long serialVersionUID = 1L;

	 JList itemlist;
	 DefaultListModel itemmodel;
	
	 JList tableitemlist;
	 DefaultListModel tableitemmodel;
	 

	 
	 JPanel innerPanel = new JPanel();

	 JButton clearButton = new JButton("Clear Items & Save Purchase");
	 JButton closeButton = new JButton("Close");
	 
	 Timer timer = new Timer(100, this);
	 
	 float totalPrice = 0;
	 
	 JLabel totalLabel = new JLabel("Total Price: $0");
	 
	public ReviewMenu() {
		setSize(500,500);
		setTitle("Review Table");
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getSize().width) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLayout(new BorderLayout());

		clearButton.addActionListener(this);
		closeButton.addActionListener(this);
		
		itemmodel = new DefaultListModel();
	    itemlist = new JList(itemmodel);
	    JScrollPane pane = new JScrollPane(itemlist);
	    
		tableitemmodel = new DefaultListModel();
	    tableitemlist = new JList(tableitemmodel);
	    JScrollPane pane2 = new JScrollPane(tableitemlist);
	    
	    pane.setPreferredSize(new Dimension(150, 300));
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
		itemmodel.clear();
		
	    String[] items = CommandExecutioner.items.split(CommandExecutioner.lSep);
	    //System.out.println(CommandExecutioner.items);
	    for (int i = 0; i < items.length; i++)
	        itemmodel.addElement(items[i].split(CommandExecutioner.iSep)[1]);

	    
	    String[] tables = CommandExecutioner.tableItems.split(CommandExecutioner.lSep);
	    
	    ArrayList<ItemPair> tableItems = new ArrayList<ItemPair>();
	    for (int i = 0; i < tables.length; i++) {
	    	try {
	    		if (tables[i].split(CommandExecutioner.iSep)[0].equals(MainWindow.eventManager.selectedTable.id + "")) {
					tableItems.add(new ItemPair(tables[i].split(CommandExecutioner.iSep)[1], tables[i].split(CommandExecutioner.iSep)[2]));
				}
				System.out.println(tables[i]);
			} catch (Exception e) {}
			
		}
	    totalPrice = 0;
	    for (int i = 0; i < tableItems.size(); i++) {
	    	tableitemmodel.addElement(tableItems.get(i).count + "X - $" + (items[Integer.parseInt(tableItems.get(i).item) - 1].split("_")[3]) + " --- " + (items[Integer.parseInt(tableItems.get(i).item) - 1].split("_")[1]) + " --- " + (items[Integer.parseInt(tableItems.get(i).item) - 1].split("_")[2]));
	    	totalPrice += Integer.parseInt(tableItems.get(i).count) * Integer.parseInt((items[Integer.parseInt(tableItems.get(i).item) - 1].split("_")[3]));
	    }
	    tableitemlist.setSelectedIndex(0);
	    itemlist.setSelectedIndex(0);
	    totalLabel.setText("Total price: $" + totalPrice);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(clearButton)) {
			int item = itemlist.getSelectedIndex()+1;
			String count = "";
			CommandExecutioner.sendQueue.add("cleartableitem" + CommandExecutioner.cSep + MainWindow.eventManager.selectedTable.id);
			CommandExecutioner.sendQueue.add("gettableitems");
			LocalDate date = LocalDate.now(); // Create a date object
			  System.out.println(date);
			CommandExecutioner.sendQueue.add("addearning" + CommandExecutioner.cSep + (int)totalPrice + CommandExecutioner.iSep + date);
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
