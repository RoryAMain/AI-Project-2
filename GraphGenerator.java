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
	
	public ArrayList<int[]> getNeighborList(int[] route)
	{
		ArrayList<int[]> neighborList = null;
		
		for(int x = 0; x < route.length; x++)
		{
			int[] neighbor = route;
			int temp = neighbor[x];
			neighbor[x] = neighbor[x+1];
			neighbor[x+1] = temp;
			neighborList.add(neighbor);
		}
		
		return neighborList;
	}
	
	
	public static void main(String[] args)
	{
		int size = 5;
		GraphGenerator testGraph = new GraphGenerator();
		int[][] graph = testGraph.getGraphOfSize(size);
		
		int[] route = {0,1,2,3,4};
		int[] route2 = {1,0,2,3,4};
		int[] route3 = {0,2,1,4,3};
		
		for(int x = 0; x <size; x++)
		{
			for(int y =0; y<size;y++)
			{
				System.out.print(graph[x][y] + ", ");
			}
			System.out.println();
		}
		
		System.out.println(testGraph.routeValue(route,graph));
		System.out.println(testGraph.routeValue(route2,graph));
		System.out.println(testGraph.routeValue(route3,graph));
		
	}
	
	
}
