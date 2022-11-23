<template>
  <div>
    <button @click="displayMarker">버튼</button>
    <div id="result"></div>
    <div id="map">12</div>
  </div>
</template>

<script>
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseKakao",
  components: {},
  data() {
    return {
      map: null,
      markers: [],
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
  computed: {
    ...mapState(houseStore, ["houses"]),
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.50125817332294, 127.03957072166146),
        level: 4,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
      const map = this.map;

      window.kakao.maps.event.addListener(map, "dragend", () => {
        // 지도의 중심좌표를 얻어옵니다
        var latlng = this.map.getCenter();

        const message =
          "중심 좌표는 위도 " +
          latlng.getLat() +
          ", 경도 " +
          latlng.getLng() +
          "입니다";

        // 좌표 변환
        var geocoder = new kakao.maps.services.Geocoder();

        var callback = function (result, status) {
          if (status === kakao.maps.services.Status.OK) {
            console.log(result);
            console.log("지역 명칭 : " + result[0].address_name);
            console.log("행정구역 코드 : " + result[0].code);
          }
        };

        geocoder.coord2RegionCode(latlng.getLng(), latlng.getLat(), callback);
      });
    },

    displayMarker() {
      this.markers = [];

      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }
      // console.log(this.houses);

      this.houses.forEach((house) => {
        this.markers.push([house.lat, house.lng]);
      });

      console.log(this.markers);

      const positions = this.markers.map(
        (position) => new kakao.maps.LatLng(...position)
      );

      console.log(positions);

      if (positions.length > 0) {
        this.markers = positions.map(
          (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position,
            })
        );

        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds()
        );

        console.log(bounds);

        this.map.setBounds(bounds);
      }
    },
  },
};
</script>

<style>
#map {
  width: 100%;
  height: 45rem;
}
</style>
