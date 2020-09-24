<template>
  <div id="app">
    <Navbar />

    <!-- 네비게이션 바 만큼 내려주기 -->
    <div id="c-blank"></div>
    
    <router-view />
    <Footer />
  </div>
</template>

<script>
import Footer from "./components/Footer.vue";
import Navbar from "./components/Navbar.vue";

export default {
  name: "App",
  components: {
    Footer,
    Navbar,
  },
}
</script>

<style>
 
@font-face {
  font-family: 'Daki';
  src:url('./assets/fonts/DAKIL.TTF');
}

#c-blank{
  margin-top:10rem;
}

#app {
  font-family: Daki ,Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

/* 비밀번호 글꼴 안먹을때 사용 */
input.myclass
    {-webkit-text-security:disc;font-family:dotsfont;}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
