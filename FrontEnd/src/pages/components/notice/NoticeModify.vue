<template>
  <div>
    <div class="page-header clear-filter" filter-color="blue">
      <b-container class="bv-example-row mt-3">
        <div class="d-flex justify-content-between">
          <div class="row" style="font-size: 2rem">
            <b-icon icon="bell-fill"></b-icon>
            <p><b>글 수정하기</b></p>
          </div>
        </div>
        <notice-input-item :userId="this.userInfo.userId" type="modify" />
      </b-container>
    </div>
  </div>
</template>

<script>
import NoticeInputItem from "@/pages/components/notice/item/NoticeInputItem";
import { getArticle } from "@/api/notice";
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "NoticeModify",
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
  created() {
    let param = this.$route.params.articleNo;
    getArticle(
      param,
      ({ data }) => {
        this.article = data.notice;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
    message() {
      if (this.article.content) return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  methods: {
    moveList() {
      this.$router.push({ name: "noticelist" });
    },
  },
};
</script>

<style></style>
