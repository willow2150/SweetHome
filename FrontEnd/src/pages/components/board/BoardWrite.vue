<template>
  <div>
    <div class="page-header clear-filter" filter-color="blue">
      <b-container class="bv-example-row mt-3">
        <div class="row" style="font-size: 2rem">
          <b-icon icon="journals"></b-icon>
          <p><b>글 작성하기</b></p>
        </div>
        <b-row class="mb-1">
          <b-col class="text-left">
            <b-button @click="moveList" size="sm" class="btn-neutral text-info">글 목록</b-button>
          </b-col>
        </b-row>
        <board-input-item :userId="this.userInfo.userId" type="register" />
      </b-container>
    </div>
  </div>
</template>

<script>
import BoardInputItem from "@/pages/components/board/item/BoardInputItem";
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "BoardWrite",
  data() {
    return {
      article: {
        articleNo: 0,
        userId: "",
        subject: "",
        content: "",
        regTime: "",
      },
    };
  },
  components: {
    BoardInputItem,
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    moveList() {
      this.$router.push({ name: "boardlist" });
    },
  },
};
</script>

<style></style>
