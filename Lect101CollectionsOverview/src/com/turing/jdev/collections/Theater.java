package com.turing.jdev.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

public class Theater {
	
	private final String theaterName;
	private List<Seat> seats = new ArrayList<Seat>();
	// the follwing will work the same as the line above
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
		Seat requestedSeat = null;
		for(Seat seat : seats){
			System.out.print("."); // to have a visual perception of the number of iterations made
			if(seat.getSeatNumber().equals(seatNumber)){
				requestedSeat = seat;
				break;
			}
		}
		
		if(requestedSeat == null){
			System.out.println("There is no seat " + seatNumber);
			return false;
		}
		
		return requestedSeat.reserve();
	}
	
	// for testing
	public void getSeats(){
		
		for(Seat seat : seats){
			System.out.println(seat.getSeatNumber());
		}
	}
	
	// CLASS SEAT
	private class Seat{
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
		
	}

}
