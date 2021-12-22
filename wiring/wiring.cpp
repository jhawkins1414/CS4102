#include <iostream>
#include <vector>
#include <string>

using namespace std;

//node class
class Node
{
public:
    string name;
    string type;
    int set;
    int place;
};

//edge class
class Edge
{
public:
    Node first;
    Node last;
    int weight;
};

//initialize algorithms
int find(int x, vector<Node> f);
void merge(Node x, Node y);
int compare(const Edge &a, const Edge &b);

//main method
int main()
{
    int n;
    cin >> n;
    int e;
    cin >> e;

    vector<Node> nodes;

    //read in nodes
    for (int i = 0; i < n; i++)
    {
        string label;
        string t;
        cin >> label;
        cin >> t;

        Node n;
        n.name = label;
        n.type = t;
        n.set = 0;
        n.place = 0;

        nodes.push_back(n);
    }

    vector<Edge> edges;

    //read in edges
    for (int i = 0; i < e; i++)
    {
        string f;
        string l;
        int w;
        cin >> f;
        cin >> l;
        cin >> w;

        Edge ed;

        for (int j = 0; j < nodes.size(); j++)
        {
            if (nodes[j].name == f)
            {
                ed.first = nodes[j];
            }
            if (nodes[j].name == l)
            {
                ed.last = nodes[j];
            }
        }

        ed.weight = w;

        edges.push_back(ed);
    }

    //sort edges by weight
    sort(edges.begin(), edges.end(), compare);

    //make array for find union algorithm
    for (int i = 0; i < nodes.size(); i++)
    {
        nodes[i].set = i;
        nodes[i].place = i;
    }

    //Kruskal's Algorithm
    int min = 0;
    int edgesAccepted = 0;
    int index = 0;

    while (edgesAccepted < nodes.size() - 1)
    {
        Edge ed = edges[index];
        int uset = find(ed.first.place, nodes);
        int vset = find(ed.last.place, nodes);
        if (uset != vset)
        {
            edgesAccepted++;
            min += ed.weight;
            merge(ed.first, ed.last);
        }
        index++;
    }

    //print result
    cout << min << endl;

    return 0;
}

//find for find union data structure
int find(int x, vector<Node> f)
{
    for (int i = x; i < f.size(); i++)
    {
        if (f[i].set == i)
        {
            return i;
        }
        i = f[i].set;
    }
}

//union for find union data structure
void merge(Node x, Node y)
{
    y.set = x.set;
}

//for sort by weight
int compare(const Edge &a, const Edge &b)
{
    return (a.weight < b.weight);
}
