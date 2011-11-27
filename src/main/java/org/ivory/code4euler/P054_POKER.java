package org.ivory.code4euler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class P054_POKER extends EasyEuler<Integer> {
	private int count = 0;
	class Card implements Comparable<Card>{
		char suit;
		int number;
		public Card(String card) {
			card = card.trim();
			String n = card.substring(0, 1);
			suit = card.charAt(1);
			if (n.startsWith("T")) {
				number = 10;
			} else if (n.startsWith("J")) {
				number = 11;
			} else if (n.startsWith("Q")) {
				number = 12;
			} else if (n.startsWith("K")) {
				number = 13;
			} else if (n.startsWith("A")) {
				number = 14;
			} else {
				number = Integer.parseInt(n);
			}
		}

		public Card(int n , char s) {
			number = n ;
			suit = s;
		}
		
		@Override
		public int compareTo(Card o) {
			return o.number - number;
		}


		@Override
		public String toString() {
			return "[" + suit + ", " + number + "]";
		}
		
	};

	
	class Game{
		List<Card> playerA;
		List<Card> playerB;
		private static final int RF = 10;
		private static final int SF = 9;		
		private static final int K4 = 8;
		private static final int FH = 7;
		private static final int F = 6;
		private static final int S = 5;
		private static final int K3 = 4;
		private static final int TP = 3;
		private static final int OP = 2;
		private static final int HC = 1;
		
		public Game(String line){
			String [] cs = line.split(" ");
			playerA = new ArrayList<Card>();
			for( int idx = 0; idx < 5 ; idx ++){
				playerA.add(new Card(cs[idx]));
			}
			playerB = new ArrayList<Card>();
			for( int idx = 5; idx < 10 ; idx ++){
				playerB.add(new Card(cs[idx]));
			}
			Collections.sort(playerA);
			Collections.sort(playerB);
		}
		
		public boolean isAWin(){
			boolean res = false;
			int p1 = p (playerA);
			int p2 = p( playerB);
			System.out.println(playerA + "\t" + p1);
			System.out.println(playerB + "\t" + p2);

			if( p1 > p2) {
				res = true;
			}else if ( p1 == p2){
				for( int i = 0; i< 5 ; i ++ ) {
					if( playerA.get(i).number !=  playerB.get(i).number) {
						res = playerA.get(i).number > playerB.get(i).number;
						break;
					}
				}
			}
			return res;
		}
		
		private int p( List<Card> cs ){
			if( isRoyalFlush(cs)){
				return RF;
			}
			if ( isStraightFlush(cs,0)){
				return SF;
			}
			if ( isFlush(cs)) {
				return F ;
			}			
			if ( isStraight(cs,cs.get(0).number)) {
				return S ;
			}
			int pp = isKind(cs);
			return pp ;
		}
		
		private boolean isRoyalFlush( List<Card> cs){
			return isStraightFlush(cs,14);
		}
		
		private boolean isFlush( List<Card> cs){
			char suit = cs.get(0).suit; 
			for( Card c : cs){
				if(  c.suit != suit ){
					return false;
				}
			}
			return true;
		}
		
		private boolean isStraight( List<Card> cs, int x){
			int idx = x;
			for( Card c : cs){
				if( idx == c.number ){
					--idx;
				}else{
					return false;
				}
			}
			return true;
			
		}
		
		private boolean isStraightFlush( List<Card> cs , int x ){
			int idx = x;
			if( idx == 0 ) {
				idx = cs.get(0).number;
			}
			return (isStraight(cs,idx) && isFlush(cs));
		}	
		
		private int isKind(List<Card> cs){
			int [] x = new int[15];
			for( Card c : cs){
				x[c.number] ++ ;
			}
			int P = HC;
			for(int idx=2 ; idx < 15 ; idx ++ ) {
				if( x[idx] == 4) {
					P = K4;
				}
				if( x[idx] == 3) {
					if( P == OP ){
						P = FH;
					}else{
						P = K3;
					}
				}
				if( x[idx] == 2) {
					if( P == K3 ){
						P = FH;
					}else if( P == OP) {
						P = TP;
					}else{
						P = OP;
					}
				}
			}
			cs.clear();
			for (int n = 4; n >= 1; n--) {
				for (int i = 14; i >= 2; i--) {
					if( x[i] == n) {
						for( int j = 0 ; j < n ; j ++ ) {
							Card c; 
							if( j==1 || j == 3 ){
								c = new Card(i,'X');
							}else{
								c = new Card(i,'P');
							}
							cs.add(c);
						}
					}
				}
			}
			return P;
		}
	}
	
	@Override
	void input() {
		try {
			Scanner cin = new Scanner(ClassLoader.class.getResourceAsStream("/poker.txt"));
			while ( cin.hasNext()) {
				String line = cin.nextLine();
				Game game = new Game(line);
				if( game.isAWin()) {
					count ++;
				}
			}
			cin.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	void output() {
		println(String.valueOf(count));
	}

	@Override
	Integer run() {
		input();
		output();
		return 0;
	}
	
	public static void main (String [] args){
		P054_POKER poker = new P054_POKER();
		poker.run();
	}
}
