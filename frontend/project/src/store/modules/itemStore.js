import SERVER from '@/api/api'
import axios from 'axios'
import Swal from 'sweetalert2'
import router from '@/router'

import createPersistedState from 'vuex-persistedstate';
import { mapState } from 'vuex'


// import cookies from 'vue-cookies'

const accountStore = {
  plugins: [
    createPersistedState(),
  ],
  namespaced: true,
  state: {
    searcheditems : null,
    detailitem : null,
  },
  computed:{
    ...mapState(['myaccount'])
  },
  getters: {
    config: state =>
      ({ headers: { Authorization: `Token ${state.authToken}` } }),
  },
  mutations: {

     SET_ITEMS(state, searcheditems){
       state.searcheditems = searcheditems
     },
     SET_DETAIL_ITEMS(state, detailitem){
      state.detailitem = detailitem
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
              
              //this.getAllItem(this.myaccount.userId)
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
        console.log("before : patchAllItem - " + info.location)
        axios.get(SERVER.URL + info.location , 
            {params:{userId:info.data}},
          )
          .then(res => {
          console.log("after : patchAllItem - " + res.data.status)
          
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
      patchDetailItem({commit}, info){
        console.log("before : patchDetailItem - " + info.location)
        axios.get(SERVER.URL + info.location + "/" + info.data.itemId , 
            {params:{userId:info.data.userId}},
          )
          .then(res => {
          console.log("after : patchDetailItem - " + res.data.status)
          
            if(res.data.status){
              
                commit("SET_DETAIL_ITEMS", res.data.object)
                
                console.log(res.data.object)
                console.log(commit)
                //router.push(info.to)
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
    getAllItem({ dispatch }, userId) {
        const info = {
          data: userId,
          location: SERVER.ROUTES.getallitem,
          //to: '/board'
        }
        dispatch('patchAllItem',info)
      },
    getDetailItem({dispatch}, getDetailReq){
      const info = {
        data: getDetailReq,
        location: SERVER.ROUTES.getdetailitem,
        //to: '/board'
      }
      dispatch('patchDetailItem',info)
    },
    }
}

export default accountStore