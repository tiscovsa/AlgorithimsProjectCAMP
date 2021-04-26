import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class graphicInterface extends JFrame {
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
		//for Shortest Path
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				response.setText("Shortest Path Entries are as follows : ");
				getShortestPath();
				panel.add(scrollBar);
				results.setShowGrid(true);
				results.setGridColor(Color.red);
				model.addColumn("#");
				model.addColumn("Stop Name");
				panel.add(timeTaken);
				//display List of Stops
				//display time taken
				
				//TODO import dataset
				//TODO add dynamic row entry
				//TODO add Cost to timeTaken
			}
		});
		submit2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				response.setText("Bus stop details are as follows : ");
				getBusStopDetails();
				panel.add(scrollBar);
				results.setShowGrid(true);
				results.setGridColor(Color.red);
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
				//display full set of info - FOR EVERY STOP MATCHED
				
				//TODO import dataset
				//TODO add dynamic row entry
			}
		});
		submit3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				response.setText("The buses that arrive at the selected time are as follows : ");
				getArrivalTime();
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

				//display all trips that return at hh:mm:ss
			    
			  //TODO import dataset
			  //TODO add dynamic row entry
			}
		});
	}
	public void getShortestPath() {	
	}
	public void getBusStopDetails() {
	}
	public void getArrivalTime() {
	}
}
