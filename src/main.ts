import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import PrimeVue from 'primevue/config'
import ConfirmationService from 'primevue/confirmationservice'
import ToastService from 'primevue/toastservice'
import 'primevue/resources/themes/aura-dark-noir/theme.css'
// import 'primevue/resources/themes/aura-light-noir/theme.css'
import 'primeicons/primeicons.css'

const app = createApp(App)
app.use(PrimeVue)
app.use(createPinia())
app.use(router)
app.use(ConfirmationService)
app.use(ToastService)
app.mount('#app')
