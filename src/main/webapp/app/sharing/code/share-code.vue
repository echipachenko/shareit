<template>
  <div class="modal-body">
    <b-row>
      <b-col>
        <b-alert show variant="success" v-if="uid != null">
          <strong>{{ uid }}</strong>
        </b-alert>

        <b-form v-on:submit.prevent="share()" v-if="uid === null">
          <b-form-textarea placeholder="Вставте код яким хочете поділитись..."
                           rows="10"
                           max-rows="20"
                           v-model="code">
          </b-form-textarea>
          <b-button variant="success" type="submit" class="mt-1">Поділитись</b-button>
        </b-form>
      </b-col>
    </b-row>
  </div>
</template>
<script lang="ts" src="./share-code.component.ts">
</script>