import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import FormView from '../views/FormView.vue'
import FormBuilder from '../views/FomBuilderView.vue'
import UserFormsView from '@/views/UserFormsView.vue'
import DoneFormView from '@/views/DoneFormView.vue'
import ToDoFillView from '@/views/ToDoFillView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/home',
      name: 'home',
      component: HomeView
    },
    {
      path: '/',
      redirect: '/home'
    },
    {
      path: '/form/fill/:id',
      name: 'formFill',
      component: FormView
    },
    {
      path: '/form/build',
      name: 'formBuild',
      component: FormBuilder
    },
    {
      path: '/user',
      name: 'user',
      component: UserFormsView
    },
    {
      path: '/form/done/:id',
      name: 'formDone',
      component: DoneFormView
    },
    {
      path: '/form/fill/step/:id',
      name: 'formStepFill',
      component: ToDoFillView
    },

  ]
})

export default router
