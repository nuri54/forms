<script async setup lang="ts">
import Card from 'primevue/card'
import Button from 'primevue/button'
import { useFormsStore } from '@/stores/formStore'
import { formatDateToDateAndTime } from '@/utils/date-utils'
import Skeleton from 'primevue/skeleton'
import { onMounted, ref } from 'vue'
import { useRouter } from 'vue-router'

const formStore = useFormsStore()
const router = useRouter()
const doneForms = ref()

const isLoading = ref(false)
const isError = ref(false)
onMounted(async () => {
  try {
    isError.value = false
    isLoading.value = true
    const response = ref(await fetch('/api/v1/processes/user/done/' + formStore.loggedInUser.id))
    doneForms.value = await response.value.json()
  } catch {
    isError.value = true
  } finally {
    isLoading.value = false
  }
})

const updatedAt = (date: string) => {
  return formatDateToDateAndTime(date)
}

const openDoneForm = (processId: string) => {
  router.push('/form/done/' + processId)
}
</script>

<template>
  <div class="pageStructure">
    <h1>Done</h1>
    <div class="cardContainer" v-if="!isLoading">
      <div v-for="form in doneForms" :key="form.id">
        <Card class="card">
          <template #title>{{ form.metaData?.title }}</template>
          <template #content> {{ updatedAt(form.metaData.updatedAt) }} </template>
          <template #footer>
            <Button @click="openDoneForm(form.id)">View form</Button>
          </template>
        </Card>
      </div>
    </div>
    <div class="cardContainer" v-else-if="isLoading">
      <div v-for="n in 1">
        <Skeleton class="p-skeleton" height="10rem"></Skeleton>
      </div>
    </div>
    <div v-else-if="isError"><h1>Error occured!</h1></div>
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
