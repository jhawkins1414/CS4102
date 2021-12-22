import java.util.*;

//GridItem class
public class GridItem implements Comparable<GridItem>{
    public int value;
    public int row;
    public int column;
    public int dyn;
    public String name;

    public GridItem(int value, int row, int column, String name){
        this.value = value;
        this.row = row;
        this.column = column;
        this.dyn = 1;
        this.name = name;
    }

    @Override
    public int compareTo(GridItem gi){
        return this.value - gi.value;
    }
}