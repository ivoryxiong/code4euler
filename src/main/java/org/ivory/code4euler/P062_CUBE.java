package org.ivory.code4euler;

import java.util.HashMap;

public class P062_CUBE extends EasyEuler<Long> {
	private long cube = 1;
	
	@Override
	void input() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void output() {
		// TODO Auto-generated method stub
		
	}

	@Override
	Long run() {
		HashMap<String,Integer> num = new HashMap<String,Integer>();
		HashMap<String,Long> org = new HashMap<String,Long>();
		for(;;){
			long c3 = cube * cube * cube;
			if( c3 < 0 ) {
				println("WR");
				break;
			}
			String sig = toSign(c3);
			int key = 0;
			if( num.containsKey(sig)) {
				key = num.get(sig);
			}
			num.put(sig, ++key);
			if( key == 1 ) {
				org.put(sig, c3);
			}
			if( key >= 5) {
				println(org.get(sig)+"");
				break;
			}
			++ cube;
		}
		return cube;
	}
	
	String toSign(Long x){
		int [] dig = new int[10];
		while ( x > 0 ) {
			dig[(int)(x%10)] ++ ;
			x/=10;
		}
		String res = "";
		for( int i = 9 ; i >= 0 ; --i){
			res = res + ""+ i +","  + dig[i];
		}
		return res;
	}

	public static void main (String [] args){
		P062_CUBE cube = new P062_CUBE();
		cube.run();
	}    
}
