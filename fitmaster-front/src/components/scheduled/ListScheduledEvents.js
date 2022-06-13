import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { useParams, useNavigate } from 'react-router-dom';
import ReactPaginate from 'react-paginate';
import EventService from "../connection/services/EventService";
import ScheduledEventService from "../connection/services/ScheduledEventService";

const ListScheduledEvents = () => {
    const [scheduledEvents, setScheduledEvents] = useState([]);
    const [numberOfPages, setNumberOfPages] = useState(0);

    const [currentScheduledEvent, setCurrentScheduledEvent] = useState(null);
    const [currentEventName, setCurrentEventName] = useState("");
    const [currentIndex, setCurrentIndex] = useState(-1);

    const [maxButtons] = useState(5);

    const [currentPage, setCurrentPage] = useState(0);
    const [pageSize, setPageSize] = useState(5);

    const pageSizes = [5, 10, 20];

    const refreshList = () => {
        retrieveScheduledEvents();
        setCurrentScheduledEvent(null);
        setCurrentEventName('');
        setCurrentIndex(-1);
    };

    useEffect(refreshList, [currentPage, pageSize]);

    const retrieveScheduledEvents = () => {
        ScheduledEventService.getAll({ currentPage, pageSize })
            .then(response => {
                setScheduledEvents(response.data.content);
                setNumberOfPages(response.data.totalPages);
                console.log(response);
            })
            .catch(e => {
                console.log(e);
            });
    };

    const handlePageChange = event => {
        console.log(
            `User requested page number ${event.selected}.`
        );
        setCurrentPage(event.selected);
    };

    const handlePageSizeChange = (event) => {
        setPageSize(event.target.value);
        setCurrentPage(0);
    };

    useEffect(() => {
        if (currentScheduledEvent) {
            EventService.get(currentScheduledEvent.eventId)
                .then(response => {
                    setCurrentEventName(response.data.title);
                    console.log(response);
                })
                .catch(e => {
                    console.log(e);
                });
        }
    }, [currentScheduledEvent])

    const setActiveScheduledEvent = (event, index) => {
        setCurrentScheduledEvent(event);
        setCurrentIndex(index);
    };

    return (
        <div>
            <div className="list row">
                <div className="my-3">
                    {"Items per Page: "}
                    <select onChange={handlePageSizeChange} value={pageSize}>
                        {pageSizes.map((size) => (
                            <option key={size} value={size}>
                                {size}
                            </option>
                        ))}
                    </select>
                </div>
                <div className="col-md-6">
                    <h4>Scheduled events list</h4>
                    <ul className="list-group">
                        {scheduledEvents &&
                            scheduledEvents.map((scheduledEvent, index) => (
                                <li
                                    className={
                                        "list-group-item " + (index === currentIndex ? "active" : "")
                                    }
                                    onClick={() => setActiveScheduledEvent(scheduledEvent, index)}
                                    key={index}
                                >
                                    {scheduledEvent.rescheduledDate.slice(0, 10)} @ {scheduledEvent.rescheduledDate.slice(11, 16)}
                                </li>
                            ))}
                    </ul>
                </div>
                <div className="col-md-6">
                    {currentScheduledEvent ? (
                        <div>
                            <h4>{currentEventName}</h4>
                        </div>
                    ) : (
                        <div>
                            <br />
                            <p>Please select an scheduled event.</p>
                        </div>
                    )}
                </div>
                <div className="col-md-6">
                    <ReactPaginate
                        breakLabel="..."
                        nextLabel="next >"
                        onPageChange={handlePageChange}
                        pageRangeDisplayed={maxButtons}
                        pageCount={numberOfPages}
                        previousLabel="< previous"
                        renderOnZeroPageCount={null}
                        breakClassName={'page-item'}
                        breakLinkClassName={'page-link'}
                        containerClassName={'pagination'}
                        pageClassName={'page-item'}
                        pageLinkClassName={'page-link'}
                        previousClassName={'page-item'}
                        previousLinkClassName={'page-link'}
                        nextClassName={'page-item'}
                        nextLinkClassName={'page-link'}
                        activeClassName={'active'}
                    />
                </div>
            </div>
        </div>
    );
}

export default ListScheduledEvents;