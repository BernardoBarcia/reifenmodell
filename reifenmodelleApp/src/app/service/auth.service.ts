import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { environment } from 'src/environments/environment';
import { tap, shareReplay } from 'rxjs/operators';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiServerUrl = environment.apiBaseUrl;

  loggedIn$: Observable<boolean>;
  private loggedInSubject = new Subject<boolean>();

  constructor(private http: HttpClient) {
    this.loggedIn$ = this.loggedInSubject.asObservable();
  }

  setLogin(data: boolean) {
    this.loggedInSubject.next(data);
  }

  login(user: User): Observable<any> {
    console.log(user.username, user.password);
    return this.http.post(`${this.apiServerUrl}/login`, user);
  }

  private setSession(authResult: any) {
    localStorage.setItem('user_token', authResult);
  }

  logout() {
    localStorage.removeItem('user_token');
  }
}
