package domain;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

public class Page
{
	private Book owningBook;
	private int pageNumber;
	private String pageName;
	private Template template;
	
	
	public Page(Book owningBook, int pageNumber, String pageName, Template template)
	{
		this.owningBook = owningBook;
		this.pageNumber = pageNumber;
		this.pageName = pageName;
		this.template = template;
	}
	
	public Book getOwningBook()
	{
		return owningBook;
	}
	
	public int getPageNumber()
	{
		return pageNumber;
	}
	
	public String getPageName()
	{
		return pageName;
	}
	
	public void setTemplate(Template template)
	{
		this.template = template;
	}
	
	public Template getTemplate()
	{
		return template;
	}
	
	private String getInternalPageName()
	{
		long now = System.currentTimeMillis();
		return owningBook.getOwningUser().getUserId() + now + pageNumber;
	}
	
	/**
	 * 
	 * @throws DocumentException 
	 * @throws IOException 
	 * @pattern Strategy
	 */
	public void renderPage() throws DocumentException, IOException
	{
		// Get the base template
		PdfReader reader = new PdfReader(template.getTemplateFile().getAbsolutePath());
		
		// Prep the page
		Document document = new Document(PageSize.LETTER);
		document.open();
		
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(getInternalPageName()));
		
		PdfContentByte canvas = writer.getDirectContent();
		
		// Templates are only one page, so the first page is all we need
		PdfImportedPage page = writer.getImportedPage(reader, 1);
		
		canvas.addTemplate(page, 1f, 0, 0, 1, 0, 0);

		document.close();
		
	}
	
}
