<template>
  <div>
    <div id="map"></div>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseKakao",
  components: {},
  data() {
    return {
      map: null,
      markers: [],
      prevCide: 0,
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=f144e405d2dfa5e48cc87aaa56f28251&libraries=services";
      document.head.appendChild(script);
    }
  },
  created() {
    this.CLEAR_APT_LIST();
  },
  computed: {
    ...mapState(houseStore, ["houses", "aptlist"]),
  },
  methods: {
    ...mapActions(houseStore, ["getHouseList", "getAptList"]),
    ...mapMutations(houseStore, ["CLEAR_APT_LIST", "CLEAR_APT_LISTS"]),

    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.50125817332294, 127.03957072166146),
        level: 3,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
      const map = this.map;

      window.kakao.maps.event.addListener(map, "dragend", () => {
        // 지도의 중심좌표를 얻어옵니다
        const latlng = this.map.getCenter();
        // const message =
        //   "중심 좌표는 위도 " +
        //   latlng.getLat() +
        //   ", 경도 " +
        //   latlng.getLng() +
        //   "입니다";

        // 좌표 변환
        const geocoder = new kakao.maps.services.Geocoder();
        // console.log(geocoder);

        const callback = (result, status) => {
          if (status === kakao.maps.services.Status.OK) {
            // console.log("지역 명칭 : " + result[0].address_name);
            // console.log("행정구역 코드 : " + result[0].code);
            const params = result[0].code.slice(0, 5);
            if (this.prevCode !== params) {
              this.CLEAR_APT_LISTS();
              this.getAptList(params);
              this.prevCode = params;
            }
          }
        };

        geocoder.coord2RegionCode(latlng.getLng(), latlng.getLat(), callback);
        this.displayMarker();
      });
    },

    displayMarker() {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }
      this.markers = [];

      if (this.aptlist.length !== 0) {
        this.aptlist.forEach((house) => {
          this.markers.push([house.lat, house.lng]);
        });
      }

      // console.log(this.aptlist);
      const positions = this.markers.map(
        (position) => new kakao.maps.LatLng(...position)
      );

      console.log(this.aptlist.length);

      // this.aptlist.forEach((apt) => {
      //   const latlng = new kakao.maps.LatLng(apt.lat, apt.lng);
      //   var marker = new kakao.maps.Marker({
      //     map: this.map, // 마커를 표시할 지도
      //     position: latlng, // 마커를 표시할 위치
      //     title: apt.houseName, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
      //   });
      // });

      if (positions.length > 0) {
        this.markers = positions.map(
          (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position: position,
            })
        );
      }
    },
  },
};
</script>

<style>
#map {
  width: 100%;
  height: 55rem;
}
</style>
