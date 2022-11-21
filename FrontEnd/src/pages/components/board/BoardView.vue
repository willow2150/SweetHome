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
            <b-button @click="moveList" size="sm" class="btn-neutral text-info"
              >목록으로</b-button
            >
          </b-col>
          <b-col class="text-right">
            <b-button
              @click="moveModifyArticle"
              size="sm"
              class="btn-neutral text-info"
              >글수정</b-button
            >
            <b-button
              @click="deleteArticle"
              size="sm"
              class="btn-neutral text-info"
              >글삭제</b-button
            >
          </b-col>
        </b-row>
        <b-row>
          <b-col>
            <b-card
              class="clear-filter text-left"
              filter-color="blue"
              :header-html="`<h3>${article.articleno}.${article.subject}
            [${article.hit}]</h3><div><h6>${article.userid}</div><div>${article.regtime}</h6></div>`"
              border-variant="dark"
              no-body
            >
              <b-card-body class="text-left">
                <div v-html="message"></div>
              </b-card-body>
            </b-card>
          </b-col>
        </b-row>
      </b-container>
    </div>
  </div>
</template>

<script>
// import moment from "moment";
import http from "@/api/http";

export default {
  name: "BoardDetail",
  data() {
    return {
      article: {
        articleno: "1",
        subject: "제목",
        userid: "정상기",
        regtime: "11.11.11",
        hit: "1",
      },
    };
  },
  computed: {
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    http.get(`/board/view/${this.$route.params.articleno}`).then(({ data }) => {
      this.article = data;
    });
  },
  methods: {
    moveModifyArticle() {
      this.$router.replace({
        name: "boardmodify",
        params: { articleno: this.article.articleno },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제할까요?")) {
        this.$router.replace({
          name: "boarddelete",
          params: { articleno: this.article.articleno },
        });
      }
    },
    moveList() {
      this.$router.push({ name: "boardlist" });
    },
  },
  // filters: {
  //   dateFormat(regtime) {
  //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style></style>
