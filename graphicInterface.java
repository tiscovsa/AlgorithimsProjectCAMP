import javax.swing.*;
import java.awt.*;

public class graphicInterface extends JFrame{
	private JLabel instructions1 = new JLabel("For 1 - Shortest Path : Enter 2 bus stop names delimited by a comma.");
	private JLabel instructions2 = new JLabel("For 2 - Bus Stop Search : Enter a bus stop's details.");
	private JLabel instructions3 = new JLabel("For 3 - Arrival Time : Enter any expected time of arrival.");
	private JTextField searchBar = new JTextField(30);
    private JButton submit = new JButton("Submit");
    private JTable results = new JTable();
    private JPanel panel = new JPanel();
    private JScrollPane scrollBar = new JScrollPane(results);
    
	public static void main(String[] args) {
		new graphicInterface("Algorithms & Data Structures II Project");
		// TODO
	}
	private graphicInterface(String title) throws HeadlessException {
		super(title);
        setSize(500, 500);
        setResizable(true);
       functionality();
       // setTable();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
	}
	private void functionality() {
		panel.add(instructions1);
		panel.add(instructions2);
		panel.add(instructions3);
		panel.add(searchBar);
		panel.add(submit);
		panel.add(scrollBar);
		add(panel);
	}

}
