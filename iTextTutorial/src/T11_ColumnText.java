import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class T11_ColumnText {

	public static final String RESULT = "results/T11_ColumnText.pdf";

	/**
	 * @param args
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static void main(String[] args) throws DocumentException, IOException {

		new T11_ColumnText().createPdf(RESULT);
	}

	public void createPdf(String filename) throws DocumentException, IOException {

		Document document = new Document(PageSize.LETTER);
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
		document.open();

		// TODO: 1. get direct content
		PdfContentByte canvas = writer.getDirectContent();

		Font font = new Font(FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.ORANGE);
		Phrase headerText = new Phrase("PSEG DP&C", font);
		int alignLeft = Element.ALIGN_LEFT;
		float right = document.getPageSize().getRight();
		float top = document.getPageSize().getTop();

		// TODO: 2. use ColumnText
		ColumnText.showTextAligned(canvas, alignLeft, headerText, right - 180, top - 36, 0);

		document.close();
	}
}
