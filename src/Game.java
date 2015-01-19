
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.applet.*;

public class Game extends JApplet implements Runnable {

	@Override
	public void run() {
		
		//background image
		BufferedImage bgd = null;
		String img_file = "space.jpg";
		try {
			if (bgd == null) {
				bgd = ImageIO.read(new File(img_file));
			}
			} catch (IOException e) {
				System.out.println("Internal Error:" + e.getMessage());
			}
		JLabel background2 = new JLabel(new ImageIcon(img_file));
		background2.setSize(1000, 500);
		background2.setLayout(new BorderLayout());

		
		final JFrame frame = new JFrame("Austin v. Penguin");
		frame.add(background2);
		frame.setLocation(100, 100);
		final JPanel status_panel = new JPanel(new GridLayout(1,3));
		final JLabel status = new JLabel("FIGHT");
		final JLabel scoreLabelP = new JLabel("Score: 0");
		final JLabel scoreLabelA = new JLabel("Score: 0");
		status_panel.add(scoreLabelP);
		status_panel.add(status);
		status_panel.add(scoreLabelA);
		background2.add(status_panel, BorderLayout.SOUTH);
		
		final GameCourt court = new GameCourt(status, scoreLabelA, scoreLabelP);
		court.setOpaque(false);
		
		final JPanel control_panel = new JPanel();
		
		final JButton reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.reset();
			}
		});
		control_panel.add(reset);
		background2.add(control_panel, BorderLayout.NORTH);
		
		background2.add(court, BorderLayout.CENTER);
	    frame.setResizable(true);

		frame.setPreferredSize(new Dimension(1000, 500));
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		court.reset();
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game());
	}

}
