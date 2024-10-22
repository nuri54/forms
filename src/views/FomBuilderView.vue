<script setup lang="ts">
import Dropdown from 'primevue/dropdown'
import InputText from 'primevue/inputtext'
import FloatLabel from 'primevue/floatlabel'
import Button from 'primevue/button'
import InputNumber from 'primevue/inputnumber'
import Textarea from 'primevue/textarea'
import InputMask from 'primevue/inputmask'
import Checkbox from 'primevue/checkbox'
import Calendar from 'primevue/calendar'
import Stepper from 'primevue/stepper'
import StepperPanel from 'primevue/stepperpanel'

import { ref, type Ref } from 'vue'
import { useRouter } from 'vue-router'

const title = ref()
const newJson = ref({
  metaData: {
    title: ''
  },
  steps: [
    {
      inputs: []
    }
  ]
})
const selectedOption = ref()
const cities = ref([
  { name: 'Textfield', code: 'text' },
  { name: 'Number', code: 'number' },
  { name: 'Telephone number', code: 'phoneNumber' },
  { name: 'E-Mail', code: 'email' },
  { name: 'Textbox', code: 'textbox' },
  { name: 'Checkbox', code: 'checkbox' },
  { name: 'Date', code: 'date' }
])

const addedInput = ref({
  name: '',
  type: '',
  options: [
    {
      name: '',
      value: ''
    }
  ]
})

const addInput = () => {
  addedInput.value.type = selectedOption.value.code
  if (addedInput.value.type == 'checkbox') selectedOption.value.options
  newJson.value.steps[currentStep.value].inputs.push(JSON.parse(JSON.stringify(addedInput.value)))
  addedInput.value = {
    name: '',
    type: '',
    options: [
      {
        name: '',
        value: ''
      }
    ]
  }
}
const router = useRouter()
const isLoading = ref(false)
const isError = ref(false)
const createForm = async () => {
  try {
    isError.value = false
    isLoading.value = true
    const response = await fetch('/api/v1/processes', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(newJson.value)
    })
  } catch (err) {
    isError.value = true
  } finally {
    isLoading.value = false
  }

  if (!isError.value) {
    router.push('/home')
  }
}

const currentStep = ref(0)
const addStep = () => {
  newJson.value.steps.push({
    inputs: []
  })
  currentStep.value += 1
}
const addOption = () => {
  addedInput.value.options.push({
    name: '',
    value: ''
  })
}
</script>

<template>
  <div class="formContainer">
    <h1>Formbuilder</h1>
    <FloatLabel>
      <InputText id="title" v-model="newJson.metaData.title" />
      <label for="title">Form title</label>
    </FloatLabel>

    <div v-for="(step, stepIndex) in newJson.steps" :key="'step-item' + stepIndex">
      <div class="formContainer">
        <h4>Step {{ stepIndex + 1 }}</h4>
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
              :key="option.name"
              style="display: flex; gap: 1rem; margin-bottom: 1rem"
            >
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

    <div class="newInput">
      <Dropdown
        v-model="selectedOption"
        :options="cities"
        optionLabel="name"
        placeholder="Select an input type"
      />

      <FloatLabel
        v-if="
          selectedOption?.code == 'text' ||
          selectedOption?.code == 'number' ||
          selectedOption?.code == 'phoneNumber' ||
          selectedOption?.code == 'email' ||
          selectedOption?.code == 'textbox' ||
          selectedOption?.code == 'date'
        "
      >
        <InputText id="inputLabel" v-model="addedInput.name" />
        <label for="inputLabel">Input Name</label>
      </FloatLabel>

      <div v-else-if="selectedOption?.code == 'checkbox'">
        <div class="formContainer">
          <FloatLabel>
            <Textarea id="optionTitle" v-model="addedInput.name" autoResize cols="40" />
            <label for="optionTitle">Checkbox Title</label>
          </FloatLabel>

          <FloatLabel v-for="(option, index) in addedInput.options" :key="index">
            <Textarea
              id="optionTitle"
              v-model="addedInput.options[index].name"
              autoResize
              cols="40"
            />

            <label for="option">Option</label>
          </FloatLabel>
          <Button @click="addOption"> Add option </Button>
        </div>
      </div>

      <Button @click="addInput" label="Add Input" severity="primary" />
    </div>
    <div class="addButtons">
      <Button @click="addStep" label="Add step" severity="secondary" />
      <Button @click="createForm" label="Create Form" severity="primary" :loading="isLoading" />
    </div>
  </div>
</template>

<style scoped>
.formContainer {
  display: flex;
  align-items: center;
  flex-direction: column;
  margin-bottom: 2rem;
  row-gap: 2rem;
  width: 100%;
}

.newInput {
  display: flex;
  align-items: center;
  flex-direction: column;
  row-gap: 2rem;
  padding: 2rem 0 2rem 0;
  min-width: 250px;
  width: 60%;
  background-color: var(--surface-card);
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}
.addButtons {
  width: 100%;
  display: flex;
  justify-content: center;
  padding-right: 1rem;
  gap: 1rem;
}
</style>
