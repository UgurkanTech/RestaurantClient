import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

public class TableMenuPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton addButton = new JButton("Add Table");
	JButton editButton = new JButton("Edit Table");
	JButton reviewButton = new JButton("Review Table");
	JButton statusButton = new JButton("Status Table");
	
	JButton supportButton = new JButton("Support");
	JButton optionsButton = new JButton("Options");
	
	
	
	public TableMenuPanel() {
		setBackground(Color.cyan);
		setLayout(new GridLayout(0, 1, 100, 0));
		
		setPreferredSize(new Dimension(250 ,(int)getSize().getHeight()));

		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		addButton.setFont(MainWindow.font);
		editButton.setFont(MainWindow.font);
		reviewButton.setFont(MainWindow.font);
		statusButton.setFont(MainWindow.font);
		
		supportButton.setFont(MainWindow.font);
		optionsButton.setFont(MainWindow.font);
		
		optionsButton.setForeground(Color.blue);
		
		addButton.setUI(new MenuButtonUI());
		editButton.setUI(new MenuButtonUI());
		reviewButton.setUI(new MenuButtonUI());
		statusButton.setUI(new MenuButtonUI());
		supportButton.setUI(new MenuButtonUI());
		optionsButton.setUI(new MenuButtonUI());
		
		addButton.setActionCommand("add");
		editButton.setActionCommand("edit");
		reviewButton.setActionCommand("review");
		statusButton.setActionCommand("status");
		supportButton.setActionCommand("support");
		optionsButton.setActionCommand("options");
		
		
		addButton.addActionListener(MainWindow.eventManager);
		editButton.addActionListener(MainWindow.eventManager);
		reviewButton.addActionListener(MainWindow.eventManager);
		statusButton.addActionListener(MainWindow.eventManager);
		supportButton.addActionListener(MainWindow.eventManager);
		optionsButton.addActionListener(MainWindow.eventManager);
		
		add(addButton);
		add(Box.createVerticalStrut(75));
		add(editButton);
		add(Box.createVerticalStrut(75));
		add(reviewButton);
		add(Box.createVerticalStrut(75));
		add(statusButton);

		add(Box.createVerticalGlue());
		add(Box.createVerticalGlue());
		add(supportButton);
		add(Box.createVerticalStrut(75));
		add(optionsButton);
		
	}

	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        Color color1 = Color.gray;
        Color color2 = new Color(40, 0, 0);
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }
}
