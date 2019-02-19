//Program created by Daniel Knöös (dakn3990), Nasimul Hasan (naha1113) and Reza Rohani Makvandi(rero6268).

public class AthleticEvent {
	
	private String eventName, higherValueIsBetter;
	private int allowedAttempts;
	
	public AthleticEvent(String eName, int aAttempts, String hValue)
	{
		eventName = eName;
		allowedAttempts = aAttempts;
		higherValueIsBetter = hValue;
	}
	
	public String getEventName()
	{
		return eventName;
	}
	
	public int getAllowedAttempts()
	{
		return allowedAttempts;
	}
	
	public String getHigherValueIsBetter()
	{
		return higherValueIsBetter;
	}
	
	public String toString()
	{
		return eventName + " - " + allowedAttempts + " attempts allowed" + " - " + higherValueIsBetter;
	}
	
}
