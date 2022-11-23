import { apiInstance } from "./http.js";
const api = apiInstance();

async function getArticleList(param, success, fail) {
  api.get(`/board/list`, { params: param }).then(success).catch(fail);
}

async function writeArticle(article, success, fail) {
  api.post(`/board`, JSON.stringify(article)).then(success).catch(fail);
}

async function getArticle(articleno, success, fail) {
  api.get(`/board/view/${articleno}`).then(success).catch(fail);
}

async function modifyArticle(article, success, fail) {
  api.put(`/board/modify`, JSON.stringify(article)).then(success).catch(fail);
}

async function deleteArticle(articleno, success, fail) {
  api.delete(`/board/delete/${articleno}`).then(success).catch(fail);
}

export { getArticleList, writeArticle, getArticle, modifyArticle, deleteArticle };
