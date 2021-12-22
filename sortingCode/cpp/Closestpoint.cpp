#include <iostream>
#include <vector>
#include <string>

using namespace std;

float bruteForce(int n);

int main(){
    int num;
    cin >> num;
    bruteForce(num);
}

float bruteForce(int n){
    for(int i = 0; i < num; i++){
        string s = "";
        cin >> s;
        string temp = "";
        vector<float> list;
        for(auto x: s){
            if (x == ' '){
                list.push_back(stof(temp));
                temp = "";
            }
            else {
                temp = temp + x;
            }
        }
        for(int j = 0; j < list.size(); j++){

        }
    }
    return 1;
}

