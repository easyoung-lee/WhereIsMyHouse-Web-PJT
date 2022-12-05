<template>
  <section class="section-sm">
    <div class="container">
      <div class="row">
        <div class="col-lg-8 mx-auto">
          <div class="content mb-5">
            <h2 id="we-would-love-to-hear-from-you">질문을 수정해주세요!</h2>
          </div>
          <form method="PUT">
            <div class="form-group">
              <label for="title" class="form-label">제목</label>
              <input type="text" class="form-control" id="title" ref="title" v-model="question.title" required />
            </div>
            <div class="form-group">
              <label for="content" class="form-label">내용</label>
              <textarea
                class="form-control"
                id="content"
                rows="3"
                ref="content"
                v-model="question.content"
                required
              ></textarea>
            </div>
            <button type="submit" class="btn btn-primary mr-2" @click="modifyHandler">수정</button>
            <button type="submit" class="btn btn-primary" @click="moveHandler">이전</button>
          </form>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
const QnaStore = 'QnaStore';

export default {
  data() {
    return {};
  },
  computed: {
    // 감시하고 싶은 state..
    ...mapGetters(QnaStore, ['question']),
  },
  methods: {
    ...mapActions(QnaStore, ['getModifyQuestion']),
    moveHandler(event) {
      console.log('moveHandler..............................');
      this.$router.push({ name: 'QnaDetailView', query: { no: this.question.no } });
    },
    modifyHandler(event) {
      console.log('modifyHandler.............................: ');
      console.log('modifyHandler.............................: ', this.question);
      this.getModifyQuestion(this.question);
      this.$router.push({ name: 'QnaDetailView', query: { no: this.question.no } });
    },
  },
};
</script>

<style></style>
