import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

public class TableButton extends JButton{

	private static final long serialVersionUID = 1L;

	public int id;
	public TableState state;
	
	public TableButton(String text, int id) {
		super(text);
		this.state = TableState.Empty;
		this.id = id;
		setUI(new TableButtonUI());
		setFont(MainWindow.font);
		setForeground(Color.white);
		setPreferredSize(TablePanel.buttonSize);
		setMinimumSize(new Dimension(100, 50));
		addActionListener(MainWindow.eventManager);
		updateTable();
	}
	
	public void updateTable() {
		switch (state) {
		case Empty:
			setBackground(new Color(153, 102, 51));
			break;
		case SelectedEmpty:
			setBackground(new Color(203, 152, 101));
			break;
		case Full:
			setBackground(new Color(153, 51, 51));
			break;
		case SelectedFull:
			setBackground(new Color(203, 101, 101));
			break;
		}

	}

}
