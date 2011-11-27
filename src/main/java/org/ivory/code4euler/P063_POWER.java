package org.ivory.code4euler;

import java.math.BigInteger;

public class P063_POWER extends EasyEuler<Integer>{

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
		int cnt = 0;
		for( int i = 1 ; i < 10 ; i ++) {
			for( int p = 1 ; p < 1000 ; p ++ ) {
				BigInteger x = new BigInteger(String.valueOf(i));
				BigInteger xx = x.pow(p);
				if( xx.toString().length() == p) {
					println(i+"^" + p + "=" + xx );
					cnt ++ ;
				}
			}
		}
		println(cnt+"");
		return cnt;
	}

	public static void main (String [] args){
		P063_POWER power = new P063_POWER();
		power.run();
	}    
	
	

}
