package org.ivory.code4euler.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ivoryxiong@gmail.com
 */
public class PrimeUtils {

    /**
     * prime generator: generate all prime that less than n in asc order
     * 
     * @param n
     * @return List<Integer>
     */
    public static List<Integer> GeneratePrime(int n) {
        boolean[] a = new boolean[n];
        List<Integer> p = new ArrayList<Integer>();
        for (int i = 2; i < n; ++i) {
            if (!(a[i]))
                p.add(i);
            for (int j = 0; (j < p.size() && i * p.get(j) < n); ++j) {
                a[i * p.get(j)] = true;
                if (i % p.get(j) == 0)
                    break;
            }
        }
        return p;
    }

}
