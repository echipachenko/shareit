<template>
  <div class="modal-body">
    <b-row>
      <b-col>
        <b-alert :show="dismissCountDown"
                 dismissible
                 :variant="alertType"
                 @dismissed="dismissCountDown=0"
                 @dismiss-count-down="countDownChanged">
          {{ alertMessage }}
        </b-alert>

        <div v-if="uid != null">
          <b-alert show variant="success">
            Ваш код успіно завантажено. UUID: <strong>{{ uid }}</strong>
          </b-alert>

          <b-button variant="primary" class="mt-2" v-on:click="copyToClipboard">
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
                             :class="{'valid': !$v.code.$invalid, 'invalid': $v.code.$invalid }"
                             v-model="$v.code.$model">
            </b-form-textarea>

            <div v-if="$v.code.$anyDirty && $v.code.$invalid">
              <small class="form-text text-danger" v-if="!$v.code.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>

            <vue-recaptcha v-if="recaptchaEnabled" v-bind:sitekey="recaptchaSiteKey" :loadRecaptchaScript="true" @verify="recaptcha">
            </vue-recaptcha>

            <b-button variant="success" type="submit" class="mt-1"
                      :disabled="$v.code.$invalid">Поділитись</b-button>
          </b-form>
        </div>
      </b-col>
    </b-row>
  </div>
</template>
<script lang="ts" src="./share-code.component.ts">
</script>
