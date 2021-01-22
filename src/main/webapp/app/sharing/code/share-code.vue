<template>
  <div class="modal-body">
    <b-row>
      <b-col>
        <div v-if="uid != null">
          <b-alert show variant="success">
            Ваш код успіно завантажено. UUID: <strong>{{ uid }}</strong>
          </b-alert>

          <b-button variant="primary" class="mt-2" v-on:click="copyAndClose">
            <font-awesome-icon icon="clipboard"/>
            Скопіювати URL
          </b-button>

          <router-link :to="{name: 'ShareCodeView', params: {uid: uid}}" tag="button" class="btn btn-success mt-2">
            > Перейти до коду
          </router-link>
        </div>

        <div v-if="uid === null">
          <b-form v-on:submit.prevent="share()">
            <b-form-textarea placeholder="Вставте код яким хочете поділитись..."
                             rows="10"
                             max-rows="20"
                             v-model="code">
            </b-form-textarea>
            <b-button variant="success" type="submit" class="mt-1">Поділитись</b-button>
          </b-form>
        </div>
      </b-col>
    </b-row>
  </div>
</template>
<script lang="ts" src="./share-code.component.ts">
</script>
