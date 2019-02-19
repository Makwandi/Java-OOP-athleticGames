//Program created by Daniel Knöös (dakn3990), Nasimul Hasan (naha1113) and Reza Rohani Makvandi(rero6268).

import java.util.ArrayList;

public class AthleteList {
	
	private Athlete oneAthlete;
	private static ArrayList<Athlete> listOfAthletes = new ArrayList<>();
	private TeamList tList;
	private ResultList rList;
	
	public void addAthlete(String firstName, String lastName, String teamName)
	{
		oneAthlete = new Athlete(firstName, lastName, teamName);
		listOfAthletes.add(oneAthlete);
		System.out.println(firstName + " " + lastName + " of " + teamName + " " + "added" );
		
		int sNumber = 0;
		for (Athlete a : listOfAthletes)
			if (a.getAthleteFirstName().equals(firstName) && a.getAthleteLastName().equals(lastName) && a.getTeamName().equals(teamName)){
				sNumber = a.getAthleteID();
			}
		tList = new TeamList();
		tList.addTeamOrMember(sNumber, firstName, lastName, teamName);
		System.out.println();
	}
	
	
	public void handleRemovalStepTwo(int givenAthleteNumber)
	{	
		rList = new ResultList();
		rList.deleteResultsForAthlete(givenAthleteNumber);
		tList.removeMemberOfTeam(givenAthleteNumber);
		tList.removeTeam(givenAthleteNumber);     				
		removeAthlete(givenAthleteNumber);
	}
	
	
	public void removeAthlete(int athleteNo)
	{//The solution below keeps else-statement from being executed more than once. If used on the iterator above, it stops the latter from removing more than one result.
		FOUND: { 
			for (Athlete a : listOfAthletes){
				if (a.getAthleteID() == athleteNo){
					String fName = a.getAthleteFirstName();
					String lName = a.getAthleteLastName();
					String tName = a.getTeamName();
					listOfAthletes.remove(a);
					System.out.println();      //Added to make output look better with testfiles
					System.out.println(fName + " " + lName + " from " + tName + " with number " + athleteNo + " has been removed");
					System.out.println();
					break FOUND;
				}
			}
			System.out.println("No such starting number exists.");
			System.out.println();
		}
	}//End of removeAthlete() method
	
	
	public ArrayList<Athlete> getAthleteList()
	{
		return listOfAthletes;
	}
	
}//class
