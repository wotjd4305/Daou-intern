import SERVER from '@/api/api'
import axios from 'axios'
//import Swal from 'sweetalert2'
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

     //메시지 불러오기
     patchPostChatingRoom({commit}, info){
      console.log("before : patchPostChatingRoom - " + info.location)
      console.log("before : patchPostChatingRoom - " + info.data)

      axios.post(SERVER.URL + info.location,  info.data)
        .then(res => {
        console.log("after : patchPostChatingRoom - " + SERVER.URL + info.location + "/" + info.data)
        
          if(res.data.isSuccess){
              commit("SET_MY_FAVORITE_ITEMS", res.data.data)
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