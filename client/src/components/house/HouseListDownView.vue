<template>
  <section class="section-sm">
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-lg-8 mb-5 mb-lg-0">
          <h2 class="h5 section-title">아파트 정보</h2>
          <table class="table table-hover text-center" style="display: ">
            <thead>
              <tr>
                <!-- <th align="right"></th> -->
                <th align="center">아파트이름</th>
                <th align="center">층</th>
                <th align="center">면적</th>
                <th align="center">법정동</th>
                <th align="center">거래금액</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(house, index) in houses" :key="index">
                <td align="center">
                  <!-- <router-link class="post-title" :to="{ name: 'HouseDetailView', query: { no: house.no } }">
                    {{ house.aptName }}
                  </router-link> -->

                  <a class="post-title" @click="setAptData(house.no)"> {{ house.aptName }}</a>
                  <!-- {{ house.aptName }} -->
                </td>
                <td align="center">{{ house.floor }}</td>
                <td align="center">{{ house.area }}</td>
                <td align="center">{{ house.dongName }}</td>
                <td align="center">{{ house.dealAmount }} 만원</td>
              </tr>
            </tbody>
          </table>
          <ul class="pagination justify-content-center">
            <li class="page-item page-item active">
              <a href="#!" class="page-link">1</a>
            </li>
            <li class="page-item">
              <a href="#!" class="page-link">2</a>
            </li>
            <li class="page-item">
              <a href="#!" class="page-link">»</a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
const HouseStore = 'HouseStore';
export default {
  data() {
    return {};
  },
  computed: {
    // 감시하고 싶은 state..
    ...mapGetters(HouseStore, ['houses']),
  },
  created() {
    //DOM객체와 연결되었고 Vue의 속성들도 초기화 된 상태
  },
  // updated() {
  //   this.initAptDatas();
  // },
  methods: {
    ...mapActions(HouseStore, ['getAptData']),
    setAptData(no) {
      this.getAptData(no);
      this.$router.push({ name: 'HouseDetailView' });
    },
  },
};
</script>

<style></style>
