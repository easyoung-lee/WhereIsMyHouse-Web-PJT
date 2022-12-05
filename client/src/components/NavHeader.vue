<template>
  <!-- navigation -->
  <header class="navigation fixed-top">
    <div class="container">
      <nav class="navbar navbar-expand-lg navbar-white">
        <a class="navbar-brand order-1" href="/">
          <img
            class="img-fluid"
            width="100px"
            src="@/assets/MyImage/logo/logo1.png"
            alt="Reader | Hugo Personal Blog Template"
          />
        </a>
        <div class="collapse navbar-collapse text-center order-lg-2 order-3" id="navigation">
          <ul class="navbar-nav mx-auto">
            <!-- <li class="nav-item dropdown">
              <a
                class="nav-link"
                href="#"
                role="button"
                data-toggle="dropdown"
                aria-haspopup="true"
                aria-expanded="false"
              >
                homepage <i class="ti-angle-down ml-1"></i>
              </a>
              <div class="dropdown-menu">
                <a class="dropdown-item" href="index-full.html">Homepage Full Width</a>

                <a class="dropdown-item" href="index-full-left.html">Homepage Full With Left Sidebar</a>

                <a class="dropdown-item" href="index-full-right.html">Homepage Full With Right Sidebar</a>

                <a class="dropdown-item" href="index-list.html">Homepage List Style</a>

                <a class="dropdown-item" href="index-list-left.html">Homepage List With Left Sidebar</a>

                <a class="dropdown-item" href="index-list-right.html">Homepage List With Right Sidebar</a>

                <a class="dropdown-item" href="index-grid.html">Homepage Grid Style</a>

                <a class="dropdown-item" href="index-grid-left.html">Homepage Grid With Left Sidebar</a>

                <a class="dropdown-item" href="index-grid-right.html">Homepage Grid With Right Sidebar</a>
              </div>
            </li> -->
            <li class="nav-item">
              <router-link to="/" class="nav-link">소개</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/house" class="nav-link">아파트매물 검색</router-link>
            </li>
            <li class="nav-item dropdown">
              <a
                class="nav-link"
                href="#"
                role="button"
                data-toggle="dropdown"
                aria-haspopup="true"
                aria-expanded="false"
              >
                부동산 인사이트 <i class="ti-angle-down ml-1"></i>
              </a>
              <div class="dropdown-menu">
                <a
                  class="dropdown-item"
                  href="https://ohou.se/store?utm_source=brand_google&utm_medium=cpc&utm_campaign=commerce&utm_content=e&utm_term=%EC%98%A4%EB%8A%98%EC%9D%98%EC%A7%91&source=14&affect_type=UtmUrl&gclid=Cj0KCQjw39uYBhCLARIsAD_SzMQfyGc3HeEd40IOdpO5XnQxaKqE5TekcmCe7T4ei2WuGIPBPAvwIjwaArycEALw_wcB"
                  target="_blank"
                >
                  인테리어 공사 (오늘의 집)</a
                >
                <a class="dropdown-item" href="https://land.naver.com/news/" target="_blank"> 네이버 부동산</a>
              </div>
            </li>
            <li class="nav-item dropdown">
              <a
                class="nav-link"
                href="#"
                role="button"
                data-toggle="dropdown"
                aria-haspopup="true"
                aria-expanded="false"
              >
                HELP DESK <i class="ti-angle-down ml-1"></i>
              </a>
              <div class="dropdown-menu">
                <router-link to="/notice" class="dropdown-item">공지사항</router-link>
                <router-link to="/qna" class="dropdown-item">Q&amp;A</router-link>
              </div>
            </li>
          </ul>
        </div>

        <div class="order-2 order-lg-3 d-flex align-items-center">
          <b-navbar-nav class="ml-auto" v-if="userInfo">
            <b-nav-item class="align-self-center">
              <b-icon class="ml-1" icon="emoji-laughing"></b-icon>
              {{ userInfo.name }}님
            </b-nav-item>

            <b-nav-item-dropdown right>
              <template #button-content>
                <b-icon icon="people" font-scale="2"></b-icon>
              </template>
              <b-dropdown-item href="#">
                <router-link :to="{ name: 'mypage' }" class="link align-self-center">
                  <b-icon icon="clipboard-check"></b-icon> 마이페이지
                </router-link>
              </b-dropdown-item>
              <b-dropdown-item href="#">
                <router-link :to="{ name: 'BookmarkView' }" class="link align-self-center">
                  <b-icon icon="house-door"></b-icon> 관심지역
                </router-link>
              </b-dropdown-item>
              <b-dropdown-item href="#">
                <a class="align-self-center link" @click.prevent="onClickLogout">
                  <b-icon icon="door-open"></b-icon> 로그아웃
                </a>
              </b-dropdown-item>
            </b-nav-item-dropdown>
          </b-navbar-nav>

          <!-- before login -->
          <b-navbar-nav class="ml-auto" v-else>
            <b-nav-item class="align-self-center">
              <router-link :to="{ name: 'login' }" class="link">
                로그인 후 이용하세요<b-icon class="ml-1" icon="person-circle"></b-icon>
              </router-link>
            </b-nav-item>
          </b-navbar-nav>
        </div>
      </nav>
    </div>
  </header>
  <!-- /navigation -->
</template>

<script>
import { mapState, mapGetters, mapActions } from 'vuex';

const UserStore = 'UserStore';

export default {
  name: 'TheHeaderNavbar',
  data() {
    return {};
  },
  computed: {
    ...mapState(UserStore, ['isLogin', 'userInfo']),
    ...mapGetters(['checkUserInfo']),
  },
  methods: {
    ...mapActions(UserStore, ['userLogout']),
    onClickLogout() {
      console.log(this.userInfo.id);
      //vuex actions에서 userLogout 실행(Backend에 저장 된 리프레시 토큰 없애기
      //+ satate에 isLogin, userInfo 정보 변경)
      // this.$store.dispatch("userLogout", this.userInfo.userid);
      this.userLogout(this.userInfo.id);
      sessionStorage.removeItem('access-token'); //저장된 토큰 없애기
      sessionStorage.removeItem('refresh-token'); //저장된 토큰 없애기
      if (this.$route.path != '/') this.$router.push({ name: 'home' });
    },
  },
};
</script>

<style scoped>
#logo {
  width: 120px;
}

.link {
  text-decoration: none;
}
</style>
