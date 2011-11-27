package org.ivory.code4euler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.ivory.code4euler.util.PrimeUtils;

/**
 * problem: http://projecteuler.net/problem=51 <br>
 * 6 : 13, 23, 43, 53, 73, and 83 <br>
 * 7 : 56003, 56113, 56333, 56443, 56663, 56773, and 56993 <br>
 * 8 : ...
 * 
 * @author ivoryxiong@gmail.com
 */
public class P051_PRIME extends EasyEuler<Integer> {
    private Set<Integer> primes;

    private int n;

    private int len;

    public P051_PRIME(int n, int len) {
        super();
        this.n = n;
        this.len = len;
        primes = new HashSet<Integer>(PrimeUtils.GeneratePrime(n));
    }

    public Integer run() {
        List<Integer> pp = new ArrayList<Integer>(primes);
        Collections.sort(pp);
        for (int p: pp) {
            if (p > 56002 && isSameDigitor(p)) {
                List<Integer> list = family(p);
                if (list.size() == len) {
                    return p;
                }
            }
        }
        return 0;
    }

    /**
     * isSameDigitor:判断数number种是否有相同数字
     * 
     * @param number
     * @return boolean
     */
    private boolean isSameDigitor(int number) {
        int[] ds = new int[10];
        while (number > 0) {
            ds[number % 10]++;
            number /= 10;
        }
        for (int n: ds) {
            if (n > 1) {
                return true;
            }
        }
        return false;
    }

    private List<Integer> family(int prime) {
        List<Integer> family = new ArrayList<Integer>();
        String p = String.valueOf(prime);
        int ds[] = new int[p.length()];
        for (int i = ds.length - 1; i >= 0; i--) {
            ds[i] = prime % 10;
            prime /= 10;
        }
        prime = Integer.parseInt(p);
        for (int d = 0; d < 10; d++) {
            boolean go = false;
            for (int i = 0; i < ds.length; i++) {
                if (d == ds[i]) {
                    ds[i] = -1;
                    go = true;
                }
            }
            if( ! go ) continue;
            List<Integer> f = new ArrayList<Integer>();
            f.add(prime);
            for (int x = d + 1; x < 10; x++) {
                int pn = toNumber(ds, -1, x);
                if (isPrime(pn)) {
                    f.add(pn);
                }
            }
            if (f.size() > family.size()) {
                family.clear();
                for (int pf: f) {
                    family.add(pf);
                }
            }
            for (int i = 0; i < ds.length; i++) {
                if (ds[i] < 0) {
                    ds[i] = d;
                }
            }
        }
        return family;
    }

    private int toNumber(int[] ds, int x, int y) {
        int res = 0;
        for (int d: ds) {
            if (d == x) {
                res = res * 10 + y;
            } else {
                res = res * 10 + d;
            }
        }
        return res;
    }

    private boolean isPrime(int p) {
        if (p < n) {
            return primes.contains(p);
        } else {
            BigInteger bg = new BigInteger(String.valueOf(p));
            return bg.isProbablePrime(10);
        }

    }

    public static void main(String[] args) {
        long beg = System.currentTimeMillis();
        P051_PRIME p51 = new P051_PRIME(1000000, 8);
        System.out.println(p51.run());
        long time = System.currentTimeMillis() - beg;
        System.out.println(time + " ms");
    }

	@Override
	void input() {
	}

	@Override
	void output() {
	}
}
