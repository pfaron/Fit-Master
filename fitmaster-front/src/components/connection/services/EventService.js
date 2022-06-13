import http from "../http-common";

const prefix = 'events';

const getAllByClubId = params => {
    const { pageSize, currentPage, clubId } = params;
    return http.get(`/${prefix}?clubId=${clubId}&size=${pageSize}&page=${currentPage}`);
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
const EventService = {
    getAllByClubId,
    get,
    create,
    update,
    remove
};
export default EventService;
