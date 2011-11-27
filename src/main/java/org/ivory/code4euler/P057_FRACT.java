package org.ivory.code4euler;

import java.math.BigInteger;

public class P057_FRACT extends EasyEuler<Integer> {
	int cnt = 0;

	public int run(int k) {
		int res = 0;
		BigInteger n1 = new BigInteger("1");
		BigInteger n2 = new BigInteger("1");
		for (int i = 1; i <= k; i++) {
			n1 = n1.add(n2).add(n2);
			n2 = n1.subtract(n2);
			if (n1.toString().length() > n2.toString().length()) {
				res++;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		long beg = System.currentTimeMillis();
		P057_FRACT p57 = new P057_FRACT();
		System.out.println(p57.run(1000));
		long time = System.currentTimeMillis() - beg;
		System.out.println(time + " ms");
	}

	@Override
	void input() {
	}

	@Override
	void output() {
	}

	@Override
	Integer run() {
		return null;
	}
}
