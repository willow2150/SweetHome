<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>공지사항 수정</h3></b-alert>
      </b-col>
    </b-row>
    <notice-input-item type="modify" />
  </b-container>
</template>

<script>
import NoticeInputItem from "@/pages/components/notice/item/NoticeInputItem";

export default {
  name: "NoticeModify",
  components: {
    NoticeInputItem,
  },
};
</script>

<style></style>
