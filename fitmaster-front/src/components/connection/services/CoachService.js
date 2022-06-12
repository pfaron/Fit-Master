import http from "../http-common";

const getAll = pageable => {
  return http.get("/coaches", pageable);
};
const get = id => {
  return http.get(`/coaches/${id}`);
};
const create = data => {
  return http.post("/coaches", data);
};
const update = (id, data) => {
  return http.patch(`/coaches/${id}`, data);
};
const remove = id => {
  return http.delete(`/tutorials/${id}`);
};
const TutorialService = {
  getAll,
  get,
  create,
  update,
  remove
};
export default TutorialService;
