<template>
  <div class="row justify-content-center">
    <aside class="col-lg-4">
      <!-- Search -->
      <div class="widget">
        <h4 class="widget-title"><span>Search</span></h4>
        <form class="widget-search">
          <input class="mb-3" id="word" name="word" ref="word" v-model="word" placeholder="제목 &amp; 내용 입력..." />
          <i class="ti-search"></i>
          <a class="btn btn-primary btn-block" @click="searchQna">검색</a>
        </form>
      </div>

      <!-- about me -->
      <div class="widget widget-about">
        <h4 class="widget-title">Q&amp;A 게시판</h4>
        <img class="img-fluid" src="@/assets/MyImage/question.png" alt="Themefisher" />
        <p>궁금하신 점이 있다면, 자유롭게 질문해주세요.</p>
        <button class="btn btn-primary mb-2" @click="moveQnaRegist">글쓰기</button>
      </div>

      <!-- Promotion -->
      <div class="promotion">
        <img src="@/assets/images/promotion1.png" class="img-fluid w-100" />
        <div class="promotion-content">
          <h5 class="text-white mb-3">과도한 대출은 <br />위험합니다</h5>
          <p class="text-white mb-4">
            최근 감당하기 어려운 집 값에 <br />
            과도하게 대출을 받으시는 분들이 계십니다<br />
            과도한 대출은 여러분들의 삶을 <br />
            무너뜨릴 수도 있습니다.
          </p>
          <a
            href="http://www.xn--h49azyt04b4kaj4u02a30jjrto0i74c.com/?gclid=CjwKCAiApvebBhAvEiwAe7mHSLlCSxY949uPf8ycOmzUwoWDIqIbJFuqfhFC4Iqs5URH-CzQnLB6JRoCX4oQAvD_BwE"
            class="btn btn-primary"
            >HELP</a
          >
        </div>
      </div>

      <!-- Social -->
      <div class="widget">
        <h4 class="widget-title"><span>Social Links</span></h4>
        <ul class="list-inline widget-social">
          <li class="list-inline-item">
            <a href="#"><i class="ti-facebook"></i></a>
          </li>
          <li class="list-inline-item">
            <a href="#"><i class="ti-twitter-alt"></i></a>
          </li>
          <li class="list-inline-item">
            <a href="#"><i class="ti-linkedin"></i></a>
          </li>
          <li class="list-inline-item">
            <a href="#"><i class="ti-github"></i></a>
          </li>
          <li class="list-inline-item">
            <a href="#"><i class="ti-youtube"></i></a>
          </li>
        </ul>
      </div>
    </aside>
    <router-view></router-view>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
const QnaStore = 'QnaStore';
export default {
  data() {
    return {
      word: '',
      key: '',
    };
  },
  computed: {
    ...mapGetters(QnaStore, ['questions']),
  },
  created() {
    this.getQnaAll({ key: '', word: '' });
  },
  methods: {
    ...mapActions(QnaStore, ['getQnaAll', 'getSearchQna']),
    searchQna() {
      console.log('key.......', this.key, ' word..........................', this.word);
      this.getQnaAll({ key: this.key, word: this.word });
    },
    moveQnaRegist() {
      this.$router.push({ name: 'QnaRegistView' });
    },
  },
};
</script>

<style></style>
