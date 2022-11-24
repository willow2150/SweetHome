<template>
  <div>
    <div class="page-header clear-filter" filter-color="blue">
      <parallax class="page-header-image"> </parallax>
      <div class="container">
        <div class="photo-container">
          <img src="" alt="" />
        </div>
        <h3 class="title">{{ userInfo.userName }}</h3>
        <p class="email">{{ userInfo.userEmail }}</p>
        <div class="content">
          <div class="social-description">
            <h2>2</h2>
            <p>내 관심지역</p>
          </div>
        </div>
      </div>
    </div>
    <div class="section">
      <div class="container">
        <div class="button-container">
          <router-link to="/profilemodify">
            <div class="btn btn-info btn-round btn-lg">회원 정보 수정</div>
          </router-link>
        </div>
        <h3 class="title">11</h3>
        <div class="row">
          <div class="col-md-6 ml-auto mr-auto">
            <h4 class="title text-center">내 관심 지역</h4>
            <div class="favorite">
              <b-dropdown
                split
                split-variant="outline-primary"
                variant="primary"
                text="관심 지역을 선택하세요."
                class="m-2"
              >
                <b-dropdown-item v-for="(favoriteRegionCode, index) in favoriteRegionCodes" :key="index">{{
                  favoriteRegionCode
                }}</b-dropdown-item>
              </b-dropdown>
              <b-button @click="searchRegionCodes"> 아파트 거래 조회하기 </b-button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { mapState } from "vuex";
import { getRegionCodes } from "@/api/favorite";

const userStore = "userStore";

export default {
  data() {
    return {
      // 관심 지역 코드 배열
      // favorite-region   ("/list/{user_id}") 로 찾기
      favoriteRegionCodes: [],
      favoriteRegionCode: {
        dongCode: "",
      },

      // 관심 지역 목록... 컨트롤러 추가해야되는데?
      favoriteRegions: [],
      favoriteRegion: {
        region: "",
      },

      // 관심 지역 거래 리스트  .. 동코드로 리스트 찾는거.
      // house		/list/{dongCode}" 동코드만 넣어주면 된다.
      // 받는 거는
      houseDealList: [],
      houseDeal: {
        dealNo: "",
        dongCode: "",
        houseName: "",
        address: "",
        buildYear: "",
        lng: "",
        lat: "",
        dealYear: "",
        dealMonth: "",
        area: "",
        floor: "",
        dealAmount: "",
      },
    };
  },
  name: "profile",
  bodyClass: "profile-page",
  components: {},
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    searchRegionCodes() {
      let param = this.userInfo.userId;
      getRegionCodes(
        param,
        ({ data }) => {
          this.favoriteRegionCodes = data.favoriteRegionCodeList;
        },
        (error) => {
          console.log(error);
        }
      );
      console.log(this.favoriteRegionCodes);
    },

    // searchDeals() {
    //   let param = {
    //     userId: this.favoriteDeal.userId,
    //     dongCode: this.favoriteDeal.housedeal_no,
    //   };
    // },
  },
};
</script>
<style>
.favorite {
  text-align: center;
}
</style>

<!--
아이디에 포함된 동코드 다찾아오기(selectFavoriteRegionCodeList)
=>selectRegionByDongCode 해서 지역 이름 다 가져오기(리스트에 뿌리기)
검색누르면 동코드로 지역 검색 시도하기(selectHouseListByDongCode)
-->
