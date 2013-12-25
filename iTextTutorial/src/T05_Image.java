import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class T05_Image {

	public static final String RESULT = "results/T05_Image.pdf";

	/**
	 * @param args
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static void main(String[] args) throws DocumentException, IOException {

		new T05_Image().createPdf(RESULT);
	}

	public void createPdf(String filename) throws DocumentException, IOException {

		Document document = new Document(PageSize.LETTER);
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));

		// TODO: force iText to respect the order in which content is added
		writer.setStrictImageSequence(true);

		document.open();

		// step 4 - add content into document
		String[] imageNames = { "35_Cal_Crutchlow.jpg", "38_Bradley_Smith.jpg", "46_Valentino_Rossi.jpg",
				"99_Jorge_Lorenzo.jpg" };
		for (int i = 0; i < 4; i++) {

			Image image = Image.getInstance("resources/img/" + imageNames[i]);

			// TODO: scale image
			image.scaleToFit(500, 500); // scale size

			document.add(image);
			document.add(new Paragraph(imageNames[i]));
		}

		document.close();
	}
}
