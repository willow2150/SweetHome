<template>
  <div>
    <div class="page-header clear-filter" filter-color="blue">
      <div class="container">
        <div class="content-center brand">
          <h1 class="h1-seo">Find Your Dream House</h1>
          <h3>여러분의 집을 찾아보세요</h3>
          <!-- <h5 class="category category-absolute">Designed by 정상기 박귀렬</h5> -->
        </div>
        <footer class="d-flex justify-content-between mb-3">
          <div class="col-6">
            <YoutubeSel />
          </div>
          <div class="col-6">
            <NewsSel />
          </div>
        </footer>
      </div>
    </div>
  </div>
</template>
<script>
import NewsSel from "../pages/components/news/NewsSel";
import YoutubeSel from "../pages/components/news/YoutubeSel";

export default {
  name: "index",
  bodyClass: "index-page",
  components: {
    NewsSel,
    YoutubeSel,
  },
};
</script>
<style></style>
