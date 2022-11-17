<template>
  <div class="page-header clear-filter" filter-color="white">
    <div class="container">
      <house-control />
      <div class="row">
        <!-- <div class="col-md-0">여기에 리스트</div> -->
        <div class="col-md-12">
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
export default {
  name: "HouseDeal",
  bodyClass: "housedeal-page",
  components: {
    [Button.name]: Button,
    [FormGroupInput.name]: FormGroupInput,
    HouseControl,
    HouseKakao,
  },
  data() {
    return {
      form: {
        firstName: "",
        email: "",
        message: "",
      },
    };
  },
};
</script>
<style></style>
