import Vue from 'vue';
import axios from 'axios';
import { IShareCode } from '@/shared/model/share-code.model';

const baseApiUrl = '/api/share/code';

export default class ShareCodeService {
  public openShareCodeWindow(instance: Vue): void {
    instance.$emit('bv::show::modal', 'share-code-page');
  }

  public shareCode(code: string, recaptchaUserResponse: string): Promise<IShareCode> {
    return new Promise<IShareCode>((resolve, reject) => {
      axios
        .post(baseApiUrl, { code: code, recaptchaUserResponse: recaptchaUserResponse })
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

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
