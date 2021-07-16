import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  public loggedIn: boolean = false;

  constructor(private authenticationService: AuthService) {
    this.authenticationService.setLogin(false);
    this.authenticationService.loggedIn$.subscribe((data) => {
      this.loggedIn = data;
    });
  }

  ngOnInit() {}

  public sendLogin(loginForm: NgForm) {
    // console.log(loginForm.value);
    this.authenticationService.login(loginForm.value).subscribe(
      (response) => {
        this.authenticationService.setLogin(true);
      },
      (error: HttpErrorResponse) => alert(error.message)
    );
    // this.authenticationService.setLogin(true);
  }

  public sendLogout() {
    this.authenticationService.logout();
    this.authenticationService.setLogin(false);
  }
}
