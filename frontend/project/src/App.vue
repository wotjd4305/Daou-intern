<template>
  <div id="app">
    <Navbar />

    <!-- 네비게이션 바 만큼 내려주기 -->
    <div id="c-blank-top"></div>
    
    <router-view />
    <div id="c-blank-bottom"></div>
    
    <Footer />
  </div>
</template>




<script>
import Footer from "./components/Footer.vue";
import Navbar from "./components/Navbar.vue";
import { mapActions } from "vuex";

(function(j,en,ni,fer) {
        j['dmndata']=[];j['jenniferFront']=function(args){window.dmndata.push(args)};
        j['dmnaid']=fer;j['dmnatime']=new Date();j['dmnanocookie']=false;j['dmnajennifer']='JENNIFER_FRONT@INTG';
        var b=Math.floor(new Date().getTime() / 60000) * 60000;var a=en.createElement(ni);
        a.src='https://d-collect.jennifersoft.com/'+fer+'/demian.js?'+b;a.async=true;
        en.getElementsByTagName(ni)[0].parentNode.appendChild(a);
    }(window,document,'script','b48dc09b'));

export default {
  name: "App",
  components: {
    Footer,
    Navbar,
  },
  methods:{
    ...mapActions(['findMyAccount']),
    ...mapActions("categoryStore",["fetchDepartmentCategory","fetchItemCategory"])
  },
  created(){
    this.findMyAccount();
    this.fetchDepartmentCategory();
    this.fetchItemCategory();
  },

}


</script>






<style>
@font-face {
  font-family: 'Daki';
  src:url('./assets/fonts/DAKIL.TTF');
}

#c-blank-top{
  margin-top:10rem;
}

#c-blank-bottom{
  margin-bottom:10rem;
}


#app{
  font-family: Daki ,Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

/* 비밀번호 글꼴 안먹을때 사용 */
input.myclass
    {-webkit-text-security:disc;font-family:dotsfont;}

/* placeholder 글자색 바꾸기 */
input::placeholder {
  color: #2682baaa;
}


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





/*  */
 
.shadow01 {
    box-shadow: 1px 1px 3px 3px #2682ba55;
    }

</style>
