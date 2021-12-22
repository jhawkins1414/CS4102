import java.util.*;

//Drainage class
public class Drainage{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int num = s.nextInt();
        ArrayList<GridItem[]> grids = new ArrayList<GridItem[]>();
        Grid[] ogGrids = new Grid[num];

        //populate array of grids
       for(int i = 0; i < num; i++){
           String city = s.next();
           int r = s.nextInt();
           int c = s.nextInt();
           GridItem[] forSort = new GridItem[r * c];
           Grid gr = new Grid(city, r, c);
           int index = 0;
           for(int j = 0; j < r; j++){
               for(int k = 0; k < c; k++){
                   int val = s.nextInt();
                    gr.g[j][k] = new GridItem(val, j, k, city);
                    forSort[index] = new GridItem(val, j, k, city);
                    index++;
               }
           }
           grids.add(forSort);
           ogGrids[i] = gr;
        }

        for(int i = 0; i < grids.size(); i++){
            int max = 1;
            GridItem[] current = grids.get(i);
            Grid now = ogGrids[i];
            Arrays.sort(current);
            for(int j = 0; j < current.length; j++){
                GridItem check = current[j];
                //check above
                if(check.row != 0){
                    if(now.g[check.row - 1][check.column].value > check.value){
                        if(now.g[check.row - 1][check.column].dyn <= now.g[check.row][check.column].dyn){
                            now.g[check.row - 1][check.column].dyn = now.g[check.row][check.column].dyn + 1;
                            if(now.g[check.row][check.column].dyn + 1 > max){
                                max = now.g[check.row][check.column].dyn + 1;
                            }
                        }
                    }
                }
                //check below
                if(check.row != now.g[0].length - 1){
                    if(now.g[check.row + 1][check.column].value > check.value){
                        if(now.g[check.row + 1][check.column].dyn <= now.g[check.row][check.column].dyn){
                            now.g[check.row + 1][check.column].dyn = now.g[check.row][check.column].dyn + 1;
                            if(now.g[check.row][check.column].dyn + 1 > max){
                                max = now.g[check.row][check.column].dyn + 1;
                            }
                        }
                    }
                }
                //check to the left
                if(check.column != 0){
                    if(now.g[check.row][check.column - 1].value > check.value){
                        if(now.g[check.row][check.column - 1].dyn <= now.g[check.row][check.column].dyn){
                            now.g[check.row][check.column - 1].dyn = now.g[check.row][check.column].dyn + 1;
                            if(now.g[check.row][check.column].dyn + 1 > max){
                                max = now.g[check.row][check.column].dyn + 1;
                            }
                        }
                    }
                }
                //check to the right
                if(check.column != now.g.length - 1){
                    if(now.g[check.row][check.column + 1].value > check.value){
                        if(now.g[check.row][check.column + 1].dyn <= now.g[check.row][check.column].dyn){
                            now.g[check.row][check.column + 1].dyn = now.g[check.row][check.column].dyn + 1;
                            if(now.g[check.row][check.column].dyn + 1 > max){
                                max = now.g[check.row][check.column].dyn + 1;
                            }
                        }
                    }
                }
            }
            System.out.print(now.name);
            System.out.print(": ");
            System.out.println(max);
        }
    }
}