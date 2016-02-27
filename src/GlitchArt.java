import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class GlitchArt {
	
	BufferedImage img = null;
	String outputFile = "";
	
	GlitchArt(String imageName, String opf) {
		
		try {
			
			img = ImageIO.read(new File(imageName));
			
		} catch (IOException ioe) {
			
		}
		
		outputFile = opf;
		
	}
	
	/* Not yet working.... */
	public void smearBlue() {
		for (int k = 0; k < img.getHeight(); k++ ) {
			for (int j = 0; j < img.getWidth(); j++) {
				
				int pixVal = img.getRGB(j, k);
				
				int pixR = (pixVal >> 16) & 255;
				int pixG = (pixVal >> 8) & 255;
				int pixB = pixVal & 255;
				
				if ((pixB > 150) && (pixG < 100) && (pixR < 100)) {
					for (int l = 0; l < (pixB * 2); l++) {
						j = j + l;
						if (j < img.getWidth()) {
							img.setRGB(j, k, 255);
						} else {
							break;
						}
					}
				}
			}
		}
	}
	
	/* If pixel is grey, turn it R G or B */
	public void deGrey(int threshold) {
		for (int j = 0; j < img.getWidth(); j++) {
			for (int k = 0; k < img.getHeight(); k++ ) {
				
				int pixVal = img.getRGB(j, k);
				
				int pixR = (pixVal >> 16) & 255;
				int pixG = (pixVal >> 8) & 255;
				int pixB = pixVal & 255;
				
				int difRG = Math.abs(pixR - pixG);
				int difRB = Math.abs(pixR - pixB);				
				
				if ((difRG < threshold) && (difRB < threshold)) {
					if (difRB < (threshold / 3)) {
						img.setRGB(j, k, 255 << 16); // Red
					} else if (difRB < (2* threshold / 3)) {
						img.setRGB(j, k, 255 << 8); // Green
					} else {
						img.setRGB(j, k, 255); // Blue
					}
				}
				
			}
		}
	}
	
	/* If RGB values are too different, pixel is turned grey */
	public void greyify(int threshold) {
		for (int j = 0; j < img.getWidth(); j++) {
			for (int k = 0; k < img.getHeight(); k++ ) {
				
				int pixVal = img.getRGB(j, k);
				
				int pixR = (pixVal >> 16) & 255;
				int pixG = (pixVal >> 8) & 255;
				int pixB = pixVal & 255;
				
				int difRG = Math.abs(pixR - pixG);
				int difRB = Math.abs(pixR - pixB);
				int difGB = Math.abs(pixG - pixB);
				
				if ((difRG + difRB + difGB) > threshold) {
					img.setRGB(j, k, pixB + (pixB << 8) + (pixB << 16));	
				}
				
			}
		}
	}

	/* Decrease green content of RGB values */
	public void deGreen(int threshold) {		
		for (int j = 0; j < img.getWidth(); j++) {
			for (int k = 0; k < img.getHeight(); k++ ) {
				int pixVal = img.getRGB(j, k);
				
				//int pixR = (pixVal >> 16) & 255;
				int pixG = (pixVal >> 8) & 255;
				//int pixB = pixVal & 255;
				
				if (pixG > threshold) {
					img.setRGB(j, k, pixVal & (16711935 + threshold)); //1111 1111 0000 0000 1111 1111
				}
			}
		}		
	}
	
	/* Decrease blue content of RGB values */
	public void deBlue(int threshold) {		
		for (int j = 0; j < img.getWidth(); j++) {
			for (int k = 0; k < img.getHeight(); k++ ) {
				int pixVal = img.getRGB(j, k);
				
				//int pixR = (pixVal >> 16) & 255;
				//int pixG = (pixVal >> 8) & 255;
				int pixB = pixVal & 255;
				
				if (pixB > threshold) {
					img.setRGB(j, k, pixVal & (16776960 + threshold)); //1111 1111 1111 1111 0100 0000
				}
			}
		}		
	}
	
	/* Decrease red content of RGB values */
	public void deRed(int threshold) {		
		for (int j = 0; j < img.getWidth(); j++) {
			for (int k = 0; k < img.getHeight(); k++ ) {
				int pixVal = img.getRGB(j, k);
				
				int pixR = (pixVal >> 16) & 255;
				//int pixG = (pixVal >> 8) & 255;
				//int pixB = pixVal & 255;
				
				if (pixR > threshold) {
					img.setRGB(j, k, pixVal & (65535 + threshold)); // 0100 0000 1111 1111 1111 1111
				}
			}
		}		
	}
	
	
	/* Increase green content of RGB values */
	public void inGreen(int threshold) {		
		for (int j = 0; j < img.getWidth(); j++) {
			for (int k = 0; k < img.getHeight(); k++ ) {
				int pixVal = img.getRGB(j, k);
				
				//int pixR = (pixVal >> 16) & 255;
				int pixG = (pixVal >> 8) & 255;
				//int pixB = pixVal & 255;
				
				if (pixG < threshold) {
					img.setRGB(j, k, pixVal | (threshold << 8)); // 0000 0000 ____ ____ 0000 0000
				}
			}
		}		
	}
	
	/* Increase blue content of RGB values */
	public void inBlue(int threshold) {		
		for (int j = 0; j < img.getWidth(); j++) {
			for (int k = 0; k < img.getHeight(); k++ ) {
				int pixVal = img.getRGB(j, k);
				
				//int pixR = (pixVal >> 16) & 255;
				//int pixG = (pixVal >> 8) & 255;
				int pixB = pixVal & 255;
				
				if (pixB < threshold) {
					img.setRGB(j, k, pixVal | threshold); // 0000 0000 0000 0000 1100 0000
				}
			}
		}		
	}
	
	/* Increase red content of RGB values */
	public void inRed(int threshold) {		
		for (int j = 0; j < img.getWidth(); j++) {
			for (int k = 0; k < img.getHeight(); k++ ) {
				int pixVal = img.getRGB(j, k);
				
				int pixR = (pixVal >> 16) & 255;
				//int pixG = (pixVal >> 8) & 255;
				//int pixB = pixVal & 255;
				
				if (pixR < threshold) {
					img.setRGB(j, k, pixVal & (threshold << 16)); // 1100 0000 0000 0000 0000 0000
				}
			}
		}		
	}


}
