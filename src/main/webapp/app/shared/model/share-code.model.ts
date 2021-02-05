export interface IShareCode {
  id?: bigint;
  userId?: bigint;
  uid?: string;
  created?: Date;
  expired?: Date;
  code?: string;
}

export class ShareCode implements IShareCode {
  constructor(
    public id?: bigint,
    public userId?: bigint,
    public uid?: string,
    public created?: Date,
    public expired?: Date,
    public code?: string
  ) {}
}
