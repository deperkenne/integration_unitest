package de.uniba.wiai.dsg.ajp.assignment3;

public abstract class Price {

	abstract double getCharge(int daysRented) throws MovieExption;

	int getFrequentRenterPoints(int daysRented) throws MovieExption{
		return 1;
	}

	abstract int getPriceCode();

}
