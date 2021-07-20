import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserRoles } from '../model/userRoles';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  public loggedIn: boolean = false;
  public name: String = '';

  constructor(private authenticationService: AuthService) {
    this.authenticationService.setLogin(false);
    this.authenticationService.loggedIn$.subscribe((data) => {
      this.loggedIn = data;
    });
  }

  ngOnInit() {}

  public sendLogin(loginForm: NgForm) {
    this.authenticationService.login(loginForm.value).subscribe(
      (response: UserRoles) => {
        // console.log(response);
        this.authenticationService.setLogin(true);
        this.authenticationService.setUserAndRoles(response);
        this.name = response.username;
        loginForm.reset();
      },
      (error: HttpErrorResponse) => alert(error.message + '\n Could not log in')
    );
    // this.authenticationService.setLogin(true);
  }

  public sendLogout() {
    this.name = '';
    this.authenticationService.logout();
  }
}
