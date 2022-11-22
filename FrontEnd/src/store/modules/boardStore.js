import { apiInstance } from "../../api/http.js";

const api = apiInstance();

// 게시판 글 목록 조회
async function getarticlelist(param, success, fail) {
  api.get(`/board/list`, { params: param }).then(success).catch(fail);
}

// // 게시판 글 작성
// async function writearticle(article, success, fail) {
//   api.post(`/board/write`, JSON.stringify(article)).then(success).catch(fail);
// }

// // 게시판 글 수정
// async function modifyarticle(article, success, fail) {
//   api.put(`/board/modify`, JSON.stringify(article)).then(success).catch(fail);
// }

// // 게시판 글 조회
// async function getarticle(articleno, success, fail) {
//   api.get(`/board/search/${articleno}`).then(success).catch(fail);
// }

// // 게시판 글 삭제
// async function deletearticle(articleno, success, fail) {
//   api.delete(`/board/delete/${articleno}`).then(success).catch(fail);
// }

// // 게시판 글 목록 검색(조건)
// async function searcharticle(articleno, success, fail) {
//   api.post(`/board/search/${articleno}`).then(success).catch(fail);
// }

const boardStore = {
  namespaced: true,
  state: {
    articles: [
      {
        articleno: null,
        userid: null,
        subject: null,
        content: null,
        hit: null,
        regtime: null,
      },
    ],
  },
  getters: {},
  mutations: {
    GET_ARTICLE_LIST(state, articles) {
      articles.forEach((article) => {
        state.sidos.push({
          articleno: article.articleno,
          userid: article.userid,
          subject: article.subject,
          content: article.content,
          hit: article.hit,
          regtime: article.regtime,
        });
      });
    },
  },
  actions: {
    async getArticleList({ commit }, articles) {
      console.log(articles);
      await getarticlelist(articles, ({ data }) => {
        commit("메써드", data.boardList);
      }).catch((error) => {
        console.log(error);
      });
    },

    // async writeArticle({ commit },) {

    // },

    // async modifyArticle({ commit },) {

    // },

    // async getArticle({ commit },) {

    // },

    // async deleteArticle({ commit },) {

    // },

    // async searchArticle({ commit },) {

    // },
  },
};

export default boardStore;
