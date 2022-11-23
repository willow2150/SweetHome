import jwtDecode from "jwt-decode";
import { apiInstance } from "../../api/http.js";

const api = apiInstance();

async function login(user, success, fail) {
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  // console.log(userid);
  await api
    .post(`/user/detail`, JSON.stringify(userid))
    .then(success)
    .catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  api.defaults.headers["refresh-token"] =
    sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
  await api.post(`/user/reissue`, user).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  const temp = {
    userId: userid,
  };
  await api
    .post(`/user/logout`, JSON.stringify(temp))
    .then(success)
    .catch(fail);
}

async function regist(user, success, fail) {
  await api.post(`/user/join`, JSON.stringify(user)).then(success).catch(fail);
}

async function changepassword(user, success, fail) {
  await api
    .put(`/user/change/password`, JSON.stringify(user))
    .then(success)
    .catch(fail);
}

async function changeprofile(user, success, fail) {
  await api.put(`/user/change`, JSON.stringify(user)).then(success).catch(fail);
}

async function deleteuser(userid, success, fail) {
  await api
    .delete(`/user/delete`, {
      data: {
        userId: userid,
      },
    })
    .then(success)
    .catch(fail);
}

const userStore = {
  namespaced: true,
  state: {
    userInfo: null,
    isLogin: false,
    isLoginError: false,
    isValidToken: false,
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
    checkToken: function (state) {
      return state.isValidToken;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_IS_VALID_TOKEN: (state, isValidToken) => {
      state.isValidToken = isValidToken;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
  },
  actions: {
    async userConfirm({ commit }, user) {
      await login(
        user,
        ({ data }) => {
          if (data.message === "success") {
            let accessToken = data["access-token"];
            let refreshToken = data["refresh-token"];
            // console.log(
            //   "login success token created!!!! >> ",
            //   accessToken,
            //   refreshToken
            // );
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            commit("SET_IS_VALID_TOKEN", true);
            sessionStorage.setItem("access-token", accessToken);
            sessionStorage.setItem("refresh-token", refreshToken);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
            commit("SET_IS_VALID_TOKEN", false);
            alert("아이디, 비밀번호를 확인하세요!");
          }
        },
        (error) => {
          console.log(error);
          commit("SET_IS_LOGIN", false);
          commit("SET_IS_LOGIN_ERROR", true);
          commit("SET_IS_VALID_TOKEN", false);
          alert("아이디, 비밀번호를 확인하세요!");
        }
      );
    },
    async getUserInfo({ commit, dispatch }, token) {
      let decodeToken = jwtDecode(token);
      // console.log("2. getUserInfo() decodeToken :: ", decodeToken);
      await findById(
        decodeToken,
        ({ data }) => {
          if (data.message === "success") {
            commit("SET_USER_INFO", data.user);
            // console.log("3. getUserInfo data >> ", data);
          } else {
            console.log("유저 정보 없음!!!!");
          }
        },
        async (error) => {
          console.log(
            "getUserInfo() error code [토큰 만료되어 사용 불가능.] ::: ",
            error.response.status
          );
          commit("SET_IS_VALID_TOKEN", false);
          await dispatch("tokenRegeneration");
        }
      );
    },
    async tokenRegeneration({ commit, state }) {
      console.log(
        "토큰 재발급 >> 기존 토큰 정보 : {}",
        sessionStorage.getItem("access-token")
      );
      await tokenRegeneration(
        JSON.stringify(state.userInfo),
        ({ data }) => {
          if (data.message === "success") {
            let accessToken = data["access-token"];
            console.log("재발급 완료 >> 새로운 토큰 : {}", accessToken);
            sessionStorage.setItem("access-token", accessToken);
            commit("SET_IS_VALID_TOKEN", true);
          }
        },
        async (error) => {
          // HttpStatus.UNAUTHORIZE(401) : RefreshToken 기간 만료 >> 다시 로그인!!!!
          if (error.response.status === 401) {
            console.log("갱신 실패");
            // 다시 로그인 전 DB에 저장된 RefreshToken 제거.
            await logout(
              state.userInfo.userid,
              ({ data }) => {
                if (data.message === "success") {
                  console.log("리프레시 토큰 제거 성공");
                } else {
                  console.log("리프레시 토큰 제거 실패");
                }
                alert("RefreshToken 기간 만료!!! 다시 로그인해 주세요.");
                commit("SET_IS_LOGIN", false);
                commit("SET_USER_INFO", null);
                commit("SET_IS_VALID_TOKEN", false);
                router.push({ name: "login" });
              },
              (error) => {
                console.log(error);
                commit("SET_IS_LOGIN", false);
                commit("SET_USER_INFO", null);
              }
            );
          }
        }
      );
    },

    async userLogout({ commit }, userid) {
      await logout(
        userid,
        ({ data }) => {
          if (data === "success") {
            commit("SET_IS_LOGIN", false);
            commit("SET_USER_INFO", null);
            commit("SET_IS_VALID_TOKEN", false);
          } else {
            console.log("유저 정보 없음!!!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },

    async userRegist({ commit }, user) {
      await regist(
        user,
        ({ data }) => {
          // console.log(data);
          if (data === "success") {
            console.log("회원가입성공!");
          } else {
            console.log("유저 정보 없음!!!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },

    async changePassword({ commit }, user) {
      console.log(user);
      await changepassword(
        user,
        ({ data }) => {
          console.log(data);
          if (data === "success") {
            alert("비밀번호 변경 완료!");
          } else {
            console.log("실패!!!!");
          }
        },
        (error) => {
          console.log(error);
          alert("기존 비밀번호를 확인하세요!");
        }
      );
    },

    async changeProfile({ commit }, user) {
      await changeprofile(
        user,
        ({ data }) => {
          if (data === "success") {
            alert("회원정보 수정 완료!");
            commit("SET_USER_INFO", user);
          } else {
            console.log("실패!!!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },

    async deleteUser({ commit }, userid) {
      await deleteuser(
        userid,
        ({ data }) => {
          if (data === "success") {
            // commit("SET_IS_LOGIN", false);
            // commit("SET_USER_INFO", null);
            // commit("SET_IS_VALID_TOKEN", false);
            alert("회원탈퇴 완료!");
          } else {
            console.log("실패!!!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default userStore;
