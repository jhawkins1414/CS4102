import java.util.*;

//Grid class
public class Grid{
    public String name;
    public GridItem[][] g;

    public Grid(String n, int row, int column){
        this.name = n;
        this.g = new GridItem[row][column];
    }
}