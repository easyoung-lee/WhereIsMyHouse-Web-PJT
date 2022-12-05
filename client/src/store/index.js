import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate';

import UserStore from '@/store/modules/UserStore';
import QnaStore from '@/store/modules/QnaStore';
import HouseStore from '@/store/modules/HouseStore';
import NoticeStore from '@/store/modules/NoticeStore';
import BookmarkStore from '@/store/modules/BookmarkStore';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    banner: [],
    parent: [],
    child: [],
  },
  getters: {
    banner(state) {
      return state.banner;
    },
    parent(state) {
      return state.parent;
    },
    child(state) {
      return state.child;
    },
  },
  mutations: {
    setBanner(state, payload) {
      console.log('[mutations]..................................banner :', payload);
      state.banner = payload;
    },
    setParent(state, payload) {
      console.log('[mutations]..................................banner :', payload);
      state.parent = payload;
    },
    setChild(state, payload) {
      console.log('[mutations]..................................banner :', payload);
      state.child = payload;
    },
  },
  actions: {
    getBanner({ commit }, payload) {
      commit('setBanner', payload);
    },
    getParent({ commit }, payload) {
      commit('setParent', payload);
    },
    getChild({ commit }, payload) {
      commit('setChild', payload);
    },
  },
  modules: {
    UserStore,
    QnaStore,
    HouseStore,
    NoticeStore,
    BookmarkStore,
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});
