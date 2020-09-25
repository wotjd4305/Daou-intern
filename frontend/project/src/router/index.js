import Vue from 'vue'
import VueRouter from 'vue-router'

//Accounts
import Login from '@/views/accounts/Login'
import Signup from '@/views/accounts/Signup'


//PageNotFound
import PageNotFound from '@/views/PageNotFound'


Vue.use(VueRouter)

const routes = [

  //유저
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/signup',
    name: 'Signup',
    component: Signup
  },

  //PageNotFound
  {
    path: '*',
    name: 'PageNotFound',
    component: PageNotFound
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
