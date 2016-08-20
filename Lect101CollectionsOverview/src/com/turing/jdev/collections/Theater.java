package com.turing.jdev.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;

public class Theater {
	
	private final String theaterName;
	public List<Seat> seats = new ArrayList<Seat>();
	
	// ANONYMOUS INNER CLASS IMPLEMENTING A COMPARATOR
	// TODO : make this comparator produce an ordering that is consisting to equals, meaning:
	// it will only return 0, if the elements being compared actually are equal.
	// whic this comparator doesn't yet accomplishes because there can be N seats with the same price
	public static final Comparator<Seat> PRICE_ORDER = new Comparator<Theater.Seat>() {
		@Override
		public int compare(Seat seat1, Seat seat2) {
			
			if(seat1.getPrice() < seat2.getPrice()){
				return -1;
			}else if (seat1.getPrice() > seat2.getPrice()){
				return 1;
			}else{
				return 0;
			}
		}
	};
	
	public Theater(String theaterName, int numRows, int seatsPerRow){
		this.theaterName = theaterName;
		
		int lastRow='A' + (numRows-1);
		for(char row = 'A'; row <= lastRow; row++){
			for(int seatNum = 1; seatNum <= seatsPerRow; seatNum++){
				
				double price = 12.00;
				if(row < 'D' && (seatNum >= 4 && seatNum <= 9)){
					price = 14.00;
				}else if (row > 'F' || (seatNum < 4 || seatNum > 9)){
					price = 7.00;
				}
				Seat seat = new Seat(row + String.format("%02d", seatNum), price);
				seats.add(seat);
			}
		}
	}
	
	public String getTheaterName(){
		return theaterName;
	}
	
	public boolean reserveSeat(String seatNumber){
		
		Seat requestedSeat = new Seat(seatNumber, 0);
		int foundSeat = Collections.binarySearch(seats, requestedSeat, null); // if we're using the in-build comparator of Collections.binarySearch() we pass the 3rd parameter as null. 
		if(foundSeat >= 0){
			return seats.get(foundSeat).reserve();
		}else{
			System.out.println("There's no seat " + seatNumber);
			return false;
		}
	}
	
	// for testing
	public Collection<Seat> getSeats(){
		return seats;
	}
	
	// making the nested class program obeys only to testing/learning purposes, we wouldn't do that in real life
	public class Seat implements Comparable<Seat>{
		private final String seatNumber;
		private boolean reserved = false;
		private double price;
		
		public Seat(String seatNumber, double price){
			this.seatNumber = seatNumber;
			this.price = price;
		}
		
		public boolean reserve(){
			if(!this.reserved){
				this.reserved = true;
				System.out.println("Seat: " + seatNumber + " reserved");
				return true;
			}else{
				return false;
			}
		}
		
		public boolean cance(){
			if(this.reserved){
				this.reserved = false;
				System.out.println("Reservation of seat " + seatNumber + " cancelled");
				return true;
			}else{
				return false;
			}
		}
		
		public String getSeatNumber(){
			return seatNumber; 
		}
		
		public double getPrice(){
			return this.price;
		}

		@Override
		public int compareTo(Seat otherSeat) {
			return this.seatNumber.compareToIgnoreCase(otherSeat.getSeatNumber());	// using compareTo() from String
		}
	}

}
