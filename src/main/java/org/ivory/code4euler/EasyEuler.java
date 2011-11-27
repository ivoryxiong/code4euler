package org.ivory.code4euler;
/**
 * 
 * @author   ivoryxiong@gmail.com
 */
public abstract class EasyEuler<T extends Number> {
    
	abstract void input();
	
	abstract void output();
	
	abstract T run();
	
    void print(String str){
        System.out.println(str);
    }

    void println(String str){
        System.out.println(str);
    }

}

