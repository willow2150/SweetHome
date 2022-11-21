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
          <div class="col-lg-4" style="text-align: center; padding-top: 19px">
            <fg-input
              class="input-lg"
              v-model="user.userId"
              placeholder="아이디를 입력하세요"
            >
            </fg-input>
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
              placeholder="비밀번호를 입력하세요"
            >
            </fg-input>
          </div>
        </div>
        <div
          class="inputBox container d-flex justify-content-center align-items-center"
        >
          <div style="width: 110px">
            <span class="h5">NAME</span>
          </div>
          <div class="col-lg-4" style="text-align: center; padding-top: 19px">
            <fg-input
              class="input-lg"
              v-model="user.userName"
              placeholder="이름을 입력하세요"
            >
            </fg-input>
          </div>
        </div>
        <div
          class="inputBox container d-flex justify-content-center align-items-center"
        >
          <div style="width: 110px">
            <span class="h5">ADDRESS</span>
          </div>
          <div class="col-lg-4" style="text-align: center; padding-top: 19px">
            <fg-input
              class="input-lg"
              v-model="user.userAddress"
              placeholder="주소를 입력하세요"
            >
            </fg-input>
          </div>
        </div>
        <div
          class="inputBox container d-flex justify-content-center align-items-center"
        >
          <div style="width: 110px">
            <span class="h5">E-MAIL</span>
          </div>
          <div class="col-lg-4" style="text-align: center; padding-top: 19px">
            <fg-input
              class="input-lg"
              v-model="user.userEmail"
              placeholder="이메일을 입력하세요"
            >
            </fg-input>
          </div>
        </div>
        <div class="" style="margin-top: 40px">
          <div class="container d-flex justify-content-center">
            <div class="button-container mr-1" @click="regist">
              <a href="#button" class="btn btn-info btn-round btn-lg"
                >회원가입</a
              >
            </div>
            <div class="button-container mr-1">
              <router-link to="/profile">
                <a href="#button" class="btn btn-default btn-round btn-lg"
                  >가입취소</a
                >
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
import { mapActions } from "vuex";

const userStore = "userStore";

export default {
  name: "Register",
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
        userName: null,
        userAddress: null,
        userEmail: null,
      },
    };
  },
  methods: {
    ...mapActions(userStore, ["userRegist"]),
    async regist() {
      console.log(this.user);
      await this.userRegist(this.user);
      this.$router.push("/");
    },
  },
};
</script>
<style></style>
