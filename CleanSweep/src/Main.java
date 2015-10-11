import java.awt.BorderLayout;
import java.awt.Label;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args){
		//1. Create the frame.
		JFrame frame = new JFrame("FrameDemo");

		//2. Optional: What happens when the frame closes?
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//3. Create components and put them in the frame.
		Label label = new Label("Test");
		frame.getContentPane().add(label, BorderLayout.CENTER);

		//4. Size the frame.
		frame.pack();

		//5. Show it.
		frame.setVisible(true);
		
		frame.setTitle("Clean Sweep");
		
		frame.setSize(1280, 720);
	}
}
