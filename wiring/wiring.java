import java.util.*;

//wiring class
public class wiring{

    //kruskals algorithm
    public static int kruskals(HashMap<String,Node> nodes, Edge[] edges, int edgeNum){
        int edgesAccepted = 0;
        DisjSet s = new DisjSet(nodes.size());
        int nodeNum = nodes.size();
        int i = 0;
        int min = 0;
        
        while(edgesAccepted < nodeNum - 1){
            int uset = s.find(edges[i].first.order);
            int vset = s.find(edges[i].last.order);
            if(uset != vset){
                edgesAccepted++;
                s.union(uset, vset);
                min += edges[i].weight;
                i = edgesAccepted;
            }
            else{
                i++;
            }
        }
        return min;
    }

    // quicksort method
	public static Edge[] quickSort(Edge[] list, int first, int last) {
		if (first < last) {
			Edge temp = list[last];
			int r = (int) (first + (Math.random() * (last - first)));
			list[last] = list[r];
			list[r] = temp;
			int p = partition(list, first, last);
			quickSort(list, first, p - 1);
			quickSort(list, p + 1, last);
		}
		return list;
	}

	// Lomuto's partition for quicksort
	private static int partition(Edge[] list, int first, int last) {
		Edge temp = list[last];
		int prev = first - 1;
		for (int next = first; next <= last - 1; next++) {
			if ((list[next].weight - temp.weight) < 0) {
				prev++;
				Edge pick = list[prev];
				list[prev] = list[next];
				list[next] = pick;
			}
		}
		Edge swap = list[prev + 1];
		list[prev + 1] = list[last];
		list[last] = swap;
		return prev + 1;
	}

    public static void main(String[] args){
        //read in number of nodes and number of edges
        int nodeNum;
        int edgeNum;
        Scanner s = new Scanner(System.in);
        nodeNum = s.nextInt();
        edgeNum = s.nextInt();

        //containers to store nodes and edges
        HashMap<String, Node> map = new HashMap<String, Node>();
        Edge[] edges = new Edge[edgeNum];
        Node prevSwitch = new Node();

        //read in nodes
        for(int i = 0; i < nodeNum; i++){
            Node n = new Node();
            n.name = s.next();
            n.type = s.next();
            n.order = i;
            n.sw = "";
            if(n.type.equals("switch")){
                prevSwitch = n;
                n.sw = n.name;
            }
            else if(n.type.equals("light")){
                n.sw = prevSwitch.name;
            }
            map.put(n.name, n);
        }

        int eNum = 0;
        int sNum = 0;
        Edge[] switches = new Edge[edgeNum];
        //read in edges
        for(int i = 0; i < edgeNum; i++){
            Edge e = new Edge();
            e.first = map.get(s.next());
            e.last = map.get(s.next()); 
            e.weight = Integer.parseInt(s.next());

            //don't add invalid edges to edge list
            if(e.first.type.equals("light") && !(e.last.sw.equals(e.first.sw))){
                ;
            }
            else if(e.last.type.equals("light") && !(e.first.sw.equals(e.last.sw))){
                ;
            } 
            else if(e.first.type.equals("switch") && e.last.type.equals("switch")){
                ;
            }
            else if(e.first.type.equals("switch") || e.last.type.equals("switch") || e.first.type.equals("light") || e.last.type.equals("light")){
                switches[sNum] = e;
                sNum++;
            }
            else{
                edges[eNum] = e;
                eNum++;
            }
        }

        //sort edges by weight
        quickSort(edges, 0, eNum - 1);
        quickSort(switches, 0, sNum - 1);

        for(int i = 0; i < sNum; i++){
            edges[eNum] = switches[i];
            eNum++;
        }

        System.out.println(kruskals(map, edges, eNum));
    }
}

