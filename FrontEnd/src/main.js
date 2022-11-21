import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import NowUiKit from "./plugins/now-ui-kit";
// import store from "./store";
import store from "./store/index";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";

Vue.config.productionTip = false;

Vue.use(NowUiKit);
Vue.use(BootstrapVue);
Vue.use(IconsPlugin);

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
