package org.ivory.code4euler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P059_ENCRYP extends EasyEuler<Integer>{
	List<Integer> codes ;
	int sum = 0;
	@Override
	void input() {
		try {
			Scanner cin = new Scanner(ClassLoader.class.getResourceAsStream("/cipher1.txt"));
			codes = new ArrayList<Integer>();
			while ( cin.hasNext()) {
				String line = cin.nextLine();
				String [] ls = line.split(",");
				for(String l : ls){
					codes.add(Integer.parseInt(l));
				}
			}
			cin.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	void output() {
		
	}

	@Override
	Integer run() {
		input();
		for( int i = 122 ; i > 96 ; i -- ){
			for( int j = 122 ; j > 96 ; j -- ){
				for( int k = 122 ; k > 96 ; k -- ){
					boolean t = true;
					int s = 0;
					int u = 0;
					String str = "";
					for( int d = 0 ; d < codes.size() ; d ++){
						if( d%3 == 0 ) {
							u = codes.get(d) ^ i;
						} else if( d%3 == 1 ) {
							u = codes.get(d) ^ j;
						} else if( d%3 == 2 ) {
							u = codes.get(d) ^ k;
						}
						if( isWord(u)) {
							s += u ;
							str=str+"&#"+u+";";
						}else{
							t = false;
							break;
						}
					}
					if( t ) {
						sum = s;
						println(String.valueOf(sum)+"<br/>");
						println(str+"<br/>");
					}
				}
			}
		}
//		println(String.valueOf(sum));
		return sum;
	}
	
	boolean isWord( int x ){
		return x <= 122;
	}
	
	public static void main (String [] args){
		P059_ENCRYP encryp = new P059_ENCRYP();
		encryp.run();
	}    
}
