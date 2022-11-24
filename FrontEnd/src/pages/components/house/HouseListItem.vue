<template>
  <div id="listcard" class="list-group list-group-flush overflow-auto h-50">
    <div class="list-group-item list-group-item-action">
      <h3>{{ house.houseName }}</h3>
      <h5>{{ house.dong }}</h5>
      건축년도 : {{ house.buildYear }}년
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
