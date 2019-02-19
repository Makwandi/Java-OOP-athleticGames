//Program created by Daniel Knöös (dakn3990), Nasimul Hasan (naha1113) and Reza Rohani Makvandi(rero6268).

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;


public class ResultList {

	private static ArrayList<Result> listOfResults = new ArrayList<>();
	private AthleteList aList = new AthleteList();
	private ArrayList<PlacementForAthlete> placementInEvent = new ArrayList<>();
	private EventList eList = new EventList();
	private ArrayList<TeamPerformance> placementForTeams = new ArrayList<>();
	private TeamList tList = new TeamList();
	private String[] medals = {"GOLD", "SILVER", "BRONZE"};    	//The stipulated array in the program is used to hold top placement designations
		
		
	public void addResult(int givenAthleteNumber, String givenEvent, double givenResult)
	{
		if (givenResult < 0 ){
			System.out.println("Error. The result cannot be a negative value.");
			System.out.println();
			return;	
		}
		else {
			listOfResults.add(new Result(givenAthleteNumber, givenEvent, givenResult));
			System.out.println();
		}
	}
	
	
	public void deleteResultsForAthlete(int givenAthleteNumber)
	{//If preceding the FOUND label in AthleteList, the iterator below avoids redundant printouts of else-statements
		Iterator<Result> resultIterator = listOfResults.iterator();  
		while (resultIterator.hasNext()){
			if (resultIterator.next().getStartingNumber() == givenAthleteNumber){
				resultIterator.remove();
			}
		}
	}
	
	
	public void showResultsForAthlete(int givenAthleteNumber)
	{
		boolean foundNumber = false;
		String firstName = null;
		String lastName = null;
		for (Athlete a : aList.getAthleteList()){
			if (a.getAthleteID() == givenAthleteNumber){
				firstName = a.getAthleteFirstName();
				lastName = a.getAthleteLastName();
				foundNumber = true;
			}
		}
		ArrayList<String> eventsForParticipant = new ArrayList<>();	//To list all events for which participant has at least one result without duplicates
		for (Result r1 : listOfResults){
			if (r1.getStartingNumber() == givenAthleteNumber){
				if (!eventsForParticipant.contains(r1.getNameOfEvent())){
					eventsForParticipant.add(r1.getNameOfEvent());
					foundNumber = true;
				}
			}
		}
		String event = null;
		ArrayList<Double> performancesOfParticipant = new ArrayList<>();
		for (int e = 0; e < eventsForParticipant.size(); e++){
			event = eventsForParticipant.get(e);
			for (Result r2 : listOfResults){
				if (r2.getStartingNumber() == givenAthleteNumber && r2.getNameOfEvent().equals(event)){
					performancesOfParticipant.add(r2.getResult());
					foundNumber = true;
				}		
			}
			System.out.println();  		//Added to make output look better with testfiles
			int numberOfPerformances = performancesOfParticipant.size();											
		    System.out.print("Results for " + firstName + " " + lastName + " in " + event +  ": ");
		    for (int p = 0; p < performancesOfParticipant.size(); p++){
		    	if ((performancesOfParticipant.indexOf(performancesOfParticipant.get(p)) + 1) == numberOfPerformances){
		    		System.out.print(performancesOfParticipant.get(p) + "");   		//Turning double into a String
		    	}
		        else {
		        	System.out.print((performancesOfParticipant.get(p) + "") + ", ");
		        }
		    }
		    performancesOfParticipant.clear();
		}
		if (!foundNumber){
			System.out.println("There is no participant with starting number " + givenAthleteNumber);	
		}
		System.out.println();
	}//End of showResultsForAthlete() method
	
	
	public void showResultsForEvent(String nameOfEvent)
	{	
		ArrayList<Result> resultsForEvent = new ArrayList<>();
		resultsForEvent = findResultsForEvent(nameOfEvent);
		
		ArrayList<PlacementForAthlete> placementInEvent = new ArrayList<>();
		placementInEvent = setPlacementInEvent(nameOfEvent, resultsForEvent);
		
		printPlacementInEvent(nameOfEvent, placementInEvent);
	}
		
	
	public ArrayList<Result> findResultsForEvent(String event)
	{
		String orderOfResults = null;
		for (AthleticEvent e : eList.getEventList()){
			if (e.getEventName().equals(event)){
				orderOfResults = e.getHigherValueIsBetter();
			}
		}
		ArrayList<Result> chosenResultList = new ArrayList<>();
		for (Result c : listOfResults){
			if (c.getNameOfEvent().equals(event)){
				chosenResultList.add(c);
			}
		}
		//To list all athletes in question as starting numbers without duplicates
		ArrayList<Integer> athletesWithResult = new ArrayList<>();
		for (Result a : chosenResultList){
			if (!athletesWithResult.contains(a.getStartingNumber())){
				athletesWithResult.add(a.getStartingNumber());
			}
		}
		//To collect all results for given athlete in temporary list
		ArrayList<Result> resultsForAthlete = new ArrayList<>();
		//To collect the best performances of all athletes
		ArrayList<Result> bestResultForEachAthlete = new ArrayList<>();
		for (Integer x : athletesWithResult){
			for (int r = 0; r < chosenResultList.size(); r++){
				if (chosenResultList.get(r).getStartingNumber() == x){
					resultsForAthlete.add(chosenResultList.get(r));
				}
			}
			if (orderOfResults.equalsIgnoreCase("yes") || orderOfResults.equalsIgnoreCase("Y")){
				Result bestPerformance = maxResultForAthlete(resultsForAthlete);
					bestResultForEachAthlete.add(bestPerformance);
			}
			else {
				Result bestPerformance = minResultForAthlete(resultsForAthlete);
				bestResultForEachAthlete.add(bestPerformance);
			}
			resultsForAthlete.clear();
		}
		MaxComparator maxC = new MaxComparator();
		MinComparator minC = new MinComparator();
		
		if (orderOfResults.equalsIgnoreCase("yes") || orderOfResults.equalsIgnoreCase("Y")){
			Collections.sort(bestResultForEachAthlete, maxC);
		}
		else {
			Collections.sort(bestResultForEachAthlete, minC);
		}
		return bestResultForEachAthlete;
	}//End of findResultsForEvent() method
	
	
	public Result maxResultForAthlete(ArrayList<Result> performances)
	{
		Result max = null;
		for (Result r : performances){
			if (max == null || r.getResult() > max.getResult()){
				max = r;
			}
		}
		return max;
	}
	
	
	public Result minResultForAthlete(ArrayList<Result> performances)
	{
		Result min = null;
		double value = 10000.0;
		for (Result r : performances){
			if (r.getResult() < value){
				value = r.getResult();
			}
			if (value == r.getResult()){
				min = r;
			}
		}
		return min;
	}

	
	public ArrayList<PlacementForAthlete> setPlacementInEvent(String event, ArrayList<Result> results)
	{
		int placement = 0;
		for (int p = 0; p < results.size(); p++){
			if (results.indexOf(results.get(p)) == 0){
				placement = results.indexOf(results.get(p)) + 1;  	//Determines the placement of the first result
			}
			//All results after the first must be compared with all previous results to get its placement determined
			else {
				int index = results.indexOf(results.get(p));	  	//The given performance's place on the list
				double performance = results.get(p).getResult();  	//Result to be compared with previous ones
				placement = setPlacementForPerformance(results, index, performance);
			}
			int sNumber = results.get(p).getStartingNumber();
			double aPerformance = results.get(p).getResult();  		//Two variables for performance seems necessary if the winner is to get his or her result displayed in the placement list
			String fName = null;
			String lName = null;
			String tName = null;
			for (Athlete a : aList.getAthleteList()){
				if (a.getAthleteID() == sNumber){
					fName = a.getAthleteFirstName();
					lName = a.getAthleteLastName();
					tName = a.getTeamName();
				}
			}
			PlacementForAthlete pFC = new PlacementForAthlete(placement, aPerformance, fName, lName, tName);
			placementInEvent.add(pFC);
		}
		ArrayList<PlacementForAthlete> placementInEvent2 = new ArrayList<>();  //Necessary to save placement data when first list is cleared
		for (PlacementForAthlete pFC2 : placementInEvent){
			placementInEvent2.add(pFC2);
		}
		placementInEvent.clear();     //To avoid getting the results from more than one event appearing on the same list
		return placementInEvent2;
	}
	
	
	public int setPlacementForPerformance(ArrayList<Result> givenResults, int givenIndex, double givenPerformance)
	{
		//To see if there is another athlete who has performed the same result
		ArrayList<Result> identicalResults = new ArrayList<>();
		//The results with which the given athlete's performance shall be compared with must come before the place on the list which the athlete has
		for (int g = 0; g < givenResults.size() + (givenIndex - givenResults.size()); g++){
			if (givenResults.get(g).getResult() == givenPerformance){
				identicalResults.add(givenResults.get(g));
			}
		}
		int numberOfIndenticalResults = 0;
		int determinedPlacement = 0;
		if (identicalResults.isEmpty()){
			numberOfIndenticalResults = 0;
		}
		else {
			numberOfIndenticalResults = identicalResults.size();
		}
		determinedPlacement = (givenIndex + 1) - numberOfIndenticalResults;
		return determinedPlacement;
	}
	
	
	public void printPlacementInEvent(String givenEvent, ArrayList<PlacementForAthlete> givenPlacements)
	{
		System.out.println();
		System.out.print("Results for " + givenEvent + ":\n");
		for (PlacementForAthlete pFC : givenPlacements){
			System.out.println(pFC);
		}
		System.out.println();
	}
	
	
	public void showResultsForTeams()
	{
		ArrayList<String> eventsWithResults = new ArrayList<>();  //To list all events with results without duplicates
		for (Result r : listOfResults){
			if (!eventsWithResults.contains(r.getNameOfEvent())){
				eventsWithResults.add(r.getNameOfEvent());
			}
		}
		//Going through the list of teams to create a performance object for each team that is first initiated to zero medals of each kind
		for (AthleticsTeam aT : tList.getTeamList()){
			String aTeam = aT.getNameOfTeam();
			TeamPerformance tPerformance = new TeamPerformance(aTeam, 0, 0, 0);
			placementForTeams.add(tPerformance);
		}
		//Finding what medals each team has won in the events and changing the values in their count of medals accordingly
		ArrayList<Result> resultsForEvent = new ArrayList<>();
		ArrayList<PlacementForAthlete> placementInEvent = new ArrayList<>();
		for (String e : eventsWithResults){
			resultsForEvent = findResultsForEvent(e);
			placementInEvent = setPlacementInEvent(e, resultsForEvent);
			for (PlacementForAthlete p : placementInEvent){
				String teamWithResult = p.getTeamName();
				for (TeamPerformance tP : placementForTeams){
					if (tP.getTeamName().equals(teamWithResult)){
						if (p.getPlace() == 1){
							int places = tP.getFirstPlaces();
							tP.setFirstPlaces(places + 1);
						}
						else if (p.getPlace() == 2){
							int places = tP.getSecondPlaces();
							tP.setSecondPlaces(places + 1);
						}
						else if (p.getPlace() == 3){
							int places = tP.getThirdPlaces();
							tP.setThirdPlaces(places + 1);
						}
					}
				}
			}
		}
		//Sorting the harvest of medals for the teams by use of the lambda expression
		placementForTeams.sort((t1, t2) -> t2.compareTo(t1));  	 //The code alternative t1.compareTo(t2) would have produced an ascending order
		System.out.println();
		
		for (int x = 0; x < medals.length; x++){  				 //Prints the headings for medals
			System.out.print(medals[x] + " ");
		}
		System.out.println("TEAM");  			 				 //Adds a heading in the row after the headings for medals
		for (int i = 0; i < 25; i++){	 	    			     //Adds a line of stars below the headings
			System.out.print("*");
		}
		System.out.println();
		for (TeamPerformance teamP : placementForTeams){  		 //Prints the sorted harvest of medals for teams
			int gold = teamP.getFirstPlaces();
			int silver = teamP.getSecondPlaces();
			int bronze = teamP.getThirdPlaces();
			String teamName = teamP.getTeamName();
			System.out.println(" " + gold + "     " + silver + "     " + bronze + "    " + teamName);
		}
		placementForTeams.clear();   					//In case another team placement list is requested after some participant has been removed
		System.out.println();
	}//End of showResultsForTeams() method
	
	
	public ArrayList<Result> getResultList()
	{
		return listOfResults;
	}
	
}
