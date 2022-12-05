<template>
  <!-- 검색 창 -->
  <div class="">
    <div class="container">
      <div class="row align-items-center">
        <!-- Start Search Form -->
        <div class="search-form wow" data-wow-delay=".7s">
          <div class="row mt-2">
            <div class="col-lg-2 col-md-2 col-12 p-0">
              <div class="search-input">
                <label for="sido"><i class="lni lni-map-marker theme-color"></i></label>
                <select name="sido" id="sido" @change="setGuGun" v-model="sido">
                  <option
                    v-for="(sidoitem, index) in sidoList"
                    :key="index"
                    :value="sidoitem.value"
                  >
                    {{ sidoitem.name }}
                  </option>
                </select>
              </div>
            </div>
            <div class="col-lg-2 col-md-2 col-12 p-0">
              <div class="search-input">
                <label for="gugun"><i class="lni lni-map-marker theme-color"></i></label>
                <select name="gugun" id="gugun" @change="setDong" v-model="gugun">
                  <option
                    v-for="(gugunitem, index) in gugunList"
                    :key="index"
                    :value="gugunitem.value"
                  >
                    {{ gugunitem.name }}
                  </option>
                </select>
              </div>
            </div>
            <div class="col-lg-2 col-md-2 col-12 p-0">
              <div class="search-input">
                <label for="dong"><i class="lni lni-map-marker theme-color"></i></label>
                <select name="dong" id="dong" v-model="dong">
                  <option
                    v-for="(dongitem, index) in dongList"
                    :key="index"
                    :value="dongitem.value"
                  >
                    {{ dongitem.name }}
                  </option>
                </select>
              </div>
            </div>
            <div class="col-lg-2 col-md-2 col-12 p-0">
              <div class="search-input">
                <label for="dong"><i class="lni lni-map-marker theme-color"></i></label>
                <input type="text" name="aptName" id="aptName" v-model="word" />
              </div>
            </div>
            <div class="col-lg-2 col-md-2 col-12 p-0 search-btn button">
              <button class="btn" @click="getAptDatas">
                <i class="lni lni-search-alt"></i> Search
              </button>
            </div>
          </div>
        </div>
        <!-- End Search Form -->
      </div>
    </div>
  </div>
  <!-- 검색 창 -->
</template>

<script>
import axios from "axios";
import { mapState, mapGetters, mapActions } from "vuex";
const userStore = "userStore";
const searchStore = "searchStore";

export default {
  name: "searchBox",
  data() {
    return {
      sido: "",
      gugun: "",
      dong: "",
      word: "",
      sidoList: [{ name: "도/광역시", value: "" }],
      gugunList: [{ name: "시/구/군", value: "" }],
      dongList: [{ name: "동", value: "" }],
      apts: [],
      pgno: "",
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },
  created() {
    this.sendRequest("sido", "*00000000");
  },
  methods: {
    ...mapActions(searchStore, ["getAptData"]),
    ...mapActions(userStore, ["userLogout"]),
    setSido(dataa, sido) {
      dataa.regcodes.forEach(function (regcode) {
        sido.push({ name: regcode.name, value: regcode.code });
      });
    },
    setGuGun() {
      this.initGuGun();
      this.initDong();
      if (this.sido != "") this.sendRequest("gugun", this.sido.substr(0, 2) + "*00000");
    },
    addGuGun(dataa, gugun) {
      dataa.regcodes.forEach(function (regcode) {
        gugun.push({ name: regcode.name.split(" ").pop(), value: regcode.code });
      });
    },
    setDong() {
      this.initDong();
      if (this.gugun != "") this.sendRequest("dong", this.gugun.substr(0, 5) + "*");
      return "";
    },
    addDong(dataa, dong) {
      dataa.regcodes.forEach(function (regcode) {
        dong.push({ name: regcode.name.split(" ").pop(), value: regcode.code });
      });
    },
    initGuGun() {
      this.gugunList = [{ name: "시/구/군", value: "" }];
      this.gugun = "";
    },
    initDong() {
      this.dongList = [{ name: "동", value: "" }];
      this.dong = "";
    },
    getAptDatas() {
      this.getAptData({
        sido: this.sido,
        gugun: this.gugun,
        dong: this.dong,
        pgno: this.pgno,
        word: this.word,
      });
      if (this.$route.fullPath == "/") this.$router.push({ name: "aptSearch" });
    },
    sendRequest(selid, regcode) {
      const url = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes";
      let params;
      switch (selid) {
        case "sido":
          params = "regcode_pattern=" + regcode + "&is_ignore_zero=true";
          axios(`${url}?${params}`).then(({ data }) => {
            this.setSido(data, this.sidoList);
          });
          break;
        case "gugun":
          params = "regcode_pattern=" + regcode + "&is_ignore_zero=true";
          axios(`${url}?${params}`).then(({ data }) => {
            console.log(data);
            this.addGuGun(data, this.gugunList);
          });
          break;
        case "dong":
          params = "regcode_pattern=" + regcode + "&is_ignore_zero=true";
          axios(`${url}?${params}`).then(({ data }) => {
            this.addDong(data, this.dongList);
          });
          break;
      }
    },
  },
};
</script>

<style></style>
