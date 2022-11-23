<template>
  <div>
    <div class="" id="modals">
      <n-button type="info" @click.native="modals.classic = true">
        <i class="now-ui-icons text_align-left"></i>
        아파트 목록 펼치기
      </n-button>
    </div>
    <!-- Classic Modal -->
    <modal :show.sync="modals.classic" id="modal">
      <h5 slot="header" class="title title-up">1</h5>
      <div v-if="aptlist && aptlist.length != 0">
        <HouseListItem v-for="(house, i) in aptlist" :key="i" :house="house" />
      </div>
      <div v-else>
        <h3 style="color: black">해당 지역에 매물이 없습니다ㅜㅜ</h3>
      </div>
    </modal>
  </div>
</template>

<script>
import { Button, Modal } from "@/components";
import HouseListItem from "./HouseListItem.vue";
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {
    [Button.name]: Button,
    Modal,
    HouseListItem,
  },
  data() {
    return {
      modals: {
        classic: false,
        mini: false,
      },
    };
  },
  computed: {
    ...mapState(houseStore, ["houses", "house", "aptlist"]),
    // houses() {
    //   return this.$store.state.houses;
    // },
  },
};
</script>

<style>
#modals {
  position: absolute;
  left: 1.5rem;
  z-index: 2;
}
#modal {
  width: 305px;
  height: 55rem;
  position: absolute;
  left: 1rem;
  z-index: 3;
  margin: 0;
}

#modal > .modal-dialog {
  margin: 0;
}

#modal > .modal-dialog > .modal-content > .modal-header {
  padding: 0;
}

#modal > .modal-dialog > .modal-content > .modal-body {
  padding: 12px;
  padding-bottom: 8px;
}

#modal::-webkit-scrollbar {
  width: 6px; /* 스크롤바의 너비 */
}

#modal::-webkit-scrollbar-thumb {
  height: 30%; /* 스크롤바의 길이 */
  background: #6b6b6b; /* 스크롤바의 색상 */

  border-radius: 10px;
}

#modal::-webkit-scrollbar-track {
  background: rgba(255, 255, 255, 0.1); /*스크롤바 뒷 배경 색상*/
}
</style>
