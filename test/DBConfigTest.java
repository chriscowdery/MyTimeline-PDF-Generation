import common.DatabaseConfig;


public class DBConfigTest
{
	public static void main (String ... args)
	{
		DatabaseConfig dbConfig = DatabaseConfig.getInstance();
		System.out.println("Hostname:" + dbConfig.getDatabaseHostname());
		System.out.println("User:" + dbConfig.getDatabaseUser());
		System.out.println("Pass:" + dbConfig.getDatabasePassword());
		System.out.println("Schema:" + dbConfig.getDatabaseSchema());
	}
}
