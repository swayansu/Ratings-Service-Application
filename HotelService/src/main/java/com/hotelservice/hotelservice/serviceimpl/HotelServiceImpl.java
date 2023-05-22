package com.hotelservice.hotelservice.serviceimpl;

import com.hotelservice.hotelservice.Exception.ResourceNotFoundException;
import com.hotelservice.hotelservice.entities.Hotel;
import com.hotelservice.hotelservice.hotelrepository.HotelRepository;
import com.hotelservice.hotelservice.services.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements Services {

    private final HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Hotel addHotel(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getListOfHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel get(String hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("The given resource with Id Is not found"));
    }
}
