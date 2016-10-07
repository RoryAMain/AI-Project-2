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
	
	
	
	public static int[][] getGraphOfSize(int sizeIn)
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
	
	public static void main(String[] args)
	{
		int size = 3;
		GraphGenerator testGraph = new GraphGenerator();
		int[][] graph = getGraphOfSize(size);
		
		for(int x = 0; x <size; x++)
		{
			for(int y =0; y<size;y++)
			{
				System.out.print(graph[x][y] + ", ");
			}
			System.out.println();
		}
	}
	
	
}
