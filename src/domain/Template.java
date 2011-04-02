package domain;

import java.io.File;

public class Template
{
	public Season season;
	public File pdfTemplate;
	
	public Template()
	{
		
	}
	
	public void setSeason(Season season, File pdfTemplate)
	{
		this.season = season;
		this.pdfTemplate = pdfTemplate;
	}
	
	public File getTemplateFile()
	{
		return pdfTemplate;
	}
}
