import java.util.*;
//ideas from https://algorithms.tutorialhorizon.com/max-flow-problem-ford-fulkerson-algorithm/

//sis class
public class sis{

    //method to make capacity flowchart and residual flowchart
    public static int flow(int r, int c, int n, intRequest[] requests, course[] courses, int s){
        int[][] matrix = new int[s + 2 + c][s + 2 + c];
        int[][] residual = new int[s + 2 + c][s + 2 + c];
        int[] parent = new int[s + 2 + c];
        int max = 0;

        //set up matrix for flow graph
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(i == j || j == 0 || i == matrix.length - 1){
                    matrix[i][j] = 0;
                    residual[i][j] = 0;
                }
                //from source to students
                else if(i == 0 && j > 0 && j < 1 + s){
                    matrix[i][j] = n;
                    residual[i][j] = n;
                }
                //from courses to sink
                else if(j == matrix[0].length - 1 && i >= 1 + s && i < matrix.length - 1){
                    matrix[i][j] = courses[i - 1 - s].numSpots;
                    residual[i][j] = courses[i - 1 - s].numSpots;
                }
                else{
                    matrix[i][j] = 0;
                    residual[i][j] = 0;
                }
            }
        }

        //add requests to adjacency matrix (from students to courses)
        for(int i = 0; i < requests.length; i++){
            matrix[requests[i].student][requests[i].classWanted] = 1;
            residual[requests[i].student][requests[i].classWanted] = 1;
        }

        //compute fordfulkerson's
        while(BFS(residual, 0, residual.length - 1, parent)){
            int capacity = Integer.MAX_VALUE;

            int check = residual.length - 1;
            while(check != 0){
                int b = parent[check];
                capacity = Math.min(capacity, residual[b][check]);
                check = b;
            }

            check = residual.length - 1;
            while(check != 0){
                int b = parent[check];
                residual[b][check] -= capacity;
                residual[check][b] += capacity;
                check = b;
            }

            max += capacity;
        }

        return max;

    }

    //method to see if there is a path from source to destination
    public static boolean BFS(int[][] residual, int src, int sink, int[] parent){
        boolean found;

        boolean [] visited = new boolean[residual.length];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(src);
        parent[src] = -1;
        visited[src] = true;
        visited[sink] = false;

        while(!queue.isEmpty()){
            int check = queue.poll();

            for(int i = 0; i < residual.length; i++){
                if(!visited[i] && residual[check][i] > 0){
                    queue.add(i);
                    parent[i] = check;
                    visited[i] = true;
                }
            }
        }
        found = visited[sink];
        return found;
    }

    //main method
    public static void main(String[] args){
        int r;
        int c;
        int n;
        int index = 1;

        Scanner s = new Scanner(System.in);

        r = Integer.parseInt(s.next());
        c = Integer.parseInt(s.next());
        n = Integer.parseInt(s.next());


        while(!(r == 0 && c == 0 && n == 0)){

            request[] requests = new request[r];
            HashMap<String, Integer> courseMap = new HashMap<String, Integer>();
            course[] courses = new course[c];
            HashMap<String, Integer> students = new HashMap<String, Integer>();
            intRequest[] intRequests = new intRequest[r];

            //read in registration requests
            for(int i = 0; i < r; i++){
                request req = new request();

                req.student = s.next();
                req.classWanted = s.next();

                requests[i] = req;

                if(!students.containsKey(req.student)){
                    students.put(req.student, index);
                    index++;
                }
            }


            //read in courses
            for(int i = 0; i < c; i++){
                course cou = new course();
                cou.name = s.next();
                cou.numSpots = Integer.parseInt(s.next());

                courses[i] = cou;
                courseMap.put(cou.name, index);
                index++;
            }

            //convert requests into int form
            for(int i = 0; i < r; i++){
                intRequest ir = new intRequest();

                ir.student = students.get(requests[i].student);
                ir.classWanted = courseMap.get(requests[i].classWanted);

                intRequests[i] = ir;
            }

            //every student takes the max classes
            int max = students.size() * n;

            int result = flow(r, c, n, intRequests, courses, students.size());

            //print yes if every student can take n classes
            if(result == max){
                System.out.println("Yes");
            }
            else{
                System.out.println("No");
            }
            r = Integer.parseInt(s.next());
            c = Integer.parseInt(s.next());
            n = Integer.parseInt(s.next());
        }

        s.close();
    }
}