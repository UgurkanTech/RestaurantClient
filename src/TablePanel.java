import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class TablePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public static final Dimension buttonSize = new Dimension(200, 100);
	
	static JPanel innerPanel = new JPanel();
	
	public static ArrayList<TableButton> tables = new ArrayList<TableButton>(0);
	
	public static Image backgroundImage = new ImageIcon(new ImageIcon("wood.jpg").getImage().getScaledInstance(1920, 1080, Image.SCALE_SMOOTH)).getImage();
	
	public TablePanel() {
		setBackground(Color.white);
		setLayout(new BorderLayout());
		
		
		
		setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(EtchedBorder.RAISED),
				"Tables", TitledBorder.LEFT, TitledBorder.TOP, MainWindow.font, Color.red));
		
		JLabel label = new JLabel("Restaurant Management System v0.1 Beta");
		label.setFont(MainWindow.font);
		add(label, BorderLayout.SOUTH);
		
		innerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 25));
		innerPanel.setBackground(Color.white);
		
		innerPanel.setPreferredSize(getPreferredSize());
		//JScrollPane scrollFrame = new JScrollPane(innerPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		innerPanel.setAutoscrolls(true);
		innerPanel.setPreferredSize(new Dimension(1000,800));
		innerPanel.setOpaque(false);
		
		add(innerPanel, BorderLayout.CENTER);
		
		CommandExecutioner.sendQueue.add("gettables");
		
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        int w = getWidth();
        int h = getHeight();
        Color color1 = Color.white;
        Color color2 = new Color(225,225,225);
        
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
        
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
        //g2d.drawImage(backgroundImage, 0, 0, this);
    }

}
