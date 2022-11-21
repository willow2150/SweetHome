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
            <fg-input class="input-lg" placeholder=""> </fg-input>
          </div>
        </div>
        <div
          class="inputBox container d-flex justify-content-center align-items-center"
        >
          <div style="width: 110px">
            <span class="h5">PASSWORD</span>
          </div>
          <div class="col-lg-4" style="text-align: center; padding-top: 19px">
            <fg-input class="input-lg" placeholder="비밀번호"> </fg-input>
          </div>
        </div>
        <div
          class="inputBox container d-flex justify-content-center align-items-center"
        >
          <div style="width: 110px">
            <span class="h5">NAME</span>
          </div>
          <div class="col-lg-4" style="text-align: center; padding-top: 19px">
            <fg-input class="input-lg" placeholder="이름"> </fg-input>
          </div>
        </div>
        <div
          class="inputBox container d-flex justify-content-center align-items-center"
        >
          <div style="width: 110px">
            <span class="h5">ADDRESS</span>
          </div>
          <div class="col-lg-4" style="text-align: center; padding-top: 19px">
            <fg-input class="input-lg" placeholder="서울특별시 역삼동">
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
            <fg-input class="input-lg" placeholder="이메일"> </fg-input>
          </div>
        </div>
        <div class="" style="margin-top: 40px">
          <div class="container d-flex justify-content-center">
            <div class="button-container mr-1">
              <a href="#button" class="btn btn-info btn-round btn-lg">수정</a>
            </div>
            <div class="button-container mr-1">
              <a href="#button" class="btn btn-danger btn-round btn-lg">탈퇴</a>
            </div>
            <div class="button-container mr-1">
              <router-link to="/profile">
                <a href="#button" class="btn btn-default btn-round btn-lg"
                  >취소</a
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
import { Card, Button, FormGroupInput } from "@/components";
export default {
  name: "Modify",
  bodyClass: "login-page",
  components: {
    [Button.name]: Button,
    [FormGroupInput.name]: FormGroupInput,
  },
};
</script>
<style></style>
