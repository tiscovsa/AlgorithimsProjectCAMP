import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class graphicInterface extends JFrame {
	private String userText;
	DefaultTableModel model = new DefaultTableModel();
	private JLabel instructions1 = new JLabel("For 1 - Shortest Path : Enter 2 bus stop names delimited by a comma.");
	private JTextField searchBar = new JTextField(30);
	private JButton submit = new JButton("Submit");
	
	private JLabel instructions2 = new JLabel("For 2 - Bus Stop Search : Enter a bus stop's details.");
	private JTextField searchBar2 = new JTextField(30);
	private JButton submit2 = new JButton("Submit");

	private JLabel instructions3 = new JLabel("For 3 - Arrival Time : Enter any expected time of arrival in format hh:mm:ss .");
	private JTextField searchBar3 = new JTextField(30);
	private JButton submit3 = new JButton("Submit");

    private JLabel response = new JLabel();
    private JTable results = new JTable(model);
    private JPanel panel = new JPanel();
    private JScrollPane scrollBar = new JScrollPane(results);
    private JLabel timeTaken = new JLabel();
    
	public static void main(String[] args) {
		
		new graphicInterface("Algorithms & Data Structures II Project");
		// TODO
	}
	private graphicInterface(String title) throws HeadlessException {
		super(title);
        setSize(500, 625);
        setResizable(true);
       functionality();
       Table();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
	}
	private void functionality() {
		panel.add(instructions1);
		panel.add(searchBar);
		panel.add(submit);
		panel.add(instructions2);
		panel.add(searchBar2);
		panel.add(submit2);
		panel.add(instructions3);
		panel.add(searchBar3);
		panel.add(submit3);
		panel.add(response);
		add(panel);
	}
	private void Table() {
		//results.setAutoCreateColumnsFromModel(true);
		//QUESTION 1
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearExistingTable();
				userText = searchBar.getText();
				response.setText("Shortest Path Entries are as follows : ");
				String values[][] =Q1();
				panel.add(scrollBar);
				results.setShowGrid(true);
				results.setGridColor(Color.green);
				model.addColumn("#");
				model.addColumn("Stop Name");
				//results.setAutoCreateColumnsFromModel(false);
				panel.add(timeTaken);
				//display List of Stops
				//display time taken
				
				for (int i = 0; i < values.length; i++) {
					model.addRow(new Object[] {i+1,values[i][0]});
				}
				System.out.println(userText);
				//TODO add Cost to timeTaken
			}
		});
		//QUESTION 2
		submit2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearExistingTable();
				userText = searchBar2.getText();
				response.setText("Bus stop details are as follows : ");
				String [][]values = Q2();
				panel.add(scrollBar);
				results.setShowGrid(true);
				results.setGridColor(Color.green);
				model.addColumn("#");
				model.addColumn("Stop ID");
				model.addColumn("Stop Code");
				model.addColumn("Stop Name");
				model.addColumn("Stop Description");
				model.addColumn("Stop Latitude");
				model.addColumn("Stop Longitude");
				model.addColumn("Zone ID");
				model.addColumn("Stop URL");
				model.addColumn("Location Type");
				model.addColumn("Parent Station");
				//results.setAutoCreateColumnsFromModel(false);
				//display full set of info - FOR EVERY STOP MATCHED
				for (int i = 0; i < values.length; i++) {
						model.addRow(new Object[] {i+1, values[i][0], values[i][1], values[i][2], values[i][3], values[i][4], values[i][5], 
								values[i][6], values[i][7], values[i][8], values[i][9]});						
				}
			}
		});
		// QUESTION 3
		submit3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearExistingTable();
				userText = searchBar3.getText();
				response.setText("The buses that arrive at the selected time are as follows : ");
				String [][]values = Q3();
				panel.add(scrollBar);
				results.setShowGrid(true);
				results.setGridColor(Color.red);
				model.addColumn("#");
			    model.addColumn("Trip ID");
			    model.addColumn("Arrival Time");
			    model.addColumn("Departure Time");
			    model.addColumn("Stop ID");
			    model.addColumn("Stop Sequence");
			    model.addColumn("Stop Headsign");
			    model.addColumn("Pickup Type");
			    model.addColumn("Drop-off Type");
			    model.addColumn("Shape Distance Travelled");
			    //results.setAutoCreateColumnsFromModel(false);

				//display all trips that return at hh:mm:ss
			    for (int i = 0; i < values.length; i++) {
					model.addRow(new Object[] {i+1, values[i][0], values[i][1], values[i][2], values[i][3], values[i][4], values[i][5], 
							values[i][6], values[i][7], values[i][8]});
			    }
			  //TODO import data-set
			  //TODO add dynamic row entry
			}
		});
	}
	public void clearExistingTable() {
		int rowCount = model.getRowCount();
		int columnCount = model.getColumnCount()-1;
		model.setColumnCount(0);
		
		if (rowCount > 0) {
			for (int i = 0; i < rowCount; i++) {
				//System.out.println(rowCount);
				model.removeRow((rowCount-1) - i);
			}
		}
		/*if (columnCount > 0) {
			System.out.println(columnCount);
			for (int i = 0; i < columnCount; i++) {
				
				//System.out.println(columnCount);
				//removeColumn(results,(columnCount-1)-i);
			}
		Enumeration<TableColumn> count = results.getColumnModel().getColumns();
		while (count.hasMoreElements()) {
			System.out.println("!weifugwei!");
			results.getColumnModel().removeColumn(count.nextElement());
		}
		}*/
	}

	
	public String[][] Q1() {	
		// magically conjures up some lovely data about Stop Times
		String [][]shortestPathStuff = {{"Dublin"} , {"Meath"} , {"Wicklow"}};
		return shortestPathStuff;
	}
	public String[][] Q2() {
		//gives us yummy bus stop names
		String [][] busStopSearchStuff = {{"2346","5-7","Carlow","Pretty ugly", "324.65","2","4","Kidnap.com","Physical","0"},
				{"728","3-9-0","Honolulu","Hot", "1212","5","5","Collect.com","Imaginary","0"}};
		return busStopSearchStuff;
	}
	public String[][] Q3() {
		//invents a load of data that means something to somebody im sure
		String [][] arrivalTimesStuff = {{"2346","24:00:43","12:00:00", "32465","2","4","Kidnap","Deposit","Many many miles"},
				{"728","17:00:25","23:12:21", "1212","5","5","Collect","Leave","A fair few many miles"}};
		return arrivalTimesStuff;
	}
	//
	//
	// ACTUALLY DO SOMETHING AS SUPPOSED TO PRETENDING THIS DATA IS REAL
	//
	//
}
