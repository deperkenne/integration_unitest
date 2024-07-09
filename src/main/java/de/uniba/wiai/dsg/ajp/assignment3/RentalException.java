package de.uniba.wiai.dsg.ajp.assignment3;

public class RentalException extends  Exception{

    String message;

    public RentalException(String message){
        super(message);
        this.message = message;
    }
}
