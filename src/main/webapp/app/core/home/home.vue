<template>
  <b-container>

    <b-row align-h="center">
      <b-col>
        <div class="text-center">
          <h3 class="m-4">Поділіться будь чим просто зараз!</h3>
          <b-button variant="primary" size="lg" class="m-4">
            <font-awesome-icon icon="code"/>
            Поділитись Кодом
          </b-button>
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
  </b-container>
</template>

<script lang="ts" src="./home.component.ts">
</script>
