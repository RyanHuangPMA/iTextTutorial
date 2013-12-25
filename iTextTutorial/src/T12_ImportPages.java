import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

public class T12_ImportPages {

	public static final String RESULT = "results/T12_ImportPages.pdf";

	/**
	 * @param args
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static void main(String[] args) throws DocumentException, IOException {

		T08_Chapter.main(null);
		T11_ColumnText.main(null);
		new T12_ImportPages().createPdf(RESULT);
	}

	public void createPdf(String filename) throws DocumentException, IOException {

		Document document = new Document(PageSize.LETTER);
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
		document.open();

		PdfPTable table = new PdfPTable(2);
		PdfReader reader = new PdfReader(T08_Chapter.RESULT);
		int n = reader.getNumberOfPages();
		for (int pageNumber = 1; pageNumber <= n; pageNumber++) {

			// TODO: import page as image
			PdfImportedPage page = writer.getImportedPage(reader, pageNumber);
			table.addCell(Image.getInstance(page));
		}
		document.add(table);

		document.close();
	}

}
