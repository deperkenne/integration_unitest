package de.uniba.wiai.dsg.ajp.assignment3;

public class RegularPrice extends Price {

	@Override
	double getCharge(int daysRented) throws MovieExption {
		double result = 2;
		if(daysRented <=0){
			throw new MovieExption("invalid input");
		}
		if (daysRented > 2) {
			result += (daysRented - 2) * 1.5;
		}
		return result;
	}

	@Override
	int getPriceCode() {
		return Movie.REGULAR;
	}

}
