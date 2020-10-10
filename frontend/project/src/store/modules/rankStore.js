import SERVER from '@/api/api'
import axios from 'axios'
import createPersistedState from 'vuex-persistedstate';


const rankStore = {
  plugins: [
    createPersistedState(),
  ],
  namespaced: true,
  state: {
    ranks:null,
  },
  getters: {
    config: state =>
      ({ headers: { Authorization: `Token ${state.authToken}` } }),
  },
  mutations: {

    SET_WRITE_RANK(state, ranks){
      state.ranks = ranks
    },
  },
  actions: {
    
    //랭크
    fetchWriteRank({commit},info) {
        console.log("before - fetchWriteRank")
        console.log("before - fetchWriteRank = " + SERVER.URL + info.location)
        
        axios.get(SERVER.URL + info.location, )
          .then(res => {
            console.log("after - fetchWriteRank")
              commit('SET_WRITE_RANK', res.data.data)
              console.log(res.data.data)
          })
          .catch(err => 
            console.log(err.response.data))
      },
      
      getWriteRank({dispatch}){
        const info = {
            //data: updateData,
            location: SERVER.ROUTES.getwriteranks,
            //to: '/userinfo'
          }
          dispatch('fetchWriteRank', info)
    },

   
  },
}

export default rankStore