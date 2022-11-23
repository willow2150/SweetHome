import axios from "axios";
import { apiInstance } from "../../api/http.js";

const api = apiInstance();

async function findApt(address, success, fail) {
  await api
    .post(`/house/list`, JSON.stringify(address))
    .then(success)
    .catch(fail);
}

async function getAptListfun(address, success, fail) {
  await api.get(`/house/aptList/${address}`).then(success).catch(fail);
}

const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    dongs: [{ value: null, text: "선택하세요" }],
    searchintput: String,
    houses: [],
    house: null,
    aptlist: [],
  },
  getters: {},
  mutations: {
    // 시군구 리스트
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.code, text: sido.name });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        const temp = gugun.name.split(" ");
        const value = temp[1];
        state.guguns.push({ value: gugun.code, text: value });
      });
    },
    SET_DONG_LIST(state, dongs) {
      dongs.forEach((dong) => {
        const temp = dong.name.split(" ");
        const value = temp[2];
        state.dongs.push({ value: dong.code, text: value });
      });
    },
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_DONG_LIST(state) {
      state.dongs = [{ value: null, text: "선택하세요" }];
    },

    // 매물정보
    CLEAR_APT_LIST(state) {
      state.houses = [];
      state.house = null;
    },
    SET_HOUSE_LIST(state, houses) {
      state.houses = houses;
    },
    SET_DETAIL_HOUSE(state, house) {
      // console.log("Mutations", house);
      state.house = house;
    },

    // 아파트 리스트
    SET_APT_LIST(state, aptlist) {
      state.aptlist = aptlist;
    },
    CLEAR_APT_LISTS(state) {
      state.aptlist = [];
      state.aptlist = null;
    },
  },
  actions: {
    getSido({ commit }) {
      axios
        .get(
          `https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?regcode_pattern=*00000000`
        )
        .then(({ data }) => {
          // console.log(data);
          commit("SET_SIDO_LIST", data.regcodes);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getGugun({ commit }, sidoCode) {
      const tempSido = { sido: sidoCode };
      const params = tempSido.sido.slice(0, 2);
      axios
        .get(
          `https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?regcode_pattern=${params}*000000&is_ignore_zero=true`
        )
        .then(({ data }) => {
          commit("SET_GUGUN_LIST", data.regcodes);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getDong({ commit }, gugunCode) {
      const tempGugun = { gugun: gugunCode };
      const params = tempGugun.gugun.slice(0, 4);
      axios
        .get(
          `https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?regcode_pattern=${params}*&is_ignore_zero=true`
        )
        .then(({ data }) => {
          commit("SET_DONG_LIST", data.regcodes);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    async getHouseList({ commit }, address) {
      // console.log(address);
      await findApt(address, ({ data }) => {
        // console.log(data.houseList);
        commit("SET_HOUSE_LIST", data.houseList);
      }).catch((error) => {
        console.log(error);
      });
    },

    async getAptList({ commit }, address) {
      // console.log(address);
      await getAptListfun(address, ({ data }) => {
        // console.log(data.houseList);
        commit("SET_APT_LIST", data.houseList);
      }).catch((error) => {
        console.log(error);
      });
    },

    detailHouse({ commit }, house) {
      // 나중에 house.일련번호를 이용하여 API 호출
      // console.log(commit, house);
      commit("SET_DETAIL_HOUSE", house);
    },
  },
  modules: {},
  plugins: [
    // createPersistedState({
    //   // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
    //   storage: sessionStorage,
    // }),
  ],
};

export default houseStore;
