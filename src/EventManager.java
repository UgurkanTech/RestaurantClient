import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventManager implements ActionListener{

	public EventManager() {
		// TODO Auto-generated constructor stub
	}


	TableButton selectedTable;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().startsWith("table")) {
			TableButton table = (TableButton) e.getSource();
			
			if(selectedTable != null) {
				if (!selectedTable.equals(table)) {
					switch (selectedTable.state) {
					case Empty:
						selectedTable.state = TableState.SelectedEmpty;
						break;
					case SelectedEmpty:
						selectedTable.state = TableState.Empty;
						break;
					case Full:
						selectedTable.state = TableState.SelectedFull;
						break;
					case SelectedFull:
						selectedTable.state = TableState.Full;
						break;
					}
					selectedTable.updateTable();
					selectedTable.setText("" + selectedTable.id + "");
				}
				else {
					selectedTable = null;
				}

			}
			switch (table.state) {
			case Empty:
				table.state = TableState.SelectedEmpty;
				selectedTable = table;
				table.setText("-" + table.id + "-");
				break;
			case SelectedEmpty:
				table.state = TableState.Empty;
				table.setText("" + table.id + "");
				break;
			case Full:
				table.state = TableState.SelectedFull;
				selectedTable = table;
				table.setText("-" + table.id + "-");
				break;
			case SelectedFull:
				table.state = TableState.Full;
				table.setText("" + table.id + "");
				break;
			}
			
			table.updateTable();
			
		}
		if (e.getActionCommand().equals("edit") && selectedTable != null) {
			switch (selectedTable.state) {
			case Empty:
				selectedTable.state = TableState.Full;
				break;
			case SelectedEmpty:
				selectedTable.state = TableState.SelectedFull;
				break;
			case Full:
				selectedTable.state = TableState.Empty;
				break;
			case SelectedFull:
				selectedTable.state = TableState.SelectedEmpty;
				break;
			}
			selectedTable.updateTable();
		}

		
	}

}
