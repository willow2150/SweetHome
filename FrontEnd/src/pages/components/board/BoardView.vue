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
            <b-button @click="deleteArticle" size="sm" class="btn-neutral text-info">글 삭제</b-button>
          </b-col>
        </b-row>
        <board-input-item type="view" />
      </b-container>
    </div>
  </div>
</template>

<script>
import BoardInputItem from "@/pages/components/board/item/BoardInputItem";
// import { getArticle } from "@/api/board";
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
    // let param = this.$route.params.articleNo;
    // getArticle(
    //   param,
    //   ({ data }) => {
    //     this.article = data.board;
    //   },
    //   (error) => {
    //     console.log(error);
    //   }
    // );
  },
  methods: {
    moveModifyArticle() {
      this.$router.replace({
        name: "boardmodify",
        params: { articleNo: this.article.articleNo },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleNo}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제할까요?")) {
        this.$router.replace({
          name: "boarddelete",
          params: { articleNo: this.article.articleNo },
        });
      }
    },
    moveList() {
      this.$router.push({ name: "boardlist" });
    },
  },
  // filters: {
  //   dateFormat(regTime) {
  //     return moment(new Date(regTime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style></style>
