<template>
  <div class="col-lg-8 mb-5 mb-lg-0">
    <h2 class="h5 section-title">게시글</h2>
    <article class="card mb-4" v-for="(question, index) in questions" :key="index">
      <div class="row card-body">
        <div class="col-md-12">
          <h3 class="h4 mb-3">
            <router-link class="post-title" :to="{ name: 'QnaDetailView', query: { no: question.no } }">
              {{ question.title }}
            </router-link>
          </h3>
          <ul class="card-meta list-inline">
            <li class="list-inline-item"><i class="ti-user"></i>{{ question.id }}</li>
            <li class="list-inline-item"><i class="ti-calendar"></i>{{ question.date }}</li>
          </ul>
          <p>{{ question.content }}</p>
        </div>
      </div>
    </article>
    <ul class="pagination justify-content-center">
      <li class="page-item page-item active">
        <a href="#!" class="page-link">1</a>
      </li>
      <li class="page-item">
        <a href="#!" class="page-link">2</a>
      </li>
      <li class="page-item">
        <a href="#!" class="page-link">&raquo;</a>
      </li>
    </ul>
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
  mounted() {
    this.getBanner('Q&A');
    this.getParent('Home');
    this.getChild('Qna');
  },
  methods: {
    ...mapActions(['getBanner', 'getParent', 'getChild']),
    ...mapActions(QnaStore, ['getQnaAll', 'getSearchQnaAll']),
    // searchQna() {
    //   console.log('key.......', this.key, ' word..........................', this.word);
    //   this.getSearchQnaAll({ key: this.key, word: this.word });
    // },
    // moveQnaRegist() {
    //   this.$router.push({ name: 'QnaRegistView' });
    // },
  },
};
</script>

<style></style>
