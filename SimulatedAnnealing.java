package simulatedannealing;

import java.util.ArrayList;
import java.lang.Math;

/*
*   function SIMULATED-ANNEALING(problem, schedule) returns a solution state
*       inputs: problem, a problem
*               schedule, a mapping from time to "temperature"
*       local variables: current, a node
*                        next, a node
*                        T, a "temperature" controlling prob. of downward steps
*        
*       current <- MAKE-NODE(INITIAL-STATE[problem])
*       for t <- 1 to infinity do
*           T <- schedule[t]
*           if T = 0 then return current
*           next <- a randomly selected successsor of current
*           *e <- VALUE[next] - VALUE[current]
*           if *e > 0 then current <- next
*           else current <- next only with probability e^(*e/T)
*/
public class SimulatedAnnealing {
  
    public void simulatedAnnealing(int[] route, int[][] graph) {
        System.arraycopy(route, 0, route, 0, route.length);
        int[] bestRoute = new int[route.length];
        int bestScore = Integer.MAX_VALUE;
        int currentScore = Integer.MAX_VALUE;
        ArrayList<int[]> neighborList;
        GraphGenerator theGenerator = new GraphGenerator();
        double T = 10000.0;
        double te = 100.0;
        double probability = (1.0/(Math.pow(Math.E, te/T)))*100;
        int randNum = 0;
        
        while (true) {
            if (currentScore < bestScore) {
                bestScore = currentScore;
                System.arraycopy(route, 0, bestRoute, 0, route.length);
            }
            neighborList = theGenerator.getNeighborList(route);

            for (int x = 0; x < neighborList.size(); x++) {
                int tempScore = theGenerator.routeValue(neighborList.get(x), graph);
                currentScore = theGenerator.routeValue(route, graph);
                randNum = (int)(Math.random() * 100 +1);
                if (tempScore < currentScore) {
                    currentScore = tempScore;
                    System.arraycopy(neighborList.get(x), 0, route, 0, route.length);

                    theGenerator.shuffleArray(route);
                }
                else if(randNum < probability)
                {
                    currentScore = tempScore;
                    System.arraycopy(neighborList.get(x), 0, route, 0, route.length);

                    theGenerator.shuffleArray(route);
                }
            }
            T--;
                if(T!=0){
                    probability = (1.0/(Math.pow(Math.E, te/T)))*100;
                }
                else
                {
                    //divide by 0 
                    //for whatever reason, compiler allowed division by 0
                    //so I used an if statement instead of a try/catch ;;
                    System.out.println("T = 10000\nte = 100");
                    System.out.println(
                            "Best route found:");
                    for (int y = 0;
                            y < bestRoute.length;
                            y++) {
                        System.out.print(bestRoute[y] + ",");
                    }

                    System.out.println(bestRoute[0]);
                    System.out.println(
                            "\nScore: " + bestScore);
                    return;
                }
                //System.out.println(
                //        "T = " + T);
        }
        
    }

}
