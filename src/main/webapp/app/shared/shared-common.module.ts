import { NgModule } from '@angular/core';

import { AjeterSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
  imports: [AjeterSharedLibsModule],
  declarations: [JhiAlertComponent, JhiAlertErrorComponent],
  exports: [AjeterSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class AjeterSharedCommonModule {}
