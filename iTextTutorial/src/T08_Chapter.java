import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfWriter;

public class T08_Chapter {

	public static final String RESULT = "results/T08_Chapter.pdf";

	/**
	 * @param args
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static void main(String[] args) throws DocumentException, IOException {

		new T08_Chapter().createPdf(RESULT);
	}

	public void createPdf(String filename) throws DocumentException, IOException {

		Document document = new Document(PageSize.LETTER);
		PdfWriter.getInstance(document, new FileOutputStream(filename));
		document.open();

		// step 4 - add content into document
		for (int i = 1; i <= 10; i++) {
			Chapter chapter = new Chapter(String.format("Chapter %s", i), i);
			Section section = chapter.addSection("Section");
			section.setIndentation(18);
			section.add(new Paragraph("TestTestTestTestTestTestTestTestTestTestTestTest"));

			for (int j = 1; j <= 3; j++) {
				Section subSection = section.addSection("Sub Section");
				subSection.add(new Paragraph("TestTestTestTestTestTestTestTestTestTestTestTest"));
			}

			document.add(chapter);
		}

		document.close();
	}
}
