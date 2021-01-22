import Component, { mixins } from 'vue-class-component';
import { Inject } from 'vue-property-decorator';
import ShareCodeViewService from '@/sharing/code/share-code-view.service';
import { IShareCode } from '@/shared/model/share-code.model';
import AlertService from '@/shared/alert/alert.service';
import AlertMixin from '@/shared/alert/alert.mixin';

@Component
export default class ShareCodeView extends mixins(AlertMixin) {
  @Inject('alertService') protected alertService: () => AlertService;
  public shareCode: IShareCode = null;

  @Inject('shareCodeViewService')
  private shareCodeViewService: () => ShareCodeViewService;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.uid) {
        vm.init(to.params.uid);
      }
    });
  }

  public init(uid: String): void {
    this.shareCodeViewService()
      .getByUid(uid)
      .then(res => {
        this.shareCode = res.data;
      });
  }

  public copyCodeToClipboard(): void {
    this.alertService().showAlert('В розробці!', 'danger');
    this.getAlertFromStore();
  }
}
