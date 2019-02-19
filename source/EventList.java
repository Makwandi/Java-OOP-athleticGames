//Program created by Daniel Knöös (dakn3990), Nasimul Hasan (naha1113) and Reza Rohani Makvandi(rero6268).

import java.util.ArrayList;

public class EventList {
	
	private AthleticEvent oneEvent;
	private static ArrayList<AthleticEvent> listOfEvents = new ArrayList<>();
	
	
	public void addEvent(String eventName, int allowedAttempts, String isHigherValueBetter)
	{
		oneEvent = new AthleticEvent(eventName, allowedAttempts, isHigherValueBetter);
		listOfEvents.add(oneEvent);
		System.out.println(eventName + " added");
	}
	
	public ArrayList<AthleticEvent> getEventList()
	{
		return listOfEvents;
	}

}
