//Program created by Daniel Knöös (dakn3990), Nasimul Hasan (naha1113) and Reza Rohani Makvandi(rero6268).

public class TeamPerformance implements Comparable<TeamPerformance> {
	
	private String teamName;
	private int firstPlaces, secondPlaces, thirdPlaces;
	
	
	public TeamPerformance(String tName, int fPlaces, int sPlaces, int tPlaces)
	{
		teamName = tName;
		firstPlaces = fPlaces;
		secondPlaces = sPlaces;
		thirdPlaces = tPlaces;
	}

	
	public void setTeamName(String tName)
	{
		teamName = tName;
	}
	
	
	public void setFirstPlaces(int fPlaces)
	{
		firstPlaces = fPlaces;
	}
	
	
	public void setSecondPlaces(int sPlaces)
	{
		secondPlaces = sPlaces;
	}
	
	
	public void setThirdPlaces(int tPlaces)
	{
		thirdPlaces = tPlaces;
	}
	
	
	public String getTeamName()
	{
		return teamName;
	}
	
	
	public int getFirstPlaces()
	{
		return firstPlaces;
	}
	
	
	public int getSecondPlaces()
	{
		return secondPlaces;
	}
	
	
	public int getThirdPlaces()
	{
		return thirdPlaces;
	}
	
	
	public String toString()
	{
		return teamName + " " + firstPlaces + " " + secondPlaces + " " + thirdPlaces;
	}
	
	
	public int compareTo(TeamPerformance otherTP)    //Gives primary value to gold medals, secondary value to silver medals, and tertiary to bronze, otherwise equal
	{
		if (firstPlaces < otherTP.firstPlaces){
			return -1;
		}
		else if (firstPlaces > otherTP.firstPlaces){
			return 1;
		}
		else if (secondPlaces < otherTP.secondPlaces){
			return -1;
		}
		else if (secondPlaces > otherTP.secondPlaces){
			return 1;
		}
		else if (thirdPlaces < otherTP.thirdPlaces){
			return -1;
		}
		else if (thirdPlaces > otherTP.thirdPlaces){
			return 1;
		}
		else {
			return 0;
		}
	}
	
}
