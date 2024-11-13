package org.me.lc;

/**
 * @author sangnk
 * @Created 07/10/2024 - 8:17 SA
 * @project = java_tool
 * @_ Mô tả:
 */
public class T2696 {
    public static void main(String[] args) {
        System.out.println(minLength("CCCCDDDD"));
    }

    public static int minLength(String s) {
        //remove AB, CD from s
        boolean stop = true;
        int i = 0;
        if(s.length() == 0) return 0;
        if(s.length() == 1) return 1;
        while (stop) {
            if(i == s.length() - 1 || s.equals("")) {
                stop = false;
            } else {
                String charI = s.substring(i, i + 1);
                String charIAdd1 = s.substring(i + 1, i + 2);
                if ( (charI.equals("A") && charIAdd1.equals("B")) || (charI.equals("C") && charIAdd1.equals("D"))) {
                    s = s.substring(0, i) + s.substring(i + 2, s.length());
                    i = 0;
                } else {
                    i++;
                }
            }
        }
        return s.length();
    }
}
