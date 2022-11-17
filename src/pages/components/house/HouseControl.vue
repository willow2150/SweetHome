<template>
  <div>
    <nav>
      <b-row class="mb-3">
        <b-col class="sm-3">
          <b-form-select
            v-model="sidoCode"
            :options="sidos"
            @change="gugunList"
          ></b-form-select>
        </b-col>
        <b-col class="sm-3">
          <b-form-select
            v-model="gugunCode"
            :options="guguns"
            @change="dongList"
          ></b-form-select>
        </b-col>
        <b-col class="sm-3">
          <b-form-select v-model="dongCode" :options="dongs"></b-form-select>
        </b-col>
      </b-row>
    </nav>
  </div>
</template>
<script>
import { mapState, mapActions, mapMutations } from "vuex";
export default {
  name: "HouseControl",
  bodyClass: "housecontrol-page",
  components: {},
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
    };
  },
  computed: {
    ...mapState(["sidos", "guguns", "dongs", "houses"]),
    sidos() {
      return this.$store.state.sidos;
    },
  },
  created() {
    // this.$store.dispatch("getSido");
    // this.sidoList();
    this.CLEAR_SIDO_LIST();
    this.CLEAR_APT_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(["getSido", "getGugun", "getDong", "getHouseList"]),
    ...mapMutations([
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "CLEAR_APT_LIST",
    ]),
    // https://juso.dev/docs/about/
    sidoList() {
      this.getSido();
    },
    gugunList() {
      // console.log(this.guguns);
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      // console.log(this.guguns);
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    searchApt() {
      if (this.gugunCode) this.getHouseList(this.gugunCode);
    },
  },
};
</script>
<style></style>
