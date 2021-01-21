import Vue from 'vue';
import axios, { AxiosPromise } from 'axios';

export default class ShareCodeService {
  public openShareCodeWindow(instance: Vue): void {
    instance.$emit('bv::show::modal', 'share-code-page');
  }
}
