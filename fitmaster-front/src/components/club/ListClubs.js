import React, { useState, useEffect } from "react";
import ReactPaginate from 'react-paginate';
import RouteClub from "./RouteClub";
import ClubService from "../connection/services/ClubService";

const ListClubs = () => {

    const daysOfWeek = ['MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY']

    const [clubs, setClubs] = useState([]);
    const [numberOfPages, setNumberOfPages] = useState(0);

    const [currentClub, setCurrentClub] = useState(null);
    const [currentIndex, setCurrentIndex] = useState(-1);

    const [maxButtons] = useState(5);

    const [currentPage, setCurrentPage] = useState(0);
    const [pageSize, setPageSize] = useState(5);

    const pageSizes = [5, 10, 20];

    const refreshList = () => {
        retrieveClubs();
        setCurrentClub(null);
        setCurrentIndex(-1);
    };

    useEffect(refreshList, [currentPage, pageSize]);

    const retrieveClubs = () => {
        ClubService.getAll({ currentPage, pageSize })
            .then(response => {
                setClubs(response.data.content);
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

    const setActiveClub = (club, index) => {
        setCurrentClub(club);
        setCurrentIndex(index);
    };

    return (
        <div>
            <RouteClub activePage="list" />
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
                    <h4>Clubs list</h4>
                    <ul className="list-group">
                        {clubs &&
                            clubs.map((club, index) => (
                                <li
                                    className={
                                        "list-group-item " + (index === currentIndex ? "active" : "")
                                    }
                                    onClick={() => setActiveClub(club, index)}
                                    key={index}
                                >
                                    {club.name}
                                </li>
                            ))}
                    </ul>
                </div>
                <div className="col-md-6">
                    {currentClub ? (
                        <div>
                            <h4>{currentClub.name}</h4>
                            {/* <div>
                                <label>
                                    <strong>Name:</strong>
                                </label>{" "}
                                {currentClub.name}
                            </div> */}
                            <div>
                                <label>
                                    <strong>Address:</strong>
                                </label>{" "}
                                {currentClub.address}
                            </div>
                            <div>
                                <label>
                                    <strong>Open times:</strong>
                                </label>{" "}
                                <ul className="list-group">
                                    {daysOfWeek.map((day, index) => (
                                        <li
                                            className={"list-group-item"}
                                            key={index}
                                        >
                                            {day}:{' '}
                                            {(currentClub.whenOpen[day].from === "00:00:00" && currentClub.whenOpen[day].to === "00:00:00" ? "closed" :
                                                currentClub.whenOpen[day].from + ' - ' + currentClub.whenOpen[day].to)}
                                        </li>
                                    ))}
                                </ul>
                            </div>
                        </div>
                    ) : (
                        <div>
                            <br />
                            <p>Please select a club.</p>
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
export default ListClubs;
