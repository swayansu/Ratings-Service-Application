package com.hotelservice.hotelservice.services;

import com.hotelservice.hotelservice.entities.Hotel;

import java.util.List;

public interface Services {

    Hotel addHotel(Hotel hotel);
    List<Hotel> getListOfHotels();
    Hotel get(String hotelId);
}
