import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AjeterSharedModule } from 'app/shared';
import {
  AuditComponent,
  AuditDetailComponent,
  AuditUpdateComponent,
  AuditDeletePopupComponent,
  AuditDeleteDialogComponent,
  auditRoute,
  auditPopupRoute
} from './';

const ENTITY_STATES = [...auditRoute, ...auditPopupRoute];

@NgModule({
  imports: [AjeterSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [AuditComponent, AuditDetailComponent, AuditUpdateComponent, AuditDeleteDialogComponent, AuditDeletePopupComponent],
  entryComponents: [AuditComponent, AuditUpdateComponent, AuditDeleteDialogComponent, AuditDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AjeterAuditModule {}