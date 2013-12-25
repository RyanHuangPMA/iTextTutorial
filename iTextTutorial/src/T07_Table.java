import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class T07_Table {

	public static final String RESULT = "results/T07_Table.pdf";

	/**
	 * @param args
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static void main(String[] args) throws DocumentException, IOException {

		new T07_Table().createPdf(RESULT);
	}

	public void createPdf(String filename) throws DocumentException, IOException {

		Document document = new Document(PageSize.LETTER);
		PdfWriter.getInstance(document, new FileOutputStream(filename));
		document.open();

		// step 4 - add content into document
		PdfPTable table = new PdfPTable(3); // columns
		PdfPCell cell;

		cell = new PdfPCell(new Phrase("Cell with colspan 3"));
		cell.setColspan(3);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Cell with rowspan 2"));
		cell.setRowspan(2);
		table.addCell(cell);

		table.addCell("row 1; cell 1");
		table.addCell("row 1; cell 2");
		table.addCell("row 2; cell 1");
		table.addCell("row 2; cell 2");

		for (int i = 0; i < 10; i++) {
			table.addCell(String.format("row %s; cell 1", i + 3));
			table.addCell(String.format("row %s; cell 2", i + 3));
			table.addCell(String.format("row %s; cell 3", i + 3));
		}

		document.add(table);

		document.close();
	}
}
