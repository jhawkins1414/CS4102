//disjoint set class
public class DisjSet{

    public int[] set;
    public int num;

    //construct disjoint set
    public DisjSet(int nodeNum){
        this.num = nodeNum;
        this.set = new int[nodeNum];
        for(int i = 0; i < nodeNum; i++){
            this.set[i] = i;
        }
    }

    //find method
    public int find(int index){
            if(this.set[index] == index){
                return index;
            }
        this.set[index] = find(this.set[index]);
        return this.set[index];
    }

    //union method
    public void union(int i, int j){
        int label1 = this.find(i);
        int label2 = this.find(j);
        this.set[label1] = label2;
    }
}