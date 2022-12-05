<template>
  <div style="width: 100%">
    <section class="section pb-0" style="height: 150px">
      <div class="container" style="width: 80%; position: relative; bottom: 100px">
        <div class="row justify-content-center">
          <div class="col-lg-10 mb-5 mb-lg-0">
            <h2 class="h5 section-title">관심지역 설정</h2>
            <div class="widget">
              <h4 class="widget-title"><span>Option</span></h4>
              <div class="order-2 order-lg-3 d-flex align-items-center justify-content-center">
                <form id="registForm" method="GET">
                  <select
                    class="form-select bg-secondary text-light mr-3"
                    name="sido"
                    id="sido"
                    @change="setGuGun"
                    v-model="sido"
                  >
                    <option v-for="(sidoitem, index) in sidoList" :key="index" :value="sidoitem.value">
                      {{ sidoitem.name }}
                    </option>
                  </select>
                  <select
                    class="form-select bg-secondary text-light mr-3"
                    name="gugun"
                    id="gugun"
                    @change="setDong"
                    v-model="gugun"
                  >
                    <option v-for="(gugunitem, index) in gugunList" :key="index" :value="gugunitem.value">
                      {{ gugunitem.name }}
                    </option>
                  </select>
                  <select class="form-select bg-secondary text-light mr-3" name="dong" id="dong" v-model="dong">
                    <option v-for="(dongitem, index) in dongList" :key="index" :value="dongitem.value">
                      {{ dongitem.name }}
                    </option>
                  </select>
                  <!-- <a class="btn btn-outline-primary" @click="searchAptDatas">검색!</a> -->
                </form>
                <button class="btn btn-primary mr-3 ml-3" id="regist" @click="setBookmark">설정하기</button>
                <!-- search -->
              </div>
            </div>
          </div>
          <div></div>
          <div class="col-12">
            <div class="border-bottom border-default"></div>
          </div>
        </div>
      </div>
    </section>
    <router-view></router-view>
  </div>
</template>

<script>
import axios from 'axios';
// import http from '@/api/http';
import { mapState, mapActions } from 'vuex';
const BookmarkStore = 'BookmarkStore';
const UserStore = 'UserStore';

export default {
  name: 'BookmarkUpView',
  data() {
    return {
      sido: '',
      gugun: '',
      dong: '',
      aptName: '',
      sidoList: [{ name: '시도선택', value: '' }],
      gugunList: [{ name: '구군선택', value: '' }],
      dongList: [{ name: '동선택', value: '' }],
      apts: [],
      pgno: 1,
    };
  },
  computed: {
    // 감시하고 싶은 state..
    ...mapState(UserStore, ['userInfo']),
  },
  created() {
    //DOM객체와 연결되었고 Vue의 속성들도 초기화 된 상태
    this.sendRequest('sido', '*00000000');
  },
  methods: {
    ...mapActions(BookmarkStore, ['getBookmarkData']),
    setBookmark() {
      this.getBookmarkData({
        id: this.userInfo.id,
        sido: this.sido,
        gugun: this.gugun,
        dong: this.dong,
      });
    },
    setSido(dataa, sido) {
      dataa.regcodes.forEach(function (regcode) {
        sido.push({ name: regcode.name, value: regcode.code });
      });
    },
    setGuGun() {
      this.initGuGun();
      this.initDong();
      if (this.sido != '') this.sendRequest('gugun', this.sido.substr(0, 2) + '*00000');
    },
    addGuGun(dataa, gugun) {
      dataa.regcodes.forEach(function (regcode) {
        gugun.push({ name: regcode.name.split(' ').pop(), value: regcode.code });
      });
    },
    setDong() {
      this.initDong();
      if (this.gugun != '') this.sendRequest('dong', this.gugun.substr(0, 5) + '*');
      return '';
    },
    addDong(dataa, dong) {
      dataa.regcodes.forEach(function (regcode) {
        dong.push({ name: regcode.name.split(' ').pop(), value: regcode.code });
      });
    },
    initGuGun() {
      this.gugunList = [{ name: '구군선택', value: '' }];
      this.gugun = '';
    },
    initDong() {
      this.dongList = [{ name: '동선택', value: '' }];
      this.dong = '';
    },
    sendRequest(selid, regcode) {
      const url = 'https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes';
      let params;
      switch (selid) {
        case 'sido':
          params = 'regcode_pattern=' + regcode + '&is_ignore_zero=true';
          axios(`${url}?${params}`).then(({ data }) => {
            this.setSido(data, this.sidoList);
          });
          break;
        case 'gugun':
          params = 'regcode_pattern=' + regcode + '&is_ignore_zero=true';
          axios(`${url}?${params}`).then(({ data }) => {
            console.log(data);
            this.addGuGun(data, this.gugunList);
          });
          break;
        case 'dong':
          params = 'regcode_pattern=' + regcode + '&is_ignore_zero=true';
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
