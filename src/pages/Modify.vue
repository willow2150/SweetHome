<template>
  <div>
    <div class="page-header clear-filter" filter-color="white">
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
              addon-left-icon="now-ui-icons users_single-02"
              placeholder="아이디"
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
              class="no-border input-lg"
              addon-left-icon="now-ui-icons users_single-02"
              placeholder="Left Nucleo Icon"
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
              class="no-border input-lg"
              addon-left-icon="now-ui-icons users_single-02"
              placeholder="이름"
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
              class="no-border input-lg"
              addon-left-icon="now-ui-icons users_single-02"
              placeholder="이메일@이메일"
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
              class="no-border input-lg"
              addon-left-icon="now-ui-icons users_single-02"
              placeholder="이메일"
            >
            </fg-input>
          </div>
        </div>
        <div class="" style="margin-top: 40px">
          <div class="container d-flex justify-content-center">
            <div class="button-container">
              <a href="#button" class="btn btn-info btn-round btn-lg">수정</a>
            </div>
            <div class="button-container">
              <a href="#button" class="btn btn-default btn-round btn-lg"
                >취소</a
              >
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { Card, Button, FormGroupInput } from "@/components";
import MainFooter from "@/layout/MainFooter";
export default {
  name: "login-page",
  bodyClass: "login-page",
  components: {
    [Button.name]: Button,
    [FormGroupInput.name]: FormGroupInput,
  },
};
</script>
<style></style>
