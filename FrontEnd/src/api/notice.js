import { apiInstance } from "./http.js";
const api = apiInstance();

async function getArticleList(param, success, fail) {
  api.get(`/notice/list`, { params: param }).then(success).catch(fail);
}

async function writeArticle(article, success, fail) {
  api.post(`/notice/write`, JSON.stringify(article)).then(success).catch(fail);
}

async function getArticle(articleno, success, fail) {
  api.get(`/notice/view/${articleno}`).then(success).catch(fail);
}

async function modifyArticle(article, success, fail) {
  api.put(`/notice/modify`, JSON.stringify(article)).then(success).catch(fail);
}

async function deleteArticle(articleno, success, fail) {
  api.delete(`/notice/delete/${articleno}`).then(success).catch(fail);
}

export { getArticleList, writeArticle, getArticle, modifyArticle, deleteArticle };
