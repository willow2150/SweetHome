<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form>
        <b-form-group id="userId-group" label="글쓴이:" label-for="userId">
          <b-form-input
            id="userId"
            :disabled="isUserid"
            v-model="this.userInfo.userId"
            type="text"
            required
            readonly
          ></b-form-input>
        </b-form-group>

        <b-form-group
          v-if="this.type === 'register' || this.type === 'modify'"
          id="subject-group"
          label="제목:"
          label-for="subject"
          description="제목을 입력하세요."
        >
          <b-form-input
            id="subject"
            v-model="article.subject"
            type="text"
            required
            placeholder="제목 입력"
          ></b-form-input>
        </b-form-group>
        <b-form-group
          v-if="this.type === 'view'"
          id="subject-group"
          label="제목:"
          label-for="subject"
        >
          <b-form-input
            id="subject"
            v-model="article.subject"
            type="text"
            required
            readonly
          ></b-form-input>
        </b-form-group>

        <b-form-group
          v-if="this.type === 'register' || this.type === 'modify'"
          id="content-group"
          label="내용:"
          label-for="content"
        >
          <b-form-textarea
            id="content"
            v-model="article.content"
            placeholder="내용 입력"
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>
        <b-form-group
          v-if="this.type === 'view'"
          id="content-group"
          label="내용:"
          label-for="content"
        >
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
            >글 작성</b-button
          >
          <b-button
            @click="modifyThisArticle"
            align="left"
            v-else-if="this.type === 'modify'"
            type="submit"
            variant="primary"
            class="btn-neutral text-info"
            >글 수정</b-button
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

      <br /><br /><br /><br />

      <b-form v-if="this.type === 'view'">
        <b-form-group
          id="userId-group"
          label="댓글을 작성하세요"
          label-for="userId"
        >
          <b-form-input
            id="userId"
            :disabled="isUserid"
            v-model="comment.content"
            type="text"
            required
          ></b-form-input>
        </b-form-group>
        <b-button
          @click="writecomment"
          align="left"
          type="submit"
          variant="primary"
          class="btn-neutral text-info"
          >댓글 작성</b-button
        >
      </b-form>

      <br />
      <b-form-group
        v-if="this.type === 'view'"
        id="content-group"
        label="댓글 목록"
        label-for="content"
      >
        <b-form-textarea
          v-for="(cmt, index) in comments"
          :key="index"
          id="content"
          v-model="cmt.content"
          readonly
        ></b-form-textarea>
      </b-form-group>
    </b-col>
  </b-row>
</template>

<script>
import {
  getArticle,
  writeArticle,
  modifyArticle,
  getCommentList,
  writeComment,
} from "@/api/board";
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "BoardInputItem",
  data() {
    return {
      article: {
        articleNo: 0,
        userId: "",
        subject: "",
        content: "",
      },
      comments: [
        {
          userId: "",
          comment: "",
        },
      ],
      comment: {
        userId: "",
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
          this.article = data.board;
        },
        (error) => {
          console.log(error);
        }
      );
    }
    if (this.type === "view") {
      getCommentList(
        this.$route.params.articleNo,
        ({ data }) => {
          this.comments = data.comments;
        },
        (error) => {
          console.log(error);
        }
      );
    }
    this.comment.userId = this.userInfo.userId;
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
      this.$router.push({ name: "boardlist" });
    },
    writecomment() {
      let param = {
        userId: this.comment.userId,
        comment: this.comment.content,
      };
      writeComment(
        param,
        ({ data }) => {
          let message = "댓글 작성 중 문제가 발생했습니다.";
          if (data.message === "success") {
            message = "댓글이 등록되었습니다.";
          }
          alert(message);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style></style>
