import { IRefGroupeActivite } from 'app/shared/model/ref-groupe-activite.model';
import { IRefActeGestion } from 'app/shared/model/ref-acte-gestion.model';
import { IRefActivite } from 'app/shared/model/ref-activite.model';

export interface IActeGestionDelai {
  id?: number;
  rACCode?: string;
  rAGCode?: string;
  rGACode?: string;
  aGDDelai?: number;
  aGDAttente?: number;
  aGDComm?: string;
  refGroupeActivite?: IRefGroupeActivite;
  refActeGestion?: IRefActeGestion;
  refActivite?: IRefActivite;
}

export class ActeGestionDelai implements IActeGestionDelai {
  constructor(
    public id?: number,
    public rACCode?: string,
    public rAGCode?: string,
    public rGACode?: string,
    public aGDDelai?: number,
    public aGDAttente?: number,
    public aGDComm?: string,
    public refGroupeActivite?: IRefGroupeActivite,
    public refActeGestion?: IRefActeGestion,
    public refActivite?: IRefActivite
  ) {}
}
