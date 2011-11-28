package org.ivory.code4euler;

import java.math.BigInteger;

public class P065_FRACT extends EasyEuler<Integer>{
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
		BigInteger a = new BigInteger("3");
		BigInteger b = new BigInteger("8");
		BigInteger c = new BigInteger("11");

		for( int i = 4 ; i <= 100 ; i ++ ) {
			if( i% 3 == 0 ) {
				int k = i / 3 * 2;
				c = b.multiply(new BigInteger(String.valueOf(k))).add(a);
			}else{
				c = b.add(a);
			}
//			println(c.toString());
			a = b ;
			b = c ;
		}
		String val = c.toString();
		int sum = 0;
		for( int i = 0; i< val.length() ; i ++){
			sum += Integer.parseInt(val.substring(i,i+1));
		}
		println(c.toString());
		println(sum+"");
		return 0;
	}
	public static void main (String [] args){
		long beg = System.currentTimeMillis();
		P065_FRACT fract = new P065_FRACT();
		fract.run();
		System.out.println("Used " + (System.currentTimeMillis()- beg) + " (ms)");
	}    
}
