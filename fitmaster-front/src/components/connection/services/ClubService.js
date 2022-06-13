import http from "../http-common";

const prefix = 'clubs';

const getAll = pageable => {
  return http.get(`/${prefix}?size=${pageable.pageSize}&page=${pageable.currentPage}`);
};
const get = id => {
  return http.get(`/${prefix}/${id}`);
};
const create = data => {
  return http.post(`/${prefix}`, data);
};
const update = (id, data) => {
  return http.patch(`/${prefix}/${id}`, data);
};
const remove = id => {
  return http.delete(`/${prefix}/${id}`);
};
const ClubService = {
  getAll,
  get,
  create,
  update,
  remove
};
export default ClubService;
