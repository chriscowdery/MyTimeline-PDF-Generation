package domain;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;

import common.ConnectionPool;

public class TimelinePassportUser implements User
{
	protected UserType userType;
	protected String userId;
	protected String firstName;
	protected String lastName;
	protected String email;
	
	public UserType getUserType()
	{
		return userType;
	}
	
	public String getUserId()
	{
		return userId;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public TimelinePassportUser(String user)
	{
		this.userType = UserType.TIMELINE_PASSPORT;
		
		Connection connection = ConnectionPool.getInstance().getConnection();
		
		try {
			Statement stmt = connection.createStatement();
			
			//TODO: Prevent against SQL injection here
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM tis_auth WHERE user = " + user);
			
			while (rs.next())
			{
				this.firstName = rs.getString("firstName");
				this.lastName = rs.getString("lastName");
				this.email = rs.getString("email");
				
				// Should only have one result
				break;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ConnectionPool.getInstance().closeConnection(connection);
		
	}	
}
