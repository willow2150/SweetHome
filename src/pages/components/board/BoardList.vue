<template>
  <div>
    <div class="page-header clear-filter" filter-color="blue">
      <b-container class="bv-example-row mt-3">
        <div class="d-flex justify-content-between">
          <div class="row" style="font-size: 2rem">
            <b-icon icon="journals"></b-icon>
            <p><b>Board</b></p>
          </div>
          <b-button @click="moveWrite" class="btn-neutral text-info">글쓰기</b-button>
        </div>
        <b-row>
          <b-col>
            <b-table striped hover :items="articles" :fields="fields" @row-clicked="viewArticle">
              <template #cell(subject)="data">
                <router-link :to="{ name: 'boardview', params: { articleno: data.item.articleno } }">
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
  name: "BoardList",
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
    http.get(`/board/list`).then(({ data }) => {
      this.articles = data;
    });
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "boardwrite" });
    },
    viewArticle(article) {
      this.$router.push({
        name: "boardview",
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
