package org.ivory.code4euler;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import org.ivory.code4euler.util.PrimeUtils;

public class P058_RATIO extends EasyEuler {
	private Set<Integer> primes;

	private int n;

	public P058_RATIO(int n) {
		super();
		this.n = n;
		primes = new HashSet<Integer>(PrimeUtils.GeneratePrime(n));
	}

	public int run(int n1, int n2) {
		int a = 1;
		int delta = 0;
		int p = 0;
		int cnt = 1;
		while (true) {
			delta += 2;
			for (int i = 0; i < 4; i++) {
				a = a + delta;
				cnt++;
				if (isPrime(a)) {
					p++;
				}
			}
			if (p * n2 < cnt * n1) {
				break;
			}
		}
		delta++;
		return delta;
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
		P058_RATIO p58 = new P058_RATIO(50000);
		System.out.println(p58.run(1, 10));
		long time = System.currentTimeMillis() - beg;
		System.out.println(time + " ms");
	}
}
