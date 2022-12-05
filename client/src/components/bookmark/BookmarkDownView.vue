<template>
  <section class="section-sm">
    <div class="container">
      <h2 class="h5 section-title">관심지역 목록</h2>
      <div class="row justify-content-center">
        <div class="col-lg-4 mb-5 mb-lg-0">
          <table class="table table-hover text-center" style="display: ">
            <thead>
              <tr>
                <!-- <th align="right"></th> -->
                <th align="center">주소</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(culMap, index) in map" :key="index">
                <td align="center">
                  <a class="post-title" @click="setMap(culMap.dongCode)">{{ culMap.address }}&nbsp;&nbsp;&nbsp;</a>
                  <a class="text-primary font-weight-600" @click="deleteBookmark(culMap.dongCode)">삭제</a>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="col-lg-8 mb-5 mb-lg-0">
          <kakao-map></kakao-map>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import KakaoMap from '@/components/bookmark/BookmarkKakaoMap.vue';
import { mapState, mapGetters, mapActions } from 'vuex';
const BookmarkStore = 'BookmarkStore';
const UserStore = 'UserStore';

export default {
  components: { KakaoMap },
  data() {
    return {};
  },
  computed: {
    // 감시하고 싶은 state..
    ...mapGetters(BookmarkStore, ['map', 'houses']),
    ...mapState(UserStore, ['userInfo']),
  },
  // watch: {
  //   map(newMap) {
  //     console.log('watch........map:', newMap);
  //   },
  // },
  created() {
    //DOM객체와 연결되었고 Vue의 속성들도 초기화 된 상태
    this.getSearchBookmark(this.userInfo.id);
  },
  // updated() {
  //   this.initAptDatas();
  // },
  // mounted() {
  //   this.getBookMarkApt(dongCode);
  // },
  methods: {
    ...mapActions(BookmarkStore, ['getBookMarkApt', 'getSearchBookmark', 'getBookMarkDelete']),
    setMap(dongCode) {
      this.getBookMarkApt(dongCode);
    },
    deleteBookmark(dongCode) {
      this.getBookMarkDelete({ id: this.userInfo.id, dongCode });
    },
  },
};
</script>
<style></style>
