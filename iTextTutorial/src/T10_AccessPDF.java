import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;

public class T10_AccessPDF {

	/**
	 * @param args
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static void main(String[] args) throws DocumentException, IOException {

		T08_Chapter.main(null);

		// TODO: use PdfReader
		PdfReader reader = new PdfReader(T08_Chapter.RESULT);

		int numberOfPages = reader.getNumberOfPages();

		System.out.println("reader.getNumberOfPages() = " + numberOfPages);

		for (int i = 1; i <= numberOfPages; i++) {
			System.out.println(i + " : " + reader.getPageSize(i));
		}

	}

}
