<template>
  <div>
    <div class="page-header clear-filter" filter-color="blue">
      <b-container class="bv-example-row mt-3">
        <div class="row" style="font-size: 2rem">
            <b-icon icon="journals"></b-icon>
            <p><b>글 작성하기</b></p>
          </div>
        <board-input-item type="register" />
      </b-container>
    </div>
  </div>

</template>

<script>
import BoardInputItem from "@/pages/components/board/item/BoardInputItem";

export default {
  name: "BoardWrite",
  components: {
    BoardInputItem,
  },
};
</script>

<style></style>
