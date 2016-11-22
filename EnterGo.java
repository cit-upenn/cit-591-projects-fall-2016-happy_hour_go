import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class EnterGo {
	private static JTextField display;

	public static void main(String[] args) {
		JFrame frame = new JFrame("HappyHour Go");
		display = new JTextField("could input time or something else");

		class PressedEnter implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				JTextField t = (JTextField) e.getSource();
				System.out.println(t.getText());
				t.setText("have fun");
			}
		}
		display.addActionListener(new PressedEnter());

		frame.setLayout(new BorderLayout());
		frame.add(getButtonPanel(), BorderLayout.CENTER); // the button filed
		frame.add(display, BorderLayout.NORTH); // the text field
		frame.setSize(400, 300); // the size of the display window
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static JPanel getButtonPanel() {
		JPanel output = new JPanel();
		output.setLayout(new GridLayout(1, 1));
		JButton[] buttons = new JButton[1];
		String[] buttonLabels = { "Go!" };

		class Dialer implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if ("javax.swing.JButton".equals(e.getSource().getClass().getName())) {
					JButton button = (JButton) e.getSource();
					String number = button.getText();
					display.setText(display.getText() + number);
				}
			}
		}
		Dialer d = new Dialer();
		buttons[0] = new JButton(buttonLabels[0]);
		buttons[0].addActionListener(d);
		output.add(buttons[0]);
		return output;
	}
}
