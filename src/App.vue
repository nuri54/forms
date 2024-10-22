<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import Header from './components/Header.vue'
</script>

<template>
  <Header style="border-radius: 0" />
  <div class="viewport">
    <Suspense>
      <RouterView />
    </Suspense>
    
  </div>
</template>
<style scoped>
.viewport {
  display: flex;
  justify-content: center;
  padding: 0 8rem 0rem 8rem;
  min-width: 400px;
}
</style>
