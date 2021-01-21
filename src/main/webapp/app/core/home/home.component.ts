import Component from 'vue-class-component';
import { Inject, Vue } from 'vue-property-decorator';
import LoginService from '@/account/login.service';
import ShareCodeService from '@/sharing/code/share-code.service';
import ShareCode from '@/sharing/code/share-code.vue';

@Component({
  components: {
    'share-code': ShareCode,
  },
})
export default class Home extends Vue {
  @Inject('loginService')
  private loginService: () => LoginService;

  @Inject('shareCodeService')
  private shareCodeService: () => ShareCodeService;

  public openLogin(): void {
    this.loginService().openLogin((<any>this).$root);
  }

  public openShareCodeWindow(): void {
    this.shareCodeService().openShareCodeWindow((<any>this).$root);
  }

  public get authenticated(): boolean {
    return this.$store.getters.authenticated;
  }

  public get username(): string {
    return this.$store.getters.account ? this.$store.getters.account.login : '';
  }
}
