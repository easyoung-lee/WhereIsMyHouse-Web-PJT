import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

/* ----CSS---- */
require("@/assets/plugins/bootstrap/bootstrap.min.css");
require("@/assets/plugins/themify-icons/themify-icons.css");
require("@/assets/plugins/slick/slick.css");
require("@/assets/css/style.css");

/* ----JS---- */
require("@/assets/plugins/jQuery/jquery.min.js");
require("@/assets/plugins/bootstrap/bootstrap.min.js");
require("@/assets/plugins/slick/slick.min.js");
require("@/assets/plugins/instafeed/instafeed.min.js");
require("@/assets/plugins/search/fuse.min.js");
require("@/assets/plugins/search/mark.js");
require("@/assets/plugins/search/search.js");
require("@/assets/js/script.js");

Vue.config.productionTip = false;

Vue.use(BootstrapVue);
Vue.use(IconsPlugin);
new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
