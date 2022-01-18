import java.awt.Component;
import java.util.LinkedList;
import java.util.Queue;

public class CommandExecutioner{

	public static Queue<String> sendQueue = new LinkedList<String>();

	
	public static boolean loginResult = false;
	public static int loginPerm = 0;
	
	static String iSep = "_"; //Item Seperator
	static String lSep = "#"; //Line Seperator
	static String cSep = "@"; //Command Seperator
	
	static String userString = "";
	static String passString = "";
	
	static String username = "";
	
	static String employees = "";
	static String tables = "";
	static String items = "";
	static String tableItems= "";
	static String earnings = "";
	
	
	static boolean loginErr = false;
	
	public static boolean getTables = false;
	
	static Thread workerThread = new Thread(new Runnable() {
		
		@Override
		public void run() {
			while(true) {
				try {
					//Login
					if (tryLogin) {
						String[] str = employees.split(lSep);
						//System.out.println(str[0]);
						for (int i = 0; i < str.length; i++) {
							String[] values = str[i].split(iSep);
							if (values[0].equals(userString) && values[3].equals(passString)) {
								tryLogin = false;
								loginResult = true;
								loginPerm = Integer.parseInt(values[4]);
								username = values[2];
								System.out.println("Access grandeed with level " + loginPerm + "!");
							}

						}
						loginErr = true;
					}
					//Update tables
					if(getTables || TablePanel.innerPanel.getComponents().length == 0) {
						if (tables != "") {
							String[] str = tables.split(lSep);
							TablePanel.tables.clear();
							Component[] componentList = TablePanel.innerPanel.getComponents();
							for(Component c : componentList){				    
							    if(c instanceof TableButton)
							    	TablePanel.innerPanel.remove(c);
							    
							}
							for (int i = 0; i < str.length; i++) {
								String[] values = str[i].split(iSep);
			
									TableButton button = new TableButton(values[0] + "", Integer.parseInt(values[0]));
									button.setActionCommand("table" + values[0]);
									//System.out.println("table" + values[0]);
									if(Integer.parseInt(values[1]) == 1)
										button.state = TableState.Full;
									TablePanel.innerPanel.add(button);
									TablePanel.tables.add(button);
									button.doClick();

							}
							TablePanel.innerPanel.revalidate();
							TablePanel.innerPanel.repaint();
							
							MainWindow.tablePanel.revalidate();
							MainWindow.tablePanel.repaint();
							
							
						}
						getTables = false;
					}
					
				} catch (Exception e) {e.printStackTrace();}
								
				try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
			}
			
		}
	});
	
	static boolean tryLogin = false;
	
	//Handle input data
	@SuppressWarnings("unused")
	public static void execute(String command) {
		
		String cmd = command.split(cSep)[0];
		String argStr = "";
		String[] args = {};
		try {
			argStr = command.split(cSep)[1];
			args = argStr.split(iSep);
		} catch (Exception e) {}
		
		switch (cmd) {
		case "employees":
			employees = argStr;
			break;
		case "tables":
			tables = argStr;
			getTables = true;
			break;
		case "items":
			items = argStr;
			break;
		case "tableitems":
			tableItems = argStr;
			break;
		case "earnings":
			earnings = argStr;
			break;
		default:
			break;
		}
		
		//System.out.println(command + " - executed!");
	}
	
	public static void login(String user, String pass) {
		tryLogin = true;
		userString = user;
		passString = RMSUtils.Password.toHash(pass);
		if(!workerThread.isAlive())
			workerThread.start();
		//init data
		sendQueue.add("getemployees");
		sendQueue.add("getitems");
		sendQueue.add("gettableitems");
		sendQueue.add("getearnings");
	}

}
