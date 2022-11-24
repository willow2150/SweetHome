<template>
  <div>
    <div class="page-header clear-filter" filter-color="blue">
      <b-container class="bv-example-row mt-3">
        <div class="d-flex justify-content-between">
          <div class="row" style="font-size: 2rem">
            <b-icon icon="journals"></b-icon>
            <p><b>글 상세 정보</b></p>
          </div>
        </div>
        <b-row class="mb-1">
          <b-col class="text-left">
            <b-button @click="moveList" size="sm" class="btn-neutral text-info">글 목록</b-button>
          </b-col>
          <b-col class="text-right">
            <b-button @click="moveModifyArticle" size="sm" class="btn-neutral text-info">글 수정</b-button>
            <b-button @click="deleteThisArticle" size="sm" class="btn-neutral text-info">글 삭제</b-button>
          </b-col>
        </b-row>
        <board-input-item :userId="this.article.userId" type="view" />
      </b-container>
    </div>
  </div>
</template>

<script>
import BoardInputItem from "@/pages/components/board/item/BoardInputItem";
import { getArticle, deleteArticle } from "@/api/board";
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "BoardDetail",
  data() {
    return {
      article: {
        articleNo: 0,
        userId: "",
        subject: "",
        content: "",
        regTime: "",
      },
      userId: "",
    };
  },
  components: {
    BoardInputItem,
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
    message() {
      if (this.article.content) return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    let param = this.$route.params.articleNo;
    getArticle(
      param,
      ({ data }) => {
        this.article = data.board;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveModifyArticle() {
      if (this.userInfo.userId !== this.article.userId) {
        alert("권한이 없는 요청입니다.");
        return;
      }
      this.$router.replace({
        name: "boardmodify",
        params: { articleNo: this.article.articleNo },
      });
    },
    deleteThisArticle() {
      if (this.userInfo.userId !== this.article.userId) {
        alert("권한이 없는 요청입니다.");
        return;
      }
      if (confirm("정말로 삭제할까요?")) {
        let param = this.article.articleNo;
        deleteArticle(
          param,
          ({ data }) => {
            let message = "삭제 처리시 문제가 발생했습니다.";
            if (data.message === "success") {
              message = "삭제가 완료되었습니다.";
            }
            alert(message);
          },
          (error) => {
            console.log(error);
          }
        );
        this.$router.push({ name: "boardlist" });
      }
    },

    moveList() {
      this.$router.push({ name: "boardlist" });
    },
  },
};
</script>

<style></style>
