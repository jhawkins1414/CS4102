import java.util.*;
import java.io.*;

//Daycare class
public class Daycare {

    // quicksort method for difference
    public static Room[] quickSort(Room[] list, int first, int last) {
        if (first < last) {
            Room temp = list[last];
            int r = (int) (first + (Math.random() * (last - first)));
            list[last] = list[r];
            list[r] = temp;
            int p = partition(list, first, last);
            quickSort(list, first, p - 1);
            quickSort(list, p + 1, last);
        }
        return list;
    }

    // Lomuto's partition for quicksort for difference
    private static int partition(Room[] list, int first, int last) {
        Room temp = list[last];
        boolean b = false;
        int prev = first - 1;
        for (int next = first; next <= last - 1; next++) {
            boolean b1 = temp.start < list[next].start;
            boolean b2 = temp.finish > list[next].finish;
            boolean b3 = temp.dif > list[next].dif;
            if(list[next].dif == temp.dif){
                if(temp.dif > 0){
                    b = b1;
                }
                else if(temp.dif <= 0){
                    b = b2;
                }
            }
            else if(temp.dif < 0 && list[next].dif < 0){
                b = b2;
            }
            else{
                b = b3;
            }
            if (!b) {
                prev++;
                Room pick = list[prev];
                list[prev] = list[next];
                list[next] = pick;
            }
        }
        Room swap = list[prev + 1];
        list[prev + 1] = list[last];
        list[last] = swap;
        return prev + 1;
    }

    // method to solve with greedy method
    public static int greedy(Room[] rooms) {
        for (int i = 0; i < rooms.length - 1; i++) {
            for (int j = 0; j < i; j++) {
                    while (rooms[i].start > 0 && rooms[j].start < rooms[j].finish) {
                        rooms[j].start++;
                        rooms[i].start--;
                }
            }
            rooms[rooms.length - 1].start += rooms[i].start;
            rooms[i].start = 0;
        }
        return rooms[rooms.length-1].start;
    }

    // main method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int count = 0;

        Scanner s = new Scanner(System.in);
        // int num = s.nextInt();

        while(true) {
            try{
                String str = s.nextLine();
                int num = Integer.parseInt(str);

                // input rooms into array
                Room[] rooms = new Room[num + 1];
                for (int i = 0; i < num; i++) {
                    Room r = new Room();
                    String ints = s.nextLine();
                    r.start = Integer.parseInt(ints.substring(0, ints.indexOf(" ")));
                    r.finish = Integer.parseInt(ints.substring(ints.indexOf(" ") + 1));
                    double d1 = r.start;
                    double d2 = r.finish;
                    r.dif = d2 - d1;
                    rooms[i] = r;
                }

                Room r = new Room();
                r.start = 0;
                r.finish = 0;
                r.dif = Integer.MAX_VALUE;
                rooms[num] = r;

                // quicksort rooms by difference
                quickSort(rooms, 0, num - 1);

                // print
                System.out.println(greedy(rooms));
            }
            catch (Exception e){
                break;
            }
            }
        s.close();
    }
}