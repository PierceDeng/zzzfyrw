import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LeetCodeNum6 {


    @Test
    public void test(){
        String s = "PAYPALISHIRING";
        System.out.println(s.length());
        int numrows = 4;
        String convert = convert(s, numrows);
        System.out.println(convert);
    }


    public String convert(String s, int numRows) {

        if(numRows < 2) return s;
        List<StringBuilder> rows = new ArrayList<StringBuilder>();
        for(int i = 0; i < numRows; i++) rows.add(new StringBuilder());
        int i = 0, flag = -1;
        for(char c : s.toCharArray()) {
            rows.get(i).append(c);
            if(i == 0 || i == numRows -1){
                flag = - flag;
            }
            i += flag;
        }
        StringBuilder res = new StringBuilder();
        for(StringBuilder row : rows) res.append(row);
        return res.toString();


    }

}
