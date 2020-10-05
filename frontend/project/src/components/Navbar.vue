<template>
  <div id="navigationBar">

   <!-- 채팅 사이드바 -->
    <b-sidebar id="sidebar-right" right shadow no-header>
      <div class="col-1">
          <div v-b-toggle.sidebar-right style="outline:none;">
            <img
                class=""
                src="@/assets/img/icons8-delete-message-120.png"
                style="width: 2rem; height: 2rem;"
                >
          </div>
      </div>
      <div>
        <b-card no-body>
          <b-tabs pills m-3 card  class="m-3" align="center">
              <b-tab title="채팅1">
                <Chat/>
              </b-tab>
              <b-tab title="채팅2">
                <Chat/>
              </b-tab>
              <b-tab title="채팅2">
                <Chat/>
              </b-tab>
              <b-tab title="채팅2">
                <Chat/>
              </b-tab>
              <b-tab title="채팅2">
                <Chat/>
              </b-tab>
            
          </b-tabs>
        </b-card>
      </div> 
    </b-sidebar>
    <!--/ 채팅 사이드바 -->

   <!-- 네비게이션 바 -->
   <b-navbar id="pageMenue">
        <b-navbar-nav style="display:contents">
        
        
          <div>
            <b-nav-item class="mr-5" href="#">
              <div class="nav-logo-txt" id="rlink" @click="goToHome">
                <img
                    class="profileImg"
                    ref="uploadItemImage"
                    accept="image/jpeg, jpg, png/"
                    src="@/assets/img/logo.png"
                    style="width: 2.5rem; height: 2.5rem;"
                  /> <strong> Daou Market </strong>
              </div
              >

            </b-nav-item>
          </div>


          <div class=" nav-middle-position">
           <b-nav-item v-if="authToken != null"  class="nav-mid-position ml-5" href="#">
              <router-link id="rlink" :to="{ path: `/board` }"
                ><strong>게시판</strong></router-link
              >
            </b-nav-item>
          </div>
          
          <div v-if="authToken != null" class="nav-rigth-position">
            <b-nav-item  class="ml-5" href="#">
              <b-dropdown
                id="dropdown"
                right
                class="nav-text"
                text="My Page"
                variant="text"
                toggle-class="text-decoration-none"
              >
                  <b-dropdown-item href="#">
                    
                      <b-nav-item v-if="authToken != null"  class="ml-5" href="#">
                        <router-link  id="rlink" :to="{ path: `/userinfo` }"
                          >
                          <div class="drop-down-item-text">
                            <strong>회원정보 수정</strong>
                          </div>
                          </router-link
                        >
                    </b-nav-item>
                  </b-dropdown-item>
                  
                  <b-dropdown-item v-b-toggle.sidebar-right href="#" >
                    <div class="drop-down-item-text text-right mr-2"> 채팅</div>
                    <!--
                    <b-modal id="chat" hide-footer>
                      <template v-slot:modal-title>채팅 테스트</template>
                      <div class="d-block text-center">
                        <Chat />
                      </div>
                    </b-modal> -->

                    <div v-b-toggle.sidebar-right style="outline:none;">
                      
                    </div>

                  </b-dropdown-item>
                  


                </b-dropdown>
            </b-nav-item>
          </div>

            <b-nav-item v-if="authToken != null" class="ml-3 nav-logout" href="#">
              <div style="color:white;" @click="logout"><strong>로그아웃</strong></div>
            </b-nav-item>
         


        </b-navbar-nav>
      </b-navbar>
    <div id="navBtn" >
    </div>
  </div>
</template>

<script>
import { mapState,mapActions } from 'vuex'
import Chat from "../views/chat/Chat.vue"

export default {
  
  data: function() {


  return {
    profileImgsrc:"@/assets/img/log.png"
   };
  },
  components: { 
  Chat
   },
  computed: {
     ...mapState(['myaccount','authToken']),
  },
  created(){
  },
  methods: {
     ...mapActions(['logout']),
       goToChatTest(){
      this.$router.push({ name: "Chat" });
    },
    goToHome(){
      this.$router.push({name:"Home"})
    }
  },
};
</script>

<style>
#navigationBar {
  position: fixed;
  margin: 0 auto;
  top: 0;
  /* right: auto; */
  left: 50%;
  transform: translate(-50%, 0%);
  width: 100%;
  border: 0;
  z-index: 10;
}
#pageMenue {
  /* border-bottom-right-radius: 4px;
  border-bottom-left-radius: 4px; */
  background-color: #2682ba;
  /* color: white;
  text-decoration-color: white; */
}
#rlink {
  color: white;
  text-decoration: none;
}
#navBtn {
  padding: 0px 20px;
  margin: 0 auto;
  width: 100%;
  height: 5%;
  /* border-bottom-right-radius: 5px;
  border-bottom-left-radius: 5px; */
  background-color: #2682ba;
  color: white;
}
/* 재정의로 덮어쓰기 */
.c-navbar-nav{
  display: contents !important
}

.nav-logo-txt{
  font-size: 1.5rem;
}

.nav-rigth-position{
  position: relative;
  text-align: right;
  width: 60%;
}

.nav-middle-position{
  position: contents;
  width: 10%;
  min-width: 7rem;
}

.nav-logout{
  min-width: 7rem;
}

.headerProfile {
  border-radius: 50%;
  border-color: #ffcabd;
  background-color: white;
}

#userMenue {
  position: absolute;
  right: 0;
  padding: 10px;
  /* float: right; */
}

.nav-text button{
  font-weight: bold;
  color:#ffffff;
  
}
#sidebar-right ::-webkit-scrollbar {
    background-color: #dddddd;
    box-shadow: inset 0px 0px 5px white;  
  }
#sidebar-right ::-webkit-scrollbar-thumb {
    background-color: #2682ba;
    border-radius: 10px;
  }




.drop-down-item-text{
  color:black; 
  font-weight:bold;
}

</style>
