import { IActeGestionDelai } from 'app/shared/model/acte-gestion-delai.model';

export interface IRefActeGestion {
  id?: number;
  rAGCode?: string;
  rAGLibCourt?: string;
  rAGLibLong?: string;
  rAGComm?: string;
  acteGestionDelais?: IActeGestionDelai[];
}

export class RefActeGestion implements IRefActeGestion {
  constructor(
    public id?: number,
    public rAGCode?: string,
    public rAGLibCourt?: string,
    public rAGLibLong?: string,
    public rAGComm?: string,
    public acteGestionDelais?: IActeGestionDelai[]
  ) {}
}
