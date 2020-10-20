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
    path: '/messenger',
    name: 'Messenger',
    component: Messenger
  }
]

const router = new VueRouter({
  routes
})

export default router
export const actions = {

}
