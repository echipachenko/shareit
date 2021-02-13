<template>
  <b-row>
    <b-col v-if="shareImage">
      <b>UID: {{ shareImage.uid }}</b>
      <p>Відобрадення зображення буде реалізовано найближчим часом</p>
    </b-col>
  </b-row>
</template>
<style scoped>
</style>
<script lang="ts">
import Component from 'vue-class-component';
import {Inject, Vue} from 'vue-property-decorator';
import ShareImageService from "./share-image.service";
import {IShareImage} from "@/shared/model/share-image.model";

@Component
export default class ShareImageView extends Vue {
  public shareImage: IShareImage = null;

  @Inject('shareImageService')
  private shareImageService: () => ShareImageService;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.uid) {
        vm.init(to.params.uid);
      }
    });
  }

  public init(uid: string): void {
    this.shareImageService()
      .getImageByUid(uid)
      .then(res => {
        this.shareImage = res;
      }).catch((err) => {
      if (err.response.status === 404) {
        this.$router.push({name: 'NotFound'});
      }
    })
  }
}
</script>
