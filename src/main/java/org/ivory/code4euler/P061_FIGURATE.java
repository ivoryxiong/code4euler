package org.ivory.code4euler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class P061_FIGURATE extends EasyEuler<Integer> {
	Map<Integer, Map<Integer,List<Integer>>> all;

	@Override
	void input() {
	}

	@Override
	void output() {
	}

	@Override
	Integer run() {
		int min = 1000;
		int max = 10000;
		int del = 1;
		int add = 1;
		all = new HashMap<Integer, Map<Integer,List<Integer>>>();
		for (int n = 3; n < 9; n++) {
			Map<Integer,List<Integer>> nn = new HashMap<Integer,List<Integer>>();
			int idx = 1;
			int g = 0;
			for (;;) {
				g = idx * (add * idx + del) / 2;
				if (g > min && g < max) {
					int pre = g / 100;
					List<Integer> suffix = null;
					if( nn.containsKey(pre)){
						suffix = nn.get(pre);
					}else{
						suffix = new ArrayList<Integer>();
					}
					suffix.add(g%100);
					nn.put(pre,suffix);
				}
				if (g > max) {
					break;
				}
				++idx;
			}
			del--;
			add++;
			all.put(n, nn);
		}
		
		Map<Integer,List<Integer>> nn = all.get(3);
		for( Map.Entry<Integer, List<Integer>> entry : nn.entrySet()){
			List<Integer> pre = new ArrayList<Integer>();
			pre.add(entry.getKey());
			Set<Integer> keys = new HashSet<Integer>();
			keys.add(3);
			dfs(keys ,pre,entry.getValue());
		}
		return 0;
	}
	
	private void dfs( Set<Integer> keys , List<Integer> prefix , List<Integer> suffix){
		if( keys.size() >= 6) {
			if( suffix.contains(prefix.get(0))) {	
				println(prefix+"");
				int sum = 0;
				for( int i = 0 ;i < 6 ; i ++ ) {
					int n = prefix.get(i) * 100 + prefix.get((i+1)%6);
					sum += n;
				}
				println(String.valueOf(sum));
			}
		}else{
			for( int key = 3 ; key < 9 ; key ++ ) {
				if( ! keys.contains(key)) {
			Map<Integer,List<Integer>> nn = all.get(key);
			for( int suf : suffix ) {
				if( nn.containsKey(suf)) {
					List<Integer> nsuffix = nn.get(suf);
					prefix.add(suf);
					keys.add(key);
					dfs(keys,prefix,nsuffix);
					prefix.remove(prefix.size()-1);
					keys.remove(key);
				}
			}}
			}
		}
	}
	
	public static void main (String [] args){
		long beg = System.currentTimeMillis();
		P061_FIGURATE figurate = new P061_FIGURATE();
		figurate.run();
		System.out.println("Used " + (System.currentTimeMillis()- beg) + " (ms)");
	}    
}
