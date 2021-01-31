<template>
  <b-container>

    <b-row align-h="center">
      <b-col>
        <div class="text-center">
          <h3 class="m-4">Поділіться будь чим просто зараз!</h3>

          <b-button-group vertical class="text-left">
            <b-button variant="primary" size="lg" class="m-1" v-on:click="openShareCodeWindow">
              <font-awesome-icon icon="code"/>
              Поділитись Кодом
            </b-button>

            <b-button variant="primary" size="lg" class="m-1" disabled v-on:click="openUploadImageDialog">
              <font-awesome-icon icon="file-image"/>
              Поділитись Зображенням
            </b-button>

            <b-button variant="primary" size="lg" class="m-1" disabled v-on:click="openUploadDocumentDialog">
              <font-awesome-icon icon="file-word"/>
              Поділитись Документом
            </b-button>
          </b-button-group>

          <input
            type="file"
            style="display: none"
            ref="imageUploadInput"
            accept="image/*"
            @change="onImageFilePicked"/>

          <input
            type="file"
            style="display: none"
            ref="documentUploadInput"
            accept=".doc,.docx,.xls,.xlsx,.txt,.csv,.pdf"
            @change="onDocumentFilePicked"/>
        </div>
      </b-col>
    </b-row>

    <b-row align-h="center">
      <b-col sm="10" md="10" lg="8" align-self="center">
        <div class="text-center">
          <p class="m-4 text-muted">Всі данні завантаженні на наш сервер зберігаються протягом 24х годин.
            Якщо Ви бажаєте зберігати дані більше 24х годин - пройдіть реєстрацію на нашому сайті.
            Після завантаження даних, ви отримуєте тимчасовий URL який доступний лише Вам (або людям, яким Ви вишлете URL).
          </p>
        </div>
      </b-col>
    </b-row>

    <b-modal id="share-code-page" hide-footer lazy>
      <span slot="modal-title" id="share-code-title">Поділитись кодом</span>
      <share-code></share-code>
    </b-modal>
  </b-container>
</template>

<script lang="ts" src="./home.component.ts">
</script>

<style>

</style>
