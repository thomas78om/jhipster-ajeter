import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { IAudit, Audit } from 'app/shared/model/audit.model';
import { AuditService } from './audit.service';

@Component({
  selector: 'jhi-audit-update',
  templateUrl: './audit-update.component.html'
})
export class AuditUpdateComponent implements OnInit {
  isSaving: boolean;
  aUDDatetimeDp: any;

  editForm = this.fb.group({
    id: [],
    aUDId: [],
    aUDUtilisateur: [],
    aUDDescription: [],
    aUDDatetime: []
  });

  constructor(protected auditService: AuditService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ audit }) => {
      this.updateForm(audit);
    });
  }

  updateForm(audit: IAudit) {
    this.editForm.patchValue({
      id: audit.id,
      aUDId: audit.aUDId,
      aUDUtilisateur: audit.aUDUtilisateur,
      aUDDescription: audit.aUDDescription,
      aUDDatetime: audit.aUDDatetime
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const audit = this.createFromForm();
    if (audit.id !== undefined) {
      this.subscribeToSaveResponse(this.auditService.update(audit));
    } else {
      this.subscribeToSaveResponse(this.auditService.create(audit));
    }
  }

  private createFromForm(): IAudit {
    return {
      ...new Audit(),
      id: this.editForm.get(['id']).value,
      aUDId: this.editForm.get(['aUDId']).value,
      aUDUtilisateur: this.editForm.get(['aUDUtilisateur']).value,
      aUDDescription: this.editForm.get(['aUDDescription']).value,
      aUDDatetime: this.editForm.get(['aUDDatetime']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAudit>>) {
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
