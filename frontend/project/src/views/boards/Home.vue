<template>
  <!-- 검색 창 -->
    <div class="container">
      <!-- <div>
        <form
          class="fleft"
          name="topSearchForm"
          id="topSearchForm"
          action="/goods/search"
        >
          <input type="hidden" name="keyword_log_flag" value="Y" />
          <div class="search-input">
            <b-img
              type="image"
              style="cursor:pointer"
              v-bind:src="require(`@/assets/img/icons8-search-120.png`)"
              width="25px"
            ></b-img>
            <input
              type="text"
              placeholder="물건명"
              v-on:keyup.enter="clickParamSearch(search_input_text)"
              v-model="search_input_text"
              value
              autocomplete="off"
              autofocus
              title="검색"
              class="search_top_text"
            />
            
            <button @click="clickParamSearch(search_input_text)" class="ml-1 btn search-button"  >검색</button>
            
          </div>
        </form>
      </div>
    
      <div class="row">
          <div class="col-5"  style="float : none; margin: 0 auto;">
            <hr class="featurette-divider" />
          </div>
      </div> -->
      <!--/ 검색 창 -->

      <!-- 이달의 다우인 -->
    <div>
        <div class ="row">
            <div class="col prize-title">
                이달의 다우인
            </div>
        </div>
        
        <div v-for="(prizeMember, idx) in ranks" :key="idx" class ="row m-auto prize-list-row">
            <div class="col-2">
                <img
                class=""
                :src= $commonUserImage(prizeImg[idx])
                style="width: 2rem; height: 2rem;"
                />
            </div>
            <div class="col-2 ml-auto">
                <img
                class="headerProfile mr-2"
                :src= $commonUserImage(prizeMember.image)
                style="width: 2.5rem; height: 2.5rem;"
                />
            </div>
            <div class="col-5 prize-name align-self-center">
                {{prizeMember.name}}
            </div>
            <div class="col-3 prize-pointer align-self-center">
                {{prizeMember.total}} Pt
            </div>
        </div>
    
    </div>


      <!--/이달의 다우인 -->



    <!-- 3D 캐러셀 -->
    <div class="mt-5">
        <carousel-3d  :width="450" :height="400">
            <div v-for="(item, i) in searcheditems" :key="i">
            <slide class="shadow01" id="carousel_card" :index="i">
                <!-- 이미지 사원설명 제품명 가격 -->
                <div class="item-card">
                    <!-- 이미지 -->
                    <div class="row item-list-row-2 p-3 pl-5 pr-5">
                         <img
                            class="item-list-img"
                            :src= getImgUrl(i)
                            
                            />
                    </div>
                    <!--/이미지 -->

                    <!-- 사원 -->
                    <div class="row pl-5 pr-5">
                        <div class="col-3 pr-3">
                            <div>
                                <img
                                class="headerProfile"
                                :src= $commonUserImage(item.user.image)
                                style="width: 2.5rem; height: 2.5rem;"      
                                />
                            </div>
                            <div class="  item-list-title-text">
                                제품명 : 
                            </div>
                            <div class="item-list-title-text">
                                가격 : 
                            </div>
                        </div>

                        <div class="col-5 align-self-center">
                             <div class="item-list-profile-text">
                                 <div>
                                    {{item.user.name}}
                                 </div>
                            </div>
                             <div class="item-list-product-text align-self-center">
                                    {{item.title}}
                            </div>
                             <div class=" item-list-price-text align-self-center">
                                     {{ item.price | comma}}
                            </div>
                        </div>
                         <div class="col-4 align-self-center">
                                 <div class="item-list-detail-btn">
                                    <button class="btn btn-info"  @click="goToDetail(item.itemId)">상세보기</button>
                                </div>
                        </div>



                    </div>

                    
                    
                   
                    
                </div>
                 <!--/ 이미지 사원설명 제품명 가격 -->
            </slide>
            </div>
        </carousel-3d>
    </div>


    <!--/ 3D 캐러셀 -->






    </div>
</template>


<script >
import { Carousel3d, Slide } from "vue-carousel-3d";
import { mapActions, mapState } from "vuex";

import SERVER from '@/api/api';


//import axios from "axios";


function searchAllReq(page, userId){
	this.page = page, // property
	this.userId = userId;  // property
}


export default {
  components: {
    Carousel3d,
    Slide,
  }
  ,
  data: () => {
    return {
      search_input_text:"",
      
      //이미지 주소
      serverPath : SERVER.IMAGE_STORE,

      //메달 이미지
      prizeImg:['icons8-gold-medal-48.png'
      ,'icons8-silver-medal-48.png',
      'icons8-bronze-medal-48.png']

   };
  },
  created() {
    this.findMyAccount();
    this.getAllItem(new searchAllReq(1, this.myaccount.userId));
    this.getWriteRank();

  },
  computed:{
      ...mapState(["myaccount"]),
      ...mapState('itemStore', ['searcheditems']),
      ...mapState('rankStore',['ranks']),
  },
  methods: {
       ...mapActions(['findMyAccount']),
       ...mapActions('itemStore', ['getAllItem']),
       ...mapActions('rankStore',['getWriteRank']),
       
    
        goToDetail(itemId){
             this.$router.push({path: `/board/detail/${itemId}`})
        },
      clickParamSearch(text){
        this.$router.push({name: 'Board', params: {searchtextparam: text}})
      },
       getImgUrl(idx){
            if(this.searcheditems[idx].picture[0]){
                return this.serverPath +this.searcheditems[idx].picture[0];
            }
            return this.serverPath + "no-image-icon-23487.png" 
        },
  }
};
</script>

<style scoped>

.search-button {
  background-color: #2682ba;
  font-weight: bold;
  color: #f8f8f8;
  height: 2rem;
  width: 4rem;
}

.prize-title{
    color: #2682ba;
    font-weight: bold;
    font-size:1.5rem;
}

.prize-list-row{
    width: 50%;
}

.prize-pointer{
    font-weight: bold;
    color: red;
}
.prize-name{
    font-weight: bold;
    color:#000000;
}


/* item */
.item-card{
    background-color: #f8f8f8;
    height: 100%;   
}
.item-list-img{
    width: 100%;
    height: 100%;
}

.item-list-row-2{
    height: 70%;
}

.item-list-profile-img{
    height: 100%;
    width: 100%;
}
.item-list-profile-text{
    font-weight: bold;
    height: 2.5rem; 
    padding-top:4%
    
}
.item-list-title-text{
    font-weight: bold;
}
.item-list-price-text{
    font-weight: bold;
    color: #2682ba;
}

.item-list-product-text{
    font-weight: bold;
    color: #2682ba;
     overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.item-list-detail-btn{
    height: 80%;
}

/* 케러세 중복정의 */
.carousel-3d-slide{
    border-radius: 5px;
    border-width:1.5px !important;
    border-color: #2682ba;
    background-color : #f8f8f8 !important;
    
   
    height: 95% !important;
    
}
.shadow01 {
    box-shadow: 2px 2px 5px 5px #2682ba;
    }




</style>
