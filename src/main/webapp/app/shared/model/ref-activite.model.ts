import { IActeGestionDelai } from 'app/shared/model/acte-gestion-delai.model';

export interface IRefActivite {
  id?: number;
  rACCode?: string;
  rACLibCourt?: string;
  rACLibLong?: string;
  rACComm?: string;
  acteGestionDelais?: IActeGestionDelai[];
}

export class RefActivite implements IRefActivite {
  constructor(
    public id?: number,
    public rACCode?: string,
    public rACLibCourt?: string,
    public rACLibLong?: string,
    public rACComm?: string,
    public acteGestionDelais?: IActeGestionDelai[]
  ) {}
}
