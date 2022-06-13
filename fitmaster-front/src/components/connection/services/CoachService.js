import http from "../http-common";

const prefix = 'coaches';

const getAll = params => {
  const { pageSize, currentPage } = params;
  return http.get(`/${prefix}?size=${pageSize}&page=${currentPage}`);
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
const CoachService = {
  getAll,
  get,
  create,
  update,
  remove
};
export default CoachService;
