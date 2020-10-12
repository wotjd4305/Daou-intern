import SERVER from '@/api/api'
import axios from 'axios'
import router from '@/router'
import Swal from 'sweetalert2'

import createPersistedState from 'vuex-persistedstate';

// import cookies from 'vue-cookies'

const accountStore = {
  plugins: [
    createPersistedState(),
  ],
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
          if(res.data.isSuccess){
              console.log(res.data.data)
              console.log(commit)
              router.push('/login')
         }
         else{
           alert("회원가입 에러 - " + res.data.message)
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


    // Login
    postAuthData2({ commit }, info) {
 
      axios.post(SERVER.URL + info.location, info.data)
        .then(res => {
          if(res.data.isSuccess){
            console.log(res.data.data)
              commit('SET_TOKEN', res.data.data, { root: true })
              const Toast = Swal.mixin({
                toast: true,
                position: 'top-end',
                showConfirmButton: false,
                timer: 2000,
                timerProgressBar: true,
                onOpen: (toast) => {
                  toast.addEventListener('mouseenter', Swal.stopTimer)
                  toast.addEventListener('mouseleave', Swal.resumeTimer)
                  }
               })
               Toast.fire({
                icon: 'success',
                title: "로그인에 성공."
              })

              router.push('/home')
         }
         else{
          alert("로그인 에러 - " + res.data.message)
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
        if(res.data.isSuccess){
            console.log(res.data.data)
            commit('SET_TOKEN', res.data.data, { root: true })

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
              icon: 'success',
              title: "유저 정보 수정 성공"
            })

            router.push(info.to)
       }
       else{
         alert("에러" + res.data.isSuccess)
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
    //유저 프로필 바꾸기
    patchUserUploadData({commit}, info){
      console.log("유저 프로필 업로드 부분! " + info.location)
      console.log("유저 프로필 업로드 부분 - 타입 " + info.data)
      //const file = event.target.files[0];


      axios
        .patch(
          SERVER.URL + info.location,
          info.data.image,
          {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          }
        )
        .then((response) => {
          console.log(response);
          if (response.data.message == "업로드 성공") {

            
            alert("프로필 업로드 성공");
            commit('SET_MY_ACCOUNT', info.data, { root: true })
            commit('SET_TOKEN', response.data.data,{ root: true })
            
          } else {
            alert("프로필 업로드 실패");
          }
        });
    },

    patchDeleteProfile({commit}, info){
      console.log("유저 프로필 삭제 부분! " + info.location)
      console.log("유저 프로필 삭제 부분 - 타입 " + info.data)

      axios
        .delete(
          SERVER.URL + info.location,
        )
        .then((response) => {
          console.log(response);
          if (response.data.message == "삭제 성공") {
            alert("프로필 이미지 삭제 성공");
            info.data.image = null;
            commit('SET_MY_ACCOUNT', info.data, { root: true })
            commit('SET_TOKEN', response.data.data,{ root: true })
            
          } else {
            alert("프로필 이미지 삭제 실패");
          }
        });
    }
    ,
    
    ///////////////////////////////
    deleteUserImg({ dispatch }, accountData) {
      const info = {
        data: accountData,
        location: SERVER.ROUTES.deleteuserimageA + "/" + accountData.userId + SERVER.ROUTES.deleteuserimageB,
        //to: '/'
      }
      dispatch('patchDeleteProfile', info)
    },
    uploadImg({ dispatch }, imgUploadData) {
      const info = {
        data: imgUploadData,
        location: SERVER.ROUTES.uploaduserimageA + "/" + imgUploadData.id + SERVER.ROUTES.uploaduserimageB,
        //to: '/'
      }
      dispatch('patchUserUploadData', info)
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