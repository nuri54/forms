<script setup>
import InputText from 'primevue/inputtext'
import InputNumber from 'primevue/inputnumber'
import Textarea from 'primevue/textarea'
import FloatLabel from 'primevue/floatlabel'
import InputMask from 'primevue/inputmask'
import Checkbox from 'primevue/checkbox'
import Calendar from 'primevue/calendar'
import Skeleton from 'primevue/skeleton'
import Button from 'primevue/button'
import Toast from 'primevue/toast'
import ConfirmDialog from 'primevue/confirmdialog'
import Dropdown from 'primevue/dropdown'

import { useConfirm } from 'primevue/useconfirm'
import { formatDateToDateAndTime } from '@/utils/date-utils'
import { useFormsStore } from '@/stores/formStore'
import { useRouter, useRoute } from 'vue-router'
import { computed } from 'vue'
import { useToast } from 'primevue/usetoast'
import { ref, onMounted } from 'vue'

const formStore = useFormsStore()
const router = useRouter()
const route = useRoute()
const confirmation = useConfirm()
formStore.form = null
formStore.fetchSpecificForm(route.params.id)

const newJson = computed(() => {
  return formStore.form
})
const sendForm = async () => {
  await formStore.sendFilledForm()
  if (formStore.sendFormError == false) {
    router.push('/home')
  } else {
    showError()
  }
}
const confirmSave = () => {
  confirmation.require({
    message: 'Are you sure you want to send the form?',
    header: 'Confirmation',
    icon: 'pi pi-exclamation-triangle',
    rejectClass: 'p-button-secondary p-button-outlined',
    rejectLabel: 'Cancel',
    acceptLabel: 'Send',
    accept: () => {
      sendForm()
    },
    reject: () => {}
  })
}

const toast = useToast()
const showError = () => {
  toast.add({
    severity: 'error',
    summary: 'Something went wrong',
    detail: 'Try again later!',
    life: 3000
  })
}
const updatedAt = computed(() => {
  return formatDateToDateAndTime(formStore.form.metaData.updatedAt)
})

onMounted(async () => {
  try {
    const response = ref(await fetch('/api/v1/accounts'))
    possibleUsers.value = await response.value.json()
  } catch {
    /* empty */
  } finally {
    /* empty */
  }
})
const selectedOption = ref()
const possibleUsers = ref()

const submitButtonIsDisabled = computed(() => {
  return newJson.value.metaData.currentUser == null
})
</script>

<template>
  <Toast />
  <div v-if="!formStore.getFormLoading && !formStore.getFormError">
    <div class="titleContainer">
      <h1>{{ newJson.metaData.title }}</h1>
    </div>

    <div class="formContainer">
      <div
        v-for="(entry, inputIndex) in newJson.steps[newJson.metaData.currentStep].inputs"
        :key="entry.name"
      >
        <FloatLabel v-if="entry.type == 'text'">
          <InputText
            id="username"
            v-model="newJson.steps[newJson.metaData.currentStep].inputs[inputIndex].value"
          />
          <label for="username">{{ entry.name }}</label>
        </FloatLabel>
        <FloatLabel v-else-if="entry.type == 'number'">
          <InputNumber
            id="number-input"
            :useGrouping="false"
            v-model="newJson.steps[newJson.metaData.currentStep].inputs[inputIndex].value"
          />
          <label for="number-input">{{ entry.name }}</label>
        </FloatLabel>
        <FloatLabel v-else-if="entry.type == 'phoneNumber'">
          <InputMask
            id="number-input"
            mask="+99 999 99999999"
            v-model="newJson.steps[newJson.metaData.currentStep].inputs[inputIndex].value"
          />
          <label for="number-input">{{ entry.name }}</label>
        </FloatLabel>
        <FloatLabel v-else-if="entry.type == 'email'">
          <InputText
            id="mail-input"
            v-model="newJson.steps[newJson.metaData.currentStep].inputs[inputIndex].value"
          />
          <label for="mail-input">{{ entry.name }}</label>
        </FloatLabel>
        <FloatLabel v-else-if="entry.type == 'textbox'">
          <Textarea
            id="textbox-input"
            v-model="newJson.steps[newJson.metaData.currentStep].inputs[inputIndex].value"
            autoResize
            cols="40"
          />
          <label for="textbox-input">{{ entry.name }}</label>
        </FloatLabel>
        <div v-else-if="entry.type == 'checkbox'">
          <h4>{{ entry.name }}</h4>
          <div
            v-for="(option, optionIndex) in entry.options"
            :key="option.name"
            style="display: flex; gap: 1rem; margin-bottom: 1rem"
          >
            <Checkbox
              id="option.name"
              v-model="
                newJson.steps[newJson.metaData.currentStep].inputs[inputIndex].options[optionIndex]
                  .value
              "
              :binary="true"
            />
            <label :for="option.name">{{ option.name }}</label>
          </div>
        </div>

        <FloatLabel v-else-if="entry.type == 'date'">
          <Calendar
            id="date-input"
            v-model="newJson.steps[newJson.metaData.currentStep].inputs[inputIndex].value"
          />

          <label for="date-input">{{ entry.name }}</label>
        </FloatLabel>
      </div>
      <Dropdown
        v-model="newJson.metaData.currentUser"
        :options="possibleUsers"
        optionLabel="name"
        placeholder="Select User to send to"
      />
    </div>

    <div class="center">
      <ConfirmDialog></ConfirmDialog>
      <Button
        :disabled="submitButtonIsDisabled"
        @click="confirmSave()"
        label="Submit"
        icon="pi pi-check"
        iconPos="right"
        :loading="formStore.sendFormLoading"
      />
    </div>
    <div class="center">
      <p>Last update: {{ updatedAt }}</p>
    </div>
  </div>

  <div v-else-if="formStore.getFormLoading && !formStore.getFormError">
    <div class="skeletonContainer">
      <div v-for="n in 6">
        <Skeleton class="p-skeleton" width="206px" height="3rem"></Skeleton>
      </div>
    </div>
    <div class="center">
      <Skeleton class="p-skeleton" width="112px" height="3rem"></Skeleton>
    </div>
  </div>

  <div v-else-if="formStore.getFormError">
    <h1>Error occured!</h1>
  </div>
</template>

<style scoped>
.titleContainer {
  display: flex;
  justify-content: center;
  text-align: center;
  margin-bottom: 1rem;
}
.formContainer {
  display: flex;
  align-items: center;
  flex-direction: column;
  margin-bottom: 2rem;
  row-gap: 2rem;
}
.skeletonContainer {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 2rem 0 2rem 0;
  gap: 2rem;
}
.center {
  display: flex;
  justify-content: center;
}
</style>
