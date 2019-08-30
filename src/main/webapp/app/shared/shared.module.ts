import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { AjeterSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';

@NgModule({
  imports: [AjeterSharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [AjeterSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AjeterSharedModule {
  static forRoot() {
    return {
      ngModule: AjeterSharedModule
    };
  }
}
