<template>
  <div>
    <div class="shadow01 container p-1 mt-5 bg-light-ivory signup-form">
      <h3>회원 수정</h3>

      <!-- 프로필과 입력창 -->
      <div class="row ">

        <!-- 프로필 -->
        <b-col align-self="stretch center">
              <b-form-text class="mb-2" id="profile-help">프로필 클릭 시, 이미지 변경이 가능합니다.</b-form-text>
              <input
                type="file"
                ref="profileImg"
                style="display: none"
                accept="image/jpeg, jpg, png/"
                @change="clickUploadImage($event)"
              />

              <button class="pro-button" id="profileImgBtn" @click="$refs.profileImg.click()">

                 <div>
                    <img
                      class="profileImg headerProfile"
                      ref="uploadItemImage"
                      id="uploadImagdId"
                      :src= this.$commonUserImage(imageTemp)
                      accept="image/jpeg, jpg, png/"
                      style="width: 10rem; height: 10rem;"
                    />
                  </div>
                
              </button>
              <br />
              <!-- 프로필 삭제 아직 덜만듦!!!!! -->
              <b-button
                class="mt-2"
                size="sm"
                variant="light"
                id="deleteImg"
                @click="clickUpDeleteImage()"
              >프로필 삭제</b-button>
        </b-col>

        <!-- 입력창 -->
        <div class="col">
          <div class="input-with-label">
            <input 
              v-model="userUpdateData.password" 
              
              v-bind:class="{error : error.password, complete:!error.password&&userUpdateData.password.length!==0}"
              class="inputs"
              id="password" 
              type="password"
              placeholder="비밀번호를 입력하세요." 
              required
            />
            <label for="password"></label>
            <div class="error-text ml-3" v-if="error.password">{{error.password}}</div>
          </div>

          <div class="input-with-label">
            <input
              v-model="userUpdateData.passwordConfirm"
              type="password"
              id="password-confirm"
              v-bind:class="{error : error.passwordConfirm, complete:!error.passwordConfirm&&userUpdateData.passwordConfirm.length!==0}"
              placeholder="비밀번호를 다시 입력해주세요."
              class="inputs"
              required
              @keyup.enter="clickUpdate"
            />
            <label for="password-confirm"></label>
            <div class="error-text ml-3" v-if="error.passwordConfirm">{{error.passwordConfirm}}</div>
          </div>
          <div class="input-with-label mt-2">
            <select v-model="userUpdateData.department" class=" custom-select">
              <option  v-for="(depart, idx) in departs" :key="idx">{{ depart }}</option>
            </select>
          </div>
        </div>

      </div>

      <div class="buttons mt-3">
        <button class="btn signup-button" :class="{disabled: !isSubmit}" @click="clickUpdate">수정하기</button>
      </div>
      
    </div> 

    <div class="container">
     <hr class="featurette-divider" />
        <div class="detail-title-text"> 작성한 게시글 </div>
    </div>


      <!-- 작성 목록 창-->
     <div class="container ">
        <div class="row ">
            <div class="col-4 mt-4" v-for="(item, idx) in iditems" :key="idx">
                <div class="forHover">
                    <div class="item-list-card shadow01">
                           <b-img
                            type="image"
                            @click="goToDetail(item.itemId)"
                            style="cursor:pointer"
                            :src= getItemImgUrl(idx)
                            class="mt-3 mb-2 pr-1 pl-1 item-list-image"
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

    <div class="container">
     <hr class="featurette-divider" />
        <div class="detail-title-text"> 찜 목록 </div>
    </div>

    <!-- 찜 목록 창-->
     <div class="container ">
        <div class="row ">
            <div class="col-4 mt-4" v-for="(item, idx) in favorititems" :key="idx">
                <div class="forHover">
                    <div class="item-list-card shadow01">
                         <div class="text-right"><b-img
                            type="image"
                            @click="clickFavorite(item.itemId,item.favorite)"
                            style="cursor:pointer"
                            :src= $favoriteImage(!item.favorite)
                            class="mr-2 pr-1 pl-1 item-list-heart"
                            ></b-img></div>
                           <b-img
                            type="image"
                            @click="goToDetail(item.itemId)"
                            style="cursor:pointer"
                            :src= getFavoriteItemImgUrl(idx)
                            class="mt-3 mb-2 pr-1 pl-1 item-list-image"
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
    <!--/ 찜 목록 창-->




    
  </div>




</template>

<script>
import { mapActions, mapState } from 'vuex'
import SERVER from '@/api/api'

function favoriteReq(itemId, userId){
	this.itemId = itemId, // property
	this.userId = userId;  // property
}

//import axios from 'axios'
export default {
  name: 'Signup',
  data() {
    return {
      //departs: ["서비스 개발부", "웹서비스 개발부", "인프라 팀"],
      departs: [],
      userUpdateData: {
        empNum : "",
        password: "",
        passwordConfirm: "",
        department: "",
      },
      error: {
        password: false,
        passwordConfirm: false,
        department: false,
      },
      isSubmit: false,
      defaultPath: "icons8-male-user-90.png",
      serverPath: SERVER.IMAGE_STORE,
      imageTemp: "",
      
    };
  },
  created() {
    //default 이미지
    //this.myaccount.image = this.defaultPath;
    
    //패치
    this.findMyAccount();
    this.getItemById(this.myaccount.userId);
    this.getFavoriteItemById(this.myaccount.userId);
    this.fetchDepartmentCategory();
 
    //this.checkPathNull();
   
   //입력란에 없기 때문에
    this.userUpdateData.empNum = this.myaccount.empNum;
    this.departs = this.departmentCategorys;

    //
    this.imageTemp = this.myaccount.image;
  },
  watch: {
    userUpdateData: {
      deep: true,
      handler() {
        this.checkPasswordForm();
        this.checkPasswordConfirmationForm();
      }
    },
    myaccount:{
      deep:true,
      handler(){
        this.imageTemp = this.myaccount.image;
      }
    }
   
  },
  computed:{
    ...mapState(['myaccount']),
    ...mapState('categoryStore',['departmentCategorys']),
    ...mapState('itemStore', ['iditems','favorititems']),
 
  },
  methods: {
    ...mapActions("accountStore",["updateUser","uploadImg","deleteUserImg"]),
    ...mapActions('categoryStore', ['fetchDepartmentCategory']),
    ...mapActions('itemStore', ['getItemById','getFavoriteItemById','postFavoriteItemById','deleteFavoriteItemById']),
    ...mapActions(['findMyAccount']),

    getItemImgUrl(idx){
            //console.log(this.items[idx].id + " -- " + this.items[idx].picture)
            if(this.iditems[idx].picture[0]){
                return this.serverPath + this.iditems[idx].picture[0];
            }
            return this.serverPath + "no-image-icon-23487.png" 
            
      },
    getFavoriteItemImgUrl(idx){
            //console.log(this.items[idx].id + " -- " + this.items[idx].picture)
            if(this.favorititems[idx].picture[0]){
                return this.serverPath + this.favorititems[idx].picture[0];
            }
            return this.serverPath + "no-image-icon-23487.png" 
            
      },
    checkPasswordForm() {
      if (this.userUpdateData.password.length > 0 && this.userUpdateData.password.length < 8) {
          this.error.password = "비밀번호가 너무 짧아요"
        } else if ( this.userUpdateData.password.length >= 8 && !this.validPassword(this.userUpdateData.password) ) {
          this.error.password = "영문, 숫자 포함 8 자리 이상이어야 해요.";
        } else this.error.password = false;
    },
    validPassword(password) {
      var va = /^(?=.*\d)(?=.*[a-zA-Z]).{8,}$/;
      return va.test(password);
    },
    checkPasswordConfirmationForm() {
      if (this.userUpdateData.password.length >= 8 && this.validPassword(this.userUpdateData.password)) {
         if (this.userUpdateData.password !== this.userUpdateData.passwordConfirm )
        this.error.passwordConfirm = "비밀번호가 일치하지 않아요."
      else this.error.passwordConfirm = false;
      }
      
      // 버튼 활성화
      if (this.userUpdateData.password.length > 0 && this.userUpdateData.passwordConfirm.length > 0 && this.userUpdateData.department.length > 0){
        let isSubmit = true;
        Object.values(this.error).map(v => {
          if (v) isSubmit = false;
        });
        this.isSubmit = isSubmit;
      }
     
    },
    goToDetail(itemId){
             this.$router.push({path: `/board/detail/${itemId}`})
    },
    clickUpDeleteImage(){
      this.deleteUserImg(this.myaccount);

      //myaccoount 갱신
      this.findMyAccount();
      this.myaccount.image = null;

  },
    clickUploadImage(event){
      const formData = new FormData();
      formData.append("image", event.target.files[0]);
      
      //기존 계정에 이미지 덮어쓰기
      this.myaccount.image = formData;
      console.log(formData.get("image"))
      //Store
      this.uploadImg(this.myaccount);
      //실패시 null..
      let itemImage = this.$refs.uploadItemImage; //img dom 접근
          itemImage.src = URL.createObjectURL(event.target.files[0]);

      //myaccoount 갱신
      this.findMyAccount();
    },
    clickUpdate() {
      if ( this.isSubmit ){
        //alert(this.userUpdateData.empNum)
        this.updateUser(this.userUpdateData)
      }
    },
      clickFavorite(itemId, isFavorite){
            let req = new favoriteReq (this.myaccount.userId)
            
            if(isFavorite){//좋아요 눌러져있으면
                this.deleteFavoriteItemById(req);
            }
            else{// 좋아요 안눌러져있으면
                this.postFavoriteItemById(req);
            }
            this.getFavoriteItemById(this.myaccount.userId);
        }
  }
}
</script>

<style scoped>
.container {
  width: 50%;
  border-radius: 25px;
}
h3 {
  color: #88a498;
  font-weight: 800;
}
.inputs {
  border-style: none;
  border-bottom: 1px solid #88A498;
  background-color: transparent;
  width: 80%;
  padding: 10px;
  padding-left: 10px;
  padding-right: 10px;
  margin-top: 20px;
}
.signup-button{
  background-color: #88A498;
  color: #F8F8F8;
  width: 50%;
}
.divide {
  width: 10%;
  border-top: 1px solid #88A498;
  margin-left: auto;
  margin-right: auto;
}
.inputs:focus {
  border-style: none;
  border-bottom: 2px solid #D6CBBD;
  outline-style: none;
}
input[type="password"] {
  font-family:sans-serif;
}
.error, .error:focus {
  border-bottom: 2px solid rgb(250, 25, 59, 0.7); 
}
.error-text {
  color: rgb(250, 25, 59, 0.7);
  text-align: left;
  padding-left: 30px;
}
.signup-button:hover {
  background-color: #3c755a;
  color: #F8F8F8;
}
.disabled, .disabled:hover {
  background-color: rgb(136, 154, 152, 0.25);
  color: #F8F8F8;
  cursor: inherit;
}
.background {
  background-image: url("../../assets/img/background.jpg");
 position: absolute;
  height: 100%;
  width:100%;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  background-repeat: no-repeat;
  background-size: cover;
}
.signup-form {
  margin-top: 15vh !important;
  opacity: 0.9;
}
.items:hover {
  cursor: pointer;
  color: #d6cbbd;
}
.pro-button{
  border: 0;
  background-color: #f7f7f7;
  opacity: 0.5;
}
.custom-select{
  width:80%
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
.detail-title-text{
    color: #2682ba;
    font-weight: bold;
    font-size:1.2rem;
}


.item-list-heart{
    width: 2rem;
    height: 2rem;
}
</style>