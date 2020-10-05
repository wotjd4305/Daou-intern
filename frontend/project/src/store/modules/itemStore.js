import SERVER from '@/api/api'
import axios from 'axios'
import Swal from 'sweetalert2'
import router from '@/router'

import createPersistedState from 'vuex-persistedstate';


// import cookies from 'vue-cookies'

const accountStore = {
  plugins: [
    createPersistedState(),
  ],
  namespaced: true,
  state: {
    searcheditems: null,
  },
  getters: {
    config: state =>
      ({ headers: { Authorization: `Token ${state.authToken}` } }),
  },
  mutations: {

     SET_ITEMS(state, searcheditems){
       state.searcheditems = searcheditems
     },
  },
  actions: {
    
   
    //글쓰기
    patchBoardWrite({commit}, info){
      axios.post(SERVER.URL + info.location, info.data, {headers: {
        "Content-Type": "multipart/form-data"},
      },)
        .then(res => {
        console.log("글쓰기" + res.data.status)
        
          if(res.data.status){
            
            //성공 메시지
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
                title: "작성 성공"
              })

              console.log(res.data.object)
              console.log(commit)
              router.push(info.to)
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

    //모든 아이템 검색
    patchAllItem({commit}, info){
        console.log("before : getAllItem - " + info.location)
          
        axios.get(SERVER.URL + info.location, info)
          .then(res => {
          console.log("after : getAllItem - " + res.data.status)
          
            if(res.data.status){
              
                //상태 저장
                commit("SET_ITEMS", res.data.object)
                console.log(res.data.object)
                console.log(commit)
                router.push(info.to)
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


    //////
    boardWrite({ dispatch }, boardWriteData) {
      const info = {
        data: boardWriteData,
        location: SERVER.ROUTES.postitem,
        to: '/board'
      }
      dispatch('patchBoardWrite', info)
    },
    getAllItem({ dispatch }) {
        const info = {
          //data: boardWriteData,
          location: SERVER.ROUTES.getallitem,
          //to: '/board'
        }
        dispatch('patchAllItem',info)
      },
    }
}

export default accountStore