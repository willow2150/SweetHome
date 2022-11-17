<template>
  <navbar
    position="fixed"
    :transparent="transparent"
    :color-on-scroll="colorOnScroll"
    menu-classes="ml-auto"
  >
    <template>
      <router-link v-popover:popover1 class="navbar-brand" to="/">
        <span class="h2" style="font-weight: bold">Sweet Home</span>
      </router-link>
      <!-- <el-popover
        ref="popover1"
        popper-class="popover"
        placement="bottom"
        width="200"
        trigger="hover"
      >
      </el-popover> -->
    </template>
    <template slot="navbar-menu">
      <li class="nav-item">
        <a
          class="nav-link"
          href="https://www.creative-tim.com/product/vue-now-ui-kit"
          target="_blank"
        >
          <!-- <i class="now-ui-icons arrows-1_cloud-download-93"></i> -->
          <p>Template Site</p>
        </a>
      </li>
      <li class="nav-item">
        <a
          class="nav-link"
          href="https://demos.creative-tim.com/now-ui-dashboard/examples/icons.html"
          target="_blank"
        >
          <p>Icon</p>
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="" target="/">
          <router-link to="/landing">
            <p>아파트 매물 조회</p>
          </router-link>
        </a>
      </li>
      <drop-down
        tag="li"
        title="게시판"
        icon="now-ui-icons files_single-copy-04"
        class="nav-item"
      >
        <nav-link to="/notice">
          <i class="now-ui-icons files_paper"></i> 공지사항
        </nav-link>
        <nav-link to="/board">
          <i class="now-ui-icons design_bullet-list-67"></i> 자유게시판
        </nav-link>
      </drop-down>
      <drop-down
        tag="li"
        title="회원 정보"
        icon="now-ui-icons users_circle-08"
        class="nav-item"
      >
        <nav-link to="/profile">
          <i class="now-ui-icons users_single-02"></i> 내 프로필
        </nav-link>
        <nav-link to="">
          <i class="now-ui-icons ui-2_favourite-28"></i> 내 관심지역
        </nav-link>
      </drop-down>

      <li class="nav-item">
        <router-link to="/login" class="nav-link btn btn-neutral text-info">
          <i class="now-ui-icons users_single-02"></i>
          <p>로그인</p>
        </router-link>
      </li>

      <!-- <li class="nav-item">
        <a
          class="nav-link"
          rel="tooltip"
          title="Follow us on Twitter"
          data-placement="bottom"
          href="https://twitter.com/CreativeTim"
          target="_blank"
        >
          <i class="fab fa-twitter"></i>
          <p class="d-lg-none d-xl-none">Twitter</p>
        </a>
      </li>
      <li class="nav-item">
        <a
          class="nav-link"
          rel="tooltip"
          title="Like us on Facebook"
          data-placement="bottom"
          href="https://www.facebook.com/CreativeTim"
          target="_blank"
        >
          <i class="fab fa-facebook-square"></i>
          <p class="d-lg-none d-xl-none">Facebook</p>
        </a>
      </li>
      <li class="nav-item">
        <a
          class="nav-link"
          rel="tooltip"
          title="Follow us on Instagram"
          data-placement="bottom"
          href="https://www.instagram.com/CreativeTimOfficial"
          target="_blank"
        >
          <i class="fab fa-instagram"></i>
          <p class="d-lg-none d-xl-none">Instagram</p>
        </a>
      </li> -->
    </template>
  </navbar>
</template>

<script>
import { DropDown, Navbar, NavLink } from "@/components";
import { Popover } from "element-ui";
export default {
  name: "main-navbar",
  props: {
    transparent: Boolean,
    colorOnScroll: Number,
  },
  components: {
    DropDown,
    Navbar,
    NavLink,
    [Popover.name]: Popover,
  },
};
</script>

<style>
p {
  font-size: 15px;
}
</style>
