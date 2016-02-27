import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



import java.awt.image.RenderedImage;

public class runArt {

	public static void main(String[] args) {
		
		String ipf = "/Users/ndrea/Pictures/Phone/2015-05-12/IMAG1336.jpg";
		String opd = "/Users/ndrea/Pictures/GlitchArtTest/";
		String ext = ".jpg";

		String opfn = "bsmearTest";
		Double randD = Math.random() * 98328;
		int randI = randD.intValue();
		String opf = String.format("%s%s%d%s", opd, opfn, randI, ext);		
		GlitchArt ga = new GlitchArt(ipf, opf);
		
		ga.smearBlue();
		
		writeArt(ga.img, ext, ga.outputFile);

	}
	
	
	public static void writeArt(RenderedImage img, String format, String opf) {
		try {
			ImageIO.write(img, "jpg", new File(opf));
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}

}
