<template>
  <div>
    <div class="h4 section-title" style="text-align: left">게시글</div>
    <!-- <div v-if="userInfo.id === 'admin'"> -->
    <div v-if="userInfo.id === 'admin'" class="d-grid gap-2 d-md-flex justify-content-md-end">
      <button class="btn btn-primary mb-2" @click="moveHandler">글 작성</button>
    </div>
    <div v-else style="text-align: right"><p>공지사항 작성은 관리자만 가능합니다.</p></div>
    <article class="card mb-4" v-for="(notice, index) in notices" :key="index" style="text-align: left">
      <div class="row card-body" styel="text-align:left">
        <div class="col-md-12">
          <h3 class="h4 mb-3">
            <router-link class="post-title" :to="{ name: 'Noticedetail', query: { no: notice.no } }">
              {{ notice.title }}
            </router-link>
          </h3>
          <ul class="card-meta list-inline">
            <li class="list-inline-item"><i class="ti-user"></i>{{ notice.id }}</li>
            <li class="list-inline-item"><i class="ti-calendar"></i>{{ notice.date }}</li>
          </ul>
          <p>{{ notice.content }}</p>
        </div>
      </div>
    </article>
  </div>
</template>

<script>
import { mapActions, mapGetters, mapState } from 'vuex';

const NoticeStore = 'NoticeStore';
const UserStore = 'UserStore';

export default {
  computed: {
    ...mapGetters(NoticeStore, ['notices']),
    ...mapState(UserStore, ['userInfo']),
  },
  created() {
    this.getSelectNotices();
  },
  mounted() {
    this.getBanner('공지사항');
    this.getParent('Home');
    this.getChild('Notice');
  },
  methods: {
    ...mapActions(['getBanner', 'getParent', 'getChild']),
    ...mapActions(NoticeStore, ['getSelectNotices']),
    moveHandler() {
      this.$router.push({ name: 'Noticeregist' });
    },
  },
};
</script>

<style></style>
