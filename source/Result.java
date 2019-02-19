//Program created by Daniel Knöös (dakn3990), Nasimul Hasan (naha1113) and Reza Rohani Makvandi(rero6268).

public class Result{
	
	private int startingNumber;
	private String nameOfEvent;
	private double result;
	
	public Result(int sNo, String nEvent, double rValue)
	{
		startingNumber = sNo;
		nameOfEvent = nEvent;
		result = rValue;	
	}
	
	public int getStartingNumber()
	{
		return startingNumber;
	}
	
	public String getNameOfEvent()
	{
		return nameOfEvent;
	}
	
	public double getResult()
	{
		return result;
	}
	
	public String toString()
	{
		return "participant " + startingNumber + "  " + nameOfEvent + "  " + result;
	}
	
}//End of class
