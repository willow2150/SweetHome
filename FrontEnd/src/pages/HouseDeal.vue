<template>
  <div class="page-header clear-filter" filter-color="white">
    <div class="container" style="max-width: 115rem">
      <div class="row">
        <div class="col-md-12 pb-0">
          <HouseList />
          <HouseKakao />
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { Button, FormGroupInput } from "@/components";
import HouseControl from "./components/house/HouseControl.vue";
import HouseKakao from "./components/house/HouseKakao.vue";
import HouseList from "./components/house/HouseList.vue";

export default {
  name: "HouseDeal",
  bodyClass: "housedeal-page",
  components: {
    [Button.name]: Button,
    [FormGroupInput.name]: FormGroupInput,
    HouseKakao,
    HouseList,
  },
  data() {
    return {};
  },
};
</script>
<style></style>
