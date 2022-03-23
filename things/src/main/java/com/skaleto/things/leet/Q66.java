package com.skaleto.things.leet;

public class Q66 {

    public int[] plusOne_20211219(int[] digits) {

        int i=digits.length-1;
        boolean needPlus=true;
        while(needPlus && i>=0){
            digits[i]++;
            if(digits[i]==10){
                digits[i]=0;
                needPlus=true;
            }else{
                needPlus=false;
            }
            i--;
        }

        if(digits[0]==0){
            int[] result=new int[digits.length+1];
            result[0]=1;
            return result;
        }

        return digits;

    }

    public int[] plusOne(int[] digits) {
        int i=digits.length-1;

        while(i>=0&&digits[i]==9){
            digits[i]=0;
            i--;
        }

        if(i>=0){
            digits[i]++;
            return digits;
        }else{
            int[] newDigits=new int[digits.length+1];
            newDigits[0]=1;
            return newDigits;
        }

    }
}
