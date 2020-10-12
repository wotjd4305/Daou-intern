<template>
  <div class="background">
    <div class="container p-3 mt-5 bg-light-ivory login-form">
      
      <!-- 설명과 로그인창 -->
      <div class="row">

        <!-- 설명 -->
        <div class="col">
          <h3 class="dsc-title">
            <strong>다우마켓</strong>
          </h3>
          <div class="dsc-contents mt-5">
            <div class="mt-5"><p> 다우기술의 임직원들을 위한 <strong>'다우마켓'</strong>으로 뜨거운 정을 나누자! </p> </div>
          </div>
        </div>

        <!-- 로그인창 -->
        <div class="col shadow01 p-3">
          <h3 class="login-title">
            <strong>로그인</strong>
          </h3>

          <div class="input-with-label">
            <input
              v-model="loginData.empNum"
              v-bind:class="{error : error.empNum, complete:!error.empNum&&loginData.empNum.length!==0}"
              class="inputs"
              id="empNum"
              placeholder="사원번호"
              type="text"
              autocapitalize="none"
              autocorrect="none"
              required
            />
            <label for="empNum"></label>
            <div class="error-text ml-3" v-if="error.empNum">{{error.empNum}}</div>
          </div>
          <div class="input-with-label">
            <input
              v-model="loginData.password"
              v-bind:class="{error : error.password, complete:!error.password&&loginData.password.length!==0}"
              class="inputs"
              id="password"
              type="password"
              placeholder="비밀번호를 입력하세요."
              required
              @keyup.enter="clickLogin"
            />
            <label for="password"></label>
            <div class="error-text ml-3" v-if="error.password">{{error.password}}</div>
          </div>
          <p class="my-3">
            <span class="items" @click="clickSignup">회원가입하기</span>
          </p>
          <div class="buttons mt-3">
            <button class="btn login-button" :class="{disabled: !isSubmit}" @click="clickLogin">로그인하기</button>
          </div>

          <hr class="divide" />
        </div> 

      </div>

    </div>
  </div>
</template>

<script>
import { mapActions, mapMutations } from "vuex";


export default {
  name: "Login",
  data() {
    return {
      loginData: {
        empNum: "",
        password: "",
      },
      error: {
        empNum: false,
        password: false,
      },
      isSubmit: false,
    };
  },
  created() {
    this.component = this;
  },
  watch: {
    loginData: {
      deep: true,
      handler() {
        this.checkSabunForm();
        this.checkPasswordForm();
      },
    },
  },
  methods: {
    ...mapMutations(["SET_TOKEN"]),
    ...mapActions("accountStore", ["login"]),
    
     
    clickLogin() {
      if (this.isSubmit) {
        this.login(this.loginData);
        
      }
    },

    checkSabunForm() {
      if (
        this.loginData.empNum.length > 0 &&
        !this.validSabun(this.loginData.empNum)
      ) {
        this.error.empNum = "숫자만 입력해 주세요.";
      } else this.error.empNum = false;
    },
    validSabun(empNum) {
      var re = /^[0-9]*$/;
      return re.test(empNum);
    },
    checkPasswordForm() {
      if (
        this.loginData.password.length > 0 &&
        this.loginData.password.length < 8
      ) {
        this.error.password = "비밀 번호는 8자리 이상 필요합니다.";
      } else if (
        this.loginData.password.length >= 8 &&
        !this.validPassword(this.loginData.password)
      ) {
        this.error.password = "영문, 숫자 포함 8 자리 이상 필요합니다.";
      } else this.error.password = false;
      // 버튼 활성화
      if (
        this.loginData.password.length > 0 &&
        this.loginData.empNum.length > 0
      ) {
        let isSubmit = true;
        Object.values(this.error).map((v) => {
          if (v) isSubmit = false;
        });
        this.isSubmit = isSubmit;
      }
    },
    validPassword(password) {
      var va = /^(?=.*\d)(?=.*[a-zA-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]).{8,}$/;
      return va.test(password);
    },
    clickSignup() {
      this.$router.push({ name: "Signup" });
    },
  },
};
</script>

<style scoped>

.dsc-title{
  color:#2682ba;
  font-size:2.8rem;
  text-align: left;
  padding-left: 5%;
}

.dsc-contents p strong{
  color:#2682ba;
  font-size:1.5rem;
}
.login-form {
  margin-top: 15vh !important;
  opacity: 0.9;
}
.container {
  width: 60%;
  border-radius: 25px;
}
h3 {
  color: #88a498;
  font-weight: 900;
}
.inputs {
  border-style: none;
  border-bottom: 1px solid #88a498;
  background-color: transparent;
  width: 80%;
  padding: 10px;
  padding-left: 10px;
  padding-right: 10px;
  margin-top: 20px;
}
.login-button {
  background-color: #88a498;
  color: #f8f8f8;
  width: 50%;
}
.divide {
  width: 10%;
  border-top: 1px solid #88a498;
  margin-left: auto;
  margin-right: auto;
}
.inputs:focus {
  border-style: none;
  border-bottom: 2px solid #d6cbbd;
  outline-style: none;
}
input[type="password"] {
  font-family: sans-serif;
}
.error,
.error:focus {
  border-bottom: 2px solid rgb(250, 25, 59, 0.7);
}
.error-text {
  color: rgb(250, 25, 59, 0.7);
  text-align: left;
  padding-left: 30px;
}
.login-button:hover {
  background-color: #3c755a;
  color: #f8f8f8;
}
.disabled,
.disabled:hover {
  background-color: rgb(136, 154, 152, 0.25);
  color: #f8f8f8;
  cursor: inherit;
}
.items:hover {
  cursor: pointer;
  color: #d6cbbd;
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
.formatting {
  opacity: 0.9;
}
.shadow01 {
    box-shadow: 1px 1px 3px 3px #2682ba55;
}
</style>