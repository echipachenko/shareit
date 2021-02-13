import Component from 'vue-class-component';
import { Inject, Vue } from 'vue-property-decorator';
import LoginService from '@/account/login.service';
import ShareCodeService from '@/sharing/code/share-code.service';
import ShareCode from '@/sharing/code/share-code.vue';
import ShareImageService from '@/sharing/image/share-image.service';

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

  @Inject('shareImageService')
  private shareImageService: () => ShareImageService;

  $refs!: {
    imageUploadInput: HTMLFormElement;
    documentUploadInput: HTMLFormElement;
  };

  public openLogin(): void {
    this.loginService().openLogin((<any>this).$root);
  }

  public openShareCodeWindow(): void {
    this.shareCodeService().openShareCodeWindow((<any>this).$root);
  }

  public openUploadImageDialog(): void {
    this.$refs.imageUploadInput.click();
  }

  public openUploadDocumentDialog(): void {
    this.$refs.documentUploadInput.click();
  }

  public onImageFilePicked(): void {
    let file = this.$refs.imageUploadInput.files[0];
    this.shareImageService()
      .uploadImage(file)
      .then(r => this.$router.push({ name: 'ShareImageView', params: { uid: r.uid } }));
  }

  public onDocumentFilePicked(): void {}

  public get authenticated(): boolean {
    return this.$store.getters.authenticated;
  }

  public get username(): string {
    return this.$store.getters.account ? this.$store.getters.account.login : '';
  }
}
