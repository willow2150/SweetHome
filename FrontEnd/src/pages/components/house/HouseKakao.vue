<template>
  <div>
    <div id="map"></div>

    <!-- Classic Modal -->
    <modal :show.sync="houseInfoModal.classic" id="houseInfoModal">
      <h5 slot="header" class="title title-up">1</h5>
      <div><span style="color: black">여기에 상세정보? 리스트?</span></div>
    </modal>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import { Modal } from "@/components";
import img from "../../../../public/img/home7.png";

const houseStore = "houseStore";

export default {
  name: "HouseKakao",
  components: {
    Modal,
  },
  data() {
    return {
      houseInfoModal: {
        classic: true,
        mini: false,
      },
      map: null,
      markers: [],
      selectedApt: null,
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
      this.displayMarker();
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
            this.CLEAR_APT_LISTS();
            this.getAptList(params);
            this.prevCode = params;
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

      const imageSrc = img, // 마커이미지의 주소입니다
        imageSize = new kakao.maps.Size(35, 35), // 마커이미지의 크기입니다
        imageOption = { offset: new kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

      var markerImage = new kakao.maps.MarkerImage(
        imageSrc,
        imageSize,
        imageOption
      );

      // 좌표로 마커찍기
      this.markers = this.aptlist.map(
        (apt) =>
          new kakao.maps.Marker({
            map: this.map,
            position: new kakao.maps.LatLng(apt.lat, apt.lng),
            title: apt.houseCode,
            image: markerImage,
          })
      );
      console.log(this.markers);
      console.log(this.aptlist);
      // 미커별 이벤트추가
      this.markers.forEach((marker) => {
        // 마커에 클릭이벤트를 등록합니다
        kakao.maps.event.addListener(marker, "click", () => {
          console.log(marker);
          // 마커 위에 인포윈도우를 표시합니다
          this.selectedApt = null;
          this.houseInfoModal.classic = true;
        });
      });
    },
  },
};
</script>

<style>
#map {
  width: 100%;
  height: 50rem;
}

#houseInfoModal {
  width: 100%;
  height: 50rem;
  position: absolute;
  margin: 0;
}

#houseInfoModal > .modal-dialog > .modal-content {
  width: 50rem;
  height: 47rem;
  position: absolute;
}
</style>
