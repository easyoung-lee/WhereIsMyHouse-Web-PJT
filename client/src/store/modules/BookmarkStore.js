import http from '@/api/http';

const BookmarkStore = {
  namespaced: true,
  state: {
    houses: [],
    sido: [],
    gugun: [],
    dong: [],
    map: [],
  },
  getters: {
    houses(state) {
      return state.houses;
    },
    sido(state) {
      return state.sido;
    },
    gugun(state) {
      return state.gugun;
    },
    dong(state) {
      return state.dong;
    },
    map(state) {
      return state.map;
    },
  },
  mutations: {
    setHouses(state, payload) {
      console.log('[mutations]..................................houses :', payload);
      state.houses = payload;
    },
    setSido(state, payload) {
      console.log('[mutations]..................................sido :', payload);
      state.sido = payload;
    },
    setGugun(state, payload) {
      console.log('[mutations]..................................gugun :', payload);
      state.gugun = payload;
    },
    setDong(state, payload) {
      console.log('[mutations]..................................dong :', payload);
      state.dong = payload;
    },
    setMap(state, payload) {
      console.log('[mutations]..................................map :', payload);
      state.map = payload;
    },
  },
  actions: {
    getSearchBookmark({ commit }, payload) {
      console.log('[actions]..................................getSearchBookmark 조회 조건:', payload);
      http
        .get(`bookmark/${payload}`)
        .then(({ data }) => {
          console.log('[actions]..................................getSearchBookmark :', data);
          commit('setMap', data);
        })
        .catch(({ response }) => {
          console.log('[actions]..................................getSearchBookmark 2 :', data);
          alert(response.data);
        });
    },
    getBookmarkData({ commit }, payload) {
      console.log('[actions]..................................getAptDatas 조회 조건:', payload);
      http
        .get(`bookmark?id=${payload.id}&dong=${payload.dong}`)
        .then(({ data }) => {
          console.log('[actions]..................................getAptDatas :', data);
          commit('setDong', payload.dong);
          commit('setMap', data);
        })
        .catch(({ response }) => {
          console.log('[actions]..................................getAptDatas 2 :', data);
          alert(response.data);
        });
    },
    getBookMarkApt({ commit }, payload) {
      console.log('[actions]..................................getBookMarkApt 조회 조건:', payload);
      http
        .get(`bookmark/search/${payload}`)
        .then(({ data }) => {
          console.log('[actions]..................................getBookMarkApt 1 :', data);
          commit('setHouses', data);
        })
        .catch(({ response }) => {
          console.log('[actions]..................................getBookMarkApt 2 :', data);
          alert(response.data);
        });
    },
    getBookMarkDelete(context, payload) {
      console.log('[actions]..................................getBookMarkDelete 조회 조건:', payload);
      let id = payload.id;
      http
        .delete(`bookmark/${payload.id}/${payload.dongCode}`)
        .then(({ data }) => {
          console.log('[actions]..................................getBookMarkDelete 1 :', data);
          context.dispatch('getSearchBookmark', id);
        })
        .catch(({ response }) => {
          console.log('[actions]..................................getBookMarkDelete 2 :', data);
          alert(response.data);
        });
    },
    initAptDatas({ commit }) {
      commit('setHouses', []);
    },
  },
};

export default BookmarkStore;
