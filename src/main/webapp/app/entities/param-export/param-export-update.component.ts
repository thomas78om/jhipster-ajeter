import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { IParamExport, ParamExport } from 'app/shared/model/param-export.model';
import { ParamExportService } from './param-export.service';

@Component({
  selector: 'jhi-param-export-update',
  templateUrl: './param-export-update.component.html'
})
export class ParamExportUpdateComponent implements OnInit {
  isSaving: boolean;
  pEXDtlastexportDp: any;

  editForm = this.fb.group({
    id: [],
    pEXPublish: [],
    pEXDtlastexport: []
  });

  constructor(protected paramExportService: ParamExportService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ paramExport }) => {
      this.updateForm(paramExport);
    });
  }

  updateForm(paramExport: IParamExport) {
    this.editForm.patchValue({
      id: paramExport.id,
      pEXPublish: paramExport.pEXPublish,
      pEXDtlastexport: paramExport.pEXDtlastexport
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const paramExport = this.createFromForm();
    if (paramExport.id !== undefined) {
      this.subscribeToSaveResponse(this.paramExportService.update(paramExport));
    } else {
      this.subscribeToSaveResponse(this.paramExportService.create(paramExport));
    }
  }

  private createFromForm(): IParamExport {
    return {
      ...new ParamExport(),
      id: this.editForm.get(['id']).value,
      pEXPublish: this.editForm.get(['pEXPublish']).value,
      pEXDtlastexport: this.editForm.get(['pEXDtlastexport']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IParamExport>>) {
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
