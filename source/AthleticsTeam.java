//Program created by Daniel Knöös (dakn3990), Nasimul Hasan (naha1113) and Reza Rohani Makvandi(rero6268).

import java.util.ArrayList;

public class AthleticsTeam {
	
	private String nameOfTeam;
	private ArrayList<Athlete> membersOfTeam;
	
	public AthleticsTeam(String nTeam, ArrayList<Athlete> membersOfT)
	{
		nameOfTeam = nTeam;
		membersOfTeam = membersOfT;
	}
	
	public String getNameOfTeam()
	{
		return nameOfTeam;
	}
	
	public ArrayList<Athlete> getMembersOfTeam()
	{
		return membersOfTeam;
	}
	
	public String toString()
	{
		return nameOfTeam + "  " + membersOfTeam;
	}
	
	public void addTeamMember(Athlete tMember)
	{
		membersOfTeam.add(tMember);
	}
	
}
