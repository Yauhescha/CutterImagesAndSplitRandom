import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Main {
	int temp = 1;

	public static void main(String[] args) {

		Shower shower = new Shower();
		Main main = new Main();
//		File folder = new File("d://2.jpg");
		File folder = new File("d://anime");
		main.processFilesFromFolder(folder);

	}

	public void processFilesFromFolder(File folder) {
		File[] folderEntries = folder.listFiles();
		for (File entry : folderEntries) {
			if (entry.isDirectory()) {
				processFilesFromFolder(entry);
				continue;
			}
			processFile(entry);
		}
	}

	private void processFile(File entry) {
		System.out.println(temp + " " + entry.getAbsolutePath());
		try {
			BufferedImage image = ImageIO.read(entry);

			BufferedImage newI = Cutter.made(image, 4, 4);

			if (newI != null) {
				String filename = entry.getAbsoluteFile().toString().replace(".jpg", ".png");
				ImageIO.write(newI, "png", entry.getAbsoluteFile());
			}
			
		} catch (Exception e) {
			System.out.println("SSSSSSSSSSSSSSSSSS");
			e.printStackTrace();
		}
		temp++;
	}

}
