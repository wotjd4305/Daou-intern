import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'


//Time, Cookie
import VueMoment from 'vue-moment'
import VueCookies from 'vue-cookies'

//BootStrap
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(VueCookies)
Vue.use(VueMoment)
Vue.use(BootstrapVue)

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
