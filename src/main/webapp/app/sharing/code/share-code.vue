<template>
  <div class="modal-body">
    <b-row>
      <b-col>
       <b-form>
         <b-form-textarea placeholder="Вставке код яким хочете поділитись..."
                     rows="10"
                     max-rows="20">
         </b-form-textarea>
         <b-button variant="success" type="submit" class="mt-1">Поділитись</b-button>
       </b-form>
      </b-col>
    </b-row>
  </div>
</template>
<script lang="ts" src="./share-code.component.ts">
</script>