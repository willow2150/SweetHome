import { apiInstance } from "./http.js";
const api = apiInstance();

async function getRegionCodes(user_id, success, fail) {
  api.get(`/favorite-region/list/{user_id}`).then(success).catch(fail);
}

// async function getFavoriteHousedeal(param, success, fail) {
//   api.get(`/board/list`, { params: param }).then(success).catch(fail);
// }

// async function getFavoriteRegion(article, success, fail) {
//   api.post(`/board/write`, JSON.stringify(article)).then(success).catch(fail);
// }

// async function getFavoriteHousedealList(param, success, fail) {
//   api.get(`/board/list`, { params: param }).then(success).catch(fail);
// }

// async function getFavoriteRegionList(article, success, fail) {
//   api.post(`/board/write`, JSON.stringify(article)).then(success).catch(fail);
// }

export { getRegionCodes };
