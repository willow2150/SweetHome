<template>
  <navbar
    position="fixed"
    :transparent="transparent"
    :color-on-scroll="colorOnScroll"
    menu-classes="ml-auto"
  >
    <template>
      <router-link v-popover:popover1 class="navbar-brand" to="/">
        <span class="h2" style="font-weight: bold">Sweet Home</span>
      </router-link>
      <!-- <el-popover
        ref="popover1"
        popper-class="popover"
        placement="bottom"
        width="200"
        trigger="hover"
      >
      </el-popover> -->
    </template>
    <template slot="navbar-menu">
      <li class="nav-item">
        <div class="nav-link" v-if="userInfo">
          <router-link to="/housedeal">
            <p>아파트 매물 조회</p>
          </router-link>
        </div>
        <div class="nav-link" v-else @click="alertLogin">
          <router-link to="/login">
            <p>아파트 매물 조회</p>
          </router-link>
        </div>
      </li>

      <drop-down
        tag="li"
        title="게시판"
        icon="now-ui-icons files_single-copy-04"
        class="nav-item"
      >
        <nav-link to="/notice" v-if="userInfo">
          <i class="now-ui-icons files_paper"></i> 공지사항
        </nav-link>
        <nav-link to="/login" v-else @click.native="alertLogin"
          ><i class="now-ui-icons files_paper"></i> 공지사항</nav-link
        >

        <nav-link to="/board" v-if="userInfo">
          <i class="now-ui-icons design_bullet-list-67"></i> 자유게시판
        </nav-link>
        <nav-link to="/login" v-else @click.native="alertLogin"
          ><i class="now-ui-icons design_bullet-list-67"></i>
          자유게시판</nav-link
        >
      </drop-down>

      <drop-down
        tag="li"
        title="회원 정보"
        icon="now-ui-icons users_circle-08"
        class="nav-item"
      >
        <nav-link to="/profile" v-if="userInfo">
          <i class="now-ui-icons users_single-02"></i> 내 프로필
        </nav-link>
        <nav-link to="/login" v-else @click.native="alertLogin">
          <i class="now-ui-icons users_single-02"></i> 내 프로필
        </nav-link>
      </drop-down>

      <li class="nav-item" v-if="userInfo">
        <div class="nav-link btn btn-neutral text-info" @click="onClickLogout">
          <i class="now-ui-icons users_single-02"></i>
          <p>로그아웃</p>
        </div>
      </li>

      <li class="nav-item" v-else>
        <router-link to="/login" class="nav-link btn btn-neutral text-info">
          <i class="now-ui-icons users_single-02"></i>
          <p>로그인</p>
        </router-link>
      </li>

      <!-- <li class="nav-item">
        <a
          class="nav-link"
          rel="tooltip"
          title="Follow us on Twitter"
          data-placement="bottom"
          href="https://twitter.com/CreativeTim"
          target="_blank"
        >
          <i class="fab fa-twitter"></i>
          <p class="d-lg-none d-xl-none">Twitter</p>
        </a>
      </li>
      <li class="nav-item">
        <a
          class="nav-link"
          rel="tooltip"
          title="Like us on Facebook"
          data-placement="bottom"
          href="https://www.facebook.com/CreativeTim"
          target="_blank"
        >
          <i class="fab fa-facebook-square"></i>
          <p class="d-lg-none d-xl-none">Facebook</p>
        </a>
      </li>
      <li class="nav-item">
        <a
          class="nav-link"
          rel="tooltip"
          title="Follow us on Instagram"
          data-placement="bottom"
          href="https://www.instagram.com/CreativeTimOfficial"
          target="_blank"
        >
          <i class="fab fa-instagram"></i>
          <p class="d-lg-none d-xl-none">Instagram</p>
        </a>
      </li> -->
    </template>
  </navbar>
</template>

<script>
import { DropDown, Navbar, NavLink } from "@/components";
import { Popover } from "element-ui";
import { mapState, mapGetters, mapActions } from "vuex";

const userStore = "userStore";

export default {
  name: "main-navbar",
  props: {
    transparent: Boolean,
    colorOnScroll: Number,
  },
  components: {
    DropDown,
    Navbar,
    NavLink,
    [Popover.name]: Popover,
  },
  computed: {
    ...mapState(userStore, ["isLogin", "isLoginError", "userInfo"]),
    ...mapGetters(userStore, ["checkUserInfo"]),
  },
  methods: {
    ...mapActions(userStore, ["userLogout"]),
    onClickLogout() {
      // this.SET_IS_LOGIN(false);
      // this.SET_USER_INFO(null);
      // sessionStorage.removeItem("access-token");
      // if (this.$route.path != "/") this.$router.push({ name: "main" });
      // console.log(this.userInfo);
      //vuex actions에서 userLogout 실행(Backend에 저장 된 리프레시 토큰 없애기
      //+ satate에 isLogin, userInfo 정보 변경)
      // this.$store.dispatch("userLogout", this.userInfo.userid);
      if (this.userInfo) {
        this.userLogout(this.userInfo.userId);
        sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
        sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      } else {
        console.log("유저 정보 없음!!!!");
      }
      if (this.$route.path != "/") this.$router.push("/");
    },
    alertLogin() {
      alert("로그인 후 이용가능한 페이지입니다!");
    },
  },
};
</script>

<style>
p {
  font-size: 15px;
}
</style>
