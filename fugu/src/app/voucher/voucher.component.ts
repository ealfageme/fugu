
import { Router, ActivatedRoute } from '@angular/router';
import { Voucher, VoucherService } from './voucher.service';
import { LoginService } from '../services/login.service';
import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-voucher',
  templateUrl: './voucher.component.html',
  styleUrls: ['./voucher.component.css']
})
export class VoucherComponent  {
  @Input()
  foodType: string;
  name: string;
  description: string;
  expiryDate: string;
  voucher: Voucher;
  newVoucher: boolean;
  min: string;
  max: string;
  vouchers: String[];
  loginService: LoginService;

  private restaurantName: string;
  constructor(private _router: Router, activatedRoute: ActivatedRoute,
    loginServiceaux: LoginService, private voucherService: VoucherService) {
      this.loginService = loginServiceaux;
    const id = activatedRoute.snapshot.params['id'];
    this.vouchers = this.voucherService.vouchers(this.restaurantName);
      this.name = '';
      this.description = '';
    this.restaurantName = this.loginService.user.name;
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
    this.vouchers =[];
    this.vouchers = this.voucherService.vouchers(this.restaurantName);
  }
  save() {
    this.voucherService.saveVoucher(this.voucher, this.restaurantName).subscribe(
      book => {
        this.vouchers =[];
        this.vouchers = this.voucherService.vouchers(this.restaurantName);
       },
      error => console.error('Error creating new voucher: ' + error)
    );
  }
}


