import Component from 'vue-class-component';
import { Inject, Vue } from 'vue-property-decorator';
import ShareCodeViewService from '@/sharing/code/share-code-view.service';
import { IShareCode } from '@/shared/model/share-code.model';

@Component
export default class ShareCodeView extends Vue {
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
}
