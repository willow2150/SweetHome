<template>
  <div>
    <nav>
      <b-row class="mb-3" style="">
        <b-col class="sm-3 pt-3">
          <b-form-select
            id="si"
            v-model="sidoCode"
            :options="sidos"
            @change="gugunList"
          ></b-form-select>
        </b-col>
        <b-col class="sm-3 pt-3">
          <b-form-select
            id="gun"
            v-model="gugunCode"
            :options="guguns"
            @change="dongList"
          ></b-form-select>
        </b-col>
        <b-col class="sm-3 pt-3">
          <b-form-select
            id="dong"
            v-model="dongCode"
            :options="dongs"
          ></b-form-select>
        </b-col>
        <b-col class="sm-3 pt-3 px-0">
          <fg-input placeholder="아파트 이름을 입력하세요"></fg-input>
        </b-col>
        <b-col cols="2" class="px-0">
          <button
            class="btn btn-info btn-lg"
            style="font-weight: bold"
            @click="searchApt"
          >
            아파트 조회
          </button>
        </b-col>
      </b-row>
    </nav>
  </div>
</template>
<script>
import { mapState, mapActions, mapMutations } from "vuex";
import { FormGroupInput } from "@/components";

const houseStore = "houseStore";

export default {
  name: "HouseControl",
  bodyClass: "housecontrol-page",
  components: {
    [FormGroupInput.name]: FormGroupInput,
  },
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      address: {
        sidoName: null,
        gugunName: null,
        dongName: null,
      },
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs", "houses"]),
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  created() {
    // this.$store.dispatch("getSido");
    // this.sidoList();
    this.CLEAR_SIDO_LIST();
    this.CLEAR_APT_LIST();
    this.getSido();
  },
  methods: {
    searchApt() {
      const tempSi = document.getElementById("si");
      const tempGun = document.getElementById("gun");
      const tempDong = document.getElementById("dong");
      this.address.sidoName = tempSi.options[tempSi.selectedIndex].text;
      this.address.gugunName = tempGun.options[tempGun.selectedIndex].text;
      if (tempDong.options[tempDong.selectedIndex].text != "선택하세요") {
        this.address.dongName = tempDong.options[tempDong.selectedIndex].text;
      } else {
        this.address.dongName = null;
      }
      // console.log(this.address);

      if (this.gugunCode) this.getHouseList(this.address);
    },
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseList",
    ]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "CLEAR_APT_LIST",
    ]),
    sidoList() {
      this.getSido();
    },
    gugunList() {
      // console.log(this.guguns);
      this.CLEAR_GUGUN_LIST();
      this.CLEAR_DONG_LIST();
      this.CLEAR_APT_LIST();
      this.gugunCode = null;
      this.dongCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      // console.log(this.guguns);
      this.CLEAR_DONG_LIST();
      this.CLEAR_APT_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
  },
};
</script>
<style></style>
