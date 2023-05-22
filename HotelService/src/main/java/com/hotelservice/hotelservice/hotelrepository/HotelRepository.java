package com.hotelservice.hotelservice.hotelrepository;

import com.hotelservice.hotelservice.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, String> {
}
