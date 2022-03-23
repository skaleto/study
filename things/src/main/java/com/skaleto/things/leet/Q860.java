package com.skaleto.things.leet;

public class Q860 {

    public boolean lemonadeChange(int[] bills) {
        int five=0;
        int ten=0;
        for(int i=0;i<bills.length;i++){
            if(bills[i]==5){
                five++;
            }else if(bills[i]==10){
                if(five==0){
                    return false;
                }
                five--;
                ten++;
            }else{
                if(five==0){
                    return false;
                }
                five--;
                if(ten==0){
                    if(five<2){
                        return false;
                    }else{
                        five-=2;
                    }
                }else{
                    ten--;
                }
            }

        }
        return true;
    }

    public boolean lemonadeChange_20211229(int[] bills) {

        //手头有的钱
        int five=0;
        int ten=0;

        int i=0;
        while(i<bills.length){
            if(bills[i]==5){
                five++;
            }
            if(bills[i]==10){
                if(five<=0){
                    return false;
                }else{
                    five--;
                    ten++;
                }
            }
            if(bills[i]==20){
                //可能找10+5或5+5+5
                if(ten>0&&five>0){
                    ten--;
                    five--;
                }else if(five>=3){
                    five-=3;
                }else{
                    return false;
                }
            }
            i++;
        }

        return true;

    }
}
