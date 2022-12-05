<template>
  <div class="widget widget-about">
    <section class="section-sm">
      <div class="container">
        <div class="row align-items-center justify-content-center">
          <div class="col-lg-5 col-md-6 mb-4 mb-md-0">
            <div class="image-wrapper">
              <img src="@/assets/MyImage/person/man.png" alt="기본이미지" height="460" width="420" />
            </div>
          </div>
          <div class="col-lg-6 col-md-6">
            <div class="content pl-lg-5 pl-0">
              <h2 id="helllo-im-richi-andorn-im-a-biography-based-researcher-and-author">
                {{ userInfo.name }}님의 마이페이지
              </h2>

              <table class="table table-borderless mt-5">
                <tbody>
                  <tr>
                    <th scope="row"><b>아이디</b></th>
                    <td>{{ userInfo.id }}</td>
                  </tr>
                  <tr>
                    <th scope="row"><b>이름</b></th>
                    <td v-if="check">{{ userInfo.name }}</td>
                    <td v-else><input type="text" class="form-control" id="name" v-model="user.name" /></td>
                  </tr>
                  <tr>
                    <th scope="row"><b>전화번호</b></th>
                    <td v-if="check">{{ userInfo.phone }}</td>
                    <td v-else>
                      <input
                        type="tel"
                        class="form-control"
                        id="phone"
                        v-model="user.phone"
                        pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}"
                      />
                    </td>
                  </tr>
                </tbody>
              </table>
              <div v-if="check">
                <button type="submit" class="btn btn-primary mr-2 mt-4" @click="changeHandler">수정</button>
                <button type="submit" class="btn btn-primary mt-4" @click="removeHandler">탈퇴</button>
              </div>
              <div v-else>
                <button type="submit" class="btn btn-primary mr-2 mt-4" @click="updateHandler">수정</button>
                <button type="submit" class="btn btn-primary mt-4" @click="initHandler">초기화</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import { mapGetters, mapActions, mapState } from 'vuex';

const UserStore = 'UserStore';

export default {
  ...mapGetters(UserStore, ['checkUserInfo']),
  name: 'UserMyPage',
  data() {
    return {
      user: {
        id: '',
        pass: '',
        name: '',
        phone: '',
      },
      check: true,
    };
  },
  components: {},
  computed: {
    ...mapState(UserStore, ['userInfo']),
  },
  created() {
    this.getBanner('마이페이지');
    this.getParent('Home');
    this.getChild('My Page');
  },
  mounted() {
    this.getBanner('마이페이지');
    this.getParent('Home');
    this.getChild('My Page');
  },
  methods: {
    ...mapActions(['getBanner', 'getParent', 'getChild']),
    ...mapActions(UserStore, ['getRemoveUser', 'getUpdateUser']),
    changeHandler(event) {
      console.log('changeHandler1................................');
      this.user.id = this.userInfo.id;
      this.user.pass = this.userInfo.pass;
      this.user.name = this.userInfo.name;
      this.user.phone = this.userInfo.phone;
      console.log('changeHandler2................................', this.userInfo);
      console.log('changeHandler2................................', this.user);
      this.check = !this.check;
    },
    removeHandler(event) {
      console.log('removeHandler.........................', this.userInfo.id);
      this.getRemoveUser(this.userInfo.id);
      this.$router.push({ name: 'home' });
    },
    initHandler(event) {
      console.log('initHandler.........................');
      this.user.id = this.userInfo.id;
      this.user.pass = this.userInfo.pass;
      this.user.name = this.userInfo.name;
      this.user.phone = this.userInfo.phone;
    },
    updateHandler(event) {
      console.log('updateHandler................................', this.user);
      this.getUpdateUser(this.user);
      // this.check = !this.check;
      this.$router.go();
    },
  },
};
</script>

<style></style>
