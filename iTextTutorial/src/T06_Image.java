import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;

public class T06_Image {

	public static final String RESULT = "results/T06_Image.pdf";

	/**
	 * @param args
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static void main(String[] args) throws DocumentException, IOException {

		new T06_Image().createPdf(RESULT);
	}

	public void createPdf(String filename) throws DocumentException, IOException {

		Document document = new Document(PageSize.LETTER);
		PdfWriter.getInstance(document, new FileOutputStream(filename));
		document.open();

		// step 4 - add content into document
		String[] imageNames = { "35_Cal_Crutchlow.jpg", "38_Bradley_Smith.jpg", "46_Valentino_Rossi.jpg",
				"99_Jorge_Lorenzo.jpg" };
		for (int i = 0; i < 4; i++) {

			// TODO: 1. Add image into Chunk
			Image image = Image.getInstance("resources/img/" + imageNames[i]);
			image.scaleToFit(500, 500);
			Chunk imageChunk = new Chunk(image, 0, 0, true);

			Phrase phrase = new Phrase(imageNames[i]);

			Paragraph paragraph = new Paragraph();
			paragraph.add(imageChunk);
			paragraph.add(Chunk.NEWLINE);
			paragraph.add(phrase);

			// TODO: 2. Ask iText to keep a paragraph together
			paragraph.setKeepTogether(true);
			paragraph.setSpacingAfter(12);

			document.add(paragraph);
		}

		document.close();
	}
}
