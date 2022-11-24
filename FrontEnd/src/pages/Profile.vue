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
            <p>{{this.favoriteRegionCodes.length }}</p>
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
        <div class="row">
          <div class="col-md-6 ml-auto mr-auto">
            <h4 class="title text-center">내 관심 지역</h4>
            <div class="favorite">
              <b-dropdown
                split
                split-variant="outline-info"
                variant="info"
                text="관심 지역을 선택하세요."
                class="m-2"
                
              >
                <b-dropdown-item 
                  v-for="(favoriteRegionName, index) in favoriteRegionNames" :key="index"
                  @click=setRegionIdx(index)>{{
                  favoriteRegionName
                }}</b-dropdown-item>
              </b-dropdown>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { mapState } from "vuex";
import { getRegionCodes, getRegionNameList } from "@/api/favorite";

const userStore = "userStore";

export default {
  data() {
    return {

      // 관심 지역 코드 배열
      favoriteRegionCodes: [],
      // 관심 지역 코드
      favoriteRegionIdx: 0,
      // 관심 지역 이름 목록...
      favoriteRegionNames: [],
    };
  },
  name: "profile",
  bodyClass: "profile-page",
  components: {},
  
  created() {
    let param = this.userInfo.userId;
    getRegionCodes(
      param,
      ({ data }) => {
        this.favoriteRegionCodes = data.favoriteRegionCodeList;
        
        this.favoriteRegionCodes.forEach((object) => {
          getRegionNameList(
            object.dongCode,
            ({ data }) => {
              
              this.favoriteRegionNames.push(data.regionName);
            },
            (error) => {
              console.log(error);
            }
          )
        });
      },
      (error) => {
        console.log(error);
      }
    );
    
    
    // console.log(this.favoriteRegionNames);

  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    setRegionIdx(index) {
      this.favoriteRegionIdx = index;
      goHouseInfosOfRegion(this.favoriteRegionIdx)
    },

    goHouseInfosOfRegion(dongCode) {
      
    }
  },
};
</script>
<style>
.favorite {
  text-align: center;
}
</style>
