<template>
  <b-container fluid="lg">
    <b-alert show variant="danger" v-if="errorNotFound">
      Код було видалено, або не існує.
    </b-alert>

    <div v-if="shareCode">
      <b-row>
        <b-col>
          <b-alert :show="dismissCountDown"
                   dismissible
                   :variant="alertType"
                   @dismissed="dismissCountDown=0"
                   @dismiss-count-down="countDownChanged">
            {{ alertMessage }}
          </b-alert>

          <b-button variant="success" class="my-2" size="sm" v-on:click="copyCodeToClipboard">
            <font-awesome-icon icon="copy"></font-awesome-icon>
            Копіювати код
          </b-button>
          <div>
            <b-badge>UID</b-badge>
            {{ shareCode.uid }}
          </div>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <highlightjs autodetect style="max-height: 2000px" :code="shareCode.code"/>
          <div>
            <b-badge>Створено</b-badge>
            {{ shareCode.created | formatDate }}
          </div>
          <div>
            <b-badge>Буде видалено</b-badge>
            {{ shareCode.expired | formatDate }}
          </div>
        </b-col>
      </b-row>
    </div>
  </b-container>
</template>
<style scoped>
.hljs {
  white-space: pre;
  overflow: auto;
}
</style>
<script lang="ts" src="./share-code-view.component.ts">
</script>
