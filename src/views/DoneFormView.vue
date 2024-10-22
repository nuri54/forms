<script setup lang="ts">
import InputText from 'primevue/inputtext'
import InputNumber from 'primevue/inputnumber'
import Textarea from 'primevue/textarea'
import FloatLabel from 'primevue/floatlabel'
import InputMask from 'primevue/inputmask'
import Checkbox from 'primevue/checkbox'
import Calendar from 'primevue/calendar'
import Skeleton from 'primevue/skeleton'
import Button from 'primevue/button'

import { useConfirm } from 'primevue/useconfirm'
import { formatDateToDateAndTime } from '@/utils/date-utils'
import { useFormsStore } from '@/stores/formStore'
import { useRouter, useRoute } from 'vue-router'
import { computed } from 'vue'

const formStore = useFormsStore()
formStore.form = null
const router = useRouter()
const route = useRoute()
const confirmation = useConfirm()

formStore.fetchSpecificForm(route.params.id)
const newJson = computed(() => {
  return formStore.form
})
const updatedAt = computed(() => {
  return formatDateToDateAndTime(newJson.value.metaData.updatedAt)
})
</script>

<template>
  <div>
    <div class="titleContainer">
      <h1>{{ newJson.metaData.title }}</h1>
    </div>

    <div class="formContainer">
      <div v-for="(step, stepIndex) in newJson.steps" :key="'step-item' + stepIndex">
        <div class="formContainer">
          <h4>Step: {{ stepIndex + 1 }}</h4>
          <div v-for="(entry, inputIndex) in step.inputs" :key="entry.name">
            <FloatLabel v-if="entry.type == 'text'">
              <InputText
                disabled
                id="username"
                v-model="newJson.steps[stepIndex].inputs[inputIndex].value"
              />
              <label for="username">{{ entry.name }}</label>
            </FloatLabel>
            <FloatLabel v-else-if="entry.type == 'number'">
              <InputNumber
                disabled
                id="number-input"
                :useGrouping="false"
                v-model="newJson.steps[stepIndex].inputs[inputIndex].value"
              />
              <label for="number-input">{{ entry.name }}</label>
            </FloatLabel>
            <FloatLabel v-else-if="entry.type == 'phoneNumber'">
              <InputMask
                disabled
                id="number-input"
                mask="+99 999 99999999"
                v-model="newJson.steps[stepIndex].inputs[inputIndex].value"
              />
              <label for="number-input">{{ entry.name }}</label>
            </FloatLabel>
            <FloatLabel v-else-if="entry.type == 'email'">
              <InputText
                disabled
                id="mail-input"
                v-model="newJson.steps[stepIndex].inputs[inputIndex].value"
              />
              <label for="mail-input">{{ entry.name }}</label>
            </FloatLabel>
            <FloatLabel v-else-if="entry.type == 'textbox'">
              <Textarea
                disabled
                id="textbox-input"
                v-model="newJson.steps[stepIndex].inputs[inputIndex].value"
                autoResize
                cols="40"
              />
              <label for="textbox-input">{{ entry.name }}</label>
            </FloatLabel>
            <div v-else-if="entry.type == 'checkbox'">
              <h4>{{ entry.name }}</h4>
              <div
                v-for="(option, optionIndex) in entry.options"
                :key="option"
                style="display: flex; gap: 1rem; margin-bottom: 1rem"
              >
                <p>
                  {{ newJson.steps[stepIndex].inputs[inputIndex].options[optionIndex].value }}
                </p>
                <Checkbox
                  disabled
                  id="option.name"
                  v-model="newJson.steps[stepIndex].inputs[inputIndex].options[optionIndex].value"
                  :binary="true"
                />
                <label :for="option.name">{{ option.name }}</label>
              </div>
            </div>

            <FloatLabel v-else-if="entry.type == 'date'">
              <Calendar
                disabled
                id="date-input"
                v-model="newJson.steps[stepIndex].inputs[inputIndex].value"
              />

              <label for="date-input">{{ entry.name }}</label>
            </FloatLabel>
          </div>
        </div>
      </div>
    </div>
    <div class="center">
      <p>Done since: {{ updatedAt }}</p>
    </div>
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
.center {
  display: flex;
  justify-content: center;
}
</style>
