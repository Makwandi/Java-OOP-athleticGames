//Program created by Daniel Knöös (dakn3990), Nasimul Hasan (naha1113) and Reza Rohani Makvandi(rero6268).

import java.util.Comparator;

public class MinComparator implements Comparator<Result>{
	
public int compare(Result r1, Result r2){
		
		double comparison = r1.getResult() - r2.getResult();
		
		if (comparison > 0){
			return 1;
		}
		else if (comparison < 0){
			return -1;
		}
		else
			return 0;
	}

}
