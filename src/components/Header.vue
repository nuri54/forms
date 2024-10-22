<script setup>
import { ref, onMounted } from 'vue'
import Menubar from 'primevue/menubar'
import { useRouter } from 'vue-router'
import { useFormsStore } from '@/stores/formStore'
import Dropdown from 'primevue/dropdown'

const formStore = useFormsStore()
const router = useRouter()

const items = ref([
  {
    label: 'Home',
    route: '/form',
    icon: 'pi pi-home',
    command: () => {
      router.push('/home')
    }
  },
  {
    label: 'Formbuilder',
    route: '/form/build',
    icon: 'pi pi-hammer',
    command: () => {
      router.push('/form/build')
    }
  },
  {
    label: 'User',
    route: '/user',
    icon: 'pi pi-user',
    command: () => {
      router.push('/user')
    }
  }
])

onMounted(async () => {
  try {
    const response = ref(await fetch('/api/v1/accounts'))
    possibleUsers.value = await response.value.json()
    formStore.loggedInUser = possibleUsers.value[0]
  } catch {
    /* empty */
  } finally {
    /* empty */
  }
})
const possibleUsers = ref()
</script>
<template>
  <Menubar class="card" :model="items">
    <template #end>
      <div class="flex items-center gap-2">
        <Dropdown
          v-model="formStore.loggedInUser"
          :options="possibleUsers"
          optionLabel="name"
          placeholder="User"
        />
      </div>
    </template>
  </Menubar>
</template>

<style scoped>
.card {
  padding-right: 8rem;
  padding-left: 8rem;
  min-width: 400px;
}
</style>
