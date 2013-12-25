import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

public class T13_ImportStationery {

	public static final String RESULT = "results/T13_ImportStationery.pdf";

	/**
	 * @param args
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static void main(String[] args) throws DocumentException, IOException {

		T11_ColumnText.main(null);
		new T13_ImportStationery().createPdf(RESULT);
	}

	public void createPdf(String filename) throws DocumentException, IOException {

		Document document = new Document(PageSize.LETTER);
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));

		// TODO: 3. get imported page
		PdfReader stationery = new PdfReader(T11_ColumnText.RESULT);
		PdfImportedPage page = writer.getImportedPage(stationery, 1);
		writer.setPageEvent(new HeaderFooter(page));

		document.open();

		// step 4 - add content into document
		for (int i = 1; i <= 10; i++) {
			Chapter chapter = new Chapter(String.format("Chapter %s", i), i);
			Section section = chapter.addSection("Section");
			section.add(new Paragraph("TestTestTestTestTestTestTestTestTestTestTestTest"));

			for (int j = 1; j <= 3; j++) {
				Section subSection = section.addSection("Sub Section");
				subSection.add(new Paragraph("TestTestTestTestTestTestTestTestTestTestTestTest"));
			}

			document.add(chapter);
		}

		document.close();
	}

	// TODO: 1. implement page event
	class HeaderFooter extends PdfPageEventHelper {

		PdfImportedPage page;

		public HeaderFooter(PdfImportedPage page) {
			this.page = page;
		}

		@Override
		public void onEndPage(PdfWriter writer, Document document) {

			// TODO: 2. add page as template
			PdfContentByte canvas = writer.getDirectContent();
			canvas.addTemplate(page, 0, 0);

			Phrase footerText = new Phrase("Page " + document.getPageNumber());
			float footerXpos = (document.getPageSize().getLeft() + document.getPageSize().getRight()) / 2;
			float footerYpos = document.getPageSize().getBottom() + 36;

			ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, footerText, footerXpos, footerYpos, 0);
		}
	}

}
