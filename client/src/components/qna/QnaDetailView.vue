<template>
  <section class="section" style="width: 100%">
    <div class="container col-md-12">
      <div class="row justify-content-center">
        <div class="col-lg-9 mb-5 mb-lg-0">
          <article>
            <h1 class="h2">{{ question.title }}</h1>
            <ul class="card-meta my-3 list-inline border-bottom">
              <li class="list-inline-item"><i class="ti-user"></i>{{ question.id }}</li>
              <li class="list-inline-item"><i class="ti-calendar"></i>{{ question.date }}</li>
              <li class="list-inline-item">
                <a
                  class="btn btn-outline-primary"
                  v-if="question.id === userInfo.id || userInfo.id === 'admin'"
                  @click="modifyHandler"
                  >수정</a
                >
              </li>
              <li class="list-inline-item">
                <a
                  class="btn btn-outline-primary"
                  v-if="question.id === userInfo.id || userInfo.id === 'admin'"
                  @click="deleteQuestionHandler"
                  >삭제</a
                >
              </li>
            </ul>
            <div class="content pt-4">
              <p>
                {{ question.content }}
              </p>
            </div>
          </article>
        </div>

        <div class="col-lg-9 col-md-12">
          <div class="mb-5 border-top mt-4 pt-3">
            <h3 class="mb-4 border-bottom pb-3">
              <h4 class="ti-comments"></h4>
              {{ answers.length }} Comments
            </h3>

            <div class="media d-block d-sm-flex mb-4 pb-4" v-for="(ans, index) in answers" :key="index">
              <div class="d-inline-block mr-2 mb-3 mb-md-0" href="#">
                <img class="mr-3" src="@/assets/images/post/arrow.png" alt="" />
              </div>
              <div class="media-body">
                <h4 class="d-inline-block mb-3">{{ ans.id }}</h4>
                <p>
                  {{ ans.content }}
                </p>
                <span class="text-black-800 mr-3 font-weight-600">{{ ans.date }}</span>
                <a
                  v-if="ans.id === userInfo.id || userInfo.id === 'admin'"
                  class="text-primary font-weight-600"
                  @click="deleteAnswerHandler(ans.no)"
                  >삭제</a
                >
              </div>
            </div>
          </div>

          <div>
            <h3 cla ss="mb-4">Leave a Reply</h3>
            <form method="GET">
              <div class="row">
                <div class="form-group col-md-12">
                  <textarea
                    class="form-control shadow-none"
                    name="comment"
                    v-model="answer.content"
                    rows="7"
                    placeholder="댓글을 입력하세요."
                    required
                  ></textarea>
                </div>
              </div>
              <a class="btn btn-outline-primary mr-2" @click="insertAnswerHandler">등록</a>
              <a class="btn btn-outline-primary" @click="moveHandler">목록</a>
            </form>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import http from '@/api/http';
import { mapActions, mapGetters, mapState } from 'vuex';
const UserStore = 'UserStore';
const QnaStore = 'QnaStore';
export default {
  data() {
    return {
      no: '',
      cnt: '',
      answer: {
        content: '',
        id: '',
        qno: '',
      },
    };
  },
  computed: {
    // 감시하고 싶은 state..
    ...mapGetters(QnaStore, ['question', 'answers']),
    ...mapState(UserStore, ['userInfo']),
  },
  created() {
    //DOM객체와 연결되었고 Vue의 속성들도 초기화 된 상태
    this.no = this.$route.query.no;
    this.answer.qno = this.no;
    this.getQestion({ no: this.no });
    this.getAnswers({ qno: this.no });
    this.answer.id = this.userInfo.id;
  },
  methods: {
    ...mapActions(QnaStore, ['getQestion', 'getAnswers', 'getInsertAnswer']),
    moveHandler() {
      this.$router.push({ name: 'QnaView' });
    },
    modifyHandler() {
      this.$router.push({ name: 'QnaModifyView' });
    },
    deleteQuestionHandler() {
      console.log('deleteHandler..................삭제: ', this.no);
      http
        .delete(`question/${this.no}`)
        .then(({ data }) => {
          if (data == 'success') {
            alert('질문 삭제 완료');
            this.moveHandler();
          }
        })
        .catch(({ data }) => {
          alert(data);
        });
    },
    deleteAnswerHandler(no) {
      console.log('deleteAnswerHandler..................삭제: ', no);
      http
        .delete(`answer/${no}`)
        .then(({ data }) => {
          if (data == 'success') {
            alert('댓글 삭제 완료');
            this.$router.go();
          }
        })
        .catch(({ data }) => {
          alert(data);
        });
    },
    insertAnswerHandler() {
      console.log('insertAnswerHandler.............................: ', this.answer);
      this.getInsertAnswer(this.answer);
      // this.$router.push({ name: 'QnaDetailView', query: { no: this.no } });
      this.$router.go();
    },
  },
};
</script>

<style></style>
