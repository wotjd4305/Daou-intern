<template>
  <div id="pageNotFound">
    <img src="@/assets/img/animat-search-color.gif" contain width="300px" />
    <h1 id="Message">페이지를 찾을 수 없습니다.</h1>
    <div id="Btn" @click="gotoMain()">메인페이지로 이동</div>
  </div>
</template>

<script>
//import SERVER from '@/api/api'

export default {
  name: "PageNotFound",
  methods:{
    gotoMain() {
      this.$router.push({name: "Login"});
    },
  }
}
</script>

<style >
#pageNotFound {
  width: 80%;
  margin: 50px auto;
  text-align: center;
  margin-bottom: 40px;
}
#Message {
  margin-bottom: 70px;
}
#Btn {
  display: inline;
  border-radius: 5px;
  background-color: #7a63ff;
  color: white;
  padding: 10px;
}
#Btn:hover {
  cursor: pointer;
  background-color: #7a63ff55
}
</style>