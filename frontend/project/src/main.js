import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'


//filter
import "./filters"

//공통함수
import Common from "./common"

//객체 모음
import ObjectList from "./dtoReq.js"

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
Vue.use(VueMoment)
Vue.use(Common)
Vue.use(ObjectList)

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
