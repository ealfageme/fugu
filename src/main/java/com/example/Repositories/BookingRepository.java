package com.example.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Entities.*;
public interface BookingRepository extends JpaRepository<Booking,Long>{

}
