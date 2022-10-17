import { Component } from '@angular/core';
import {ComputerService} from './computer/computer.service';
import {finalize} from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  error: string = "";
  odds: number | null = null;
  loading: boolean = false;


  constructor(private readonly computerService: ComputerService) {
  }

  uploadFile(files: FileList): void {
    this.odds = null;

    if (files.length > 1) {
      this.error = "Only one communication file at time allowed";
    } else {
      this.error = "";
      this.loading = true;
      this.computerService.uploadCommunications(files[0])
        .pipe(finalize(() => this.loading = false))
        .subscribe({
            next: odds => this.odds = 100 * odds,
            error: error => this.error = error.message,
          });
    }
  }

  onFileChange($event: any): void {
    this.uploadFile($event.target.files);
  }

}
