//Program created by Daniel Knöös (dakn3990), Nasimul Hasan (naha1113) and Reza Rohani Makvandi(rero6268).

public class PlacementForAthlete {
	
	private int place;
	private String firstName, lastName, teamName;
	private double result;
	
	public PlacementForAthlete(int p, double r, String fName, String lName, String tName)
	{
		place = p;
		result = r;
		firstName = fName;
		lastName = lName;
		teamName = tName;
	}
	
	public int getPlace()
	{
		return place;
	}
	
	public double getResult()
	{
		return result;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public String getTeamName()
	{
		return teamName;
	}
	
	public String toString()
	{
		return place + "  " + result + "  " + firstName + " " + lastName + "  " + teamName;
	}
	
}
