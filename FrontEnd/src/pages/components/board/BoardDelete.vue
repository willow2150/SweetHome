<template>
  <div>
    <div class="page-header clear-filter" filter-color="blue">
      <b-container class="bv-example-row mt-3"> </b-container>
    </div>
  </div>
</template>

<script>
import { deleteArticle } from "@/api/board";

export default {
  name: "BoardDelete",
  created() {
    deleteArticle(
      this.$route.params.articleNo,
      ({ data }) => {
        let message = "삭제 처리시 문제가 발생했습니다.";
        if (data.message === "success") {
          message = "삭제가 완료되었습니다.";
        }
        alert(message);
        // 현재 route를 /list로 변경.
        this.$router.push({ name: "boardlist" });
      },
      (error) => {
        console.log(error);
      }
    );
  },
};
</script>

<style></style>
