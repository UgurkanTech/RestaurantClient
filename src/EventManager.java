import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class EventManager implements ActionListener{

	public EventManager() {
		// TODO Auto-generated constructor stub
	}


	TableButton selectedTable;
	
	String selectedTableItems;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().startsWith("table")) {
			TableButton table = (TableButton) e.getSource();
			
			if(selectedTable != null) {
				if (!selectedTable.equals(table)) {
					//CommandExecutioner.sendQueue.add("removetable" + CommandExecutioner.cSep + selectedTable.id);
					switch (selectedTable.state) {
					case Empty:
						selectedTable.state = TableState.SelectedEmpty;
						CommandExecutioner.sendQueue.add("addtable" + CommandExecutioner.cSep + selectedTable.id + CommandExecutioner.iSep + "0");
						break;
					case SelectedEmpty:
						selectedTable.state = TableState.Empty;
						CommandExecutioner.sendQueue.add("addtable" + CommandExecutioner.cSep + selectedTable.id + CommandExecutioner.iSep + "0");
						break;
					case Full:
						selectedTable.state = TableState.SelectedFull;
						CommandExecutioner.sendQueue.add("addtable" + CommandExecutioner.cSep + selectedTable.id + CommandExecutioner.iSep + "1");
						break;
					case SelectedFull:
						selectedTable.state = TableState.Full;
						CommandExecutioner.sendQueue.add("addtable" + CommandExecutioner.cSep + selectedTable.id + CommandExecutioner.iSep + "1");
						break;
					}
					selectedTable.updateTable();
					selectedTable.setText("" + selectedTable.id + "");
				}
				else {
					selectedTable = null;
				}

			}
			//CommandExecutioner.sendQueue.add("removetable" + CommandExecutioner.cSep + table.id);
			switch (table.state) {
			case Empty:
				table.state = TableState.SelectedEmpty;
				selectedTable = table;
				table.setText("-" + table.id + "-");
				CommandExecutioner.sendQueue.add("addtable" + CommandExecutioner.cSep + table.id + CommandExecutioner.iSep + "0");
				break;
			case SelectedEmpty:
				table.state = TableState.Empty;
				table.setText("" + table.id + "");
				CommandExecutioner.sendQueue.add("addtable" + CommandExecutioner.cSep + table.id + CommandExecutioner.iSep + "0");
				break;
			case Full:
				table.state = TableState.SelectedFull;
				selectedTable = table;
				table.setText("-" + table.id + "-");
				CommandExecutioner.sendQueue.add("addtable" + CommandExecutioner.cSep + table.id + CommandExecutioner.iSep + "1");
				break;
			case SelectedFull:
				table.state = TableState.Full;
				table.setText("" + table.id + "");
				CommandExecutioner.sendQueue.add("addtable" + CommandExecutioner.cSep + table.id + CommandExecutioner.iSep + "1");
				break;
			}
			
			table.updateTable();
			CommandExecutioner.sendQueue.add("gettableitems");
		}
		if (e.getActionCommand().equals("status") && selectedTable != null) {
			switch (selectedTable.state) {
			case Empty:
				selectedTable.state = TableState.Full;
				CommandExecutioner.sendQueue.add("addtable" + CommandExecutioner.cSep + selectedTable.id + CommandExecutioner.iSep + "1");
				break;
			case SelectedEmpty:
				selectedTable.state = TableState.SelectedFull;
				CommandExecutioner.sendQueue.add("addtable" + CommandExecutioner.cSep + selectedTable.id + CommandExecutioner.iSep + "1");
				break;
			case Full:
				selectedTable.state = TableState.Empty;
				CommandExecutioner.sendQueue.add("addtable" + CommandExecutioner.cSep + selectedTable.id + CommandExecutioner.iSep + "0");
				break;
			case SelectedFull:
				selectedTable.state = TableState.SelectedEmpty;
				CommandExecutioner.sendQueue.add("addtable" + CommandExecutioner.cSep + selectedTable.id + CommandExecutioner.iSep + "0");
				break;
			}
			selectedTable.updateTable();
			
		}
		
		if (e.getActionCommand().equals("add")) {
			int counter = 1;
			for (int i = 0; i < TablePanel.tables.size(); i++) {
				if (counter ==  TablePanel.tables.get(i).id) {
					counter++;
				}
				else {
					break;
				}
			}
			CommandExecutioner.sendQueue.add("addtable" + CommandExecutioner.cSep + (counter) + CommandExecutioner.iSep + "0");
			CommandExecutioner.sendQueue.add("gettables");
		}
		
		if (e.getActionCommand().equals("edit")) {
			if (MainWindow.eventManager.selectedTable != null)
				new EditMenu();
			
		}
		if (e.getActionCommand().equals("review")) {
			if (MainWindow.eventManager.selectedTable != null)
				new ReviewMenu();
		}
		if (e.getActionCommand().equals("earnings")) {
			new EarningsMenu();
		}
		if (e.getActionCommand().equals("exit")) {
			System.exit(0);
		}
		if (e.getActionCommand().equals("manageitems")) {
			if (CommandExecutioner.loginPerm < 4) {
				JOptionPane.showMessageDialog(null, "You don't have permission.", "Permission level 4 required!", JOptionPane.ERROR_MESSAGE);
				
			}
			else {
				JOptionPane.showMessageDialog(null, "This feature will be added soon..", "Opps!", JOptionPane.INFORMATION_MESSAGE);
				//will be implemented
			}
			
		}
		if (e.getActionCommand().equals("employees")) {
			if (CommandExecutioner.loginPerm < 4) {
				JOptionPane.showMessageDialog(null, "You don't have permission.", "Permission level 4 required!", JOptionPane.ERROR_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null, "This feature will be added soon..", "Opps!", JOptionPane.INFORMATION_MESSAGE);
				//will be implemented
			}
		}
		if (e.getActionCommand().equals("options")) {
			new OptionsMenu();
		}
		if (e.getActionCommand().equals("support")) {
			new SupportMenu();
		}
		
	}
	

}
