package com.turing.jdev.collections.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.turing.jdev.collections.Theater;

public class Main {

	public static void main(String[] args){
		Theater theater = new Theater("Olympian", 8, 12);
		
		// the right way to copy a collection, Collections.copy() works... not very nicely
		List<Theater.Seat> seatCopy = new ArrayList<Theater.Seat>(theater.seats);
		// printList(seatCopy);
		
		// reserving A02, which affects both arrays as they share the same data ....
		seatCopy.get(1).reserve(); 		
		if(theater.reserveSeat("A02")){
			System.out.println("Please pay for A02");
		}else{
			System.out.println("seat already reserved");
		}
		
		// ... but they're still different collections, the reverse method doens't affect both structures
		Collections.reverse(seatCopy);
		System.out.println("Printing seatCopy" );
		printList(seatCopy);
		System.out.println("theter.seats" );
		printList(theater.seats);
		
		// we can also use
		// Collections.shuffle(seatCopy);
		
		// we can also obtain the MAX an MIN according to the natural order of our collection
		Theater.Seat minSeat = Collections.min(seatCopy);
		Theater.Seat maxSeat = Collections.max(seatCopy);
		System.out.println("minSeat " + minSeat.getSeatNumber());
		System.out.println("maxSeat " + maxSeat.getSeatNumber());
	}
	
	public static void printList(List<Theater.Seat> list){
		for(Theater.Seat seat : list){
			System.out.print(" " + seat.getSeatNumber());
		}
		System.out.println();
		System.out.println("=====================================================================================");
	}
	
}
