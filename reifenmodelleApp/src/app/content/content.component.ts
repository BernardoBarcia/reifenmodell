import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Reifenmodell } from '../model/reifenmodell';
import { ReifenmodellService } from '../service/reifenmodell.service';
import * as _ from 'lodash';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css'],
})
export class ContentComponent implements OnInit {
  public reifenmodellen: Reifenmodell[] = [];
  public deleteReifen: Reifenmodell | undefined;
  public updateReifen!: Reifenmodell | undefined;
  private selectedSort: any;
  public loggedin: boolean = false;

  constructor(
    private reifenmodellService: ReifenmodellService,
    private loggingService: AuthService
  ) {
    this.loggingService.loggedIn$.subscribe((data) => {
      this.loggedin = data;
      if (this.loggedin) {
        this.getAllReifenmodellen();
        // this.sampleInfo();
      } else {
        this.reifenmodellen.length = 0;
      }
    });
  }

  ngOnInit() {
    // Code to do on init
  }

  public checkAdminRole() {
    return this.loggingService
      .getUserAndRoles()
      ?.roles.some((e) => e === 'admin');
  }

  public getAllReifenmodellen(): void {
    this.reifenmodellService.getAllReifenmodellen().subscribe(
      (response: Reifenmodell[]) => (this.reifenmodellen = response),
      (error: HttpErrorResponse) => alert(error)
    );
  }

  public onAddReifenmodell(addForm: NgForm): void {
    console.log(addForm.value);
    document.getElementById('closeAddReifen')?.click();
    this.reifenmodellService.addReifenmodell(addForm.value).subscribe(
      (response: Reifenmodell) => {
        console.log(response);
        this.getAllReifenmodellen();
      },
      (error: HttpErrorResponse) => alert(error.message)
    );
    addForm.reset();
  }

  public onUpdateReifenmodell(reifenmodell: Reifenmodell): void {
    console.log(reifenmodell);
    document.getElementById('closeUpdateReifen')?.click();
    this.reifenmodellService.updateReifenmodell(reifenmodell).subscribe(
      (response: Reifenmodell) => {
        console.log(response);
        this.getAllReifenmodellen();
      },
      (error: HttpErrorResponse) => alert(error.message)
    );
  }

  public onDeleteReifenmodell(reifenmodellId: number | undefined): void {
    console.log(reifenmodellId);
    document.getElementById('closeDeleteReifen')?.click();
    this.reifenmodellService.deleteReifenmodell(reifenmodellId).subscribe(
      () => {
        this.getAllReifenmodellen();
      },
      (error: HttpErrorResponse) => alert(error.message)
    );
  }
  public onOpenModal(
    reifenmodell: Reifenmodell | undefined,
    mode: string
  ): void {
    if (mode === 'add') {
    }
    if (mode === 'edit') {
      this.updateReifen = reifenmodell;
    }
    if (mode === 'delete') {
      this.deleteReifen = reifenmodell;
    }
  }

  public sortReifen(
    attribute: string,
    descending: boolean,
    selected: any = null
  ) {
    this.selectedSort?.classList.remove('activeSort');
    this.reifenmodellen = _.sortBy(this.reifenmodellen, attribute);
    if (descending) this.reifenmodellen.reverse();
    this.selectedSort = selected.target;
    //console.log(this.selectedSort);
    this.selectedSort.classList.add('activeSort');
  }

  private sampleInfo() {
    this.reifenmodellen.push({
      id: 12,
      breite: 30,
      hoeheZuBreiteVerhaeltnis: 40,
      reifenbauart: 'R',
      durchmesser: 50,
      imageURL:
        'https://images.efulfilment.de/get_image/?t=FEB47DCDF3DAEECB0FEFCDFB6FA59875',
    });
    this.reifenmodellen.push({
      id: 24,
      breite: 35,
      hoeheZuBreiteVerhaeltnis: 50,
      reifenbauart: 'B',
      durchmesser: 60,
      imageURL: '',
    });
    this.reifenmodellen.push({
      id: 2,
      breite: 44,
      hoeheZuBreiteVerhaeltnis: 55,
      reifenbauart: 'D',
      durchmesser: 66,
      imageURL: '',
    });
    this.reifenmodellen.push({
      id: 6,
      breite: 3,
      hoeheZuBreiteVerhaeltnis: 7,
      reifenbauart: 'R',
      durchmesser: 90,
      imageURL: '',
    });
  }
}
