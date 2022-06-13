import React, { useState, useEffect } from "react";
import CoachService from "../connection/services/CoachService";
import { Link } from "react-router-dom";
import ReactPaginate from 'react-paginate';
import RouteCoach from "./RouteCoach";

const ListCoaches = () => {
    const [coaches, setCoaches] = useState([]);
    const [numberOfPages, setNumberOfPages] = useState(0);

    const [currentCoach, setCurrentCoach] = useState(null);
    const [currentIndex, setCurrentIndex] = useState(-1);

    const [maxButtons] = useState(5);

    const [currentPage, setCurrentPage] = useState(0);
    const [pageSize, setPageSize] = useState(5);

    const pageSizes = [5, 10, 20];

    const refreshList = () => {
        retrieveCoaches();
        setCurrentCoach(null);
        setCurrentIndex(-1);
    };

    useEffect(refreshList, [currentPage, pageSize]);

    const retrieveCoaches = () => {
        CoachService.getAll({ currentPage, pageSize })
            .then(response => {
                setCoaches(response.data.content);
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

    const setActiveCoach = (coach, index) => {
        setCurrentCoach(coach);
        setCurrentIndex(index);
    };

    return (
        <div>
            <RouteCoach activePage="list" />
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
                    <h4>Coaches list</h4>
                    <ul className="list-group">
                        {coaches &&
                            coaches.map((coach, index) => (
                                <li
                                    className={
                                        "list-group-item " + (index === currentIndex ? "active" : "")
                                    }
                                    onClick={() => setActiveCoach(coach, index)}
                                    key={index}
                                >
                                    {coach.firstName} {coach.lastName}
                                </li>
                            ))}
                    </ul>
                </div>
                <div className="col-md-6">
                    {currentCoach ? (
                        <div>
                            <h4>Coach</h4>
                            <div>
                                <label>
                                    <strong>First name:</strong>
                                </label>{" "}
                                {currentCoach.firstName}
                            </div>
                            <div>
                                <label>
                                    <strong>Last name:</strong>
                                </label>{" "}
                                {currentCoach.lastName}
                            </div>
                            <div>
                                <label>
                                    <strong>Year of birth:</strong>
                                </label>{" "}
                                {currentCoach.yearOfBirth}
                            </div>
                            <Link
                                to={"/coaches/" + currentCoach.id}
                                className="btn btn-primary"
                            >
                                Edit
                            </Link>
                        </div>
                    ) : (
                        <div>
                            <br />
                            <p>Please select a coach.</p>
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
};
export default ListCoaches;
