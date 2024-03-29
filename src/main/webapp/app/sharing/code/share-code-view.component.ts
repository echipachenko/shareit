import Component, { mixins } from 'vue-class-component';
import { Inject } from 'vue-property-decorator';
import ShareCodeService from '@/sharing/code/share-code.service';
import { IShareCode } from '@/shared/model/share-code.model';
import AlertService from '@/shared/alert/alert.service';
import AlertMixin from '@/shared/alert/alert.mixin';

@Component
export default class ShareCodeView extends mixins(AlertMixin) {
  @Inject('alertService') protected alertService: () => AlertService;
  public shareCode: IShareCode = null;

  @Inject('shareCodeService')
  private shareCodeService: () => ShareCodeService;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.uid) {
        vm.init(to.params.uid);
      }
    });
  }

  public init(uid: string): void {
    this.shareCodeService()
      .getByUid(uid)
      .then(res => {
        this.shareCode = res;
      })
      .catch(err => {
        if (err.response.status === 404) {
          this.$router.push({ name: 'NotFound' });
        }
      });
  }

  public copyCodeToClipboard(): void {
    let data = this.shareCode.code;
    navigator.clipboard
      .writeText(data)
      .then(() => {
        this.alertService().showAlert('Скопійовано!', 'success');
        this.getAlertFromStore();
      })
      .catch(err => {
        console.log('Something went wrong', err);
        this.alertService().showAlert('Виникла помилка :(...' + err, 'danger');
        this.getAlertFromStore();
      });
  }
}
