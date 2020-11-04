import Vue from 'vue'
import App from './App.vue'
import VueResource from 'vue-resource'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import router from './router'
import {store} from "./store/store.js";
import VueNativeSock from 'vue-native-websocket'

Vue.use(BootstrapVue)
Vue.use(IconsPlugin)
Vue.use(VueResource)
Vue.use(VueNativeSock, 'ws://localhost:9000/startWebsocketConnection', {
  store: store,
  format: 'json'
})
Vue.config.productionTip = false

new Vue({
  store: store,
  router,
  render: h => h(App)
}).$mount('#app')
