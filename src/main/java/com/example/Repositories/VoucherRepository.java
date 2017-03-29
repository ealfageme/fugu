package com.example.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Entities.*;
public interface VoucherRepository extends JpaRepository<Voucher,Long>{
	Page<Voucher> findByRestaurant(Restaurant restaurant, Pageable page);
}
