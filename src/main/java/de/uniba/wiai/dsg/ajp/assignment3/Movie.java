package de.uniba.wiai.dsg.ajp.assignment3;


public class Movie {

	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	public static final int CHILDRENS = 2;
	public static final int LOW_BUDGET = 3;

	private Price price;

	private String title;


	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	private ImageQuality imageQuality;

	public Movie(String title, int priceCode,ImageQuality imageQuality) throws MovieExption {
		if(title.trim().isEmpty() || title == null || imageQuality == null){
			throw new MovieExption("input invalid");
		}

		this.title = title;
		this.setPriceCode(priceCode);
		setImageQuality(imageQuality);

	}
	// Nouveau constructeur pour accepter un objet Price
	public Movie(String title, Price price, ImageQuality imageQuality) throws MovieExption {
		if(title.trim().isEmpty() || price == null || imageQuality == null){
			throw new MovieExption("input invalid");
		}
		this.title = title;
		this.price = price;
		this.imageQuality = imageQuality;
	}


	public void setImageQuality(ImageQuality imageQuality){
		this.imageQuality = imageQuality;
	}

	public ImageQuality getImageQuality() throws MovieExption {
		if(imageQuality == null){
			throw new MovieExption("imageQuality must not be null");
		}
		 return this.imageQuality;
	}



	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	double getCharge(int daysRented) throws RentalException, MovieExption {
		double newPrice = price.getCharge(daysRented);
				switch (this.imageQuality) {
					case SD:
						// Pas de changement pour SD
						break;
					case HD:
						newPrice += 2;
						break;
					case K4:
						newPrice += 4;
						break;
				}


		return newPrice;
	}




	public int getPriceCode() {
		return price.getPriceCode();
	}

	public void setPriceCode(int priceCode)  {
		switch (priceCode) {
		case REGULAR:
			price = new RegularPrice();
			break;
		case CHILDRENS:
			price = new ChildrensPrice();
			break;
		case NEW_RELEASE:
			price = new NewReleasePrice();
			break;
			case LOW_BUDGET:
				price = new LowBudGet();
				break;
		default:
			throw new IllegalArgumentException("Incorrect Price Code");
		}
	}

	public int getFrequentRenterPoints(int daysRented) throws RentalException, MovieExption {
        int day = price.getFrequentRenterPoints(daysRented);
		return day;
	}



}
