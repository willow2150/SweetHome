<template>
  <div class="page-header clear-filter" filter-color="blue">
    <!-- <div class="page-header-image" style="background-image: url('')"></div> -->
    <div class="content">
      <div class="container">
        <div class="col-md-5 ml-auto mr-auto">
          <card type="login" plain>
            <div slot="header" class="logo-container">
              <img v-lazy="'img/now-logo.png'" alt="" />
            </div>

            <fg-input
              class="no-border input-lg"
              addon-left-icon="now-ui-icons users_circle-08"
              placeholder="아이디 입력하세요"
              v-model="user.userId"
              @keyup.enter="confirm"
            >
            </fg-input>

            <fg-input
              class="no-border input-lg"
              addon-left-icon="now-ui-icons text_caps-small"
              placeholder="비밀번호 입력하세요"
              v-model="user.userPwd"
              @keyup.enter="confirm"
            >
            </fg-input>

            <template slot="raw-content">
              <div class="row">
                <div class="col">
                  <LoginNaverButton />
                </div>
                <div class="col">구글</div>
                <div class="col">카카오</div>
              </div>
              <div class="card-footer text-center">
                <span
                  class="btn btn-info btn-round btn-lg btn-block"
                  @click="confirm"
                  >로그인</span
                >
              </div>
              <div class="pull-left">
                <router-link to="/register">
                  <a href="#pablo" class="link footer-link h5">회원가입</a>
                </router-link>
              </div>
              <div class="pull-right">
                <h6>
                  <a href="#pablo" class="link footer-link h5">비밀번호 찾기</a>
                </h6>
              </div>
            </template>
          </card>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { Card, Button, FormGroupInput } from "@/components";
import LoginNaverButton from "./components/user/LoginNaverButton.vue";
import { mapState, mapActions } from "vuex";

const userStore = "userStore";

export default {
  name: "login-page",
  bodyClass: "login-page",
  components: {
    Card,
    LoginNaverButton,
    [Button.name]: Button,
    [FormGroupInput.name]: FormGroupInput,
  },
  data() {
    return {
      // isLoginError: false,
      user: {
        userId: null,
        userPwd: null,
      },
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    ...mapActions(userStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      // console.log("1. confirm() token >> " + token);
      if (this.isLogin) {
        await this.getUserInfo(token);
        // console.log("4. confirm() userInfo :: ", this.userInfo);
        this.$router.push("/");
      }
    },
    movePage() {
      this.$router.push({ name: "join" });
    },
  },
};
</script>
<style></style>
