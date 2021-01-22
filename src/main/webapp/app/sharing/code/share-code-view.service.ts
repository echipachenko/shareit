import axios from 'axios';

export default class ShareCodeViewService {
  public getByUid(uid: String): Promise<any> {
    return axios.get(`/api/share-codes/${uid}`);
  }
}
