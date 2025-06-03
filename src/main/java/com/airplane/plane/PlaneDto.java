package com.airplane.plane;

import java.time.LocalDateTime;

public class PlaneDto {

	    private int id;
	    private LocalDateTime planeTime;
	    private String departureName;
	    private String destinationName;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public LocalDateTime getPlaneTime() {
			return planeTime;
		}
		public void setPlaneTime(LocalDateTime planeTime) {
			this.planeTime = planeTime;
		}
		public String getDepartureName() {
			return departureName;
		}
		public void setDepartureName(String departureName) {
			this.departureName = departureName;
		}
		public String getDestinationName() {
			return destinationName;
		}
		public void setDestinationName(String destinationName) {
			this.destinationName = destinationName;
		}
	
}
