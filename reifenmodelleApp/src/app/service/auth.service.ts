import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { environment } from 'src/environments/environment';
import { tap, shareReplay } from 'rxjs/operators';
import { User } from '../model/user';
import { UserRoles } from '../model/userRoles';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiServerUrl = environment.apiBaseUrl;

  loggedIn$: Observable<boolean>;
  private loggedInSubject = new Subject<boolean>();
  private headers: HttpHeaders | undefined;
  private userInfo: UserRoles | undefined;

  constructor(private http: HttpClient) {
    this.loggedIn$ = this.loggedInSubject.asObservable();
  }

  setLogin(data: boolean) {
    this.loggedInSubject.next(data);
  }

  login(user: User): Observable<any> {
    this.headers = new HttpHeaders({
      Authorization: 'Basic ' + btoa(user.username + ':' + user.password),
    });
    return this.http.post<UserRoles>(
      `${this.apiServerUrl}/authenticate`,
      user,
      {
        headers: this.headers,
      }
    );
  }

  getAuth(): HttpHeaders | undefined {
    if (this.loggedIn$) return this.headers;
    return undefined;
  }

  logout() {
    this.headers = undefined;
    this.setLogin(false);
    this.userInfo = undefined;
  }

  setUserAndRoles(userInfo: UserRoles) {
    this.userInfo = userInfo;
  }

  getUserAndRoles(): UserRoles | undefined {
    return this.userInfo;
  }
}
