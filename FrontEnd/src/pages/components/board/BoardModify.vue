<template>
  <div>
    <div class="page-header clear-filter" filter-color="blue">
      <b-container class="bv-example-row mt-3">
        <div class="d-flex justify-content-between">
          <div class="row" style="font-size: 2rem">
            <b-icon icon="journals"></b-icon>
            <p><b>글 수정하기</b></p>
          </div>
        </div>
        <board-input-item :userId="this.userInfo.userId" type="modify" />
      </b-container>
    </div>
  </div>
</template>

<script>
import BoardInputItem from "@/pages/components/board/item/BoardInputItem";
import { getArticle } from "@/api/board";
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "BoardModify",
  // props: {
  //   userId: {
  //     type: String
  //   }
  // },
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
  computed: {
    ...mapState(userStore, ["userInfo"]),
    message() {
      if (this.article.content) return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  methods: {
    moveList() {
      this.$router.push({ name: "boardlist" });
    },
  },
};
</script>

<style></style>
