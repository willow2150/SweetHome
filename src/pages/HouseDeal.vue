<template>
  <div class="page-header clear-filter" filter-color="white">
    <div class="container">
      <house-control />
      <div class="row">
        <div class="col-md-12">
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
    HouseControl,
    HouseKakao,
    HouseList,
  },
  data() {
    return {
    };
  },
};
</script>
<style></style>
