<template>
  <section class="section-sm">
    <div class="container">
      <div class="row">
        <div class="col-lg-8 mx-auto">
          <div class="content mb-5">
            <h2 id="we-would-love-to-hear-from-you">환영합니다!!</h2>
            <p>여러분의 생활에 꼭 맞는 집을 찾아드리기 위해 노력하겠습니다.</p>
          </div>

          <div>
            <div class="form-group" style="text-align: left">
              <label for="id">아이디</label>
              <input type="text" id="id" v-model="user.id" class="form-control" placeholder="예시) ssafy8" required />
            </div>
            <div class="form-group" style="text-align: left">
              <label for="pass">비밀번호</label>
              <input
                type="password"
                id="pass"
                v-model="user.pass"
                class="form-control"
                placeholder="예시) 1234"
                required
              />
            </div>
            <div class="form-group" style="text-align: left">
              <label for="name">이름</label>
              <input
                type="text"
                id="name"
                v-model="user.name"
                class="form-control"
                placeholder="예시) 홍싸피"
                required
              />
            </div>
            <div class="form-group" style="text-align: left">
              <label for="phone">핸드폰 번호</label>
              <input
                type="tel"
                id="phone"
                v-model="user.phone"
                class="form-control"
                placeholder="예시) 010-1234-5678"
                required
              />
            </div>
            <div class="form-group" style="text-align: left">
              <label for="message">
                원하는 기능이나 정보가 있으신가요?&nbsp;작성해주시면 앞으로의 사이트 운영에 많은 도움이 됩니다!</label
              >
              <textarea
                name="message"
                id="message"
                class="form-control"
                placeholder="예시) 저는 제 정보를 돈주고 다른 사람에게 안파셨으면 좋겠어요"
              ></textarea>
            </div>
            <button type="submit" class="btn btn-primary" @click="registHandler">완료</button>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import { mapActions } from 'vuex';

const UserStore = 'UserStore';

export default {
  data() {
    return {
      user: {
        id: '',
        pass: '',
        name: '',
        phone: '',
      },
    };
  },
  created() {
    this.getBanner('회원가입');
    this.getParent('Home');
    this.getChild('Sign Up');
  },
  mounted() {
    this.getBanner('회원가입');
    this.getParent('Home');
    this.getChild('Sign Up');
  },
  methods: {
    ...mapActions(['getBanner', 'getParent', 'getChild']),
    ...mapActions(UserStore, ['getRegisterUser']),
    registHandler(event) {
      console.log('registHandler....................................', this.user);
      this.getRegisterUser(this.user);
      this.$router.push({ name: 'login' });
    },
  },
};
</script>

<style></style>
