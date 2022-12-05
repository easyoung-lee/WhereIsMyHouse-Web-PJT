<template>
  
  <div class="widget widget-about">
    <section class="section" style="width: 100%">
      <div class="container col-md-12">
        <div class="row justify-content-center">
          <div class="col-lg-9 mb-5 mb-lg-0">
            <article style="text-align:left">
              <div v-if="check"><h1 class="h2">{{ notice.title }}</h1></div>
              <div v-else>
                <div class="form-group" style="text-align: left">
                  <label for="title" class="form-label" style="text-align: left">제목</label>
                  <input type="text" class="form-control" id="title" v-model="board.title" required/>
                </div>
              </div>
              <ul class="card-meta my-3 list-inline border-bottom">
                <li class="list-inline-item"><i class="ti-user"></i>{{ notice.id }}</li>
                <li class="list-inline-item"><i class="ti-calendar"></i>{{ notice.date }}</li>
                <li class="list-inline-item">
                  <div v-if="userInfo.id === 'admin'">
                    <div v-if="check">
                      <div v-if="userInfo.id === 'admin'">
                        <a class="btn btn-outline-primary mr-2" @click="changeHandler">수정</a>
                        <a class="btn btn-outline-primary" @click="deleteHandler">삭제</a>
                      </div>
                    </div>
                    <!-- <div v-else>
                      <button class="btn btn-outline-primary mr-2" @click="modifyHandler">수정</button>
                      <button class="btn btn-outline-primary" @click="initHandler">초기화</button>
                    </div> -->
                  </div>
                </li>
              </ul>
              <div class="content pt-4">
                <div v-if="check"><p> {{ notice.content }} </p></div>
                <div v-else>
                  <div class="form-group" style="text-align: left">
                    <label for="title" class="form-label" style="text-align: left">내용</label>
                    <textarea
                      class="form-control"
                      id="content"
                      rows="3"
                      ref="content"
                      v-model="board.content"
                      required
                    ></textarea>
                  </div>
                </div>
              </div>
              <li class="list-inline-item">
                <button class="btn btn-primary mt-3" @click="moveHandler">목록</button>
              </li>
              <li class="list-inline-item" >
                <div v-if="!check">
                  <a class="btn btn-outline-primary mt-3 mr-2" @click="modifyHandler">수정</a>
                  <a class="btn btn-outline-primary mt-3" @click="initHandler">초기화</a>
                </div>
                <!-- <div v-if="check">
                  <div v-if="userInfo.id === 'admin'">
                    <button class="btn btn-primary mr-2" @click="changeHandler">수정</button>
                    <button class="btn btn-primary" @click="deleteHandler">삭제</button>
                  </div>
                </div>
                <div v-else>
                  <button class="btn btn-primary mr-2" @click="modifyHandler">수정</button>
                  <button class="btn btn-primary" @click="initHandler">초기화</button>
                </div> -->
              </li>
            </article>
          </div>
        </div>
      </div>
    </section>
  </div>

</template>

<script>
import http from "@/api/http";
import { mapActions, mapGetters, mapState } from "vuex";

const NoticeStore = "NoticeStore";
const UserStore = "UserStore";

export default {
  data() {
    return {
      board: {
        no: '',
        title: '',
        content: '',
        id: '',
      },
      check: true,
    };
  },
  computed: {
    // 감시하고 싶은 state..
    ...mapGetters(NoticeStore, ["notice"]),
    ...mapState(UserStore, ["userInfo"]),
  },
  created() {
    this.board.no = this.$route.query.no;
    this.getSelectNotice(this.board.no);
  },
  methods: {
    ...mapActions(NoticeStore, ["getSelectNotice", "getModifyNotice"]),
    moveHandler() {
      this.$router.push({ name: "Noticelist" });
    },
    changeHandler() {
      console.log("changeHandler.....................................");
      this.board.title = this.notice.title;
      this.board.content = this.notice.content;
      this.board.id = this.notice.id;
      console.log("changeHandler.....................................notice : ", this.notice);
      console.log("changeHandler.....................................board : ", this.board);
      this.check = !this.check
    },
    initHandler(event) {
      console.log('initHandler.........................');
      this.board.title = this.notice.title;
      this.board.content = this.notice.content;
      this.board.id = this.notice.id;
    },
    modifyHandler(event) {
      console.log('modifyHandler................................', this.board);
      this.getModifyNotice(this.board);
      // this.check = !this.check;
      this.$router.go();
    },
    deleteHandler() {
      console.log("deleteHandler..................삭제: ", this.board.no);
      http
        .delete(`notice/${this.board.no}`)
        .then(({ data }) => {
          if (data == "success") {
            alert("공지사항을 삭제하였습니다!");
            this.moveHandler();
          }
        })
        .catch(({ data }) => {
          alert(data);
        });
    },
  },
};
</script>

<style>

</style>