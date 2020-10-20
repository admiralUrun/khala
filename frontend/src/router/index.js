import Vue from 'vue'
import VueRouter from 'vue-router'
import Welcome from "@/components/Welcome";
import Messenger from "@/views/Messenger";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Welcome',
    component: Welcome
  },
  {
    path: '/messenger/:user',
    name: 'Messenger',
    component: Messenger,
    props: true
  }
]

const router = new VueRouter({
  routes
})

export default router
