import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

public class T04_Chunk {

	public static final String RESULT = "results/T04_Chunk.pdf";

	/**
	 * @param args
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static void main(String[] args) throws DocumentException, IOException {

		new T04_Chunk().createPdf(RESULT);
	}

	public void createPdf(String filename) throws DocumentException, IOException {

		Document document = new Document(PageSize.LETTER);
		PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(filename));

		// TODO: set leading
		pdfWriter.setInitialLeading(32); // space between two lines

		document.open();

		// step 4 - add content into document
		for (int i = 0; i < 5; i++) {

			Chunk chunk = new Chunk(Integer.toString(i), new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE));
			chunk.setTextRise(12);
			chunk.setBackground(BaseColor.RED);
			document.add(chunk);

			chunk = new Chunk("Hello", new Font(FontFamily.HELVETICA, 32, Font.BOLD));
			chunk.setHorizontalScaling(2);
			document.add(chunk);

			chunk = new Chunk("World", new Font(FontFamily.COURIER, 40, Font.ITALIC));
			chunk.setCharacterSpacing(12);
			document.add(chunk);

			chunk = new Chunk("!!!", new Font(FontFamily.TIMES_ROMAN, 40));
			chunk.setUnderline(1, 12);
			document.add(chunk);

			document.add(Chunk.NEWLINE);
		}

		document.close();
	}

}
