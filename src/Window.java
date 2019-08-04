import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {
	Window(int width,int height,String title, Game game) {
		JFrame f = new JFrame();//Ctrl + Shift + O to import JFrame
		f.setTitle(title);
		f.setPreferredSize(new Dimension(width,height));
		f.setMaximumSize(new Dimension(width,height));
		f.setMinimumSize(new Dimension(width,height));
		f.add(game);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
}
