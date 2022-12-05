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
// import TempView from '@/house/tmp';
// import HouseListUp from '@/comments/house/HouseListUpView';
// import HouseListDown from '@/components/house/HouseListDownView.vue';
// import HouseListUpView from '@/components/house/HouseListUpView.vue';
import { mapActions } from 'vuex';
const BookmarkStore = 'BookmarkStore';

export default {
  name: 'BookmarkView',
  // components: { HouseListDown, HouseListUpView },
  computed: {
    // 감시하고 싶은 state..
    // ...mapGetters(['banner', 'parent', 'child']),
  },
  created() {
    // this.getBanner('아파트매물 검색');
    // this.getParent('Home');
    // this.getChild('House');
  },
  mounted() {
    this.getBanner('관심지역');
    this.getParent('Home');
    this.getChild('Bookmark');
    // this.initAptDatas();
  },
  methods: {
    ...mapActions(['getBanner', 'getParent', 'getChild']),
    ...mapActions(BookmarkStore, ['initAptDatas']),
  },
};
</script>

<style></style>
