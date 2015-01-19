import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Austin extends GameObj{
	
	public static final String img_file = "AustinAdvanceorRetreat.png";
	public static final String img_file2 = "AustinAttack1.png";
	
	public static final int SIZE = 200;
	public static final int INIT_X = 900;
	public static final int INIT_Y = 100;
	public static final int INIT_VEL_X = 0;
	public static final int INIT_VEL_Y = 0;
	
	private static BufferedImage img;
	private static BufferedImage img2;

	public Austin(int courtWidth, int courtHeight) {
		super(INIT_VEL_X, INIT_VEL_Y, INIT_X, INIT_Y, SIZE, SIZE, courtWidth,
				courtHeight);
		try {
			if (img == null) {
				img = ImageIO.read(new File(img_file));
			}
		} catch (IOException e) {
			System.out.println("Internal Error:" + e.getMessage());
		}
		try {
			if (img2 == null) {
				img2 = ImageIO.read(new File(img_file2));
			}
		} catch (IOException e) {
			System.out.println("Internal Error:" + e.getMessage());
		}
	}
	
	@Override
	public void draw(boolean mode, Graphics g) {
		if(mode) {
			g.drawImage(img2, pos_x, pos_y, width, height, null);
		}
		else {
			g.drawImage(img, pos_x, pos_y, width, height, null);
		}
	}
}
