package com.turing.jdev.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

public class Theater {
	
	private final String theaterName;
	private List<Seat> seats = new ArrayList<Seat>();
	// the following will work the same as the line above
	// private Collection<Seat> seats = new LinkedList<Seat>();
	// private Collection<Seat> seats = new HashSet<Seat>();
	// private Collection<Seat> seats = new LinkedHashSet<Seat>();
	
	
	public Theater(String theaterName, int numRows, int seatsPerRow){
		this.theaterName = theaterName;
		
		int lastRow='A' + (numRows-1);
		for(char row = 'A'; row <= lastRow; row++){
			for(int seatNum = 1; seatNum <= seatsPerRow; seatNum++){
				Seat seat = new Seat(row + String.format("%02d", seatNum));
				seats.add(seat);
			}
		}
	}
	
	public String getTheaterName(){
		return theaterName;
	}
	
	public boolean reserveSeat(String seatNumber){
		
		int low = 0;
		int high = seats.size() - 1;

		while(high >= low) {
			System.out.print(".");
			int mid = (low + high) / 2;
			Seat midVal = seats.get(mid);
			int cmp = midVal.getSeatNumber().compareTo(seatNumber);
			
			if(cmp < 0) {
				low = mid + 1;
			}
			else if(cmp < 0) {
				high= mid - 1;
			}
			else {
				return seats.get(mid).reserve();
			}
		}
		
		return false;
	}
	
	// for testing
	public void getSeats(){
		
		for(Seat seat : seats){
			System.out.println(seat.getSeatNumber());
		}
	}
	
	// CLASS SEAT
	private class Seat implements Comparable<Seat>{
		private final String seatNumber;
		private boolean reserved = false;
		
		private Seat(String seatNumber){
			this.seatNumber = seatNumber;
		}
		
		public boolean reserve(){
			if(!this.reserved){
				this.reserved = true;
				System.out.println("Seat " + seatNumber + " reserved");
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

		@Override
		public int compareTo(Seat otherSeat) {
			return this.seatNumber.compareToIgnoreCase(otherSeat.getSeatNumber());	// using compareTo() from String
		}
	}

}
