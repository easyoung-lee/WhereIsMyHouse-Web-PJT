<template>
  <b-container class="bv-example-row mt-3">
    <!-- <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>로그인</h3></b-alert>
      </b-col>
    </b-row> -->
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-alert show variant="danger" v-if="isLoginError">아이디 또는 비밀번호를 확인하세요.</b-alert>
            <b-form-group label="아이디:" label-for="id">
              <b-form-input
                id="id"
                v-model="user.id"
                required
                placeholder="아이디 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="pass">
              <b-form-input
                type="password"
                id="pass"
                v-model="user.pass"
                required
                placeholder="비밀번호 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-button type="button" variant="primary" class="m-1" @click="confirm">로그인</b-button>
            <b-button type="button" variant="primary" class="m-1" @click="join">회원가입</b-button>
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions } from 'vuex';

const UserStore = 'UserStore';

export default {
  name: 'UserLogin',
  data() {
    return {
      // isLoginError: false,
      user: {
        id: null,
        pass: null,
      },
    };
  },
  computed: {
    ...mapState(UserStore, ['isLogin', 'isLoginError', 'userInfo']),
  },
  created() {
    this.getBanner('로그인');
    this.getParent('Home');
    this.getChild('Login');
  },
  mounted() {
    this.getBanner('로그인');
    this.getParent('Home');
    this.getChild('Login');
  },
  methods: {
    ...mapActions(['getBanner', 'getParent', 'getChild']),
    ...mapActions(UserStore, ['userConfirm', 'getUserInfo']),
    async confirm() {
      // console.log("UserLogin : ", this.user);
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem('access-token');
      // console.log("1. confirm() token >> " + token);
      if (this.isLogin) {
        // console.log("UserLogin confirm : ", token);
        await this.getUserInfo(token);
        // console.log("4. confirm() userInfo :: ", this.userInfo);
        this.$router.push({ name: 'home' });
      }
    },
    join() {
      this.$router.push({ name: 'join' });
    },
  },
};
</script>

<style></style>
