import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  console.log("api user : ", user);
  console.log("api JSON.user : ", JSON.stringify(user));
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(id, success, fail) {
  console.log("api findById : ", id);
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${id}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  api.defaults.headers["refresh-token"] = sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
  await api.post(`/user/refresh`, user).then(success).catch(fail);
}

async function logout(id, success, fail) {
  await api.get(`/user/logout/${id}`).then(success).catch(fail);
}

async function register(user, success, fail) {
  console.log("api register: ", user);
  await api.post(`/user/register`, user).then(success).catch(fail);
}

async function update(user, success, fail) {
  console.log("api update: ", user);
  await api.put(`/user/update`, user).then(success).catch(fail);
}

async function remove(id, success, fail) {
  console.log("api remove: ", id);
  await api.delete(`/user/remove/${id}`).then(success).catch(fail);
}

export { login, findById, tokenRegeneration, logout, register, update, remove };
