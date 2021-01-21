import axios from 'axios';
import Component from 'vue-class-component';
import { Vue } from 'vue-property-decorator';

@Component({
  watch: {
    $route() {
      this.$root.$emit('bv::hide::modal', 'share-code-page');
    },
  },
})
export default class ShareCode extends Vue {
  public code = null;
  public uid = null;

  public share(): void {
    const data = { code: this.code };
    axios.post('api/share-codes', data).then(result => {
      this.uid = result.data.uid;
    });
  }

  public copyAndClose(): void {
    console.warn('Implement me!');
    this.$root.$emit('bv::hide::modal', 'share-code-page');
  }
}
