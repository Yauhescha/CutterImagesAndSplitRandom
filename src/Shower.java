import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Shower {
	private static class FrameShower implements Runnable {
		final Frame frame;

		public FrameShower(Frame frame) {
			this.frame = frame;
		}

		public void run() {
			frame.show();
		}
	}

	public static void showImage(String filename) throws IOException {
		if(filename.length()<1)return;
		// Read
		File file = new File(filename);
		BufferedImage input = ImageIO.read(file);

		input = Cutter.made(input, 4, 4);

		// Convert
		Kernel sharpKernel = new Kernel(3, 3, new float[] { 0.0f, -1.0f, 0.0f, -1.0f, 5.0f, -1.0f, 0.0f, -1.0f, 0.0f });
		ConvolveOp convolveOp = new ConvolveOp(sharpKernel, ConvolveOp.EDGE_NO_OP, null);
		int width = input.getWidth();
		int height = input.getHeight();
		BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		convolveOp.filter(input, output);
		// Make screen
		Icon icon = new ImageIcon(output);
		JLabel label = new JLabel(icon);
		JFrame frame = new JFrame("Sharp Image");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(label, BorderLayout.CENTER);
		frame.pack();
		// Show
		Runnable runner = new FrameShower(frame);
		EventQueue.invokeLater(runner);
	}

	public static void showImage(BufferedImage image) throws IOException {
		BufferedImage input = image;


		// Convert
		Kernel sharpKernel = new Kernel(3, 3, new float[] { 0.0f, -1.0f, 0.0f, -1.0f, 5.0f, -1.0f, 0.0f, -1.0f, 0.0f });
		ConvolveOp convolveOp = new ConvolveOp(sharpKernel, ConvolveOp.EDGE_NO_OP, null);
		int width = input.getWidth();
		int height = input.getHeight();
		BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		convolveOp.filter(input, output);
		// Make screen
		Icon icon = new ImageIcon(output);
		JLabel label = new JLabel(icon);
		JFrame frame = new JFrame("Sharp Image");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(label, BorderLayout.CENTER);
		frame.pack();
		// Show
		Runnable runner = new FrameShower(frame);
		EventQueue.invokeLater(runner);
	}
}