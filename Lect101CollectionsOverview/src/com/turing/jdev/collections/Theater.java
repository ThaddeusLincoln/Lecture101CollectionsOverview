package com.turing.jdev.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

public class Theater {
	
	private final String theaterName;
	public List<Seat> seats = new ArrayList<Seat>();
	// the following will work the same as the line above
	// private Collection<Seat> seats = new LinkedList<Seat>();
	// private Collection<Seat> seats = new HashSet<Seat>();
	// private Collection<Seat> seats = new LinkedHashSet<Seat>();
	
	
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
		
		private Seat(String seatNumber, double price){
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
