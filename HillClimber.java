import java.util.*;
public class HillClimber {
	
	public void climbHill(int[]routeIn,int[][]graphIn)
	{
		int[] route = new int[routeIn.length];
		System.arraycopy(routeIn, 0, route, 0, routeIn.length);
		int[][] graph = graphIn;
		boolean stuck = false;
		int[] bestRoute = new int[routeIn.length];
		int bestScore = Integer.MAX_VALUE;
		int currentScore = Integer.MAX_VALUE;
		int restartCount = 0;
		int maxRestarts = 5;
		ArrayList<int[]> neighborList;
		
		GraphGenerator theGenerator = new GraphGenerator();
		
		while(restartCount < maxRestarts)
		{
			
			while(!stuck)
			{
				stuck = true;
				currentScore = theGenerator.routeValue(route, graph);
				if(currentScore < bestScore)
				{
					bestScore = currentScore;
					System.arraycopy(route, 0, bestRoute, 0, route.length);
				}
				neighborList = theGenerator.getNeighborList(route);
				
				for(int x = 0; x < neighborList.size();x++)
				{
					int tempScore = theGenerator.routeValue(neighborList.get(x), graph);
					if(tempScore > currentScore)
					{
						currentScore = tempScore;
						System.arraycopy(neighborList.get(x), 0, route, 0, route.length);
						stuck = false;
					}
				}
			}
			restartCount++;
			//route = getRandomRoute
			//not yet implemented.
		}
		
		System.out.println("Best route found:\n");
		for(int y = 0; y < bestRoute.length;y++)
		{
			System.out.print(bestRoute[y] + ",");
		}
		
		System.out.print(bestRoute[0]);
		
	}
	
}
