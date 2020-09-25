<template>
  <div class="footer">
    Copyright 2020. 온새미로 all rights reserved.
  </div>
</template>

<script>
export default {
  name: "Footer",
};
</script>

<style>
.footer {
  position: fixed;
  left: 0;
  bottom: 0;
  width: 100%;
  background-color: #2385c6;
  color: white;
  text-align: center;
  padding: 10px 0;
  z-index: 9;
}

/* footer 글씨체 카피라이트는 고딕으로하는 것이 약속임 */
.footer {
  font-family: Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #ffffff;
}
</style>