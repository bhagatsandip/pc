package com.planetclubs.util;

public class PlanetClubError {
		//private Type type;
		private String message;
		
		public PlanetClubError(String message) {
			this.message = message;
		}

		public PlanetClubError() {
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

}
