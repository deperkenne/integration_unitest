package de.uniba.wiai.dsg.ajp.assignment3;

public class CustomerException extends  Exception{
    public static class ValidationInput {
        Movie movie ;
        Rental rental;
        Customer customer;

       public ValidationInput(Movie movie){
            this.movie = movie;
        }
      public  ValidationInput(Rental rental){
            this.rental = rental;
        }

       public ValidationInput(Customer customer){
            this.customer = customer;
        }

        private void validateInputMovie() throws MovieExption {
            if(movie.getTitle().trim().isEmpty()|| movie.getTitle() == null || movie.getImageQuality() == null){
                throw new MovieExption("input must be valide");
            }
        }

        private void validateInputRental() throws MovieExption {
            if(rental.getMovie()== null || rental.getDaysRented() <= 0){
                throw new MovieExption("input  must be valide");
            }
        }

        private  void validateInputCustomer() throws MovieExption {
            if(customer.getName().trim().isEmpty() || customer.getName() == null){
                throw new MovieExption("name must not be null or empty");
            }
        }

        public  void validate() throws MovieExption {

            validateInputMovie();

        }

        public void validateRental() throws MovieExption {
            validateInputRental();
        }

        public void validateCustomerInput() throws MovieExption {
           validateInputCustomer();
        }



    }
}
