import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
class Edges
{
    String source,destination;
    int weight;
    Edges(String source,String destination,int weight)
    {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
    String source()
    {
        return source;
    }
    String destin()
    {
        return destination;
    }
    int weight()
    {
        return weight;
    }
}
public class AvgDistance {
    int vertices,path,total;
    LinkedList<Edges> list[];

    public static void main(String[] args) {
        AvgDistance p = new AvgDistance(5);
        p.list();
        System.out.print("First source node : ");
        Scanner input1 = new Scanner(System.in);
        String x = input1.next().toUpperCase();
        System.out.print("Enter destination node : ");
        Scanner input2 = new Scanner(System.in);
        String y = input2.next().toUpperCase();

        System.out.println(p.calculateAverageDistanceBetweenTwoPoints(x, y));
    }
    void list()
    {
        addEdges("A","B",12);
        addEdges("A", "C", 13);
        addEdges("B", "C", 3);
        addEdges("A", "E", 8);
        addEdges("E", "C", 4);
        addEdges("A", "D", 11);
        addEdges("D", "E", 7);
    }
    void addEdges(String source,String destination,int weight)
    {
        Edges front = new Edges(source,destination,weight);
        list[source.charAt(0) - 'A'].add(front);
        Edges back = new Edges(destination,source,weight);
        list[destination.charAt(0)- 'A'].add(back);
    }
    AvgDistance(int vertices)
    {
        this.vertices = vertices;
        list = new LinkedList[vertices];
        for(int i=0;i<vertices;i++)
        {
            list[i] = new LinkedList<Edges>();
        }
    }
    public double calculateAverageDistanceBetweenTwoPoints(String x,String y)
    {
        boolean v[] = new boolean[vertices];
        for(int i=0;i<vertices;i++)
        {
            v[i] = false;
        }
        path =0;
        total = 0;
        v[(int)(x.charAt(0))-'A']=true;
        dfs(x,y,v,0);
        return ((double) total/(double)path);

    }
    void dfs(String x,String y,boolean[] v,int distance)
    {
        if(x.equals(y))
        {
            path++;
            total +=distance;
        }
        for(Edges i : list[x.charAt(0)-'A'])
        {
            String reachedVer = i.destin();
            int index = (reachedVer.charAt(0))-'A';
            if(!v[index])
            {
                v[index] = true;
                dfs(reachedVer,y,v,distance+i.weight());
                v[index] = false;
            }
        }
    }

}
