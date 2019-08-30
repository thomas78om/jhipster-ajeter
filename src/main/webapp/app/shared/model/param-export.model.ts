import { Moment } from 'moment';

export interface IParamExport {
  id?: number;
  pEXPublish?: number;
  pEXDtlastexport?: Moment;
}

export class ParamExport implements IParamExport {
  constructor(public id?: number, public pEXPublish?: number, public pEXDtlastexport?: Moment) {}
}
