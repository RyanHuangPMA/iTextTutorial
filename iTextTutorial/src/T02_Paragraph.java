import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

public class T02_Paragraph {

	public static final String RESULT = "results/T02_Paragraph.pdf";

	/**
	 * @param args
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static void main(String[] args) throws DocumentException, IOException {

		new T02_Paragraph().createPdf(RESULT);
	}

	public void createPdf(String filename) throws DocumentException, IOException {

		Document document = new Document(PageSize.LETTER);
		PdfWriter.getInstance(document, new FileOutputStream(filename));
		document.open();

		// step 4 - add content into document
		for (int i = 0; i < 5; i++) {
			document.add(new Paragraph("Hello World !", new Font(FontFamily.HELVETICA, 32, Font.BOLD)));
		}

		document.close();
	}
}
