import http from "@/api/http";

const NoticeStore = {
  namespaced: true,
  state: {
    notices: [],
    notice: [],
  },
  getters: {
    notices(state) {
      return state.notices;
    },
    notice(state) {
      return state.notice;
    },
  },
  mutations: {
    setNotice(state, payload) {
      console.log("[mutations]......................................notice : ", payload);
      state.notice = payload;
    },
    setNotices(state, payload) {
      console.log("[mutations]......................................notices : ", payload);
      state.notices = payload;
    },
    setModifyNotice(state, payload) {
      console.log("[mutations]......................................notices : ", payload);
      state.notices.push(payload);
    },
  },
  actions: {
    getSelectNotice({ commit }, payload) {
      console.log("[actions]..................................getNotice");
      http
        .get(`notice/${payload}`)
        .then(({ data }) => {
          console.log("[actions].......................................getNotice: ", data);
          commit("setNotice", data);
        })
        .catch(({ response }) => {
          alert(response.data);
        });
    },
    getSelectNotices({ commit }) {
      console.log("[actions]..................................getNotices");
      http
        .get(`notice`)
        .then(({ data }) => {
          console.log("[actions].......................................getNotices: ", data);
          commit("setNotices", data);
        })
        .catch(({ response }) => {
          alert(response.data);
        });
    },
    getRegistNotice({ commit }, payload) {
      console.log("[actions]..................................getRegistNotice :", payload);
      http
        .post("/notice", payload)
        .then(({ data }) => {
          console.log("[actions]..................................getRegistNotice:", data);
          commit("setNotices", data);
        })
        .catch(({ response }) => {
          alert(response.data);
        });
    },
    getModifyNotice({ commit }, payload) {
      console.log("[actions]..................................getModifyNotice :", payload);
      http
        .put("/notice", payload)
        .then(({ data }) => {
          console.log("[actions]..................................getModifyNotice :", data);
          commit("setModifyNotice", data);
        })
        .catch(({ response }) => {
          alert(response.data);
        });
    },
  }
}

export default NoticeStore;