import axios from 'axios';
import { IShareCode } from '@/shared/model/share-code.model';

const baseApiUrl = '/api/share-codes';

export default class ShareCodeViewService {
  public getByUid(uid: string): Promise<IShareCode> {
    return new Promise<IShareCode>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${uid}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
