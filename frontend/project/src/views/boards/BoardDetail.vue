<template>
  <div>
    <!-- 검색 창 -->
    <!-- <div class="container">
        <div class="row align-self-center" style="display:block">
          
            <div class="col align-self-center">
                <b-img
                type="image"
                @click="searchA()"
                style="cursor:pointer"
                v-bind:src="require(`@/assets/img/icons8-search-120.png`)"
                width="25px"
                ></b-img>
                <input
                type="text"
                placeholder="검색하실 단어를 입력해주세요."
                v-on:keyup.enter="searchA()"
                value
                autocomplete="off"
                autofocus
                title="검색"
                class="search_top_text "
                />
                
                <button class=" btn ml-1 search-button" >검색</button>
            </div>
          </div>
    </div> -->
    <!--/검색 창 -->


    <!-- 상세 창 -->
    <div class="container mt-5">
         
        <div class="detail-card shadow01">
            <div class="row">
                <!-- 뒤로가기 -->
                <div class="col text-left align-self-center write-title" style="display:block">
                    <div class="mt-3" style="margin-left:10% ">
                        <span> 
                            <b-img
                                v-bind:src="require('@/assets/img/icons8-chevron-left-48.png')"
                                class="write-left-btn"
                                @click="goToBoard()"
                                style="cursor:pointer"
                            />
                        </span> 상세보기
                    </div>
                    <div>
                    </div>
                </div>
                <!--/ 뒤로가기 -->

                <!-- 상태 -->
                
                <div class="col text-right align-self-center write-title" style="display:block">
                    <div>
                        <div v-if="!isWriter()" class="text-right"><b-img
                            type="image"
                            @click="clickFavorite(detailitem.itemId, detailitem.favorite)"
                            style="cursor:pointer"
                            :src= $favoriteImage(this.detailitem.favorite)
                            class="mr-2 pr-1 pl-1 item-list-heart"
                            ></b-img></div>
                    </div>
                    <div class="mt-3 mr-5">
                        <span @click="clickChangeStatus" v-if="!isWriter() || !isClickedStatus">
                            {{detailitem.status | itemStatus}}
                        </span>
                        <select  v-if="isWriter() && isClickedStatus" v-on:input="clickSelector($event.target.value)" style="height: 36.5px;">
                            <option v-for="option in periods" :key="option.aptid">{{option | itemStatus}}</option>
                        </select>
                    </div>
                    <div>
                    </div>
                </div>
                <!--/ 상태 -->

            </div>
            <!-- 수정 삭제 아이콘 -->
             <div  class ="text-right mt-2 mr-2">
                 <div v-if="isWriter()">
                     <!-- 기본 -->
                    <b-img v-if="!isUpdateChecked"
                            v-bind:src="require(`@/assets/img/icons8-pencil-120.png`)"
                            @click="clickUpdate()"
                            class="detail-icon"
                    ></b-img>
                    <!-- 수정 중 -->
                    <b-img v-if="isUpdateChecked"
                            v-bind:src="require(`@/assets/img/icons8-save-48.png`)"
                            @click="clickSave()"
                            class="detail-icon"
                    ></b-img>
                    <b-img
                            v-bind:src="require(`@/assets/img/icons8-trash-144.png`)"
                            @click="clickDetailDelete(detailitem.itemId)"
                            class="detail-icon"
                    ></b-img>
                 </div>
             </div>
            <!--/ 수정 삭제 아이콘-->
            <div class="row align-self-center">
                
                <!-- 이미지 -->
                <div class="col-6 mr-4 ml-4 mb-4 mt-1 align-self-center">
                           <img
                                class="detailImg"
                                :src = getImgUrl()
                            />
                </div>
                <!-- 정보 -->
                <div class="col-4 align-self-center" style="margin-bottom:auto">
                    <div v-if="!isUpdateChecked"  class="row">
                        <div class="detail-title-text">
                           {{this.detailitem.title}}
                        </div>
                    </div>
                    <div v-if="isUpdateChecked"  class="row">
                        <div>
                           <b-form-input
                            class="detail-title-text ml-2"
                            style="width:100%"
                            v-model="detailitem.title"
                          ></b-form-input>
                          
                        </div>
                    </div>
                    <!-- 기본 -->
                    <div v-if="!isUpdateChecked"  class="row mt-4">
                        <div class="col detail-price-text  text-right">
                            가격 : {{detailitem.price | comma}}
                        </div>
                    </div>
                    <!-- 수정 중 -->
                     <div v-if="isUpdateChecked"  class="row mt-4">
                           <span class="detail-price-text text-right"> 가격 : </span> 
                           <b-form-input
                            class="col detail-price-text detail-price-text-update text-right"
                            v-model="detailitem.price"
                          ></b-form-input>
                    </div>
                    <div class="row">
                        <div class="col detail-user-text text-right"> 
                            <span class=""> <img
                                class="headerProfile"
                                :src = getUserImgUrl()
                                style="width: 2.5rem; height: 2.5rem;"      
                                /> </span> 
                            
                            판매자 : {{ this.detailitem.user.name}}
                        </div>
                    </div>
                    
                     <hr class="featurette-divider" />
                    <div style="height:20px" class="row detail-date-text text-right">
                        <div class="col" style="height:10%">
                            <span> 
                                <b-img
                                    style="height:1000%"
                                    class="detail-clock-img"
                                    v-bind:src="require(`@/assets/img/icons8-clock-96.png`)"
                            />
                            </span>
                            <span class="ml-2">
                                {{detailitem.date | calculateTime}}
                            </span>
                        </div> 
                    </div>

                    <div class="row">
                        <!-- 수정중 -->
                        <div v-if="isUpdateChecked" class="col ml-3 mt-3 text-right">
                            <select v-model="detailitem.category" class="detail-category-text-update custom-select">
                                <option v-for="(category, idx) in categorys" :key="idx">
                                    {{ category }}
                                </option>
                            </select>
                        </div>
                        <!-- 기본-->
                        <div v-if="!isUpdateChecked" class="detail-date-text col mt-3 text-right">
                            카테고리 : {{detailitem.category}}
                        </div>
                    </div>

                    <!-- 기본 -->
                    <div v-if="!isUpdateChecked" class="row"> 
                        {{detailitem.content}}
                    </div>
                    <!-- 수정 중 -->
                     <div v-if="isUpdateChecked"  class="row">
                        <div class="detail-title-text-update">
                           <b-form-textarea
                            style="z-index:10;"
                            value="Contents"
                            placeholder="내용을 입력하세요."
                            rows="3"
                            v-model="detailitem.content"
                            ></b-form-textarea>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 메시지 버튼 -->
            <div class ="mt-5 mb-5 msg-button-div align-self-center">
                <button v-if="!isWriter()" @click="clickMakeChatingRoom()" class="align-self-center btn ml-1 msg-button" >메시지 보내기</button>
            </div>
            <!--/ 메시지 버튼 -->
        </div>
    </div>

    <!--/상세 창 -->


     

  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
import SERVER from '@/api/api'

import axios from 'axios'
import Swal from 'sweetalert2'

import Debounce from '@/debounceM.js'




(function(j,en,ni,fer) {
        j['dmndata']=[];j['jenniferFront']=function(args){window.dmndata.push(args)};
        j['dmnaid']=fer;j['dmnatime']=new Date();j['dmnanocookie']=false;j['dmnajennifer']='JENNIFER_FRONT@INTG';
        var b=Math.floor(new Date().getTime() / 60000) * 60000;var a=en.createElement(ni);
        a.src='https://d-collect.jennifersoft.com/'+fer+'/demian.js?'+b;a.async=true;
        en.getElementsByTagName(ni)[0].parentNode.appendChild(a);
    }(window,document,'script','b48dc09b'));



function getDetailReq(itemId, userId){
	this.itemId = itemId, // property
	this.userId = userId;  // property
}

function upDateStatusReq(itemId, status){
	this.itemId = itemId, // property
	this.status = status;  // property
}


function favoriteReq(userId , itemId){
    this.userId = userId,  // property
	this.itemId = itemId; // property
}
function makeChatReq(buyerId , itemId, sellerId){
    this.buyerId = buyerId,  // property
    this.itemId = itemId, // property
    this.sellerId = sellerId;  // property
}

export default {
    mixins:[Debounce],
    data: () => {
    return {
        itemId:"",
        deleteReq: "",
        isUpdateChecked: false,
        itemDetail:[],
        categorys:[],
        periods:["S", "I", "C"],
        isClickedStatus:false,
      };
   },
    created(){
        //아이템 id 확인
        this.itemId = this.$route.params.itemId;

        
        
        //이미지 주소
        this.serverPath = SERVER.IMAGE_STORE,

        //패치
        this.findMyAccount();
        this.fetchItemCategory();

        //디테일 요청
        this.getDetailReq = {itemId:this.itemId, userId:this.myaccount.userId }
        this.getDetailItem(this.getDetailReq);

        //적용
        this.deleteReq = this.itemId;
        this.categorys = this.itemCategorys;

    },
    mounted(){
        this.getDetailReq = {itemId:this.itemId, userId:this.myaccount.userId }
        this.getDetailItem(this.getDetailReq);
    }
    ,
    computed:{
        ...mapState(['myaccount']),
        ...mapState('itemStore',['detailitem']),
        ...mapState('categoryStore',['itemCategorys']),
    },
    methods:{
        ...mapActions('itemStore', ['getDetailItem', 'updateDetailItem','postFavoriteItemById','deleteFavoriteItemById','updateItemStatus']),
        ...mapActions('categoryStore', ['fetchItemCategory']),
        ...mapActions(['findMyAccount']),
        ...mapActions('chatStore', ['postChatingRoom','getChatingRooms']),

    //디테일의 작성자 판단
    isWriter(){
        if(this.myaccount.userId == this.detailitem.userId){
            return true;
        }
        return false;
    },
    getUserImgUrl(){
            if(this.detailitem.user.image){
                return this.serverPath +this.detailitem.user.image;
            }
            return this.serverPath + "icons8-male-user-90.png" 
        },
    getImgUrl(){
            if(this.detailitem.picture[0] ){
                return this.serverPath +this.detailitem.picture[0];
            }
            return this.serverPath + "no-image-icon-23487.png" 
        },

     deleteDetailItem(itemId){

        console.log("before : deleteDetailItem - " + itemId)
        axios.delete(SERVER.URL + SERVER.ROUTES.deletedetailitem + "/" + itemId)
          .then(res => {
          console.log("after : deleteDetailItem - " + res.data.isSuccess)
            if(res.data.isSuccess){
                console.log(res.data.data)
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



     //push
    goToBoard(){
        this.$router.push({ path: "/board" });
    },

    //click
    clickDetailDelete(){
        this.deleteDetailItem(this.deleteReq)
        this.goToBoard();
    },
    clickUpdate(){
        this.isUpdateChecked = !this.isUpdateChecked;
        return this.isUpdateChecked;
    },
    clickSave(){

        this.updateDetailItem(this.detailitem)
        this.isUpdateChecked = !this.isUpdateChecked;
        return this.isUpdateChecked;
    },
    clickFavorite(itemId){
        this.debounce(() => {
          this.clickFavoriteDebounce(itemId);
        }, 100);
    },
     async clickFavoriteDebounce(itemId){
            let req = new favoriteReq(this.myaccount.userId, itemId);
            try{
                if(this.detailitem.favorite){//좋아요 눌러져있으면
                    await this.deleteFavoriteItemById(req);//비동기
                }
                else{// 좋아요 안눌러져있으면
                    await this.postFavoriteItemById(req);//비동기
                }
            }catch{
                alert("에러")
            }finally{
                let req2 = new getDetailReq(this.deleteReq, this.myaccount.userId)
                this.getDetailItem(req2);//비동기
                this.isfavoriteBtn = !this.isfavoriteBtn;
            }

        },
    clickSelector(input){
        
        //alert(this.$itemStatusReverse(input))
        let req = new upDateStatusReq(this.itemId, this.$itemStatusReverse(input));
        this.updateItemStatus(req);
    },
    clickChangeStatus(){
        this.isClickedStatus = !this.isClickedStatus;
    },
     clickMakeChatingRoom(){
        let req = new makeChatReq(this.myaccount.userId, this.itemId, this.detailitem.userId )
         this.postChatingRoom(req);

        this.getChatingRooms(this.myaccount.userId);
    },
    }
}
</script>

<style scoped>
/* 판매중 버튼 */
.register-button{
     background-color: #2682ba !important;
    font-weight: bold !important;
    color: #ffffff !important;
    height: 2.5rem;
     width: 7rem;
}

/* 제목 */
.write-title{
    font-weight: bold !important;
    color:  #2682ba !important;
     font-size: 2rem;
}
.write-left-btn{
    width: 3%;
    opacity: 50%;
}

/* 검색 */
.search-button {
  background-color: #2682ba !important;
  font-weight: bold !important;
  color: #f8f8f8 !important;
  height: 2rem;
  width: 4rem;
}
.search-input{
    text-align: end;
}
.search_top_text{
    width: 20rem;
}


/* 디테일 */
.detail-card{
  border : 1px #2682ba55 solid;
  border-radius: 10px;
  height: 100%;
  width: 100%;
}
.shadow01 {
    box-shadow: 1px 1px 3px 3px #2682ba55;
    }
.msg-button{
     background-color: #e84c3d !important;
    font-weight: bold !important;
    color: #ffffff !important;
    height: 2.5rem;
     width: 10rem;
}
.msg-button-div{
    display: block;
    height: 10%;
}
.detailImg{
    width: 100%;
    height: 20rem;
}
.detail-icon{
    width: 4%;
}
.detail-title-text{
    font-weight: bold;
    font-size:2rem;
}
.detail-title-text-update{
    font-weight: bold;
    width: 100%;
    font-size:2rem;
}
.detail-price-text{
    font-weight: bold;
    color: #2682ba;
    font-size:1.5rem
}
.detail-user-text{
    font-weight: bold;
    color: #00000055;

}
.detail-date-text{
    font-weight: bold;
    color: #00000055;
}
.detail-clock-img{
    height: 20%;
    opacity: 50%;
}
.detail-price-text-update{
    width: 60%;
}
.detail-category-text-update{
    width: 50%;
}
</style>

