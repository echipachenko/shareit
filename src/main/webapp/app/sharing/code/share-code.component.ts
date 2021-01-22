import axios from 'axios';
import Component, { mixins } from 'vue-class-component';
import AlertMixin from '@/shared/alert/alert.mixin';

@Component({
  watch: {
    $route() {
      this.$root.$emit('bv::hide::modal', 'share-code-page');
    },
  },
})
export default class ShareCode extends mixins(AlertMixin) {
  public code = null;
  public uid = null;

  public share(): void {
    const data = { code: this.code };
    axios.post('api/share-codes', data).then(result => {
      this.uid = result.data.uid;
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
