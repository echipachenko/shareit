import Component, { mixins } from 'vue-class-component';
import AlertMixin from '@/shared/alert/alert.mixin';
import ShareCodeService from '@/sharing/code/share-code.service';
import { Inject } from 'vue-property-decorator';
import { required } from 'vuelidate/lib/validators';
import VueRecaptcha from 'vue-recaptcha';
import { RECAPTCHA_ENABLED, RECAPTCHA_SITE_KEY } from '@/constants';

const validations: any = {
  code: { required },
};

@Component({
  watch: {
    $route() {
      this.$root.$emit('bv::hide::modal', 'share-code-page');
    },
  },
  validations,
  components: {
    VueRecaptcha,
  },
})
export default class ShareCode extends mixins(AlertMixin) {
  public recaptchaEnabled = RECAPTCHA_ENABLED;
  public recaptchaSiteKey = RECAPTCHA_SITE_KEY;
  public code: String = null;
  public uid: String = null;
  public recaptchaUserResponse: String = null;

  @Inject('shareCodeService')
  private shareCodeService: () => ShareCodeService;

  public recaptcha(response: String): void {
    this.recaptchaUserResponse = response;
  }

  public share(): void {
    this.shareCodeService()
      .shareCode(this.code, this.recaptchaUserResponse)
      .then(result => {
        this.uid = result.uid;
      })
      .catch(err => {
        if (err.response.status === 400 && err.response.data.message) {
          const msg = this.$t(err.response.data.message);
          this.alertService().showAlert(msg, 'danger');
          this.getAlertFromStore();
        }
      });
  }

  public copyAndClose(): void {
    this.alertService().showAlert('В розробці!', 'danger');
    this.getAlertFromStore();
    /*setTimeout(() => {
      this.$root.$emit('bv::hide::modal', 'share-code-page');
    }, 2500);*/
  }
}
