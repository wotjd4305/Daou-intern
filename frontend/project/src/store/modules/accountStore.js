import SERVER from '@/api/api'
import axios from 'axios'
import router from '@/router'
import Swal from 'sweetalert2'

// import cookies from 'vue-cookies'

const accountStore = {
  namespaced: true,
  state: {
  },
  getters: {
    config: state =>
      ({ headers: { Authorization: `Token ${state.authToken}` } }),
  },
  mutations: {

  },
  actions: {

    //회원가입
    postAuthData1({ commit }, info) {

      //alert(SERVER.URL + info.location)
      axios.post(SERVER.URL + info.location, info.data)
        .then(res => {
          if(res.data.status){
              console.log(res.data.object)
              console.log(commit)
              router.push('/login')
         }
         else{
           alert("에러")
         }
        })
        .catch(err => {
          const Toast = Swal.mixin({
            toast: true,
            position: 'top-end',
            showConfirmButton: false,
            timer: 3000,
            timerProgressBar: false,
            onOpen: (toast) => {
              toast.addEventListener('mouseenter', Swal.stopTimer)
              toast.addEventListener('mouseleave', Swal.resumeTimer)
              }
           })
           Toast.fire({
            icon: 'error',
            title: err.response.data
          })
        })
    },


    // Login
    postAuthData2({ commit }, info) {
 
      axios.post(SERVER.URL + info.location, info.data)
        .then(res => {
          if(res.data.status){
              console.log(res.data.object)
              commit('SET_TOKEN', res.data.object, { root: true })
              

              router.push('/home')
         }
         else{
           alert("에러" + res.data.status)
         }
        })
        .catch(err => {
          const Toast = Swal.mixin({
            toast: true,
            position: 'top-end',
            showConfirmButton: false,
            timer: 3000,
            timerProgressBar: false,
            onOpen: (toast) => {
              toast.addEventListener('mouseenter', Swal.stopTimer)
              toast.addEventListener('mouseleave', Swal.resumeTimer)
              }
           })
           Toast.fire({
            icon: 'error',
            title: err.response.data.message
          })
        })
    },

    //유저 수정
    updateUserData({ commit }, info){
      console.log("유저수정 부분! " + info.location)
      axios.patch(SERVER.URL + info.location, info.data)
      .then(res => {
        if(res.data.status){
            console.log(res.data.object)
            commit('SET_TOKEN', res.data.object, { root: true })
            

            router.push(info.to)
       }
       else{
         alert("에러" + res.data.status)
       }
      })
      .catch(err => {
        const Toast = Swal.mixin({
          toast: true,
          position: 'top-end',
          showConfirmButton: false,
          timer: 3000,
          timerProgressBar: false,
          onOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
            }
         })
         Toast.fire({
          icon: 'error',
          title: err.response.data.message
        })
      })
    },

    signup({ dispatch }, signupData) {
      const info = {
        data: signupData,
        location: SERVER.ROUTES.signup,
        to: '/signup'
      }
      dispatch('postAuthData1', info)
    },
    login({ dispatch }, loginData) {
      const info = {
        data: loginData,
        location: SERVER.ROUTES.login,
        to: '/login'
      }
      dispatch('postAuthData2', info)
    },
    findPassword(email) {
      const info = {
        data: email,
      }
      axios.post(SERVER.URL + SERVER.ROUTES.password, info)
        .then (() => {
          router.push({ name: 'PasswordFindEmail'})
        })
        .catch (err =>{
          console.log(err.response)
        })
    },
    updateUser({dispatch}, updateData){
      const info = {
        data: updateData,
        location: SERVER.ROUTES.updateuser,
        to: '/userinfo'
      }
      dispatch('updateUserData', info)
    },
    
  },
}

export default accountStore