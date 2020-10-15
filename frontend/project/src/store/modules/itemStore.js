import SERVER from '@/api/api'
import axios from 'axios'
import Swal from 'sweetalert2'
import router from '@/router'

import createPersistedState from 'vuex-persistedstate';
import { mapActions, mapState } from 'vuex'

// import cookies from 'vue-cookies'

const itemStore = {
  plugins: [
    createPersistedState(),
  ],
  namespaced: true,
  state: {
    iditems:null,
    favorititems:null,
    searcheditems : null,
    detailitem : null,
    pages:null,
    isLoading:null,
  },
  computed:{
    ...mapState(['myaccount'])
  },
  getters: {
    config: state =>
      ({ headers: { Authorization: `Token ${state.authToken}` } }),
  },
  methods:{
    ...mapActions(['findMyAccount']),
    delay : (duration =500) =>{
      return new Promise((resolve)=>{
        setTimeout(() => {
          resolve();
        }, duration);
      })
    },
  },
  mutations: {

     SET_ITEMS(state, searcheditems){
       state.searcheditems = searcheditems
     },
     SET_DETAIL_ITEMS(state, detailitem){
      state.detailitem = detailitem
    },
     SET_MY_ITEMS(state, iditems){
         state.iditems= iditems
     },
     SET_MY_FAVORITE_ITEMS(state, favorititems){
        state.favorititems= favorititems
    },
    SET_ITEMS_PAGE(state,pages){
        state.pages= pages
    },
    SET_LOADING_STATE(state, status=true){
      state.isLoading = status;
    }
  },
  actions: {
    
   
    //글쓰기
    patchBoardWrite({commit}, info){
      axios.post(SERVER.URL + info.location, info.data, {headers: {
        "Content-Type": "multipart/form-data"},
      },)
        .then(res => {
        console.log("글쓰기" + res.data.isSuccess)
        
          if(res.data.isSuccess){
            
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
              console.log(res.data.data)
              console.log(commit)
              router.push(info.to)
         }
         else{
          alert("글쓰기 에러 - " + res.data.message)
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
        console.log("before : patchAllItem - " + info.data.userId)
        
        axios.get(SERVER.URL + info.location , 
            {params:{userId:info.data.userId, page:info.data.page}},
          )
          .then(res => {
          
          console.log("after : patchAllItem - " + res.data.isSuccess)
          
            if(res.data.isSuccess){
              
                //상태 저장
                commit("SET_ITEMS", res.data.data)
                commit("SET_ITEMS_PAGE", res.data.pageMaker)
                console.log(res.data.data)
                console.log(commit)
                router.push(info.to)
           }
           else{
             alert("아이템 검색 에러 - " + res.data.message)
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
      //아이템 상세보기
      patchDetailItem({commit}, info){
        console.log("before : patchDetailItem - " + info.location)
        console.log("before : patchDetailItem - " + info.data.itemId)
        console.log("before : patchDetailItem - " + info.data.userId)
        
        var d1 = new Date();
        var beforeTime = d1.getTime()

        axios.get(SERVER.URL + info.location + "/" + info.data.itemId , 
            {params:{userId:info.data.userId}},
          )
          .then(res => {
            var d2 = new Date();
            var afterTime = d2.getTime()

          console.log("after : patchDetailItem - Time : " + (afterTime - beforeTime))
          console.log("after : patchDetailItem - " + res.data.data.favorite)
          
            if(res.data.isSuccess){
              
                commit("SET_DETAIL_ITEMS", res.data.data)
                
                console.log(res.data.data)
                console.log(commit)
                //router.push(info.to)
           }
           else{
            alert("아이템 상세보기 에러 - " + res.data.message)
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
      //상세보기 수정
      patchUpdateDetailItem({commit}, info){
        console.log("before : patchUpdateDetailItem - " + info.location)
        console.log("before : patchUpdateDetailItem - " + info.data.title)

        axios.patch(SERVER.URL + info.location , info.data )
          .then(res => {
          console.log("after : patchUpdateDetailItem - " + res.data.data)
          
            if(res.data.isSuccess){
                commit("SET_DETAIL_ITEMS", info.data)
                
                console.log(commit)
                //router.push(info.to)
           }
           else{
            alert("아이템 수정 에러 - " + res.data.message)
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
       //키워드로 검색 
       async patchItemByKeyword({commit}, info){
        console.log("before : patchItemByKeyword - " + info.location)
        console.log("before : patchItemByKeyword - " + info.data.keyword)
        
        var d1 = new Date();
        var beforeTime = d1.getTime()

        try{
            commit("SET_LOADING_STATE",true);
            
            let categoryListStr ="";
            for(var i=0; i<info.data.category.length; i++){
                categoryListStr = categoryListStr + "category=" + info.data.category[i]  +"&"
            }
            console.log(categoryListStr)

            await axios.get(SERVER.URL + info.location + "?"+ categoryListStr,
                {params:{userId: info.data.userId, keyword : info.data.keyword, page : info.data.page}})
              .then(res => {
                var d2 = new Date();
                var afterTime = d2.getTime();

              console.log("after : patchItemByKeyword - Time : " + (afterTime - beforeTime))
              console.log("after : patchItemByKeyword - " + res.data.data)
              
              
                if(res.data.isSuccess){
                    commit("SET_ITEMS", res.data.data)
                    commit("SET_ITEMS_PAGE", res.data.pageMaker)
                    console.log(commit)
                    console.log("after : patchItemByKeyword - " + res.data.pageMaker.endPage)
              }
              else{
                alert("키워드 검색 에러 - " + res.data.message)
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
            
    
            
        } catch(err){
          console.error(err)
        } finally{
          commit("SET_LOADING_STATE", false)
        }
      },
      //내가 등록한 아이템 
      patchItemById({commit}, info){
        console.log("before : patchItemById - " + info.location)
        console.log("before : patchItemById - " + info.data)

        axios.get(SERVER.URL + info.location,
            {params:{userId : info.data.userId}})
          .then(res => {
          console.log("after : patchItemById - " + res.data.data)
          
            if(res.data.isSuccess){
                commit("SET_MY_ITEMS", res.data.data)
                console.log(commit)
           }
           else{
            alert("내가 작성한 아이템 출력 에러 - " + res.data.message)
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
     //내가 찜한 아이템 
    patchFavoriteItemById({commit}, info){
        console.log("before : patchFavoriteItemById - " + info.location)
        console.log("before : patchFavoriteItemById - " + info.data)
        

        axios.get(SERVER.URL + info.location + "/" + info.data)
          .then(res => {
          console.log("after : patchFavoriteItemById - " + SERVER.URL + info.location + "/" + info.data)
          
            if(res.data.isSuccess){
                commit("SET_MY_FAVORITE_ITEMS", res.data.data)
                console.log(commit)
           }
           else{
            alert("내가 찜한 아이템 출력 에러 - " + res.data.message)
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
      patchPostFavoriteItemById({commit}, info){
        console.log("before : patchPostFavoriteItemById - " + info.location)
        console.log("before : patchPostFavoriteItemById - " + info.data.userId + " - " + info.data.itemId)

        var d1 = new Date();
        var beforeTime = d1.getTime()

        axios.post(SERVER.URL + info.location,  info.data)
          .then(res => {
          var d2 = new Date();
          var afterTime = d2.getTime()
          
          console.log("after : patchPostFavoriteItemById - Time : " + (afterTime - beforeTime))
          console.log("after : patchPostFavoriteItemById - " + SERVER.URL + info.location)
          
            if(res.data.isSuccess){
                console.log(commit)
           }
           else{
            alert("찜하기 에러 - " + res.data.message)
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
      patchDeleteFavoriteItemById({commit}, info){
        console.log("before : patchDeleteFavoriteItemById - " + info.location)
        console.log("before : patchDeleteFavoriteItemById - " + info.data)

        var d1 = new Date();
        var beforeTime = d1.getTime()

        axios.delete(SERVER.URL + info.location + "/" + info.data.itemId + "/" + info.data.userId)
          .then(res => {
          var d2 = new Date();
          var afterTime = d2.getTime()
          
          console.log("after : patchDeleteFavoriteItemById - Time : " + (afterTime - beforeTime))
          console.log("after : patchDeleteFavoriteItemById - " + SERVER.URL + info.location + "/" + info.data.itemId + "/" + info.data.userId)
          
            if(res.data.isSuccess){
                console.log(commit)
           }
           else{
            alert("찜 삭제 에러 - " + res.data.message)
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
      patchUpdateStatusByItemid({commit}, info){
        console.log("before : patchUpdateStatusByItemid - " + info.location)
        console.log("before : patchUpdateStatusByItemid - " + info.data.status)

        axios.patch(SERVER.URL + info.location + "?status=" + info.data.status )
          .then(res => {
          console.log("after : patchUpdateStatusByItemid - " + SERVER.URL + info.location)
          
            if(res.data.isSuccess){
                console.log(commit)
           }
           else{
            alert("아이템 상태 변경 에러 - " + res.data.message)
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
    boardWrite({ dispatch }, boardWriteData) {
      const info = {
        data: boardWriteData,
        location: SERVER.ROUTES.postitem,
        to: '/board'
      }
      dispatch('patchBoardWrite', info)
    },
    getAllItem({ dispatch }, userData) {
        const info = {
          data: userData,
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
    updateDetailItem({dispatch}, udpateDataReq)
      {
        const info = {
          data: udpateDataReq,
          location: SERVER.ROUTES.updateitem,
          //to: '/board'
        }
        dispatch('patchUpdateDetailItem',info)
      },
    getItemByKeyword({dispatch}, keywordDataReq){
        const info = {
            data: keywordDataReq,
            location: SERVER.ROUTES.getitembykeword,
            //to: '/board'
         }
         console.log("info loc " + info.location)
         console.log("info loc " + info.data.keyword)
         console.log(SERVER.ROUTES.getitembykeword)
         
        dispatch('patchItemByKeyword',info)
    },
    getItemById({dispatch}, idReq){
        const info = {
            data: idReq,
            location: SERVER.ROUTES.getitembyidA +idReq +SERVER.ROUTES.getitembyidB,
            //to: '/board'
         }
        console.log("ㅇㅇ" + info.location)
        dispatch('patchItemById',info)
    },
    getFavoriteItemById({dispatch}, idReq){
        const info = {
            data: idReq,
            location: SERVER.ROUTES.getfavoriteitembyid,
            //to: '/board'
         }
        console.log("getFavoriteItemById " + info.data)
        dispatch('patchFavoriteItemById',info)
    },
    postFavoriteItemById({dispatch}, favorReq){
      const info = {
        data: favorReq,
        location: SERVER.ROUTES.registerfavorite,
        //to: '/board'
     }
    dispatch('patchPostFavoriteItemById',info)
    }
    ,
    deleteFavoriteItemById({dispatch}, favorReq){
      const info = {
        data: favorReq,
        location: SERVER.ROUTES.deletefavorite,
        //to: '/board'
     }
    dispatch('patchDeleteFavoriteItemById',info)
    },
    updateItemStatus({dispatch}, statusReq){
      const info = {
        data: statusReq,
        location: SERVER.ROUTES.updateitemstatusA + statusReq.itemId + SERVER.ROUTES.updateitemstatusB,
        //to: '/board'
     }
    dispatch('patchUpdateStatusByItemid',info)
    
    },

  }
}

export default itemStore