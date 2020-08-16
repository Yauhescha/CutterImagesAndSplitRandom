import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Cutter {
	static int widthChapter;
	static int heightChapter;
	static ArrayList<BufferedImage> arr;
	static BufferedImage imgReturn;

	public static BufferedImage made(BufferedImage input, int row, int col) {
		getImageSize(input, row, col);

		getImageChapters(input, row, col);

		imgReturn = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_ARGB);

		randomizeImagesChapters(row, col);
		arr.clear();
		return imgReturn;
	}

	private static void randomizeImagesChapters(int row, int col) {
		Random rand = new Random();
		int random;
		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++) {
				random = rand.nextInt(arr.size());
				imgReturn.getGraphics().drawImage(arr.get(random), i * widthChapter, j * heightChapter, widthChapter,
						heightChapter, null);
				arr.remove(random);
			}
	}

	private static void getImageChapters(BufferedImage input, int row, int col) {
		arr = new ArrayList<BufferedImage>();
		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++) {
				BufferedImage img = new BufferedImage(widthChapter, heightChapter, BufferedImage.TYPE_INT_ARGB);
				img = input.getSubimage(i * widthChapter, j * heightChapter, widthChapter, heightChapter);
				arr.add(img);
			}
	}

	private static void getImageSize(BufferedImage input, int row, int col) {
		widthChapter = input.getWidth() / col;
		heightChapter = input.getHeight() / row;
	}
}
