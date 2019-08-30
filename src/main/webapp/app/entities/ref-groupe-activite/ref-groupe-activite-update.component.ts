import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IRefGroupeActivite, RefGroupeActivite } from 'app/shared/model/ref-groupe-activite.model';
import { RefGroupeActiviteService } from './ref-groupe-activite.service';

@Component({
  selector: 'jhi-ref-groupe-activite-update',
  templateUrl: './ref-groupe-activite-update.component.html'
})
export class RefGroupeActiviteUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    rGACode: [],
    rGALibCourt: [],
    rGALibLong: [],
    rGAComm: []
  });

  constructor(
    protected refGroupeActiviteService: RefGroupeActiviteService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ refGroupeActivite }) => {
      this.updateForm(refGroupeActivite);
    });
  }

  updateForm(refGroupeActivite: IRefGroupeActivite) {
    this.editForm.patchValue({
      id: refGroupeActivite.id,
      rGACode: refGroupeActivite.rGACode,
      rGALibCourt: refGroupeActivite.rGALibCourt,
      rGALibLong: refGroupeActivite.rGALibLong,
      rGAComm: refGroupeActivite.rGAComm
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const refGroupeActivite = this.createFromForm();
    if (refGroupeActivite.id !== undefined) {
      this.subscribeToSaveResponse(this.refGroupeActiviteService.update(refGroupeActivite));
    } else {
      this.subscribeToSaveResponse(this.refGroupeActiviteService.create(refGroupeActivite));
    }
  }

  private createFromForm(): IRefGroupeActivite {
    return {
      ...new RefGroupeActivite(),
      id: this.editForm.get(['id']).value,
      rGACode: this.editForm.get(['rGACode']).value,
      rGALibCourt: this.editForm.get(['rGALibCourt']).value,
      rGALibLong: this.editForm.get(['rGALibLong']).value,
      rGAComm: this.editForm.get(['rGAComm']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRefGroupeActivite>>) {
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
