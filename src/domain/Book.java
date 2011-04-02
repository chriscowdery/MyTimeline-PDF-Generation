package domain;

import java.util.Vector;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

public class Book
{
	private Vector<Page> pages;
	private int currentPageNumber;
	private User owningUser;
	private String bookName;
	private File bookPDFLocation;
	
	public Book()
	{
		pages = new Vector<Page>();
		currentPageNumber = 0;
	}
	
	public void setBookName(String bookName)
	{
		this.bookName = bookName;
	}
	
	public String getBookName()
	{
		return bookName;
	}
	
	public void addPage(Page page)
	{
		pages.add(page);
	}
	
	private String getInternalBookName()
	{
		long now = System.currentTimeMillis();
		return getOwningUser().getUserId() + now + bookName;
	}
	
	public void renderBook()
	{
		// Initialize the PDF
		
		
		// Prep the page
		Document document = new Document(PageSize.LETTER);
		document.open();
		
		try
		{
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(getInternalBookName()));
			
			// Render all the pages to PDF
			for (Page page : pages)
			{
				page.renderPage();
			}
			
		}
		catch (DocumentException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public User getOwningUser()
	{
		return owningUser;
	}
	
	private void initializeBook()
	{
		 Document document = new Document(PageSize.LETTER);
		 document.addAuthor(owningUser.getFirstName() + owningUser.getLastName()); 
		 
	}
	
}
