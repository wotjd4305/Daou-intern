import SERVER from '@/api/api'
import axios from 'axios'
import Swal from 'sweetalert2'
//import router from '@/router'


// import cookies from 'vue-cookies'

const chatStore = {
  
  namespaced: true,
  state: {
    chatMessages:null,
    chatingRooms:null,
  },
  getters: {
    config: state =>
      ({ headers: { Authorization: `Token ${state.authToken}` } }),
  },
  mutations: {

    SET_CHATING_MESSAGE(state, chatMessages){
      state.chatMessages = chatMessages
    },
    SET_CHATING_ROOMS(state, chatingRooms){
      state.chatingRooms = chatingRooms
    }
  },
  actions: {

     //
     patchPostChatingRoom({commit}, info){
      console.log("before : patchPostChatingRoom - " + info.location)
      console.log("before : patchPostChatingRoom - " + info.data.sellerId)
      console.log("before : patchPostChatingRoom - " + info.data.itemId)
      console.log("before : patchPostChatingRoom - " + info.data.buyerId)
      

      axios.post(SERVER.URL + info.location,  info.data)
        .then(res => {
        console.log("after : patchPostChatingRoom - " + SERVER.URL + info.location + "/" + info.data)
        
          if(res.data.isSuccess){
            console.log("after : patchPostChatingRoom - " + res.data.isSuccess)
              console.log(commit)
            alert("채팅방 생성 성공")
         }
         else{
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
            icon: 'info',
            title: "이미 있는 채팅방입니다! 채팅 확인해주세요"
          })
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
            icon: 'info',
            title: err.response.data.message
          })
        })
    },

     //채팅방 갯수
     patchGetChatingRooms({commit}, info){
      console.log("before : patchGetChatingRooms - " + info.location)
      console.log("before : patchGetChatingRooms - " + info.data)

      axios.get(SERVER.URL + info.location + "/" +  info.data)
        .then(res => {
        console.log("after : patchGetChatingRooms - " + SERVER.URL + info.location + "/" + info.data)
        
          if(res.data.isSuccess){
              commit("SET_CHATING_ROOMS", res.data.data)
              console.log(commit)
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
            icon: 'info',
            title: err.response.data.message
          })
        })
    },
    
    //////



    //
    postChatingRoom({dispatch}, Req){
          const info = {
            data: Req,
            location: SERVER.ROUTES.postchatingroom,
            //to: '/board'
         }
        dispatch('patchPostChatingRoom',info)
    },
    //
    getChatMessage({dispatch}, Req){
      const info = {
        data: Req,
        location: SERVER.ROUTES.getchatingmessage,
        //to: '/board'
     }
    dispatch('patchGetChatMessage',info)
    
    },
    getChatingRooms({dispatch}, Req){
      const info = {
        data: Req,
        location: SERVER.ROUTES.getchatingrooms,
        //to: '/board'
     }
    dispatch('patchGetChatingRooms',info)
    
    },

    

   
  },
}

export default chatStore