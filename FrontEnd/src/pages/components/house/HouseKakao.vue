<template>
  <div>
    <div id="map"></div>

    <!-- Classic Modal -->
    <modal :show.sync="houseInfoModal.classic" id="houseInfoModal">
      <h5 slot="header" class="title title-up">1</h5>
      <!-- <img src="img/apt/1.PNG" width="350px" /> -->
      <div id="roadview" style="width: 100%; height: 350px"></div>
      <p class="category text-info" style="color: black; font-size: 36px">
        {{ this.selectedApt.houseName }}
      </p>
      <div class="row" style="color: black; text-align: center">
        <div class="col-2"></div>
        <div class="col-3"><p class="category text-info">상세 주소</p></div>
        <div class="col-6">
          <p class="description" style="color: black">
            {{ this.selectedApt.address }}
          </p>
        </div>
        <div class="col-2"></div>
      </div>
      <div class="row" style="color: black; text-align: center">
        <div class="col-2"></div>
        <div class="col-3"><p class="category text-info">거래 일시</p></div>
        <div class="col-6">
          <p class="description" style="color: black">
            {{ this.selectedApt.dealYear }}년 {{ this.selectedApt.dealMonth }}월
          </p>
        </div>
        <div class="col-2"></div>
      </div>
      <div class="row" style="color: black; text-align: center">
        <div class="col-2"></div>
        <div class="col-3"><p class="category text-info">가격</p></div>
        <div class="col-6">
          <p class="description" style="color: black">
            {{ this.selectedApt.dealAmount }}만원
          </p>
        </div>
        <div class="col-2"></div>
      </div>
      <div class="row" style="color: black; text-align: center">
        <div class="col-2"></div>
        <div class="col-3"><p class="category text-info">면적</p></div>
        <div class="col-6">
          <p class="description" style="color: black">
            {{ this.selectedApt.area }}㎡
          </p>
        </div>
        <div class="col-2"></div>
      </div>
      <div class="row" style="color: black; text-align: center">
        <div class="col-2"></div>
        <div class="col-3"><p class="category text-info">층수</p></div>
        <div class="col-6">
          <p class="description" style="color: black">
            {{ this.selectedApt.floor }}층
          </p>
        </div>
        <div class="col-2"></div>
      </div>
    </modal>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import { Modal } from "@/components";
import img from "../../../../public/img/home7.png";
import { apiInstance } from "../../../api/http.js";

const api = apiInstance();

const houseStore = "houseStore";

function random(n) {
  return Math.floor(Math.random() * 100000) % 6;
}

export default {
  name: "HouseKakao",
  components: {
    Modal,
  },
  data() {
    return {
      houseInfoModal: {
        classic: false,
        mini: false,
      },
      map: null,
      markers: [],
      selectedApts: [],
      selectedApt: {},
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
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=f144e405d2dfa5e48cc87aaa56f28251&libraries=services,clusterer,drawing";
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
        level: 5,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);

      window.kakao.maps.event.addListener(this.map, "dragend", () => {
        // 지도의 중심좌표를 얻어옵니다
        const latlng = this.map.getCenter();

        // 좌표 변환
        const geocoder = new kakao.maps.services.Geocoder();

        const callback = (result, status) => {
          if (status === kakao.maps.services.Status.OK) {
            const params = result[0].code.slice(0, 5);
            this.CLEAR_APT_LISTS();
            this.getAptList(params);
            this.prevCode = params;
            clusterer = null;
          }
        };

        geocoder.coord2RegionCode(latlng.getLng(), latlng.getLat(), callback);
        this.displayMarker();

        // 마커 클러스터러를 생성합니다
        var clusterer = new kakao.maps.MarkerClusterer({
          map: this.map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
          averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
          minLevel: 6, // 클러스터 할 최소 지도 레벨
        });

        clusterer.addMarkers(this.markers);
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
            title: apt.houseName,
            image: markerImage,
          })
      );

      var roadviewContainer = document.getElementById("roadview"); //로드뷰를 표시할 div
      var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
      var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체
      let position = new kakao.maps.LatLng(33.450701, 126.570667);
      // 미커별 이벤트추가
      this.markers.forEach((marker) => {
        var index = 0;
        // 마커에 클릭이벤트를 등록합니다
        kakao.maps.event.addListener(marker, "click", () => {
          // 마커 위에 인포윈도우를 표시합니다
          this.chooseApt(marker.Gb);
          this.houseInfoModal.classic = true;
          index = this.markers.indexOf(marker, 0);
        });

        position = new kakao.maps.LatLng(
          this.aptlist[index].lat,
          this.aptlist[index].lng
        );
      });

      // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
      roadviewClient.getNearestPanoId(position, 30, (panoId) => {
        roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
      });
    },

    chooseApt(aptCode) {
      const params = { houseName: aptCode };
      api.post(`/house/list`, JSON.stringify(params)).then((data) => {
        this.selectedApt = null;
        this.selectedApts = null;
        this.selectedApts = data.data.houseList;
        this.selectedApt = this.selectedApts[0];
        // console.log(this.selectedApt);
        // console.log(this.selectedApt.houseName);
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

#houseInfoModal > .modal-dialog > .modal-content > .modal-body {
  padding-top: 0;
}
</style>
