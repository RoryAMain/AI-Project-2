import java.util.*;
public class HillClimber {
	
	//The larger the size the more random restarts are recquired to get a good answer.
	//There is a point where random restarts will recieve diminishing returns.
	//For example:
	//A graph with 10 cities.
	//5000 random restarts almost always returnsa better result than 500 random restarts.
	//However, 50,000 random restarts will usually return the sam result as 5000 random restarts.
	
	public void climbHill(int[]routeIn,int[][]graphIn, int maxRestartsIn)
	{
		int[] route = new int[routeIn.length];
		System.arraycopy(routeIn, 0, route, 0, routeIn.length);
		int[][] graph = graphIn;
		boolean stuck = false;
		int[] bestRoute = new int[routeIn.length];
		int bestScore = Integer.MAX_VALUE;
		int currentScore = Integer.MAX_VALUE;
		int restartCount = 0;
		int maxRestarts = maxRestartsIn;
		ArrayList<int[]> neighborList;
		
		GraphGenerator theGenerator = new GraphGenerator();
		
		/*
		//Print out graph
		for(int x = 0; x <route.length; x++)
		{
			for(int y =0; y<route.length;y++)
			{
				System.out.print(graph[x][y] + ", ");
			}
			System.out.println("\n");
		}
		//
		*/
		
		while(restartCount < maxRestarts)
		{
			
			while(!stuck)
			{
				stuck = true;
				currentScore = theGenerator.routeValue(route, graph);
				/*
				//Printing
				System.out.println("Current route: ");
				for(int y = 0; y < route.length;y++)
				{
					System.out.print(route[y] + ",");
				}
				System.out.print(route[0] + ",");
				System.out.println("\n");
				*/
				//System.out.println("Number of restarts: " + restartCount);
				if(currentScore < bestScore)
				{
					bestScore = currentScore;
					System.arraycopy(route, 0, bestRoute, 0, route.length);
				}
				neighborList = theGenerator.getNeighborList(route);
				
				for(int x = 0; x < neighborList.size();x++)
				{
					int tempScore = theGenerator.routeValue(neighborList.get(x), graph);
					if(tempScore < currentScore)
					{
						currentScore = tempScore;
						System.arraycopy(neighborList.get(x), 0, route, 0, route.length);
						stuck = false;
					}
				}
			}
			restartCount++;
			theGenerator.shuffleArray(route);
			stuck = false;
			//System.out.println("Restarting randomly.\n");
		}
		
		System.out.println("Best route found:\n");
		for(int y = 0; y < bestRoute.length;y++)
		{
			System.out.print(bestRoute[y] + ",");
		}
		
		System.out.println(bestRoute[0]);
		System.out.println("\nScore: " + bestScore);
		
	}
	
}
