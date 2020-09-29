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
                @change="uploadImage($event)"
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
              v-model="signupData.name"
              v-bind:class="{error: error.name, complete:!error.name&&signupData.name.length!==0}"
              class="inputs"
              id="Name"
              placeholder="이름" 
              type="text" 
              autocapitalize="none"
              autocorrect="none"
              style="text-transform:lowercase"
            />
            <label for="Name"></label>
            <div class="error-text ml-3" v-if="error.name">{{error.name}}</div>
          </div>

          <div class="input-with-label">
            <input 
              v-model="signupData.num" 
              v-bind:class="{error : error.num, complete:!error.num&&signupData.num.length!==0}"
              class="inputs"
              id="num" 
              placeholder="사번" 
              type="text" 
              autocapitalize="none"
              autocorrect="none"
              style="text-transform:lowercase"
              required
              />
            <label for="num"></label>
            <div class="error-text ml-3" v-if="error.num">{{error.num}}</div>
          </div>

          <div class="input-with-label">
            <input 
              v-model="signupData.password" 
              
              v-bind:class="{error : error.password, complete:!error.password&&signupData.password.length!==0}"
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
              v-model="signupData.passwordConfirm"
              type="password"
              id="password-confirm"
              v-bind:class="{error : error.passwordConfirm, complete:!error.passwordConfirm&&signupData.passwordConfirm.length!==0}"
              placeholder="비밀번호를 다시 입력해주세요."
              class="inputs"
              required
              @keyup.enter="clickSignup"
            />
            <label for="password-confirm"></label>
            <div class="error-text ml-3" v-if="error.passwordConfirm">{{error.passwordConfirm}}</div>
          </div>
          <div class="input-with-label mt-2">
            <select v-model="signupData.department" class=" custom-select">
              <option  v-for="(depart, idx) in departs" :key="idx">{{ depart }}</option>
            </select>
          </div>
        </div>

      </div>

      <div class="buttons mt-3">
        <button class="btn signup-button" :class="{disabled: !isSubmit}" @click="clickSignup">회원가입</button>
      </div>
      
    </div>



    <!-- 검색 창 -->
    <div class="container">
      


       <!-- 목록 창-->
     <div class="container ">
        <div class="row ">
            <div class="col-3 mt-4" v-for="(item, idx) in itemsDummy" :key="idx">
                <div class="forHover">
                    <div class="item-list-card shadow01">
                        <b-img
                        type="image"
                        @click="goToDetail()"
                        style="cursor:pointer"
                        :src=item.picture
                        width="200rem"
                        height="150rem"
                        class="mt-3 mb-2"
                        ></b-img>

                        <div class="row">
                            <div class="ml-5 text-left">
                                <span class="item-list-title-text">
                                    제품명 : 
                                </span>
                                <span class="  item-list-title-contents">
                                    {{item.title}} 
                                </span>
                            </div>
                            <div class="ml-5 text-left">
                                <span class="item-list-title-text">
                                    카테고리 : 
                                </span>
                                <span class="  item-list-title-contents">
                                    {{item.category}} 
                                </span>
                            </div>
                            <div class="ml-5 text-left">
                                <span class="item-list-title-text">
                                    등록일 : 
                                </span>
                                <span class="item-list-title-contents">
                                    {{item.date}} 
                                </span>
                            </div>
                            <div class="ml-5 text-left">
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
    </div>
    <!--/목록 창-->










    
  </div>




  




</template>

<script>
import { mapActions } from 'vuex'
export default {
  name: 'Signup',
  data() {
    return {
      departs: ["서비스 개발부", "웹서비스 개발부", "인프라 팀"],
      signupData: {
        num: "",
        password: "",
        passwordConfirm: "",
        name: "",
        department: "",
      },
      error: {
        num: false,
        name: false,
        password: false,
        passwordConfirm: false,
      },
      isSubmit: false,
    };
  },
  created() {
    this.component = this;
  },
  watch: {
    signupData: {
      deep: true,
      handler() {
        this.checkNameForm();
        this.checkSabunForm();
        this.checkPasswordForm();
        this.checkPasswordConfirmationForm();
      }
    }
  },
  methods: {
    checkNameForm() {
      if ( this.signupData.name.length > 0) {
        this.error.Name = false;
      }
      else this.error.Name="이름을 입력하세요."
    },
    checkSabunForm() {
      if ( this.signupData.num.length > 0 && !this.validSabun(this.signupData.num) ) {
        this.error.num = "숫자만 입력하세요."   
      }
      else this.error.num = false;
    },
    validSabun(num) {
      var re = /^[0-9]*$/;
      return re.test(num);
    },
    checkPasswordForm() {
      if (this.signupData.password.length > 0 && this.signupData.password.length < 8) {
          this.error.password = "비밀번호가 너무 짧아요"
        } else if ( this.signupData.password.length >= 8 && !this.validPassword(this.signupData.password) ) {
          this.error.password = "영문, 숫자 포함 8 자리 이상이어야 해요.";
        } else this.error.password = false;
    },
    validPassword(password) {
      var va = /^(?=.*\d)(?=.*[a-z])(?=.*[a-zA-Z]).{8,}$/;
      return va.test(password);
    },
    checkPasswordConfirmationForm() {
      if (this.signupData.password.length >= 8 && this.validPassword(this.signupData.password)) {
         if (this.signupData.password !== this.signupData.passwordConfirm )
        this.error.passwordConfirm = "비밀번호가 일치하지 않아요."
      else this.error.passwordConfirm = false;
      }
      
      // 버튼 활성화
      if (this.signupData.name.length > 0 && this.signupData.num.length > 0 && this.signupData.password.length > 0 && this.signupData.passwordConfirm.length > 0){
        let isSubmit = true;
        Object.values(this.error).map(v => {
          if (v) isSubmit = false;
        });
        this.isSubmit = isSubmit;
      }
     
    },
    clickSignup() {
      if ( this.isSubmit ){
        this.signup(this.signupData)
      }
    },
    toLogin() {
      this.$router.push({name: "Login"});
    },
    ...mapActions('accountStore', ['signup'])
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