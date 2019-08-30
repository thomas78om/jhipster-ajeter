import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AjeterSharedModule } from 'app/shared';
import {
  RefActeGestionComponent,
  RefActeGestionDetailComponent,
  RefActeGestionUpdateComponent,
  RefActeGestionDeletePopupComponent,
  RefActeGestionDeleteDialogComponent,
  refActeGestionRoute,
  refActeGestionPopupRoute
} from './';

const ENTITY_STATES = [...refActeGestionRoute, ...refActeGestionPopupRoute];

@NgModule({
  imports: [AjeterSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    RefActeGestionComponent,
    RefActeGestionDetailComponent,
    RefActeGestionUpdateComponent,
    RefActeGestionDeleteDialogComponent,
    RefActeGestionDeletePopupComponent
  ],
  entryComponents: [
    RefActeGestionComponent,
    RefActeGestionUpdateComponent,
    RefActeGestionDeleteDialogComponent,
    RefActeGestionDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AjeterRefActeGestionModule {}