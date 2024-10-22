<script setup lang="ts">
import Card from 'primevue/card'
import Button from 'primevue/button'
import Dropdown from 'primevue/dropdown'
import { useFormsStore } from '@/stores/formStore'
import { useRouter } from 'vue-router'
import { formatDateToDateAndTime } from '@/utils/date-utils'
import Skeleton from 'primevue/skeleton'

const formStore = useFormsStore()
const router = useRouter()
formStore.fetchAllForms()

const openSpecificForm = (processId: string) => {
  router.push('/form/fill/' + processId)
}
const updatedAt = (date: string) => {
  return formatDateToDateAndTime(date)
}
</script>

<template>
  <div class="pageStructure">
    <h1>Available Forms</h1>

    <div class="cardContainer" v-if="!formStore.getAllFormsLoading && !formStore.getAllFormsError">
      <div v-for="form in formStore.allForms" :key="form.id">
        <Card class="card">
          <template #title>{{ form.metaData?.title }}</template>
          <template #content> {{ updatedAt(form.metaData.updatedAt) }} </template>
          <template #footer>
            <Button @click="openSpecificForm(form.id)"> View form</Button>
          </template>
        </Card>
      </div>
    </div>

    <div
      class="cardContainer"
      v-else-if="formStore.getAllFormsLoading && !formStore.getAllFormsError"
    >
      <div v-for="n in 15">
        <Skeleton class="p-skeleton" height="10rem"></Skeleton>
      </div>
    </div>
    <div v-else-if="formStore.getAllFormsError"><h1>Error occured!</h1></div>
  </div>
</template>
<style scoped>
.pageStructure {
  display: flex;
  flex-direction: column;
  text-align: center;
  margin-bottom: 1rem;
  width: 100%;
}
.cardContainer {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  grid-auto-rows: 1fr;
  gap: 2rem;
}
.card {
  height: 100%;
  overflow-wrap: anywhere;
}
</style>
