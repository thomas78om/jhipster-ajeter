import { Moment } from 'moment';

export interface IAudit {
  id?: number;
  aUDId?: number;
  aUDUtilisateur?: string;
  aUDDescription?: string;
  aUDDatetime?: Moment;
}

export class Audit implements IAudit {
  constructor(
    public id?: number,
    public aUDId?: number,
    public aUDUtilisateur?: string,
    public aUDDescription?: string,
    public aUDDatetime?: Moment
  ) {}
}
