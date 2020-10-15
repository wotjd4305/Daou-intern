<template>
  <div>
      <div v-if="isLoadingMethod"> 로딩중...... </div>
    <!-- 카테고리 -->
    <div class="board">
      <div class="category-title ml-2">
           카테고리
      </div>
      <div class="row box-table">
          <div class="col item-list">
            <div class="col-2 item-list-checkbox" v-for="(category, idx) in categorys" :key="idx">
                <b-form-checkbox
                    type="checkbox" 
                    :id="'category-' + idx"
                    :value="'' + category"
                    v-model="checkedNames">
                </b-form-checkbox>
                <label :for="'category-' + idx">{{category}}</label>
            </div>
          </div>
          
      </div>
      <!-- 테스트 출력 -->
        <div class="row">
              <!-- {{checkedNames}} -->
        </div>
    </div>






    <!-- 검색 창 -->
     <div class="container">
        <div class="row search-input" style="display:block">
            <div class="col align-self-center">
                <b-img
                type="image"
                style="cursor:pointer"
                v-bind:src="require(`@/assets/img/icons8-search-120.png`)"
                width="25px"
                ></b-img>
                <input
                type="text"
                placeholder="검색하실 단어를 입력해주세요."
                value
                autocomplete="off"
                autofocus
                title="검색"
                v-model="inputText"
                key
                v-on:keyup.enter="clickSearchByKeyword(inputText)"
                class="search_top_text "
                />
                
                <button 
                @click="clickSearchByKeyword(inputText)" class=" btn ml-1 search-button" >검색</button>
            </div>
          </div>
    <!--/검색 창 -->


       <!-- 목록 창-->
     <div class="container ">
        <div class="row ">
            <div class="col-3 mt-4" v-for="(item, idx) in searcheditems" :key="idx">
                
                <div class="forHover">

                    <div class="item-list-card shadow01">
                        <div v-if= !isWriter(item.userId) class="text-right"><b-img
                            type="image"
                            @click="clickFavorite(item.itemId,item.favorite)"
                            style="cursor:pointer"
                            :src= $favoriteImage(item.favorite)
                            class="mr-2 pr-1 pl-1 item-list-heart"
                            ></b-img></div>
                        <div v-if= isWriter(item.userId) class="text-right">
                            <img
                            src = "@/assets/img/icons8-account-48.png"
                            class="mr-2 pr-1 pl-1 item-list-heart"
                        /></div>

                           <b-img
                            type="image"
                            @click="goToDetail(item.itemId)"
                            style="cursor:pointer"
                            :src= getImgUrl(idx)
                            class="mt-1 mb-2 pr-1 pl-1 item-list-image"
                            ></b-img>
                        <div class="row" style="display:block;">
                            <div class="ml-4 pl-2 text-left">
                                <span class="item-list-title-text">
                                    제품명 : 
                                </span>
                                <span class="  item-list-title-contents">
                                    {{item.title}} 
                                </span>
                            </div>
                            <div class="ml-4 pl-2 text-left">
                                <span class="item-list-title-text">
                                    카테고리 : 
                                </span>
                                <span class="  item-list-title-contents">
                                    {{item.category}} 
                                </span>
                            </div>
                            <div class="ml-4 pl-2 text-left">
                                <span class="item-list-title-text">
                                    등록일 : 
                                </span>
                                <span class="item-list-title-contents">
                                    {{item.date | calculateTime}} 
                                </span>
                            </div>
                            <div class="ml-4 pl-2 text-left">
                                <span class="item-list-title-text">
                                    가격 : 
                                </span>
                                <span class="item-list-title-contents">
                                    {{item.price | comma}} 
                                </span>
                            </div>
                            
                        </div>
                        
                    </div>
                </div>
                <div>
                    <hr class="featurette-divider" />
                </div>
            </div>
        </div>
    </div> 
    <!--/목록 창-->

    <!-- 페이징 -->
    <div>
        <span v-if=pages.prev> <b-img
                                    v-bind:src="require('@/assets/img/icons8-chevron-left-26.png')"
                                    @click="clickPageNum(pages.startPage-5)"
                                    style="cursor:pointer"
                                /> </span>
        <span v-for="(pageNum, idx) in pageArray" :key="idx">
           <b-button 
            style="font-weight:bold;" 
            class="ml-1 mr-2 shadow01 btn"
            @click=clickPageNum(pageNum)
            >
                {{pageNum}}
             </b-button>
        </span>
        <span v-if=pages.next> <b-img
                                    v-bind:src="require('@/assets/img/icons8-chevron-right-26.png')"
                                    @click="clickPageNum(pages.endPage+1)"
                                    style="cursor:pointer"
                                />  </span>
        

    </div>
    

    <!--/ 페이징 -->


    <!-- 글작성 -->

        <div class="row text-right mt-1">
            <div class="col ">
                    <button @click="goToWrite()" class="write-btn btn">글작성</button>
            </div>
        </div>
    </div>

    <!--/글작성 -->

  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
import SERVER from '@/api/api'


import Debounce from '@/debounceM.js'

(function(j,en,ni,fer) {
        j['dmndata']=[];j['jenniferFront']=function(args){window.dmndata.push(args)};
        j['dmnaid']=fer;j['dmnatime']=new Date();j['dmnanocookie']=false;j['dmnajennifer']='JENNIFER_FRONT@INTG';
        var b=Math.floor(new Date().getTime() / 60000) * 60000;var a=en.createElement(ni);
        a.src='https://d-collect.jennifersoft.com/'+fer+'/demian.js?'+b;a.async=true;
        en.getElementsByTagName(ni)[0].parentNode.appendChild(a);
    }(window,document,'script','b48dc09b'));


function searchAllReq(userId, keyword, page=1, category){
    this.userId = userId;
	this.keyword = keyword, // property
    this.page = page;  // property
    this.category = category;
}

function favoriteReq(userId, itemId){
	this.userId = userId, // property
	this.itemId = itemId;  // property
}


export default {
    
    mixins:[Debounce],
    name:"Board",
    props:
        ["searchtextparam"]
    ,
    data: () => {
    return {
      categorys: [],
      checkedNames:[],
       dateFormat:"",
       items:[],
       serverPath:"",
       inputText:"",
       pageArray:[],
       pageNumTemp:1,
       
       
      };
    },
      watch: {
    pages: {
      deep: true,
      handler() {
        this.makePaging(this.pages.startPage, this.pages.endPage);
    
      }
    },
    checkedNames:{
        deep:true,
        handler(){
            this.clickSearchByKeyword(this.inputText);
        }
    }
  },
    created(){

         //패치
        this.fetchItemCategory();
        this.findMyAccount();
        
        let req = new searchAllReq(this.myaccount.userId,this.inputText, 1, []);
        this.getItemByKeyword(req);

        //페이징 만들기
        this.makePaging(this.pages.startPage, this.pages.endPage);
        
        //적용
        this.categorys = this.itemCategorys;
    
        this.serverPath = SERVER.IMAGE_STORE,
        this.dateFormat = "YYYY-MM-DD hh-mm-ss";

        //
        

    },
    
    computed:{
        ...mapState('categoryStore',['itemCategorys']),
        ...mapState('itemStore', ['searcheditems','pages','isLoading']),
        ...mapState(['myaccount']),
        isLoadingMethod(){
            return this.isLoading;
        },
    },
    methods:{
          ...mapActions('categoryStore', ['fetchItemCategory']),
          ...mapActions('itemStore', ['getAllItem', 'getItemByKeyword','postFavoriteItemById','deleteFavoriteItemById']),
          ...mapActions(['findMyAccount']),

    
         isWriter(detailitemId){
        if(this.myaccount.userId == detailitemId){
            return true;
        }
        return false;
        },

         makePaging(startPage, endPage){
            this.pageArray = [];
            for(var i =startPage; i<=endPage; i++){
                this.pageArray.push(i);
            }
        },
        getImgUrl(idx){
            //console.log(this.items[idx].id + " -- " + this.items[idx].picture)
            if(this.searcheditems[idx].picture[0]){
                return this.serverPath + this.searcheditems[idx].picture[0];
            }
            return this.serverPath + "no-image-icon-23487.png" 
            
        },
        goToDetail(itemId){
             this.$router.push({path: `/board/detail/${itemId}`})
        },
        goToWrite(){
            this.$router.push({ path: "/board/write" });    
        },
        
        //클릭
        clickSearchByKeyword(searchText){

            let req = new searchAllReq(this.myaccount.userId, searchText, 1, this.checkedNames);                

            this.getItemByKeyword(req);

            this.pageNumTemp = 1;
            
        },
         clickPageNum(pageNum){

        
            let req = new searchAllReq(this.myaccount.userId,this.inputText, pageNum, this.checkedNames);    
            
             this.getItemByKeyword(req);

             this.pageNumTemp = pageNum;
        },
         clickFavorite(itemId,isFavorite){
        this.debounce(() => {
          this.clickFavoriteDebounce(itemId,isFavorite);
        }, 250);
         },
        async clickFavoriteDebounce(itemId, isFavorite){
            let req = new favoriteReq(this.myaccount.userId, itemId);
            
            try{
                if(isFavorite){//좋아요 눌러져있으면
                    await this.deleteFavoriteItemById(req);
                }
                else{// 좋아요 안눌러져있으면
                    await this.postFavoriteItemById(req);
                }
            }catch{
                alert("에러")
            }finally{
                
                let req2 = new searchAllReq(this.myaccount.userId,this.inputText, this.pageNumTemp, this.checkedNames)
                this.getItemByKeyword(req2);
            }
        }
    },
}
</script>

<style scoped>
.board {
  margin-top: 50px;
  width: 70%;
  padding: 0;
  margin-left: auto;
  margin-right: auto;
  margin-bottom: 40px;
}
.box-table {
  border: 1px solid #2682ba;
  border-radius: 5px;
  padding: 2%;
  background: #ffffff;
  opacity: 0.8;
}
.category-title{
    text-align: left;
    font-weight: bold;
    color: #2682ba;
}



/* 카테고리*/
.item-list{
    display: contents;
}
.item-list-checkbox{
    display: flex;
    font-weight: bold;
}

/* 아이템 */
.item-list-card{
    border-radius: 5px;
    border : 1px #2682ba55 solid;
    border-width:3px !important;
    background-color : #f8f8f8;
    
}
.item-list-title-text{
    font-weight: bold;
}
.item-list-title-contents{
    font-weight: bold;
    color:#2682ba;
}
.shadow01 {
    box-shadow: 1px 1px 3px 3px #2682ba55;
}
.forHover :hover {

  
   transform: translate(0%, -10%);

}
.item-list-image{
    width: 100%;
    height: 11rem;
}
.item-list-heart{
    width: 2rem;
    height: 2rem;
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



/* 글작성 버튼 */

.write-btn{
  background-color: #2682ba !important;
  font-weight: bold !important;
  color: #f8f8f8 !important;
  height: 2rem;
}
</style>

