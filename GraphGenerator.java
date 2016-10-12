package simulatedannealing;


import java.util.*;
public class GraphGenerator {
	//////////////////////////////////////////////////////////////////////////
	//
	//	Class GraphGenerator creates the graph used
	//	for the travelling salesman problem.
	//	The graph is simply an int[][] of values
	//	between 100-2500$, representing the cost of
	//	travel between nodes.
	//
	//	The cost to travel from a node to itself is -1,
	//	to indicate an improper movement.
	//
	//	The getGraphOfSize method will return an X by X graph,
	//	given an integer size X.
	//
	//	The main method is an example of the use, and should be commented out.
	//
	////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////
	//
	//	getGraphOfSize
	//
	//	Input: An int X indicating a graph of size [X][X] should be made.
	//
	//	Output: An int[][] representing the costs to travel from one city to another.
	//			For example [0][1] is the cost to travel from city 0 to city 1.
	//			These costs are automatically filled with values between 100 and 2500.
	//
	//
	/////////////////////////////////////////////////////////////////////////////
	
	public int[][] getGraphOfSize(int sizeIn)
	{
		Random rand = new Random();
		int[][] graph = new int[sizeIn][sizeIn];
		
		for(int x = 0; x <sizeIn;x++)
		{
			for(int y = 0; y <sizeIn; y++)
			{
				graph[x][y] = rand.nextInt(2400) + 100;
				if(x == y)
				{
					graph[x][y] = -1;
				}
			}
		}
		
		return graph;
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//
	//	routeValue
	//
	//	Input: An int[] representing the route being taken.
	//			{0,1,2} meaning to travel from 0->1->2->0. This method fills in the final return trip,
	//			and it need not be included in the route.
	//			It also takes an int[][] representing the graph.
	//
	//	Output: Outputs an int cost to take the given route on the given graph.
	//
	//			Note that the route must be the same length as the graph. As every city must be visited.
	//
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public int routeValue(int[] route, int[][] graph)
	{
		int value = 0;
		int currentNode = 0;
		int nextNode = 0;
		
		//Route returns to the start state, so its length should equal the length of the graph + 1.
		if(route.length != graph.length)
		{
			System.out.println("Route length not equal to graph length.");
			return -1;
		}
		else
		{
			for(int x = 0; x < route.length-1;x++)
			{
				currentNode = route[x];
				nextNode = route[x+1];
				value = value + graph[currentNode][nextNode];
			}
			
			value = value + graph[nextNode][route[0]];
			
		}
		return value;
	}
	
	//////////////////////////////////////////////////////////////////////////
	//
	//	getNeighborList
	//
	//	Input: An int[] representing the route taken through the graph.
	//
	//	Output: An ArrayList of int arrays representing the neighbors of the given route.
	//
	//			Note that for this assignment we define a neighbor of an int[] to be
	//			a slightly altered int[].
	//			In this case we alter the array by swapping a single pair of adjacent elements.
	//			Every possible case is a different neighbor.
	//
	//			As an example, given the route:
	//
	//			{0,1,2}
	//
	//			The neighbors returned are:
	//			{1,0,2}
	//			{0,2,1}
	//
	//			Keep in mind that the getValue function will always calculate the return to the beginning
	//			of the route, so we don't need to worry about balancing the first and final elements.
	//
	/////////////////////////////////////////////////////////////////////////////
	
	
	
	public ArrayList<int[]> getNeighborList(int[] route)
	{
		ArrayList<int[]> neighborList = new ArrayList<int[]>();
		
		for(int x = 0; x < route.length-1; x++)
		{
			int[] neighbor = new int[route.length];
			System.arraycopy(route, 0, neighbor, 0, route.length);
			int temp = neighbor[x];
			neighbor[x] = neighbor[x+1];
			neighbor[x+1] = temp;
			neighborList.add(neighbor);
		}
		
		return neighborList;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////
	//
	//	shuffleArray
	//
	//	Input: An int array. 
	//
	//	Output: Void.
	//
	//	This function will shuffle the provided int array, making it a random route
	//	with all of the same elements.
	//
	//////////////////////////////////////////////////////////////////////////////////////////
	
	public void shuffleArray(int[] array)
	{
	    int index, temp;
	    Random random = new Random();
	    for (int i = array.length - 1; i > 0; i--)
	    {
	        index = random.nextInt(i + 1);
	        temp = array[index];
	        array[index] = array[i];
	        array[i] = temp;
	    }
	}
	
	/*
	public static void main(String[] args)
	{
		
		int size = 10;
		GraphGenerator testGraph = new GraphGenerator();
		int[][] graph = testGraph.getGraphOfSize(size);
		
		int[] route = {0,1,2,3,4,5,6,7,8,9};
		
		HillClimber climber = new HillClimber();
		//climber.climbHill(route, graph,5);
		//climber.climbHill(route, graph, 50);
		//climber.climbHill(route, graph, 1000);
		testGraph.shuffleArray(route);
		climber.climbHill(route, graph, 5000);
		//climber.climbHill(route, graph, 50000);
	}
	*/
	
}
