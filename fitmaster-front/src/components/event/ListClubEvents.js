import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { useParams, useNavigate } from 'react-router-dom';
import ReactPaginate from 'react-paginate';
import EventService from "../connection/services/EventService";
import CoachService from "../connection/services/CoachService";
import ClubService from "../connection/services/ClubService";

const ListClubEvents = props => {
    const { clubId } = useParams();

    const [events, setEvents] = useState([]);
    const [numberOfPages, setNumberOfPages] = useState(0);

    const [currentEvent, setCurrentEvent] = useState(null);
    const [currentCoachName, setCurrentCoachName] = useState("");
    const [currentClubName, setCurrentClubName] = useState("");
    const [currentIndex, setCurrentIndex] = useState(-1);

    const [maxButtons] = useState(5);

    const [currentPage, setCurrentPage] = useState(0);
    const [pageSize, setPageSize] = useState(5);

    const pageSizes = [5, 10, 20];

    const refreshList = () => {
        retrieveEvents();
        setCurrentEvent(null);
        setCurrentCoachName('');
        setCurrentClubName('');
        setCurrentIndex(-1);
    };

    useEffect(refreshList, [currentPage, pageSize]);

    const retrieveEvents = () => {
        EventService.getAllByClubId({ clubId, currentPage, pageSize })
            .then(response => {
                setEvents(response.data.content);
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
        if (currentEvent) {
            CoachService.get(currentEvent.coachId)
                .then(response => {
                    setCurrentCoachName(response.data.firstName + ' ' + response.data.lastName);
                    console.log(response);
                })
                .catch(e => {
                    console.log(e);
                });

            ClubService.get(currentEvent.clubId)
                .then(response => {
                    setCurrentClubName(response.data.name);
                    console.log(response);
                })
                .catch(e => {
                    console.log(e);
                });
        }
    }, [currentEvent])

    const setActiveEvent = (event, index) => {
        setCurrentEvent(event);
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
                    <h4>Events list</h4>
                    <ul className="list-group">
                        {events &&
                            events.map((event, index) => (
                                <li
                                    className={
                                        "list-group-item " + (index === currentIndex ? "active" : "")
                                    }
                                    onClick={() => setActiveEvent(event, index)}
                                    key={index}
                                >
                                    {event.title}
                                </li>
                            ))}
                    </ul>
                </div>
                <div className="col-md-6">
                    {currentEvent ? (
                        <div>
                            <h4>{currentEvent.title}</h4>
                            <div>
                                <label>
                                    <strong>Day of week:</strong>
                                </label>{" "}
                                {currentEvent.dayOfWeek}
                            </div>
                            <div>
                                <label>
                                    <strong>Time:</strong>
                                </label>{" "}
                                {currentEvent.beginningTime.slice(0, 5)} - {currentEvent.endingTime.slice(0, 5)}
                            </div>
                            <div>
                                <label>
                                    <strong>Participants limit:</strong>
                                </label>{" "}
                                {currentEvent.participantsLimit}
                            </div>
                            <div>
                                <label>
                                    <strong>Coach:</strong>
                                </label>{" "}
                                <Link
                                    to={`/coaches/${currentEvent.coachId}`}
                                    className="link"
                                >
                                    {currentCoachName}
                                </Link>
                            </div>
                            <div>
                                <label>
                                    <strong>Club:</strong>
                                </label>{" "}
                                <Link
                                    to={`/clubs/list`}
                                    className="link"
                                >
                                    {currentClubName}
                                </Link>
                            </div>
                            {/* <Link
                                to={"/coaches/" + currentCoach.id}
                                className="btn btn-primary"
                            >
                                Edit
                            </Link> */}
                        </div>
                    ) : (
                        <div>
                            <br />
                            <p>Please select an event.</p>
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

export default ListClubEvents;