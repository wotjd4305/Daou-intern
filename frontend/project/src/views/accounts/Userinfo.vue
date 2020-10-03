<template>
  <div class="background">
    <div class="container p-1 mt-5 bg-light-ivory signup-form">
      <h3>회원 수정.. 아직 안건듬</h3>

      <!-- 프로필과 입력창 -->
      <div class="row">

        <!-- 프로필 -->
        <b-col align-self="stretch center">
              <b-form-text class="mb-2" id="profile-help">프로필 클릭 시, 이미지 변경이 가능합니다.</b-form-text>
              <input
                type="file"
                ref="profileImg"
                style="display: none"
                accept="image/jpeg, jpg, png/"
                @change="clickuploadImage($event)"
              />

              <button class="pro-button" id="profileImgBtn" @click="$refs.profileImg.click()">
                <img
                  class="profileImg"
                  ref="uploadItemImage"
                  accept="image/jpeg, jpg, png/"
                  src="@/assets/img/icons8-male-user-90.png"
                  style="width: 10rem; height: 10rem;"
                />
              </button>
              <br />
              <!-- 프로필 삭제 아직 덜만듦!!!!! -->
              <b-button
                class="mt-2"
                size="sm"
                variant="light"
                id="deleteImg"
                @click="deleteP()"
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
        <button class="btn signup-button" :class="{disabled: !isSubmit}" @click="clickUpdate">회원가입</button>
      </div>
      
    </div>



    
  </div>




</template>

<script>
import { mapActions, mapState } from 'vuex'
//import SERVER from '@/api/api'

//import axios from 'axios'

export default {
  name: 'Signup',
  data() {
    return {
      departs: ["서비스 개발부", "웹서비스 개발부", "인프라 팀"],
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
    };
  },
  created() {
    this.findMyAccount();
    this.userUpdateData.empNum = this.myaccount.empNum;
    
  },
  watch: {
    userUpdateData: {
      deep: true,
      handler() {
        this.checkPasswordForm();
        this.checkPasswordConfirmationForm();
      }
    }
  },
  computed:{
    ...mapState(['myaccount'])
  },
  methods: {
    ...mapActions("accountStore",["updateUser","uploadImg"]),
    ...mapActions(['findMyAccount']),
    
     
    checkPasswordForm() {
      if (this.userUpdateData.password.length > 0 && this.userUpdateData.password.length < 8) {
          this.error.password = "비밀번호가 너무 짧아요"
        } else if ( this.userUpdateData.password.length >= 8 && !this.validPassword(this.userUpdateData.password) ) {
          this.error.password = "영문, 숫자 포함 8 자리 이상이어야 해요.";
        } else this.error.password = false;
    },
    validPassword(password) {
      var va = /^(?=.*\d)(?=.*[a-z])(?=.*[a-zA-Z]).{8,}$/;
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

    clickuploadImage(event){

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
    },
    clickUpdate() {
      if ( this.isSubmit ){
        //alert(this.userUpdateData.empNum)
        this.updateUser(this.userUpdateData)
      }
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

</style>