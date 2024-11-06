package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.Ticket;

import java.util.concurrent.TimeUnit;

public class FareCalculatorService {

    private final TicketDAO ticketDAO;

    private static final double FREE_TIME_LIMIT = 0.5; // 1/2 hour limit
    private static final double DISCOUNT_RATE = 0.95; // 5% discount

    public FareCalculatorService(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    public void calculateFare(Ticket ticket){
        // Exit time is null or exit time is from a day before
        if ((ticket.getOutTime() == null)) {
            throw new NullPointerException("Out time can't be found");
        }
         else if( (ticket.getOutTime().before(ticket.getInTime())) ){
            throw new IllegalArgumentException("Out time provided is incorrect:"+ticket.getOutTime().toString());
        }

        long inHour = ticket.getInTime().getTime();
        long outHour = ticket.getOutTime().getTime(); // Returns ms


        //TODO: Some tests are failing here. Need to check if this logic is correct
        long difference = outHour - inHour; // If divided here, division will result an integer and 0 if (0 < number < 1)
        double duration = difference / 3600000.0;// Converts to hours


        // Adding free 30 minutes
        if (duration > FREE_TIME_LIMIT)
        {
            switch (ticket.getParkingSpot().getParkingType()){
                case CAR: {
                    // Apply 5% discount
                    if(ticketDAO.recurrentVisitor(ticket)){
                        ticket.setPrice((duration) * Fare.CAR_RATE_PER_HOUR * DISCOUNT_RATE);
                    }
                    // Does nothing
                    else {
                        ticket.setPrice((duration) * Fare.CAR_RATE_PER_HOUR);
                    }
                    break;
                }
                case BIKE: {
                    // Apply 5% discount
                    if (ticketDAO.recurrentVisitor(ticket)){
                        ticket.setPrice((duration) * Fare.BIKE_RATE_PER_HOUR * DISCOUNT_RATE);
                    }
                    //Does nothing
                    else {
                        ticket.setPrice((duration) * Fare.BIKE_RATE_PER_HOUR);
                    }
                    break;
                }
                default: throw new IllegalArgumentException ("Unkown Parking Type");

                }

        }else {
            ticket.setPrice(Fare.VEHICLE_RATE_FREE_TIME);
        }
    }
}