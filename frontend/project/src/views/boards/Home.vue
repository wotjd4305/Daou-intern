<template>
  <!-- 검색 창 -->
    <div class="container">
      <div class="custom_search_container">
        <form
          class="fleft"
          name="topSearchForm"
          id="topSearchForm"
          action="/goods/search"
        >
          <input type="hidden" name="keyword_log_flag" value="Y" />
          <div class="search-input">
            <b-img
              type="image"
              @click="searchA(search_input_text, search_input_period)"
              style="cursor:pointer"
              v-bind:src="require(`@/assets/img/icons8-search-120.png`)"
              width="25px"
            ></b-img>
            <input
              type="text"
              placeholder="물건 혹은 카테고리"
              v-on:keyup.enter="searchA(search_input_text, search_input_period)"
              v-model="search_input_text"
              value
              autocomplete="off"
              autofocus
              title="검색"
              class="search_top_text"
            />
            
            <button class="ml-1 btn search-button" :class="{disabled: !isSubmit}" @click="clickLogin">검색</button>
            
          </div>
        </form>
      </div>
    
      <div class="row">
          <div class="col-5"  style="float : none; margin: 0 auto;">
            <hr class="featurette-divider" />
          </div>
      </div>
      <!--/ 검색 창 -->

      <!-- 이달의 다우인 -->
    <div>
        <div class ="row">
            <div class="col prize-title">
                이달의 다우인
            </div>
        </div>
        
        <div class ="row m-auto prize-list-row">
            <div class="col-2">
                <img
                class=""
                src="@/assets/img/icons8-gold-medal-48.png"
                style="width: 2rem; height: 2rem;"
                />
            </div>
            <div class="col-2 ml-auto">
                <img
                class="headerProfile mr-2"
                :src="prizeProfileImgsrc"
                style="width: 2rem; height: 2rem;"
                />
            </div>
            <div class="col-5 prize-name align-self-center">
                박재성 인턴
            </div>
            <div class="col-3 prize-pointer align-self-center">
                100 Pt
            </div>
        </div>
    
        <div class ="row m-auto prize-list-row">
            <div class="col-2">
                <img
                class=""
                src="@/assets/img/icons8-silver-medal-48.png"
                style="width: 2rem; height: 2rem;"
                />
            </div>
            <div class="col-2 ml-auto">
                <img
                class="headerProfile mr-2"
                :src="prizeProfileImgsrc"
                style="width: 2rem; height: 2rem;"
                />
            </div>
            <div class="col-5 prize-name align-self-center">
                최떙땡 인턴
            </div>
            <div class="col-3 prize-pointer align-self-center">
                90 Pt
            </div>
        </div>

        <div class ="row m-auto prize-list-row">
            <div class="col-2">
                <img
                class=""
                src="@/assets/img/icons8-bronze-medal-48.png"
                style="width: 2rem; height: 2rem;"
                />
            </div>
            <div class="col-2 ml-auto">
                <img
                class="headerProfile mr-2"
                :src="prizeProfileImgsrc"
                style="width: 2rem; height: 2rem;"
                />
            </div>
            <div class="col-5 prize-name align-self-center">
                김땡땡 인턴
            </div>
            <div class="col-3 prize-pointer align-self-center">
                30 Pt
            </div>
        </div>
    
    </div>


      <!--/이달의 다우인 -->



    <!-- 3D 캐러셀 -->
    <div class="mt-5">
        <carousel-3d :width="300" :height="300">
            <div v-for="(num, i) in slidesLength" :key="i">
            <slide id="carousel_card" :index="i">
                <div class="m-3">
                <h2 class="mb-2"> 안녕 </h2>
                </div>
            </slide>
            </div>
        </carousel-3d>
    </div>


    <!--/ 3D 캐러셀 -->






    </div>
</template>

<script>
import { Carousel3d, Slide } from "vue-carousel-3d";
//import axios from "axios";

export default {
  components: {
    Carousel3d,
    Slide,
  },
  data: () => {
    return {
      slidesLength: 10,
      prizeProfileImgsrc:"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbNQI5h%2FbtqGJuUCIN5%2FemfrZIKbSQvU9AYp9xXWhK%2Fimg.jpg",
    };
  },
  created() {
  
  },
  methods: {
  }
};
</script>

<style scoped>

.search-button {
  background-color: #2682ba;
  font-weight: bold;
  color: #f8f8f8;
  height: 2rem;
  width: 4rem;
}

.prize-title{
    color: #2682ba;
    font-weight: bold;
    font-size:1.5rem;
}

.prize-list-row{
    width: 50%;
}

.prize-pointer{
    font-weight: bold;
    color: red;
}
.prize-name{
    font-weight: bold;
    color:#000000;
}


</style>
