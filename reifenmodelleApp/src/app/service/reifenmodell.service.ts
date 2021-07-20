import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Reifenmodell } from '../model/reifenmodell';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class ReifenmodellService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(
    private http: HttpClient,
    private authenticationService: AuthService
  ) {}

  public getAllReifenmodellen(): Observable<Reifenmodell[]> {
    return this.http.get<Reifenmodell[]>(
      `${this.apiServerUrl}/reifenmodell/all`,
      { ...this.authenticationService.getAuth() }
    );
  }

  public addReifenmodell(reifenmodell: Reifenmodell): Observable<Reifenmodell> {
    return this.http.post<Reifenmodell>(
      `${this.apiServerUrl}/reifenmodell/add`,
      reifenmodell,
      { ...this.authenticationService.getAuth() }
    );
  }

  public updateReifenmodell(
    reifenmodell: Reifenmodell
  ): Observable<Reifenmodell> {
    return this.http.put<Reifenmodell>(
      `${this.apiServerUrl}/reifenmodell/update`,
      reifenmodell,
      { ...this.authenticationService.getAuth() }
    );
  }

  public deleteReifenmodell(
    reifenmodellId: number | undefined
  ): Observable<void> {
    return this.http.delete<void>(
      `${this.apiServerUrl}/reifenmodell/delete/${reifenmodellId}`,
      { ...this.authenticationService.getAuth() }
    );
  }
}
