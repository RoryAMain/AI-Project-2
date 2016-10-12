package simulatedannealing;


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
		
		int routesChecked = 0;
		
		GraphGenerator theGenerator = new GraphGenerator();
		
		//This while loop will run as long as we haven't hit the restart cap.
		while(restartCount < maxRestarts)
		{
			
			//This loop will run until we don't take a better neighbor. We have hit the local optimum.
			while(!stuck)
			{
				stuck = true;
				currentScore = theGenerator.routeValue(route, graph);
				
				//Uncomment this block to see every route that gets visited.
				//For any practical purpose this is way too long though.
				
				//////////////////////////////////////////////////////
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
				////////////////////////////////////////////////////////
				
				
				//If the current score(the last neighbor we chose) is the best we've seen yet, keep track of it.
				
				if(currentScore < bestScore)
				{
					bestScore = currentScore;
					System.arraycopy(route, 0, bestRoute, 0, route.length);
				}
				
				//Generate the neighbors for the current route.
				neighborList = theGenerator.getNeighborList(route);
				
				//For each neighbor check if it has a better value than we have now.
				//If it does, set currentRoute to that. Also, keep the loop going.
				for(int x = 0; x < neighborList.size();x++)
				{
					routesChecked++;
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
		System.out.println("Number of restarts: " + restartCount);
		System.out.println("Best route found:");
		for(int y = 0; y < bestRoute.length;y++)
		{
			System.out.print(bestRoute[y] + ",");
		}
		
		System.out.println(bestRoute[0]);
		System.out.println("\nScore: " + bestScore);
		System.out.println("Total routes checked: " + routesChecked);
		
	}
	
}
