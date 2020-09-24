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
    </div>
</template>

<script>
//import { Carousel3d, Slide } from "vue-carousel-3d";
//import axios from "axios";

export default {
  components: {
    //Carousel3d,
    //Slide,
  },
  data: () => {
    return {
      slidesLength: 1,
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

</style>
