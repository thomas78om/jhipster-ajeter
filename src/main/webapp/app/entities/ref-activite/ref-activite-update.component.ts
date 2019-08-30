import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IRefActivite, RefActivite } from 'app/shared/model/ref-activite.model';
import { RefActiviteService } from './ref-activite.service';

@Component({
  selector: 'jhi-ref-activite-update',
  templateUrl: './ref-activite-update.component.html'
})
export class RefActiviteUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    rACCode: [],
    rACLibCourt: [],
    rACLibLong: [],
    rACComm: []
  });

  constructor(protected refActiviteService: RefActiviteService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ refActivite }) => {
      this.updateForm(refActivite);
    });
  }

  updateForm(refActivite: IRefActivite) {
    this.editForm.patchValue({
      id: refActivite.id,
      rACCode: refActivite.rACCode,
      rACLibCourt: refActivite.rACLibCourt,
      rACLibLong: refActivite.rACLibLong,
      rACComm: refActivite.rACComm
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const refActivite = this.createFromForm();
    if (refActivite.id !== undefined) {
      this.subscribeToSaveResponse(this.refActiviteService.update(refActivite));
    } else {
      this.subscribeToSaveResponse(this.refActiviteService.create(refActivite));
    }
  }

  private createFromForm(): IRefActivite {
    return {
      ...new RefActivite(),
      id: this.editForm.get(['id']).value,
      rACCode: this.editForm.get(['rACCode']).value,
      rACLibCourt: this.editForm.get(['rACLibCourt']).value,
      rACLibLong: this.editForm.get(['rACLibLong']).value,
      rACComm: this.editForm.get(['rACComm']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRefActivite>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
