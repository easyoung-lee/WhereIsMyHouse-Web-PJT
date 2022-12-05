<template>
  <b-container class="bv-example-row mt-3 mb-5 text-center">
    <!-- <h2>UserView</h2> -->
    <router-view></router-view>
  </b-container>
</template>

<script>
import { mapActions } from 'vuex';

export default {
  name: 'UserView',
  // components: { HouseListDown, HouseListUpView },
  computed: {
    // 감시하고 싶은 state..
    // ...mapGetters(['banner', 'parent', 'child']),
  },
  created() {
    // this.getBanner('');
    // this.getParent('');
    // this.getChild('');
  },
  // updated() {
  //   this.getBanner('');
  //   this.getParent('');
  //   this.getChild('');
  // },
  methods: {
    ...mapActions(['getBanner', 'getParent', 'getChild']),
  },
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(72, 190, 233, 0.3) 30%);
}
</style>
