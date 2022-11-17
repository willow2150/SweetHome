<template>
  <div>
    <div class="page-header clear-filter" filter-color="blue">
      <b-container class="bv-example-row mt-3">
        <div class="d-flex justify-content-between">
          <div class="row" style="font-size: 2rem">
            <b-icon icon="bell-fill"></b-icon>
            <p><b>Notice</b></p>
          </div>
          <b-button @click="moveWrite" class="btn-neutral text-info">공지사항 작성하기</b-button>
        </div>
        <b-row>
          <b-col>
            <b-table striped hover :items="articles" :fields="fields" @row-clicked="viewArticle">
              <template #cell(subject)="data">
                <router-link :to="{ name: 'noticeview', params: { articleno: data.item.articleno } }">
                  {{ data.item.subject }}
                </router-link>
              </template>
            </b-table>
          </b-col>
        </b-row>
      </b-container>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";

export default {
  name: "NoticeList",
  data() {
    return {
      articles: [],
      fields: [
        { key: "articleno", label: "글번호", tdClass: "tdClass" },
        { key: "subject", label: "제목", tdClass: "tdSubject" },
        { key: "userid", label: "작성자", tdClass: "tdClass" },
        { key: "regtime", label: "작성일", tdClass: "tdClass" },
        { key: "hit", label: "조회수", tdClass: "tdClass" },
      ],
    };
  },
  created() {
    http.get(`/notice/list`).then(({ data }) => {
      this.articles = data;
    });
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "noticewrite" });
    },
    viewArticle(article) {
      this.$router.push({
        name: "noticeview",
        params: { articleno: article.articleno },
      });
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
