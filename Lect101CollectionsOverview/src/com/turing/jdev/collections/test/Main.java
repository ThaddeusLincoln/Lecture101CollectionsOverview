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
		printList(seatCopy);
		Collections.reverse(seatCopy);
		printList(seatCopy);
		
		// reserving A02, which affects both arrays as they share the same data ....
		seatCopy.get(1).reserve(); 		
		if(theater.reserveSeat("A02")){
			System.out.println("Please pay for A02");
		}else{
			System.out.println("seat already reserved");
		}
		
		// testing comparator
		List<Theater.Seat> priceSeats = new ArrayList<Theater.Seat>(theater.getSeats());
		priceSeats.add(theater.new Seat("B00", 14.00));
		priceSeats.add(theater.new Seat("A00", 14.00));
		Collections.sort(priceSeats, Theater.PRICE_ORDER);
		System.out.println("Printing priceSeats after sort with comparator");
		printList(priceSeats);
		
	}
	
	public static void printList(List<Theater.Seat> list){
		for(Theater.Seat seat : list){
			System.out.print(" " + seat.getSeatNumber() + " " + seat.getPrice());
		}
		System.out.println();
		System.out.println("=====================================================================================");
	}
	
	
	
}
