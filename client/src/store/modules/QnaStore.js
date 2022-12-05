import http from '@/api/http';

const QnaStore = {
  namespaced: true,
  state: {
    questions: [],
    question: [],
    answers: [],
    answer: [],
  },
  getters: {
    questions(state) {
      return state.questions;
    },
    question(state) {
      return state.question;
    },
    answer(state) {
      return state.answer;
    },
    answers(state) {
      return state.answers;
    },
  },
  mutations: {
    setSearchQnaAll(state, payload) {
      console.log('[mutations]..................................questions :', payload);
      state.questions = payload;
    },
    setQuestion(state, payload) {
      console.log('[mutations]..................................question :', payload);
      state.question = payload;
    },
    setModifyQuestion(state, payload) {
      console.log('[mutations]..................................question :', payload);
      state.questions.push(payload);
    },
    setAnswers(state, payload) {
      console.log('[mutations]..................................answer :', payload);
      state.answers = payload;
    },
    setInsertAnswer(state, payload) {
      console.log('[mutations]..................................answer :', payload);
      state.answers.push(payload);
    },
  },
  actions: {
    getQestion({ commit }, payload) {
      console.log('[actions]..................................getQestion :');
      http
        .get(`question/${payload.no}`)
        .then(({ data }) => {
          console.log('[actions]..................................getQestion :', data);
          commit('setQuestion', data);
        })
        .catch(({ response }) => {
          alert(response.data);
        });
    },
    getQnaAll({ commit }, payload) {
      console.log('[actions]..................................getQnaAll :');
      http
        .get(`question?key=${payload.key}&word=${payload.word}`)
        .then(({ data }) => {
          console.log('[actions]..................................getQnaAll :', data);
          commit('setSearchQnaAll', data);
        })
        .catch(({ response }) => {
          alert(response.data);
        });
    },
    getSearchQna({ commit }, payload) {
      console.log('[actions]..................................getSearchQna 조회 조건:', payload);
      http
        .get(`question/search?id=${payload.id}&key=${payload.key}&word=${payload.word}`)
        .then(({ data }) => {
          console.log('[actions]..................................getSearchQna :', data);
          commit('setSearchQnaAll', data);
        })
        .catch(({ response }) => {
          alert(response.data);
        });
    },
    getRegistQuestion({ commit }, payload) {
      console.log('[actions]..................................getRegistQuestion 1 :', payload);
      http
        .post('/question', payload)
        .then(({ data }) => {
          console.log('[actions]..................................getRegistQuestion 2 :', data);
          commit('setQuestion', data);
        })
        .catch(({ response }) => {
          alert(response.data);
        });
    },
    getModifyQuestion({ commit }, payload) {
      console.log('[actions]..................................getRegistQuestion 1 :', payload);
      http
        .put('/question', payload)
        .then(({ data }) => {
          console.log('[actions]..................................getRegistQuestion 2 :', data);
          commit('setModifyQuestion', data);
        })
        .catch(({ response }) => {
          alert(response.data);
        });
    },
    getAnswers({ commit }, payload) {
      console.log('[actions]..................................getAnswers 조회 조건:', payload);
      http
        .get(`answer/${payload.qno}`)
        .then(({ data }) => {
          console.log('[actions]..................................getAnswers :', data);
          commit('setAnswers', data);
        })
        .catch(({ response }) => {
          console.log('[actions]..................................getAnswers 2 :', data);
          alert(response.data);
        });
    },
    getInsertAnswer({ commit }, payload) {
      console.log('[actions]..................................getInsertAnswer 1 :', payload);
      http
        .post('/answer', payload)
        .then(({ data }) => {
          console.log('[actions]..................................getInsertAnswer 2 :', data);
          commit('setInsertAnswer', data);
        })
        .catch(({ response }) => {
          console.log('[actions]..................................getInsertAnswer error :', data);
          alert(response.data);
        });
    },
  },
};

export default QnaStore;
