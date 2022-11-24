<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form>
        <b-form-group id="userId-group" label="글쓴이:" label-for="userId">
          <b-form-input v-if="this.type === 'view'"
            id="userId"
            :disabled="isUserid"
            v-model="this.article.userId"
            type="text"
            required
            readonly
          ></b-form-input>
          <b-form-input v-else
            id="userId"
            :disabled="isUserid"
            v-model="this.userId"
            type="text"
            required
            readonly
          ></b-form-input>
        </b-form-group>

        <b-form-group v-if="this.type === 'register' || this.type === 'modify'" id="subject-group" label="제목:" label-for="subject" description="제목을 입력하세요.">
          <b-form-input
            id="subject"
            v-model="article.subject"
            type="text"
            required
            placeholder="제목 입력"
          ></b-form-input>
        </b-form-group>
        <b-form-group v-if="this.type === 'view'" id="subject-group" label="제목:" label-for="subject"">
          <b-form-input
            id="subject"
            v-model="article.subject"
            type="text"
            required
            readonly
          ></b-form-input>
        </b-form-group>


        <b-form-group v-if="this.type === 'register' || this.type === 'modify'" id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="article.content"
            placeholder="내용 입력"
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>
        <b-form-group v-if="this.type === 'view'" id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="article.content"
            rows="10"
            max-rows="15"
            readonly
          ></b-form-textarea>
        </b-form-group>

        <div align="left">
          <b-button
            @click="registarticle"
            align="left"
            v-if="this.type === 'register'"
            type="submit"
            variant="primary"
            class="btn-neutral text-info"
            >공지사항 작성</b-button
          >
          <b-button
            @click="modifyThisArticle"
            align="left"
            v-else-if="this.type === 'modify'"
            type="submit"
            variant="primary"
            class="btn-neutral text-info"
            >공지사항 수정</b-button
          >
          <b-button
            @click="moveList"
            align="left"
            v-if="this.type !== 'view'"
            type="reset"
            variant="primary"
            class="btn-neutral text-info"
            >돌아가기</b-button
          >
        </div>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import { getArticle, writeArticle, modifyArticle } from "@/api/notice";
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "NoticeInputItem",
  data() {
    return {
      article: {
        articleNo: 0,
        userId: "",
        subject: "",
        content: "",
      },
      isUserid: false,
    };
  },
  props: {
    userId: {
      type: String,
    },
    type: { type: String },
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  created() {
    let param = this.$route.params.articleNo;
    if (this.type !== "register") {
      getArticle(
        param,
        ({ data }) => {
          this.article = data.notice;
        },
        (error) => {
          console.log(error);
        }
      );
    }
  },
  methods: {
    registarticle() {
      this.article.userId = this.userInfo.userId;
      let param = this.article;
      writeArticle(
        param,
        ({ data }) => {
          let message = "글 작성 중 문제가 발생했습니다.";
          if (data.message === "success") {
            message = "작성이 완료되었습니다.";
          }
          alert(message);
          // 현재 route를 /list로 변경.
        },
        (error) => {
          console.log(error);
        }
      );
      this.moveList();
    },
    modifyThisArticle() {
      let param = {
        articleNo: this.article.articleNo,
        userId: this.article.userId,
        subject: this.article.subject,
        content: this.article.content,
      };
      modifyArticle(
        param,
        ({ data }) => {
          let message = "수정 처리시 문제가 발생했습니다.";
          if (data.message === "success") {
            message = "수정이 완료되었습니다.";
          }
          alert(message);
          // 현재 route를 /list로 변경.
        },
        (error) => {
          console.log(error);
        }
      );
      this.moveList();
    },
    moveList() {
      this.$router.push({ name: "noticelist" });
    },
  },
};
</script>

<style></style>
