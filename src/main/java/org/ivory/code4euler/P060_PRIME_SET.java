package org.ivory.code4euler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.ivory.code4euler.util.PrimeUtils;

public class P060_PRIME_SET extends EasyEuler<Integer> {
	int total = 0 ;
	int n ;
	int val = Integer.MAX_VALUE ;
	int len = 5 ;
	HashSet<Integer> primes;
	Map<Integer,List<Integer>> edge = new HashMap<Integer,List<Integer>>();
	@Override
	void input() {
		// TODO Auto-generated method stub
	}

	@Override
	void output() {
		// TODO Auto-generated method stub
		
	}

	@Override
	Integer run() {
		n = 10000;
		List<Integer> ps = PrimeUtils.GeneratePrime(n);
		primes = new HashSet<Integer>(ps);
		System.out.println(primes.size());
		int cnt = 0 ;
		
		for( int i = 0 ; i < ps.size() ; i ++ ){
			List<Integer> tos = new ArrayList<Integer>();
			for( int j = i + 1 ; j < ps.size() ; j ++) {
				if( test(ps.get(i),ps.get(j))) {
					tos.add(ps.get(j));
				    ++ cnt ;
				}
			}
			edge.put(ps.get(i), tos);
		}
		for( int p : ps) {
			List<Integer> pre = new ArrayList<Integer>();
			pre.add(p);
			dfs(1,pre,edge.get(p));
		}
		println(val+"");
		return cnt;
	}

	private void dfs( int level,List<Integer> pre , List<Integer> coms) {
		if( level >= len ) {
			println(pre+"");
			int sum = 0;
			for( int t :  pre){
				sum += t;
			}
			if( sum < val) {
				val = sum;
			}
		}else{
			for( int c : coms) {
				List<Integer> xs = edge.get(c);
				List<Integer> ncoms = commons(xs,coms);
				if( level + ncoms.size() >= len -1) {
					pre.add(c);
					dfs(level+1,pre,ncoms);
					pre.remove(pre.size()-1);
				}
			}
		}
	}
	
	private List<Integer> commons ( List<Integer> a , List<Integer> b){
		List<Integer> res = new ArrayList<Integer>();
		int ai = 0;
		int bi = 0;
		int aa = 0;
		int bb = 0;
		while ( ai < a.size() && bi < b.size()) {
			aa = a.get(ai);
			bb = b.get(bi);
			if( aa == bb ) {
				res.add(aa);
				ai ++ ;
				bi ++ ;
			}else{
				if( aa > bb) {
					bi ++ ;
				}else{
					ai ++ ;
				}
			}
		}
		return res;
	}
	boolean test( int a , int b ){
		int ca = Integer.parseInt(a+""+b);
		if( isPrime(ca)) {
			int cb = Integer.parseInt(b+""+a);
			return isPrime(cb); 
		}
		return false;
	}

    private boolean isPrime(int p) {
        if (p < n) {
            return primes.contains(p);
        } else {
            BigInteger bg = new BigInteger(String.valueOf(p));
            return bg.isProbablePrime(10);
        }
    }
    
	public static void main (String [] args){
		P060_PRIME_SET primeSet = new P060_PRIME_SET();
		primeSet.run();
	}    
}
