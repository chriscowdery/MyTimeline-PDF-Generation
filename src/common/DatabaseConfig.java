package common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DatabaseConfig
{
	private static DatabaseConfig instance;
	
	private String databaseHostname;
	private String databaseUser;
	private String databasePassword;
	private String databaseSchema;
	private String jdbcString;
	//private String jdbcProtocol;
	
	private DatabaseConfig()
	{}
	
	public static DatabaseConfig getInstance()
	{
		if (instance == null)
		{
			instance = new DatabaseConfig();
		}
		
		return instance;
	}
	
	public String getDatabaseHostname()
	{
		
		if (databaseHostname == null)
		{
			try
			{
				BufferedReader input =  new BufferedReader(new FileReader("conn_strings/databaseHostname"));
				return input.readLine();
			}
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		return databaseHostname;
	}
	
	public String getDatabaseUser()
	{
		if (databaseUser == null)
		{
			try
			{
				BufferedReader input =  new BufferedReader(new FileReader("conn_strings/timelinedbuser"));
				return input.readLine();
			}
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		return databaseUser;
	}
	
	public String getDatabasePassword()
	{
		if (databasePassword == null)
		{
			try
			{
				BufferedReader input =  new BufferedReader(new FileReader("conn_strings/timelinedbpass"));
				return input.readLine();
			}
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		return databasePassword;
	}
	
	public String getDatabaseSchema()
	{
		if (databaseSchema == null)
		{
			try
			{
				BufferedReader input =  new BufferedReader(new FileReader("conn_strings/schema"));
				return input.readLine();
			}
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		return databaseSchema;
	}
	
	public String getDriver()
	{
		return "com.mysql.jdbc.Driver";
	}
	
}
