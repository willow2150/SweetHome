<template>
  <div>
    <div class="page-header clear-filter" filter-color="blue">
      <b-container class="bv-example-row mt-3">
        <div class="row" style="font-size: 2rem">
          <b-icon icon="bell-fill"></b-icon>
          <p><b>공지사항 작성하기</b></p>
        </div>
        <b-row class="mb-1">
          <b-col class="text-left">
            <b-button @click="moveList" size="sm" class="btn-neutral text-info">글 목록</b-button>
          </b-col>
        </b-row>
        <notice-input-item :userId="this.userInfo.userId" type="register" />
      </b-container>
    </div>
  </div>
</template>

<script>
import NoticeInputItem from "@/pages/components/notice/item/NoticeInputItem";
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "NoticeWrite",
  data() {
    return {
      article: {
        articleNo: 0,
        userId: "",
        subject: "",
        content: "",
        regTime: "",
      },
    };
  },
  components: {
    NoticeInputItem,
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    moveList() {
      this.$router.push({ name: "noticelist" });
    },
  },
};
</script>

<style></style>
