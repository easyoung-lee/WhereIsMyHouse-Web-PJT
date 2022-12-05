<template>
  
  <div class="widget widget-about">
    <section class="section-sm">
      <div class="container">
        <div class="row">
          <div class="col-lg-8 mx-auto">
            <div class="content mb-5">
              <h2 id="we-would-love-to-hear-from-you" style="text-align:left">공지사항을 작성해주세요</h2>
            </div>
            <div class="form-group" style="text-align: left">
              <label for="title" class="form-label" style="text-align: left">제목</label>
              <input
                type="text"
                class="form-control"
                id="title"
                ref="title"
                v-model="notice.title"
                required
              />
            </div>
            <div class="form-group" style="text-align: left">
              <label class="form-label">내용</label>
              <textarea
                class="form-control"
                id="content"
                rows="3"
                ref="content"
                v-model="notice.content"
                required
              ></textarea>
            </div>
            <button type="submit" class="btn btn-primary mr-2" @click="registHandler">등록</button>
            <button type="submit" class="btn btn-primary" @click="moveHandler">목록</button>
          </div>
        </div>
      </div>
    </section>
  </div>

</template>

<script>
import { mapActions, mapState } from "vuex";

const NoticeStore = "NoticeStore";
const UserStore = "UserStore";

export default {
  data() {
    return {
      notice: {
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
    this.notice.id = this.userInfo.id;
    console.log("created.............................: ", this.notice);
  },
  methods: {
    ...mapActions(NoticeStore, ["getRegistNotice"]),
    moveHandler(event) {
      console.log("moveHandler..............................");
      this.$router.push({ name: "notice" });
    },
    registHandler(event) {
      console.log("registHandler.............................: ", this.notice);
      this.getRegistNotice(this.notice);
      this.$router.push({ name: "Noticelist" });
    },
  },
};
</script>

<style>

</style>