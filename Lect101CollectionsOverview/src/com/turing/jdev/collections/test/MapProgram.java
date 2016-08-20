package com.turing.jdev.collections.test;

import java.util.HashMap;
import java.util.Map;

public class MapProgram {

	public static void main(String[] args) {
		
		Map<String, String> languages = new HashMap<String, String>();
		/*if(languages.containsKey("Java")){
			System.out.println("Java is already in the map");
		}else{
			System.out.println("Java daba dooo!");
		}*/
		
		languages.put("Java", "Always strong");
		languages.put("C#", "The North Remembers");
		
		System.out.println(languages.get("Java"));
		languages.put("Java", "A java once was a cup of coffee, you know.... once upon a time");
		System.out.println(languages.get("Java"));
		
		// will return null if the key didn't existed before
		System.out.println(languages.put("Delphi", "Cuando era joven y alocado... :) "));
		
		// will return the value in the pair key-value, of the key that's is gonna be dupplicated
		System.out.println(languages.put("C#", "College college... MATCOM!"));
		
		// checking out that the key doesn't exist before adding it
		if(languages.containsKey("Java")){
			System.out.println("Java is already in the map");
		}else{
			System.out.println("Java daba dooo!");
		}
		
		// there's no guarantee of order when using a map
		for(String key : languages.keySet()){
			System.out.println(key + " : " + languages.get(key));
		}
		
		languages.remove("Delphi");
		// languages.remove("Delphi", "Old language");	// returns a boolean and it's Java 8 functionality
		
		
	}

}
