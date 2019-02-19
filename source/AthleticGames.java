//Program created by Daniel Knöös (dakn3990), Nasimul Hasan (naha1113) and Reza Rohani Makvandi(rero6268).

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Locale;

public class AthleticGames {

	private EventList eList = new EventList();
	private Scanner scan = new Scanner(System.in).useLocale(Locale.US);
	private String adjustedName, normalizedName;
	private char firstChar;
	private AthleteList aList = new AthleteList();
	private TeamList tList = new TeamList();
	private ResultList rList = new ResultList();
	
	
	public static void main(String[] args)
	{		
		AthleticGames gamesProgram = new AthleticGames();
		gamesProgram.run();	
	}
	
	
	public void run()
	{	
		System.out.println("Welcome to the Athletic Games!");
		System.out.println();
		gamesMenu();
		
		while(true)
		{
			String command = readCommand();
			handleCommand(command);
		}		
	} 
	
	
	public void gamesMenu()
	{
		System.out.print("Please give a command for what you want do to...\n" +
				"To see this menu for the games program later, write: menu\n" +
				"To register an event, write: add event\n" +
				"To register a participant, write: add participant\n" +
				"To remove a participant, write: remove participant\n" +
				"To register a result, write: add result\n" +
				"To see the results of a given participant, write: participant\n" +
				"To see the results of a given event, write the name of the event\n" +
				"To see the results of the teams, write: teams\n" +
				"To delete all data for participants and teams, write: reinitialize\n" +
				"To get a message presented with emphasis, write: message [blank space] [message phrase]\n" +
				"To quit the program, write: exit\n");
		System.out.println();
	}
	
	
	public String readCommand()
	{	
		return readText("Command: ").toLowerCase();
	}
	
	
	public String readText(String textInput)
	{
		System.out.print(textInput);
		return scan.nextLine();   			
	}
	
	
	public int readNumber(String textInput)
	{
		System.out.print(textInput);							
		int number = scan.nextInt();															
		scan.nextLine();										
		return number;
	}
	
	
	public void handleCommand(String givenCommand)
	{
		boolean eventIsGiven = false;
		for (AthleticEvent e : eList.getEventList()){
			if (e.getEventName().equalsIgnoreCase(givenCommand)){
				String normalizedEventName = nameNormalizer(givenCommand);
				rList.showResultsForEvent(normalizedEventName);
				eventIsGiven = true;
				break;
			}
		}
		if (!eventIsGiven)
		{
			if (givenCommand.contains("message")){
				String statedMessage = givenCommand.substring(8);
				showMessage(statedMessage);
			}
			else {
				switch (givenCommand)
				{
					case "menu":
						gamesMenu();
						break;
						
					case "add event":
						handleEvent();
						break;		
						
					case "add participant":
						handleParticipant();
						break;
									
					case "remove participant":
						handleRemovalStepOne();
						break;
									
					case "add result":
						handleResultStepOne();
						break;
							
					case "participant":
						handleResultsForParticipantToBeShown();                            
						break;
					
					case "teams":
						rList.showResultsForTeams();
						break;
					
					case "reinitialize":
						rList.getResultList().clear();
						tList.getTeamList().clear();
						aList.getAthleteList().clear();
						Athlete.setStartingNumber(99);
						System.out.println("All data for participants and teams have been deleted"); 
						System.out.println();
						break;
							
					case "exit":
						System.exit(0);
						
					default:
						System.out.println("Wrong (type of) command. Try again.");
						System.out.println();			
				} //End of switch clause
			}
		}
	}//End of runCommand() method
	
	
	public String nameNormalizer(String textInput)
	{
		adjustedName = textInput.trim().toLowerCase();
		firstChar = Character.toUpperCase(adjustedName.charAt(0));
		normalizedName = firstChar + adjustedName.substring(1);
		return normalizedName;
	}
	
	
	public void showMessage(String givenPhrase)
	{
		int textLength = 56;
		System.out.println();
			
		//Prints the top line
		for (int firstLine = 0; firstLine <= 59; firstLine++){
			System.out.print("#");	
		}
		System.out.println();
		System.out.print("#");	
				
		//Prints second row
		for (int secondLine = 0; secondLine < 58; secondLine++){
			System.out.print(" ");
		}
		printAStar();
				
		//Prints third row with message	
		System.out.print("#");
	    if (givenPhrase.length() > textLength) {
	    	givenPhrase = givenPhrase.substring(0, textLength);
	    }
	    System.out.print(" " + givenPhrase.toUpperCase());              
	    for (int thirdLineSpace = 0; thirdLineSpace <= (textLength - givenPhrase.length()); thirdLineSpace++){
	        System.out.print(" ");
	    }    
	    printAStar();
			
	    //Prints the fourth row
		System.out.print("#");		
		for (int fourthLine = 0; fourthLine < 58; fourthLine++){
			System.out.print(" ");
		}
		printAStar();
			
		//Prints the bottom line			
		for (int fifthLine = 0; fifthLine <= 59; fifthLine++){
			System.out.print("#");
		}
		System.out.println();
		}

	
	public void printAStar()
	{
		System.out.print("#");
		System.out.println();
	}
	
	
	public void handleEvent()
	{
		String normalEventName = null;
		if (eList.getEventList().isEmpty()){
			String eName = checkForEmptyEventName();
			normalEventName = nameNormalizer(eName);
		}
		else {
			ArrayList<String> eventNames = new ArrayList<>();
			for (AthleticEvent e : eList.getEventList()){
				eventNames.add(e.getEventName());
			}
			String eName = checkForEmptyEventName();
			normalEventName = nameNormalizer(eName);
			//Below code is what the test files demand by giving a new command after a test for entering a duplicate; a real program might have used some sort of a while clause
			if (eventNames.contains(normalEventName)){
				System.out.println("Cannot enter given event twice.");
				return;
			}	
		}
		
		int maxAttempts;
		do {
			maxAttempts = readNumber("State the number of attempts allowed in the event (integer): ");
			if (maxAttempts <= 0){
				System.out.println("The number given is too low. State a higher number.");
			}
		} while (maxAttempts <= 0); 
		
		String yesOrNo;
		do {
			yesOrNo = readText("Is a higher value better? ");
			if (!(yesOrNo.equalsIgnoreCase("Yes") || yesOrNo.equalsIgnoreCase("Y") || yesOrNo.equalsIgnoreCase("No") || yesOrNo.equalsIgnoreCase("N"))){
				System.out.println("Wrong input. Enter Yes, Y, No or N.");
				System.out.println();
			}
		}while (!(yesOrNo.equalsIgnoreCase("Yes") || yesOrNo.equalsIgnoreCase("Y") || yesOrNo.equalsIgnoreCase("No") || yesOrNo.equalsIgnoreCase("N")));	

		System.out.println();
		eList.addEvent(normalEventName, maxAttempts, yesOrNo);
		System.out.println();	
 	}//End of handleEvent() method
	
	
	public String checkForEmptyEventName()
	{
		String nameOfEvent;
		do {
			nameOfEvent = readText("State the name of the event: ");
			if (nameOfEvent.equals("") || nameOfEvent.trim().isEmpty()){		//Takes care of empty name and empty name with blank space
				System.out.println("You must state a name of the event. Try again.");
			}
		} while (nameOfEvent.equals("") || nameOfEvent.trim().isEmpty());
		return nameOfEvent;
	}
	
	
	public void handleParticipant()
	{
		String fName = readText("State the first name of participant: ");
		while (fName.equals("")|| fName.trim().isEmpty()){
			System.out.println("You must state a first name. Try again.");
			fName = readText("State the first name of participant: ");
		}
		String normalFirstName = nameNormalizer(fName);
	
		String lName = readText("State the last name of participant: ");
		while (lName.equals("") || lName.trim().isEmpty()){
			System.out.println("You must state a last name. Try again.");
			lName = readText("State the last name of participant: ");
		}
		String normalLastName = nameNormalizer(lName);
		
		String tName = readText("State name of the team to which the participant belongs: ");
		while (tName.equals("") || tName.trim().isEmpty()){
			System.out.println("You must state a team name. Try again.");
			tName = readText("State name of the team to which the participant belongs: ");
		}
		System.out.println();        //Added to make the output look better with testfiles
		String normalTeamName = nameNormalizer(tName);
					
		aList.addAthlete(normalFirstName, normalLastName, normalTeamName);
	}//End of handleParticipant() method
	
	
	public void handleRemovalStepOne(){
		System.out.println();        //Added to make the output look better with testfiles
		int participantToBeRemoved = readNumber("Number: ");
		
		ArrayList<Integer> athletes = new ArrayList<>();
		for (Athlete a : aList.getAthleteList()){
			if (!aList.getAthleteList().isEmpty()){
				athletes.add(a.getAthleteID());
			}
		}
		//Below code is what the test files demand by entering a wrong number and then giving a new command; a real program might have used some sort of a while clause
		if ((!athletes.contains(participantToBeRemoved)) || participantToBeRemoved < 100){
			System.out.println("There is no participant with that number.");
			return;
		}
		
		aList.handleRemovalStepTwo(participantToBeRemoved);
	}
	
	
	public void handleResultStepOne()
	{
		int startingNumber = readNumber("State starting number of participant for which there is a result: ");
		ArrayList<Integer> athletes = new ArrayList<>();
		for (Athlete a : aList.getAthleteList()){
			if (!(aList.getAthleteList().isEmpty())){
				athletes.add(a.getAthleteID());
			}
		}
		//Below code is what the testfiles demand by entering the wrong number and then giving a new command...
		if ((!athletes.contains(startingNumber)) || startingNumber < 100){
			System.out.println("There is no participant with that number.");
			return;
		}
		//Below code is what the testfiles demand by giving an empty name once and then giving a new command...
		String namedEvent = readText("State the name of the event: ");
		if (namedEvent.equals("")|| namedEvent.trim().isEmpty()){
			System.out.println("Error. You must state a name.");
			return;
		}
		String normalizedEventName = nameNormalizer(namedEvent);
		
		ArrayList<String> events = new ArrayList<>();
		for (AthleticEvent e : eList.getEventList()){
			events.add(e.getEventName());
		}
		//Below code is what the testfiles demand by giving the non-existing event name once and then giving a new command...
		if (!events.contains(normalizedEventName)){
			System.out.println("Error. You must state an event that is held in these Athletic Games.");
			return;
		}

		int allowedAttempts = 0;
		for (AthleticEvent e : eList.getEventList()){
			if (e.getEventName().equals(normalizedEventName)){
				allowedAttempts = e.getAllowedAttempts();
			}
		}
		ArrayList<Result> resultsForAthlete = new ArrayList<>();
		for (Result r : rList.getResultList()){
			if ((r.getStartingNumber() == startingNumber) && (r.getNameOfEvent().equals(normalizedEventName))){
				resultsForAthlete.add(r);
			}
		}
		int numberOfResultsInEvent = resultsForAthlete.size();
		handleResultStepTwo(startingNumber, normalizedEventName, allowedAttempts, numberOfResultsInEvent);
	}	
	
	
	public void handleResultStepTwo(int sNumber, String nEventName, int aAttempts, int nOfResultsInEvent)
	{
		while (aAttempts == nOfResultsInEvent){
			System.out.println("The participant referred to has already made maximum number of attempts allowed in the event.");
			handleResultStepOne();
		}
		System.out.print("State result (decimal number with dot): ");
		double result = scan.nextDouble();
		scan.nextLine();       //NECESSARY?
		while (result < 0){
			System.out.println("Error. The result cannot be a negative value. Try again.");
			System.out.print("State result (decimal number with dot): ");
			result = scan.nextDouble();
			scan.nextLine();   //NECESSARY?
		} 
		rList.addResult(sNumber, nEventName, result);
	}

	
	public void handleResultsForParticipantToBeShown()
	{
		int participantWithResults = readNumber("Number: ");
		
		ArrayList<Integer> athletes = new ArrayList<>();
		for (Result r : rList.getResultList()){
			if (!rList.getResultList().isEmpty()){
				athletes.add(r.getStartingNumber());
			}
		}
		//Below code is what the testfiles demand by giving the wrong number and then giving a new command...
		if ((!athletes.contains(participantWithResults)) || participantWithResults < 100){
			System.out.println("No participant with a result has that number.");
			return;
		}
		
		rList.showResultsForAthlete(participantWithResults);
	}

}//End of class
