<template>
  <b-container fluid="lg">
    <b-row>
      <b-col v-if="shareImage">
        <div>
          <b-badge>UID</b-badge>
          {{ shareImage.uid }}
        </div>
        <div>
          <b-badge>Назва</b-badge>
          {{ shareImage.fileName }}
        </div>
        <b-img fluid thumbnail center v-bind:src="`/api/share/image/${shareImage.uid}/image`"></b-img>
        <div>
          <b-badge>Створено</b-badge>
          {{ shareImage.created | formatDate }}
        </div>
        <div>
          <b-badge>Буде видалено</b-badge>
          {{ shareImage.expired | formatDate }}
        </div>
      </b-col>
    </b-row>
  </b-container>
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
