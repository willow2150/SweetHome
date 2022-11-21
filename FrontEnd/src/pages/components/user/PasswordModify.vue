<template>
  <div>
    <div class="page-header clear-filter" filter-color="blue">
      <div class="container">
        <div
          class="inputBox container d-flex justify-content-center align-items-center"
        >
          <div style="width: 110px">
            <span class="h5">ID</span>
          </div>
          <div
            class="col-lg-4"
            style="text-align: center; padding-top: 19px; padding-bottom: 19px"
          >
            <span class="h3">{{ userInfo.userId }}</span>
          </div>
        </div>
        <div
          class="inputBox container d-flex justify-content-center align-items-center"
        >
          <div style="width: 110px">
            <span class="h5">PASSWORD</span>
          </div>
          <div class="col-lg-4" style="text-align: center; padding-top: 19px">
            <fg-input
              class="input-lg"
              v-model="user.userPwd"
              placeholder="기존 패스워드"
            >
            </fg-input>
          </div>
        </div>
        <div
          class="inputBox container d-flex justify-content-center align-items-center"
        >
          <div style="width: 110px">
            <span class="h5">NEW PASSWORD</span>
          </div>
          <div class="col-lg-4" style="text-align: center; padding-top: 19px">
            <fg-input
              class="input-lg"
              v-model="user.newUserPwd"
              placeholder="새 패스워드"
            >
            </fg-input>
          </div>
        </div>
        <div class="" style="margin-top: 40px">
          <div class="container d-flex justify-content-center">
            <div class="button-container mr-1">
              <span class="btn btn-info btn-round btn-lg" @click="changePass"
                >수정</span
              >
            </div>
            <div class="button-container mr-1">
              <router-link to="/profilemodify">
                <span class="btn btn-default btn-round btn-lg">취소</span>
              </router-link>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { Button, FormGroupInput } from "@/components";
import { mapState, mapActions } from "vuex";

const userStore = "userStore";

export default {
  name: "PasswordModify",
  bodyClass: "login-page",
  components: {
    [Button.name]: Button,
    [FormGroupInput.name]: FormGroupInput,
  },
  data() {
    return {
      user: {
        userId: null,
        userPwd: null,
        newUserPwd: null,
      },
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(userStore, ["changePassword"]),
    async changePass() {
      this.user.userId = this.userInfo.userId;
      // console.log(this.user);
      await this.changePassword(this.user);
      this.$router.push("/profilemodify");
    },
  },
};
</script>
<style></style>
