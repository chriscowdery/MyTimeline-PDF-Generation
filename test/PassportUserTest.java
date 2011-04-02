import domain.TimelinePassportUser;


public class PassportUserTest {

	public static void main (String ...strings)
	{
		TimelinePassportUser chris = new TimelinePassportUser("chris");
		
		System.out.println("FirstName:" + chris.getFirstName());
	}
	
}
