package com.userservice.userservice.external.services;

import com.userservice.userservice.entities.Rating;
import lombok.Builder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(name = "RATINGS-SERVICE")
public interface RatingService {

    @GetMapping("/ratings/{userId}")
    List<Rating> getRatingsByUserId(@PathVariable String userId);
}
