import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AjeterSharedModule } from 'app/shared';
import {
  ParamExportComponent,
  ParamExportDetailComponent,
  ParamExportUpdateComponent,
  ParamExportDeletePopupComponent,
  ParamExportDeleteDialogComponent,
  paramExportRoute,
  paramExportPopupRoute
} from './';

const ENTITY_STATES = [...paramExportRoute, ...paramExportPopupRoute];

@NgModule({
  imports: [AjeterSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ParamExportComponent,
    ParamExportDetailComponent,
    ParamExportUpdateComponent,
    ParamExportDeleteDialogComponent,
    ParamExportDeletePopupComponent
  ],
  entryComponents: [ParamExportComponent, ParamExportUpdateComponent, ParamExportDeleteDialogComponent, ParamExportDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AjeterParamExportModule {}
