import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;

public class T03_Phrase {

	public static final String RESULT = "results/T03_Phrase.pdf";

	/**
	 * @param args
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static void main(String[] args) throws DocumentException, IOException {

		new T03_Phrase().createPdf(RESULT);
	}

	public void createPdf(String filename) throws DocumentException, IOException {

		Document document = new Document(PageSize.LETTER);
		PdfWriter.getInstance(document, new FileOutputStream(filename));
		document.open();

		// step 4 - add content into document
		for (int i = 0; i < 5; i++) {
			document.add(new Phrase("Hello", new Font(FontFamily.HELVETICA, 32, Font.BOLD)));
			document.add(new Phrase("World", new Font(FontFamily.COURIER, 40, Font.ITALIC)));
			document.add(new Phrase("!!!", new Font(FontFamily.TIMES_ROMAN, 40)));

			document.add(Chunk.NEWLINE);
		}

		document.close();
	}
}
