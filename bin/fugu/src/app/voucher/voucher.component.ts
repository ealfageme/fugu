
import { Router, ActivatedRoute } from '@angular/router';
import { Voucher, VoucherService } from './voucher.service';
import { LoginService } from '../services/login.service';
import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-voucher',
  templateUrl: './voucher.component.html',
  styleUrls: ['./voucher.component.css']
})
export class VoucherComponent  {
  @Input()
  private foodType: string;  
  name: string;
  description: string;
  expiryDate: string;
  voucher: Voucher;
  newVoucher: boolean;
  min: string;
  max: string;
   

  private restaurantName: string;
  constructor(private _router: Router, activatedRoute: ActivatedRoute, private voucherService: VoucherService,private loginService: LoginService) { 
    const id = activatedRoute.snapshot.params['id'];
      this.name="";
      this.description="";
    this.restaurantName = loginService.user.name;
    this.loginService.user.name
      if (id) {
        voucherService.getVoucher(id, this.restaurantName).subscribe(
          vou => this.voucher = vou,
          error => console.error(error)
        );
        this.newVoucher = false;
      } else {
        this.voucher = { name: '', description: '', expiryDate: ''};
        this.newVoucher = true;
      }
  }

  ngOnInit() {
  }
  save() {
    console.log("entro");
    this.voucherService.saveVoucher(this.voucher, this.restaurantName).subscribe(
      book => { },
      error => console.error('Error creating new book: ' + error)
    );
  }
}


