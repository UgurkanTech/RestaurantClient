import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
			CommandExecutioner.sendQueue.add("addtable" + CommandExecutioner.cSep + (MainWindow.tablePanel.tables.size()+1) + CommandExecutioner.iSep + "0");
			CommandExecutioner.sendQueue.add("gettables");
		}
		
		if (e.getActionCommand().equals("edit")) {
			new EditMenu();
		}
		if (e.getActionCommand().equals("review")) {
			new ReviewMenu();
		}
		if (e.getActionCommand().equals("earnings")) {
			new EarningsMenu();
		}
		
	}

}
