import http from "../http-common";

const prefix = 'events/scheduled';

const getAll = params => {
    const { pageSize, currentPage } = params;
    return http.get(`/${prefix}?size=${pageSize}&page=${currentPage}`);
};
const schedule = data => {
    return http.post(`/${prefix}`, data);
};
const cancel = id => {
    return http.post(`/${prefix}/cancel?id=${id}`);
};
const reenlist = id => {
    return http.post(`/${prefix}/reenlist?id=${id}`);
};
const reschedule = params => {
    const { id, newDate } = params;
    return http.post(`/${prefix}/reschedule?id=${id}&newDate=${newDate}`);
};
const ScheduledEventService = {
    getAll,
    schedule,
    cancel,
    reenlist,
    reschedule
};
export default ScheduledEventService;
