package common;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.util.ArrayList;

//import org.apache.log4j.Logger;

import common.DatabaseConfig;

public class ConnectionPool
{
	private static ConnectionPool instance = new ConnectionPool();
	
	public static ConnectionPool getInstance()
	{
		return instance;
	}
	
	/**
	 * Enforce Singleton
	 */
	private ConnectionPool()
	{
	}
	
	//private ArrayList<Connection> connnections;
	//static Logger logger = Logger.getLogger(ConnectionPool.class.getName());
	
	/**
	 * 
	 * @return
	 */
    public Connection getConnection()
    {
    	
    	DatabaseConfig config = DatabaseConfig.getInstance();
    	Connection connection = null;
    	
    	try
        {
            String userName = config.getDatabaseUser();
            String password = config.getDatabasePassword();
            String url = "jdbc:mysql://" + config.getDatabaseHostname() + "/" + config.getDatabaseSchema();
            
            System.out.println("Information: Connecting to database with user: " + userName + 
        			" and url " + url);
            
            Class.forName (config.getDriver()).newInstance ();
            connection = DriverManager.getConnection (url, userName, password);
            System.out.println("Information: Connection to database successful. Created instance: " + connection.toString());
        }
        catch (Exception e)
        {
            System.err.println("Error: Unable to connect to " + config.getDatabaseHostname());
        }
        
        return connection;
    }
    
    /**
     * 
     * @param config
     * @return
     * @deprecated
     */
    public Connection initializeConnection(DatabaseConfig config)
    {
    	Connection tempConnection = null;
    	
    	try
        {
            String userName = config.getDatabaseUser();
            String password = config.getDatabasePassword();
            String url = "jdbc:mysql://" + config.getDatabaseHostname() + "/" + config.getDatabaseSchema();
            
            System.out.println("Information: Connecting to database with user: " + userName + 
        			" and url " + url);
            
            Class.forName (config.getDriver()).newInstance ();
            tempConnection = DriverManager.getConnection (url, userName, password);
            System.out.println("Information: Connection to database successful.");
        }
        catch (Exception e)
        {
        	System.err.println("Error: Unable to connect to " + config.getDatabaseHostname());
        }
        
        return tempConnection;
    }
    
    public void closeConnection(Connection toClose)
    {
        if (toClose != null)
        {
            try
            {
            	toClose.close();
                System.out.println("Information: Closing DB Connection with ID " + toClose.toString());
            }
            catch (Exception e) {
            	
            	System.err.println("Problems were enountered when closing the connection. See stack trace below:");
            	e.printStackTrace();
            }
        }
    }
	
}
