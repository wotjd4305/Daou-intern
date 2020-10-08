import SERVER from '@/api/api'
import axios from 'axios'
//import Swal from 'sweetalert2'
//import router from '@/router'

import createPersistedState from 'vuex-persistedstate';


// import cookies from 'vue-cookies'

const categoryStore = {
  plugins: [
    createPersistedState(),
  ],
  namespaced: true,
  state: {
    itemCategorys:null,
    departmentCategorys:null,
  },
  getters: {
    config: state =>
      ({ headers: { Authorization: `Token ${state.authToken}` } }),
  },
  mutations: {

    SET_DEPARTMENT_CATEGORY(state, departmentCategorys){
      state.departmentCategorys = departmentCategorys
    },
    SET_ITEM_CATEGORY(state, itemCategorys){
      state.itemCategorys = itemCategorys
    },
  },
  actions: {
    
    ///////////////////////////////
    //아이템
    fetchItemCategory({commit}) {
        console.log("fetchItemCategory")
        
        axios.get(SERVER.URL + SERVER.ROUTES.itemcategory, )
          .then(res => {
              commit('SET_ITEM_CATEGORY', res.data.data)
              console.log(res.data.data)
          })
          .catch(err => 
            console.log(err.response.data))
      },
    //부서
    fetchDepartmentCategory({commit}) {
        console.log("fetchDepartmentCategory")
        
        axios.get(SERVER.URL + SERVER.ROUTES.departmentcategory, )
          .then(res => {
              commit('SET_DEPARTMENT_CATEGORY', res.data.data)
              console.log(res.data.data)
          })
          .catch(err => 
            console.log(err.response.data))
      },

   
  },
}

export default categoryStore