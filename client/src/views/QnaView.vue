<template>
  <section class="section-sm">
    <div class="container">
      <router-view></router-view>
    </div>
  </section>
</template>

<script>
import { mapActions } from 'vuex';

export default {
  name: 'QnaView',
  computed: {
    // 감시하고 싶은 state..
  },
  created() {},
  methods: {
    ...mapActions(['getBanner', 'getParent', 'getChild']),
  },
};
</script>

<style></style>
