import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useFormsStore = defineStore('forms', () => {
  const form = ref()
  const allForms = ref()

  const getFormLoading = ref(false)
  const getFormError = ref(false)

  const sendFormLoading = ref(false)
  const sendFormError = ref(false)

  const getAllFormsLoading = ref(false)
  const getAllFormsError = ref(false)

  const loggedInUser = ref()

  const fetchSpecificForm = async (processId: string) => {
    getFormLoading.value = true
    getFormError.value = false
    try {
      const response = await fetch('/api/v1/processes/' + processId)
      form.value = await response.json()
    } catch (err) {
      getFormError.value = true
    } finally {
      getFormLoading.value = false
    }
  }

  const sendFilledForm = async () => {
    sendFormLoading.value = true
    sendFormError.value = false

    try {
      form.value.id = undefined
      form.value.metaData.owner = loggedInUser.value
      const response = await fetch('/api/v1/processes/send', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(form.value)
      })
      if (response.status == 202) {
        sendFormLoading.value = false
      } else throw new Error('Something went wrong')
    } catch {
      sendFormError.value = true
    } finally {
      sendFormLoading.value = false
    }
  }

    const sendFilledStep = async () => {
    sendFormLoading.value = true
    sendFormError.value = false
    
    try {      
      const response = await fetch('/api/v1/processes/send', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(form.value)
      })
      if (response.status == 202) {
        sendFormLoading.value = false
      } else throw new Error('Something went wrong')
    } catch {
      sendFormError.value = true
    } finally {
      sendFormLoading.value = false
    }
  }

  const fetchAllForms = async () => {
    getAllFormsError.value = false
    getAllFormsLoading.value = true
    try {
      const response = await fetch('/api/v1/processes/templates')
      allForms.value = await response.json()
      getFormError.value = false
    } catch (err) {
      getAllFormsError.value = true
    } finally {
      getAllFormsLoading.value = false
    }
  }
  return {
    form,
    allForms,
    getFormLoading,
    getFormError,
    sendFormLoading,
    sendFormError,
    getAllFormsLoading,
    getAllFormsError,
    loggedInUser,
    fetchSpecificForm,
    sendFilledForm,
    fetchAllForms,
    sendFilledStep
  }
})
