package com.zzzfyrw.common.utils;

public class NumberFormatUtils {

    public static String toChinese(int amount){
        String str ="";
        if(amount<0||amount>99){
            str="不合法的数字";
        }else if(amount>=1&&amount<=10){
            str = cast(amount);
        }else if(amount>=11&&amount<=19){
            int num = amount%10;
            str = "十"+cast(num);
        }else if(amount >= 20){
            int num1 = amount/10%10;
            int num2 = amount%10;
            str = cast(num1)+"十"+cast(num2);
        }

        return str;

    }

    private static String cast(int num){
        String str="";
        switch(num){
            case 0:
                str="";
                break;
            case 1:
                str="一";
                break;
            case 2:
                str="二";
                break;
            case 3:
                str="三";
                break;
            case 4:
                str="四";
                break;
            case 5:
                str="五";
                break;
            case 6:
                str="六";
                break;
            case 7:
                str="七";
                break;
            case 8:
                str="八";
                break;
            case 9:
                str="九";
                break;
            case 10:
                str="十";
                break;
        }
        return str;

    }


    public static void main(String[] args) {
        int y = 1;
        int x = 11;
        int i = 22;
        System.out.println(toChinese(y));
        System.out.println(toChinese(x ));
        System.out.println(toChinese(i));
    }
}
