<template>
  <section class="section-sm">
    <div class="container">
      <div class="row justify-content-center">
        <router-view></router-view>
      </div>
    </div>
  </section>
</template>

<script>
import { mapActions } from 'vuex';
const HouseStore = 'HouseStore';

export default {
  name: 'HouseView',
  computed: {
    // 감시하고 싶은 state..
  },
  created() {},
  mounted() {
    this.getBanner('아파트매물 검색');
    this.getParent('Home');
    this.getChild('House');
    this.initAptDatas();
  },
  methods: {
    ...mapActions(['getBanner', 'getParent', 'getChild']),
    ...mapActions(HouseStore, ['initAptDatas']),
  },
};
</script>

<style></style>
