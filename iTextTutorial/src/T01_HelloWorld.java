import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class T01_HelloWorld {

	public static final String RESULT = "results/T01_HelloWorld.pdf";

	/**
	 * @param args
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static void main(String[] args) throws DocumentException, IOException {

		new T01_HelloWorld().createPdf(RESULT);
	}

	public void createPdf(String filename) throws DocumentException, IOException {

		// step 1 - create PDF document
		Document document = new Document(PageSize.LETTER);

		// step 2 - bind PDF document with output stream
		PdfWriter.getInstance(document, new FileOutputStream(filename));

		// step 3 - open document
		document.open();

		// step 4 - add content into document
		document.add(new Paragraph("Hello World!"));

		// step 5 - close document
		document.close();
	}

}
