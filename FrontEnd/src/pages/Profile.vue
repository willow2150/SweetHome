<template>
  <div>
    <div class="page-header clear-filter" filter-color="blue">
      <parallax class="page-header-image"> </parallax>
      <div class="container">
        <div class="photo-container">
          <img src="" alt="" />
        </div>
        <h3 class="title">{{ userInfo.userName }}</h3>
        <p class="email">{{ userInfo.userEmail }}</p>
        <div class="content">
          <div class="social-description">
            <h2>2</h2>
            <p>내 관심지역</p>
          </div>
          <div class="social-description">
            <h2>1</h2>
            <p>내 관심 아파트</p>
          </div>
        </div>
      </div>
    </div>
    <div class="section">
      <div class="container">
        <div class="button-container">
          <router-link to="/profilemodify">
            <div class="btn btn-info btn-round btn-lg">회원 정보 수정</div>
          </router-link>
        </div>
        <h3 class="title">11</h3>
        <div class="row">
          <div class="col-md-6 ml-auto mr-auto">
            <h4 class="title text-center">내 관심 지역</h4>
          </div>
          <div class="col-md-6 ml-auto mr-auto">
            <h4 class="title text-center">내 관심 매물</h4>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "profile",
  bodyClass: "profile-page",
  components: {},
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
};
</script>
<style></style>
