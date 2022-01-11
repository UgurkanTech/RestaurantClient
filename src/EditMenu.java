import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

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

public class EditMenu extends JFrame implements ActionListener{

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
	 
	 JTextField amount;
	 
	 JPanel innerPanel = new JPanel();

	 JButton addButton = new JButton("Add");
	 JButton removeButton = new JButton("Remove");
	 
	 Timer timer = new Timer(100, this);
	 
	public EditMenu() {
		setSize(500,300);
		setTitle("Edit Table");
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getSize().width) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		amount = new JTextField(15);
		amount.setText("1");
		
		addButton.addActionListener(this);
		removeButton.addActionListener(this);
		
		itemmodel = new DefaultListModel();
	    itemlist = new JList(itemmodel);
	    JScrollPane pane = new JScrollPane(itemlist);
	    
		tableitemmodel = new DefaultListModel();
	    tableitemlist = new JList(tableitemmodel);
	    JScrollPane pane2 = new JScrollPane(tableitemlist);
	    
	    pane.setPreferredSize(new Dimension(150, 300));
	    pane2.setPreferredSize(new Dimension(150, 300));
	    updateLists();
	    
	    innerPanel.setLayout(new GridLayout(0, 1, 100, 0));
	    innerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	    innerPanel.add(addButton);
	    innerPanel.add(Box.createVerticalStrut(75));
	    innerPanel.add(new JLabel("Amount:"));
	    innerPanel.add(amount);
	    innerPanel.add(Box.createVerticalStrut(75));
	    innerPanel.add(removeButton);
	    
	    add(pane, BorderLayout.WEST);
	    add(innerPanel, BorderLayout.CENTER);
	    add(pane2, BorderLayout.EAST);
		setVisible(true);
	}
	
	private void updateLists() {
		tableitemmodel.clear();
		itemmodel.clear();
		
	    String[] items = CommandExecutioner.items.split(CommandExecutioner.lSep);
	    //System.out.println(CommandExecutioner.items);
	    if(items.length == 1)
	    	CommandExecutioner.sendQueue.add("getitems");
	    
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
	    for (int i = 0; i < tableItems.size(); i++)
	    	tableitemmodel.addElement((items[Integer.parseInt(tableItems.get(i).item) - 1].split("_")[1]) + "- " +tableItems.get(i).count);
	    
	    tableitemlist.setSelectedIndex(0);
	    itemlist.setSelectedIndex(0);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(addButton)) {
			int item = itemlist.getSelectedIndex()+1;
			String count = amount.getText();
			CommandExecutioner.sendQueue.add("addtableitem" + CommandExecutioner.cSep + MainWindow.eventManager.selectedTable.id + CommandExecutioner.iSep + item + CommandExecutioner.iSep + count);
			CommandExecutioner.sendQueue.add("gettableitems");
			timer.start();
		}
		if(e.getSource().equals(removeButton)) {
			
			String[] items = CommandExecutioner.items.split(CommandExecutioner.lSep);
			String item = "";
		    for (int i = 0; i < items.length; i++)
		       if(((String)tableitemlist.getSelectedValue()).startsWith(items[i].split(CommandExecutioner.iSep)[1]))
		    	   item = (Integer.parseInt(items[i].split(CommandExecutioner.iSep)[0])) + "";
			
			CommandExecutioner.sendQueue.add("removetableitem" + CommandExecutioner.cSep + MainWindow.eventManager.selectedTable.id + CommandExecutioner.iSep + item);
			CommandExecutioner.sendQueue.add("gettableitems");
			timer.start();
		}
		if(e.getSource().equals(timer)) {
			updateLists();
			timer.stop();
		}
		
	}

}
