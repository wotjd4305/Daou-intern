<template>
  <div>
    <!-- 검색 창 -->
    <div class="container">
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
    </div>
    <!--/검색 창 -->


    <!-- 상세 창 -->
    <div class="container mt-5">
        <div class="detail-card shadow01">
            <!-- 수정 삭제 아이콘 -->
             <div  class ="text-right mt-2 mr-2">
                 <div v-if="isWriter()">
                    <b-img
                            v-bind:src="require(`@/assets/img/icons8-pencil-120.png`)"
                            class="detail-icon"
                    ></b-img>
                    <b-img
                            v-bind:src="require(`@/assets/img/icons8-trash-144.png`)"
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
                                 src="https://t1.daumcdn.net/cfile/blog/216CB83A54295C1C0E"
                            />
                </div>
                <!-- 정보 -->
                <div class="col-4 align-self-center">
                    <div class="row">
                        <div class="detail-title-text">
                           {{this.detailitem.title}}
                        </div>
                    </div>
                    <div class="row mt-4">
                        <div class="col detail-price-text text-right">가격 : {{this.comma(detailitem.price)}}</div>
                    </div>
                    <div class="row">
                        <div class="col detail-user-text text-right"> 
                            <span class=""> <img
                                class="headerProfile"
                                :src = getImgUrl()
                                style="width: 2.5rem; height: 2.5rem;"      
                                /> </span> 
                            
                            판매자 : {{ this.detailitem.user.name}}
                        </div>
                    </div>
                     <hr class="featurette-divider" />
                    <div class="row detail-date-text text-right">
                        <div class="col">
                            <span> 
                                <b-img
                                    class="detail-clock-img"
                                    v-bind:src="require(`@/assets/img/icons8-clock-96.png`)"
                            />
                            </span>
                            <span class="ml-2">
                                {{calculateTime(this.detailitem.date)}}
                            </span>
                        </div> 
                    </div>
                    <div class="row"> {{this.detailitem.content}}</div>
                    
                </div>
            </div>
            <!-- 메시지 버튼 -->
            <div class ="mt-5 mb-5 msg-button-div align-self-center">
                <button class="align-self-center btn ml-1 msg-button" >메시지 보내기</button>
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
import moment from 'moment'


export default {
    data: () => {
    return {
        itemId:"",
        getDetailReq:{
            itemId:"",
            userId:"",
        },
      };
    },
   
    created(){
        //아이템 id 확인
        this.itemId = this.$route.params.itemId;
        console.log("init : BoardDetail = " +this.itemId )
        
        //이미지 주소
        this.serverPath = SERVER.IMAGE_STORE,

        //패치
        this.findMyAccount();

        //디테일 요청
        this.getDetailReq.itemId = this.itemId;
        this.getDetailReq.userId = this.myaccount.userId;
        this.getDetailItem(this.getDetailReq);
    }
    ,
    computed:{
        ...mapState(['myaccount']),
        ...mapState('itemStore',['detailitem'])
    },
    methods:{
        ...mapActions('itemStore', ['getDetailItem']),
        ...mapActions(['findMyAccount']),

    //가격 필터
    comma(val){
        if(val == 0) return "무료나눔♥"
        return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
     },
    //시간 필터
    calculateTime(){
        var nowTime = moment();
        var writeTime = moment(this.detailitem.date, 'YYYY-MM-DD HH:mm:ss');

        var diffHour = moment.duration(nowTime.diff(writeTime)).asHours();
        var diffDay = moment.duration(nowTime.diff(writeTime)).asDays();
        var diffMin = moment.duration(nowTime.diff(writeTime)).asMinutes();
        
        //60분 이내
        if(diffMin < 60){
            return parseInt(diffMin) + "분 전"
        }
        //24시간 이내
        if(diffHour < 24){
            return parseInt(diffHour) + "시간 전";
        }
        //7일 이내
        if(diffDay < 7){
            return parseInt(diffDay) + "일 전";
        }
        return writeTime
        
      
    },
    //디테일의 작성자 판단
    isWriter(){
        if(this.myaccount.userId == this.detailitem.user.userId){
            return true;
        }
        return false;
    },
    getImgUrl(){
            if(this.detailitem.user.image){
                return this.serverPath +this.detailitem.user.image;
            }
            return this.serverPath + "no-image-icon-23487.png" 
        },
    },
    searchA(text){
        alert(text);
    },
}
</script>

<style scoped>
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
    height: 100%;
}
.detail-icon{
    width: 4%;
}
.detail-title-text{
    font-weight: bold;
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

</style>

