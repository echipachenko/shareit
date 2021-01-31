export interface IShareImage {
  id?: number;
  userId?: number;
  uid?: string;
  created?: Date;
  fileName?: string;
  expired?: Date;
}

export class ShareImage implements IShareImage {
  constructor(
    public id?: number,
    public userId?: number,
    public uid?: string,
    public created?: Date,
    public fileName?: string,
    public expired?: Date
  ) {}
}
