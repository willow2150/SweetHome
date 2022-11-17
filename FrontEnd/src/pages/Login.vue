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
            >
            </fg-input>

            <fg-input
              class="no-border input-lg"
              addon-left-icon="now-ui-icons text_caps-small"
              placeholder="비밀번호 입력하세요"
            >
            </fg-input>

            <template slot="raw-content">
              <div class="card-footer text-center">
                <a href="#pablo" class="btn btn-info btn-round btn-lg btn-block"
                  >로그인</a
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
    <main-footer></main-footer>
  </div>
</template>
<script>
import { Card, Button, FormGroupInput } from "@/components";
import MainFooter from "@/layout/MainFooter";
export default {
  name: "login-page",
  bodyClass: "login-page",
  components: {
    Card,
    MainFooter,
    [Button.name]: Button,
    [FormGroupInput.name]: FormGroupInput,
  },
};
</script>
<style></style>
