import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageLabels;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.SimpleBookmark;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

public class T14_TOC {

	public static final String RESULT = "results/T14_TOC.pdf";

	/**
	 * @param args
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static void main(String[] args) throws DocumentException, IOException {

		T13_ImportStationery.main(null);
		new T14_TOC().createPdf(RESULT);
	}

	public void createPdf(String filename) throws DocumentException, IOException {

		// TODO: 1. retrieve outline to ColumnText
		PdfReader reader = new PdfReader(T13_ImportStationery.RESULT);
		ColumnText ct = new ColumnText(null);
		ct.addElement(new Paragraph("Table of Contents"));
		List<HashMap<String, Object>> bookmarkList = SimpleBookmark.getBookmark(reader);
		fillColumnText(bookmarkList, ct, 18, 0);

		// TODO: 2. generate TOC
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfStamper stamper = new PdfStamper(reader, baos);
		int tocPageEnd = 0;
		while (true) {
			stamper.insertPage(++tocPageEnd, reader.getPageSize(1));

			ct.setCanvas(stamper.getOverContent(tocPageEnd));
			ct.setSimpleColumn(36, 36, 558, 693);
			if (!ColumnText.hasMoreText(ct.go()))
				break;
		}
		stamper.close();

		// FileOutputStream file = new FileOutputStream(filename);
		// baos.writeTo(file);
		// file.close();

		// TODO: 3. change page labels
		PdfPageLabels labels = new PdfPageLabels();
		labels.addPageLabel(1, PdfPageLabels.LOWERCASE_ROMAN_NUMERALS);
		labels.addPageLabel(tocPageEnd + 1, PdfPageLabels.DECIMAL_ARABIC_NUMERALS);

		// TODO: 4. generate final PDF
		FileOutputStream file = new FileOutputStream(filename);
		reader = new PdfReader(baos.toByteArray());
		stamper = new PdfStamper(reader, file);
		stamper.getWriter().setPageLabels(labels);
		stamper.close();
	}

	@SuppressWarnings("unchecked")
	protected void fillColumnText(List<HashMap<String, Object>> bookmarkList, ColumnText ct, float leftIndent, int depth) {
		if (null != bookmarkList) {
			for (int i = 0; i < bookmarkList.size(); i++) {
				HashMap<String, Object> bookmark = bookmarkList.get(i);

				String title = (String) bookmark.get("Title");
				String pageNum = ((String) bookmark.get("Page")).split(" ")[0];
				Paragraph paragraph = new Paragraph(title);
				paragraph.add(new Chunk(new DottedLineSeparator()));
				paragraph.add(Integer.toString((Integer.parseInt(pageNum))));
				paragraph.setIndentationLeft(leftIndent * depth);
				paragraph.setSpacingBefore(0 == depth ? 9 : 0);
				ct.addElement(paragraph);

				fillColumnText(((List<HashMap<String, Object>>) bookmark.get("Kids")), ct, leftIndent, depth + 1);
			}
		}
	}

}
