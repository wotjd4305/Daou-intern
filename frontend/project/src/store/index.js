import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

import accountStore from '@/store/modules/accountStore'


import router from '@/router'
import axios from 'axios'
import cookies from 'vue-cookies'
import SERVER from '@/api/api'
import Swal from 'sweetalert2'

export default new Vuex.Store({
  state: {
    authToken: cookies.get('auth-token'),
    myaccount: null,
    users: null,
  },
  getters:{
    config:state=>({headers:{ jwt: state.authToken}})
  },
  mutations: {
    SET_TOKEN(state, token) {
      state.authToken = token
      cookies.set('auth-token', token)
    },
    SET_USERS(state, users) {
      state.users = users
    },
    SET_MY_ACCOUNT(state, user) {
      state.myaccount = user
    },
  },
  actions: {
    
    findMyAccount({commit}) {
      console.log(this.state.authToken)
      //console.log(rootGetters.config)
      console.log("findMyAccount")
      
      axios.post(SERVER.URL + SERVER.ROUTES.myaccount, {token :this.state.authToken})
        .then(res => {
            console.log("여기에요!1" + res.data.data)
            console.log("여기에요!2" + res.data.object.id)
            commit('SET_MY_ACCOUNT', res.data.object)
        })
        .catch(err => 
          console.log(err.response.object))
    },
    logout({ commit }) {
      commit('SET_TOKEN', null)
      cookies.remove('auth-token')
      // commit('SET_INIT')
      const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 3000,
        timerProgressBar: true,
        onOpen: (toast) => {
          toast.addEventListener('mouseenter', Swal.stopTimer)
          toast.addEventListener('mouseleave', Swal.resumeTimer)
          }
       })
       Toast.fire({
        icon: 'success',
        title: '로그아웃되었습니다.'
      })
      router.push({ name: 'Login' })
    },
    fetchUsers({ commit }) {
      axios.get(SERVER.URL + '/users/')
        .then(res => {
          commit('SET_USERS', res.data)
        })
        .catch(err => console.log(err.response.data))
    },
  },
  modules: {
    accountStore: accountStore,
  }
})
