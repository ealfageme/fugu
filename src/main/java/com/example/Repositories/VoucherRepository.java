package com.example.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Entities.*;
import com.example.Repositories.*;
import com.example.Security.*;
import com.example.Controllers.*;
public interface VoucherRepository extends JpaRepository<Voucher,Long>{

}
