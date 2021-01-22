<template>
  <div v-if="shareCode">
    <b-row>
      <b-col>
        <b-button variant="success" size="sm" class="my-2">
          <font-awesome-icon icon="copy"></font-awesome-icon>
          Copy
        </b-button>
      </b-col>
    </b-row>

    <b-row>
      <b-col>
        <highlightjs autodetect style="max-height: 2000px" :code="shareCode.code"/>
      </b-col>
    </b-row>

    <b-row>
      <b-col cols="6">
        <table class="table table-sm table-borderless">
          <tr>
            <td>UUID</td>
            <td>{{ shareCode.uid }}</td>
          </tr>
          <tr>
            <td>Створено</td>
            <td>{{ shareCode.created }}</td>
          </tr>
          <tr>
            <td>Доступний до</td>
            <td>{{ shareCode.expired }}</td>
          </tr>
        </table>
      </b-col>
    </b-row>
  </div>

</template>
<style scoped>
.hljs {
  white-space: pre;
  overflow: auto;
}
</style>
<script lang="ts" src="./share-code-view.component.ts">
</script>
