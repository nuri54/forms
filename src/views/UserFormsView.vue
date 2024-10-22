<script setup lang="ts">
import DoneForms from '@/components/DoneForms.vue'
import ToDoForms from '@/components/ToDoForms.vue'
import WaitingForms from '@/components/WaitingForms.vue'
</script>

<template>
  <div class="container">
    <ToDoForms />
    <WaitingForms />
    <DoneForms />
  </div>
</template>

<style scoped>
.container {
  width: 100%;

  gap: 1rem;
}
</style>
