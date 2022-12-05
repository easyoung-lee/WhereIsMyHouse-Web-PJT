<template>
  <!-- String 프로젝트에서 가져온거 -->
  <section class="section-sm">
    <div class="container">
      <div class="row">
        <div class="col-lg-8 mx-auto">
          <div class="content mb-5">
            <h2 id="we-would-love-to-hear-from-you">질문을 작성해주세요!</h2>
            <p> 질문 내용을 최대한 자세히 작성해주시면, 더 많은 정보를 얻으실 수 있습니다</p>
          </div>
          <form method="PUT" action="#">
            <div class="form-group">
              <label for="title" class="form-label">제목</label>
              <input
                type="text"
                class="form-control"
                id="title"
                ref="title"
                v-model="question.title"
                required
              />
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
            <button type="submit" class="btn btn-primary mr-2" @click="registHandler">등록</button>
            <button type="submit" class="btn btn-primary" @click="moveHandler">목록</button>
          </form>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import { mapActions, mapState } from "vuex";
const QnaStore = "QnaStore";
const UserStore = "UserStore";

export default {
  data() {
    return {
      question: {
        title: "",
        content: "",
        id: "",
      },
    };
  },
  computed: {
    ...mapState(UserStore, ["userInfo"]),
  },
  created() {
    this.question.id = this.userInfo.id;
    console.log("created.............................: ", this.question);
  },
  methods: {
    ...mapActions(QnaStore, ["getRegistQuestion"]),
    moveHandler(event) {
      console.log("moveHandler..............................");
      this.$router.push({ name: "QnaView" });
    },
    registHandler(event) {
      console.log("registHandler.............................: ");
      console.log("registHandler.............................: ", this.question);
      this.getRegistQuestion(this.question);
      this.$router.push({ name: "QnaView" });
    },
  },
};
</script>

<style></style>
