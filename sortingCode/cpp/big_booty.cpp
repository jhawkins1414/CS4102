big_booty(a, b){
    if(list.size() == 1){
        return 0;
    }
    int dif = f([0, n/2] [n/2+1,n-1]);
    if(dif) > 0){
        return n/2+1 + big_booty(n/2 + 1, n-1)
    }
    else(dif < 0){
        return 0 + big_booty([0, n/2])
    }
}

T(n) = f(n) + 1.5T(n/2)

To start my algorithm, I will have my base case. This will stipulate that if the list size (n) is equal to 1, then the function will return 0. Next, my function will check if the supplied function on every index in the first half of the list and every index in the second half of the list f([0..., n/2], [n/2 + 1..., n - 1]) is greater than 0. If so, my function will return n/2 + 1 + a recursive call on the second half of the list. Else, my function will return 0 + a recursive call on the first half of the list.
