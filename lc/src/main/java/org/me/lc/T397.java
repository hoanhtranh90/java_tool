package org.me.lc;

import java.util.Vector;

/**
 * @author sangnk
 * @Created 07/10/2024 - 10:44 SA
 * @project = java_tool
 * @_ Mô tả:
 */
public class T397 {

    /*
     Given a positive integer n, you can apply one of the following operations:

    If n is even, replace n with n / 2.
    If n is odd, replace n with either n + 1 or n - 1.
    Return the minimum number of operations needed for n to become 1.
     */
    public static void main(String[] args) {
        System.out.println(new T397().dfs(1234));
    }

    public int dfs(int n) {
        int count = 0;
        while(n != 1){
            if(n == 2147483647) return 32;
            if(n == 3) return count+2;
            if(n % 2 == 0) n /= 2;
            else if(((n + 1)/2)%2 == 0) n += 1;
            else n-=1;
            count++;
        }
        return count;
    }
}
