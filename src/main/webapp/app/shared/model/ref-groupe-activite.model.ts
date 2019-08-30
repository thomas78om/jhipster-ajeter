import { IActeGestionDelai } from 'app/shared/model/acte-gestion-delai.model';

export interface IRefGroupeActivite {
  id?: number;
  rGACode?: string;
  rGALibCourt?: string;
  rGALibLong?: string;
  rGAComm?: string;
  acteGestionDelais?: IActeGestionDelai[];
}

export class RefGroupeActivite implements IRefGroupeActivite {
  constructor(
    public id?: number,
    public rGACode?: string,
    public rGALibCourt?: string,
    public rGALibLong?: string,
    public rGAComm?: string,
    public acteGestionDelais?: IActeGestionDelai[]
  ) {}
}
