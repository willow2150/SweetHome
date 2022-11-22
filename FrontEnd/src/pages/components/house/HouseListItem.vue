<template>
  <div id="listcard" class="list-group list-group-flush overflow-auto h-50">
    <div class="list-group-item list-group-item-action">
      <h3>{{ house.houseName }}</h3>
      거래금액 : {{ house.dealAmount }} 만원<br />
      면적 : 76.66 2018.4.6 <br />
      거래일시 : {{ house.dealYear }}년, {{ house.dealMonth }}월
    </div>
  </div>
</template>

<script>
export default {
  name: "HouseListItem",
  props: {
    house: Object,
  },
};
</script>

<style>
#listcard {
  cursor: pointer;
}
</style>
