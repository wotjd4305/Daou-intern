<template>
  <div>
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
              {{checkedNames}}
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
                class="search_top_text "
                />
                
                <button class=" btn ml-1 search-button" >검색</button>
            </div>
          </div>
    <!--/검색 창 -->


       <!-- 목록 창-->
     <div class="container ">
        <div class="row ">
            <div class="col-3 mt-4" v-for="(item, idx) in items" :key="idx">
                <div class="forHover">
                    <div class="item-list-card shadow01">
                        <b-img
                        type="image"
                        @click="goToDetail(item.id)"
                        style="cursor:pointer"
                        :src= getImgUrl(idx)
                        width="200rem"
                        height="150rem"
                        class="mt-3 mb-2"
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
                                    {{item.date}} 
                                </span>
                            </div>
                            <div class="ml-4 pl-2 text-left">
                                <span class="item-list-title-text">
                                    가격 : 
                                </span>
                                <span class="item-list-title-contents">
                                    {{item.price}} 
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

export default {
    
    data: () => {
    return {
      categorys: [],
      checkedNames:[],
       dateFormat:"",
       items:[],

       serverPath:"",
      };
    },
    created(){

         //패치
        this.fetchItemCategory();
        this.getAllItem(this.myaccount.userId);
        this.findMyAccount();
        
        //적용
        this.categorys = this.itemCategorys;
        this.items = this.searcheditems;

        this.serverPath = SERVER.IMAGE_STORE,
        this.dateFormat = "YYYY-MM-DD hh-mm-ss";



        //더미데이타

       //this.categorys = ["디지털/가전","가구/인테리어","생활/가공식품","유아동/유아도서", "무료나눔", "여성잡화", "여성의류"],
       
    //    this.items = [ 
    //    {id:"1",title:"홍삼1",date:this.getDate(),category:"생활/가공식품", price:"17,000", picture:"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbNQI5h%2FbtqGJuUCIN5%2FemfrZIKbSQvU9AYp9xXWhK%2Fimg.jpg"}
    //   ,{id:"2",title:"홍삼2",date:this.getDate(),category:"생활/가공식품", price:"17,000", picture:"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbNQI5h%2FbtqGJuUCIN5%2FemfrZIKbSQvU9AYp9xXWhK%2Fimg.jpg"}
    //   ,{id:"3",title:"홍3미",date:this.getDate(),category:"생활/가공식품",price:"27,000", picture:"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbNQI5h%2FbtqGJuUCIN5%2FemfrZIKbSQvU9AYp9xXWhK%2Fimg.jpg"}
    //   ,{id:"4",title:"홍삼5",date:this.getDate(),category:"생활/가공식품", price:"37,000", picture:"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbNQI5h%2FbtqGJuUCIN5%2FemfrZIKbSQvU9AYp9xXWhK%2Fimg.jpg"}
    //   ,{id:"5",title:"홍삼6",date:this.getDate(),category:"생활/가공식품", price:"47,000", picture:"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbNQI5h%2FbtqGJuUCIN5%2FemfrZIKbSQvU9AYp9xXWhK%2Fimg.jpg"}
    //   ,{id:"6",title:"홍삼7",date:this.getDate(),category:"생활/가공식품", price:"57,000", picture:"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbNQI5h%2FbtqGJuUCIN5%2FemfrZIKbSQvU9AYp9xXWhK%2Fimg.jpg"}
    //   ,{id:"7",title:"홍삼8",date:this.getDate(),category:"생활/가공식품", price:"77,000", picture:"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbNQI5h%2FbtqGJuUCIN5%2FemfrZIKbSQvU9AYp9xXWhK%2Fimg.jpg"}
    //   ,{id:"8",title:"홍삼9",date:this.getDate(),category:"생활/가공식품", price:"87,000", picture:"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbNQI5h%2FbtqGJuUCIN5%2FemfrZIKbSQvU9AYp9xXWhK%2Fimg.jpg"}
    //   ,{id:"9",title:"홍삼10",date:this.getDate(),category:"생활/가공식품", price:"97,000", picture:"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbNQI5h%2FbtqGJuUCIN5%2FemfrZIKbSQvU9AYp9xXWhK%2Fimg.jpg"}
    //   ,{id:"10",title:"홍삼11",date:this.getDate(),category:"생활/가공식품", price:"117,000", picture:"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbNQI5h%2FbtqGJuUCIN5%2FemfrZIKbSQvU9AYp9xXWhK%2Fimg.jpg"}
    //   ,{id:"11",title:"홍삼12",date:this.getDate(),category:"생활/가공식품",price:"127,000", picture:"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbNQI5h%2FbtqGJuUCIN5%2FemfrZIKbSQvU9AYp9xXWhK%2Fimg.jpg"}
    //   ,{id:"12",title:"홍삼13",date:this.getDate(),category:"생활/가공식품", price:"137,000", picture:"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbNQI5h%2FbtqGJuUCIN5%2FemfrZIKbSQvU9AYp9xXWhK%2Fimg.jpg"} ]
    
    }
    ,
    computed:{
        ...mapState('categoryStore',['itemCategorys']),
        ...mapState('itemStore', ['searcheditems']),
        ...mapState(['myaccount']),
    },
    methods:{
          ...mapActions('categoryStore', ['fetchItemCategory']),
          ...mapActions('itemStore', ['getAllItem']),
          ...mapActions(['findMyAccount']),

        getImgUrl(idx){
            //console.log(this.items[idx].id + " -- " + this.items[idx].picture)
            if(this.items[idx].picture[0]){
                return this.serverPath + this.items[idx].picture[0];
            }
            return this.serverPath + "no-image-icon-23487.png" 
        },
        getDate(){
             var date = new Date();
            var year = date.getFullYear();
            var month = date.getMonth() + 1;
            var day = date.getDate();

            if (day < 10) day = "0" + day;
            if (month < 10) month = "0" + month;

            var startdate = year + "-" + month + "-" + day;

            return startdate;
        },
        goToDetail(itemId){
             this.$router.push({path: `/board/detail/${itemId}`})
        },
        goToWrite(){
            this.$router.push({ path: "/board/write" });    
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

