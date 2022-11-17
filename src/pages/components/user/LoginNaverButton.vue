<template>
  <div id="naver_id_login"></div>
</template>

<script>
import { naverService } from "@/api/naverLogin";
export default {
  mounted() {
    const naver_id_login = new window.naver_id_login(
      "s_0k7R0AnBQN3krf8yAF",
      "http://localhost:8080/login/naver"
    );
    const state = naver_id_login.getUniqState();
    naver_id_login.setButton("white", 3, 53); // 버튼 설정
    naver_id_login.setState(state);
    // naver_id_login.setPopup(); // popup 설정을 위한 코드
    naver_id_login.init_naver_id_login();
  },
};
</script>
