<template>
  <div>

    <!-- 상세 창 -->
    <div class="container mt-5 ">
        <div class="detail-card shadow01">
            <div class="row">
                <div class="col text-left align-self-center write-title" style="display:block">
                    <div class="mt-3" style="margin-left:10% ">
                       <span> 
                           <b-img
                                    v-bind:src="require('@/assets/img/icons8-chevron-left-48.png')"
                                    class="write-left-btn"
                                    @click="goToBoard()"
                                    style="cursor:pointer"
                                />
                    </span>
                     글작성
                    </div>
                      <div>
                        <hr class="featurette-divider"  style="width:90%"/>
                     </div>
                </div>
                
            </div>
            <div class="row align-self-center">
                
                <!-- 왼쪽 이미지-->
                <div class = "col">
                    <div>
                        <div class = "row m-5">
                        <!-- 이미지 -->
                    
                            <div class="col-4  mt-1 align-self-center">
                                    <b-img
                                            class="detailImg"
                                            v-bind:src="require('@/assets/img/no-image-icon-23487.png')"
                                        />
                            </div>
                            <!-- 이미지 -->
                            <div class="col-4  mt-1 align-self-center">
                                    <b-img
                                            class="detailImg"
                                            v-bind:src="require('@/assets/img/no-image-icon-23487.png')"
                                        />
                            </div>
                            <!-- 이미지 -->
                            <div class="col-4  mt-1 align-self-center">
                                    <b-img
                                            class="detailImg"
                                            v-bind:src="require('@/assets/img/no-image-icon-23487.png')"
                                        />
                            </div>
                            <!-- 이미지 -->
                            <div class="col-4  mt-1 align-self-center">
                                    <b-img
                                            class="detailImg"
                                            v-bind:src="require('@/assets/img/no-image-icon-23487.png')"
                                        />
                            </div>
                            <!-- 이미지 -->
                            <div class="col-4  mt-1 align-self-center">
                                    <b-img
                                            class="detailImg"
                                            v-bind:src="require('@/assets/img/no-image-icon-23487.png')"
                                        />
                            </div>
                        </div>
                        <div class="row ">
                            <div class="col align-self-center">
                                <button class="align-self-center btn write-register-button"> 사진등록</button>
                            </div>
                        </div>
                    </div>
                </div>
                

                <!-- 오른쪽 정보 -->
                <div class="col mt-5 mb-5 ml-1 mr-1">
                    <div class="detail-title-text text-left ">
                        제목
                    </div>
                    <div class="row">
                        <div class="write-width">
                            <input v-model="boardWriteData.title" type="text" class="form-control" placeholder="제목을 입력해주세요.">
                        </div>
                    </div>

                     <div class="detail-title-text text-left ">
                        가격
                    </div>
                    <div class="row">
                        <div class="write-width">
                            <input v-model="boardWriteData.price" type="text" class="form-control" placeholder="제목을 입력해주세요.">
                        </div>
                    </div>
                   
                    <div class="detail-title-text text-left ">
                        카테고리
                    </div>
                    <div class="row">
                        <select v-model="boardWriteData.category" class="custom-select">
                            <option v-for="(category, idx) in categorys" :key="idx">
                                {{ category }}
                            </option>
                        </select>
                    </div>

                    <div class="detail-title-text text-left ">
                        내용
                    </div>
                    <div class="row">
                        <div class="write-width">
                            <textarea v-model="boardWriteData.content" class="form-control" id="exampleFormControlTextarea1" rows="6"></textarea>
                        </div>
                    </div>
                   
                </div>

                
            </div>
            <!-- 작성 버튼 -->
            <div class ="mt-5 mb-5 msg-button-div align-self-center">
                <button @click="clickBoardWrite" class="align-self-center btn ml-1 write-register-button" >작성하기</button>
            </div>
            <!--/ 작성 버튼 -->
        </div>
    </div>


    <!--/상세 창 -->
    

     

  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'

function boardWriteDataReq(category, content, images, price, title ,userId){
	this.category = category, // property
    this.content = content;  // property
    this.images = images, // property
    this.price = price;  // property
    this.title = title, // property
	this.userId = userId;  // property
}


export default {
    data: () => {
    return {
        categorys: [],
        boardWriteData: new boardWriteDataReq(),
      };
    },
    created(){
        //패치
        this.fetchItemCategory();
        this.findMyAccount();
        
        //적용
        this.categorys = this.itemCategorys;
    },
    computed:{
        ...mapState('categoryStore',['itemCategorys']),
        ...mapState(['myaccount'])
        
    }
    ,
    methods:{
        ...mapActions('categoryStore', ['fetchItemCategory']),
        ...mapActions('itemStore', ['fetchItemCategory','boardWrite']),
        ...mapActions('itemStore', ['getAllItem']),
        ...mapActions(['findMyAccount']),

       //click
       clickBoardWrite() {

            const formData = new FormData();
            
            
            formData.append("category", this.boardWriteData.category);
            formData.append("content", this.boardWriteData.content);
            formData.append("price", this.boardWriteData.price);
            formData.append("title", this.boardWriteData.title);
            formData.append("userId", this.myaccount.userId);

            this.boardWriteData = formData;

            console.log("click write " +formData)
            console.log(formData.get("category"))
            console.log(this.myaccount.userId)
            
            

            this.boardWrite(this.boardWriteData)
        },

       //push
       goToBoard(){
            this.$router.push({ path: "/board" });
        },
    },
}
</script>

<style scoped>
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



/* 디테일 */
.detail-card{
  border : 1px #2682ba55 solid;
  border-radius: 10px;
  height: 100%;
  width: 80%;
  margin : auto;
}
.shadow01 {
    box-shadow: 1px 1px 3px 3px #2682ba55;
    }

.write-register-button{
     background-color: #2682ba !important;
    font-weight: bold !important;
    color: #ffffff !important;
    height: 2.5rem;
     width: 7rem;
}

.detailImg{
    width: 100%;
    height: 100%;
}
.detail-icon{
    width: 4%;
}
.detail-title-text{
    color: #2682ba;
    font-weight: bold;
    font-size:1.2rem;
}
.custom-select{
  width:80%
}
.write-width{
    width: 80%;
}

</style>

