//Program created by Daniel Knöös (dakn3990), Nasimul Hasan (naha1113) and Reza Rohani Makvandi(rero6268).

public class Athlete{
	
	private int athleteID;
	private static int startingNumber = 99;
	private String athleteFirstName, athleteLastName, teamName;
	
	public Athlete(String firstName, String lastName, String tName)
	{
		athleteID = ++startingNumber;
		athleteFirstName = firstName;
		athleteLastName = lastName;
		teamName = tName;	
	}
	
	//A second constructor specially devised for team member to be added to a given team, where team name is collected by other method
	public Athlete(int aNumber, String firstName, String lastName){  
		athleteID = aNumber;
		athleteFirstName = firstName;
		athleteLastName = lastName;
	}
	
	public static void setStartingNumber(int sNumber)
	{
		startingNumber = sNumber;
	}
	
	public int getAthleteID()
	{
		return athleteID;
	}
	
	public String getAthleteFirstName()
	{
		return athleteFirstName;
	}
	
	public String getAthleteLastName()
	{
		return athleteLastName;
	}
	
	public String getTeamName()
	{
		return teamName;
	}
	
	public String toString()
	{
		if (teamName == null){  //toString() alternative for team member, who does not need a team name for parameter
			return "participant " + athleteID  + "  " + athleteFirstName + " " + athleteLastName;
		}
		else {
			return "participant " + athleteID  + "  " + athleteFirstName + " " + athleteLastName + "  " + teamName;
		}
	}
}
