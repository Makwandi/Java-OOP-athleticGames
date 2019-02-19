//Program created by Daniel Knöös (dakn3990), Nasimul Hasan (naha1113) and Reza Rohani Makvandi(rero6268).

import java.util.ArrayList;
import java.util.Iterator;

public class TeamList {
	
	private Athlete oneMember;
	private ArrayList<Athlete> teamMembers = new ArrayList<>();
	private static ArrayList<AthleticsTeam> listOfTeams = new ArrayList<>();
	private AthleteList aList = new AthleteList();
	
	
	public void addTeamOrMember(int athleteNumber, String firstName, String lastName, String teamName)
	{		
		oneMember = new Athlete(athleteNumber, firstName, lastName);
		teamMembers.add(oneMember);
		boolean foundTeam = false;
		for (AthleticsTeam aTeam : listOfTeams){
			if (aTeam.getNameOfTeam().equals(teamName)){
				aTeam.addTeamMember(oneMember);
				foundTeam = true;
				break;
			}
		}
		if (!foundTeam){
			AthleticsTeam oneTeam = new AthleticsTeam(teamName, teamMembers);
			listOfTeams.add(oneTeam);
		}
	}

	
	public void removeMemberOfTeam(int athleteNo)
	{
		for (AthleticsTeam anyTeam : listOfTeams){
			ArrayList<Athlete> memberList = anyTeam.getMembersOfTeam();
			Iterator<Athlete> memberIterator = memberList.iterator(); //A different iteration method here may throw a ConcurrentModificationException.
			while (memberIterator.hasNext()){
				if (memberIterator.next().getAthleteID() == athleteNo){
					memberIterator.remove();
				}
			}
		}   
		//If the last member of the team has been removed...
		//removeTeam(athleteNo);  
	}//End of removeMemberOfTeam() method
	
	
	public void removeTeam(int givenAthleteNumber)
	{
		int location = 0;
		for (Athlete anyAthlete : aList.getAthleteList()){
			if (anyAthlete.getAthleteID() == givenAthleteNumber){
				String teamForAthlete = anyAthlete.getTeamName();
				for (AthleticsTeam xTeam : listOfTeams){
					if (xTeam.getNameOfTeam().equals(teamForAthlete)){
						ArrayList<Athlete> listOfMembers = xTeam.getMembersOfTeam();
						int numberOfMembers = listOfMembers.size();
						int minimumMembers = 1;
						if (numberOfMembers < minimumMembers){
							location = listOfTeams.indexOf(xTeam);	
						}
						else {
							return;
						}
					}
				}
			}	
		}
		listOfTeams.remove(location);
	}
	
	
	public ArrayList<AthleticsTeam> getTeamList()
	{
		return listOfTeams;
	}

}
