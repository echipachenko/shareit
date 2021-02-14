import axios from 'axios';
import { IShareImage } from '@/shared/model/share-image.model';

const baseApiUrl = '/api/share/image';

export default class ShareImageService {
  public uploadImage(file: File): Promise<IShareImage> {
    let formData = new FormData();
    formData.append('file', file);

    return new Promise<IShareImage>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, formData, {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        })
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public getImageByUid(uid: string): Promise<IShareImage> {
    return new Promise<IShareImage>((resolve, reject) => {
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
