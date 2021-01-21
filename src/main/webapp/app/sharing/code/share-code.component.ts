import axios from 'axios';
import Component from 'vue-class-component';
import { Vue, Inject } from 'vue-property-decorator';
@Component({
  watch: {
    $route() {
      this.$root.$emit('bv::hide::modal', 'share-code-page');
    },
  },
})
export default class ShareCode extends Vue {
  public share(): void {}
}
