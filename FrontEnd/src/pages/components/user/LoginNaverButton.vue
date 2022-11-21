<template>
  <div>
    <div ref="loginButton" id="naver_id_login" style="display: none"></div>
    <a href="#" id="naverLogin" title="naverLogin" @click="click">
      <img
        src="../../../../public/img/naver_button2.png"
        style="height: 60px"
      />
    </a>
  </div>
</template>

<script>
import { naverService } from "@/api/naverLogin";
import ButtonImg from "/public/img/naver_button.png";
export default {
  mounted() {
    const naver_id_login = new window.naver_id_login(
      "s_0k7R0AnBQN3krf8yAF",
      "http://localhost:8080/login/naver"
    );
    const state = naver_id_login.getUniqState();
    // naver_id_login.setButton("ButtonImg", 3, 53); // 버튼 설정
    naver_id_login.setState(state);
    naver_id_login.setPopup(); // popup 설정을 위한 코드
    naver_id_login.init_naver_id_login();
  },
  methods: {
    click() {
      this.$refs.loginButton.firstChild.click();
    },
  },
};
</script>
