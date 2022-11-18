<template>
  <div class="list-group list-group-flush overflow-auto h-50">
    <div class="list-group-item list-group-item-action">
      <h3>{{ house.아파트 }}</h3>
      거래금액 : {{ house.거래금액 }} 만원<br />
      면적 : 76.66 2018.4.6
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

<style></style>
