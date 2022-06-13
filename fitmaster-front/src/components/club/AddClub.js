import React, { useState } from "react";
import ClubService from "../connection/services/ClubService";
import RouteClub from "./RouteClub";

const AddClub = () => {

    const daysOfWeek = ['MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY']

    const initialClubState = {
        name: "",
        address: "",
        whenOpen: {
            MONDAY: {
                from: null,
                to: null
            },
            TUESDAY: {
                from: null,
                to: null
            },
            WEDNESDAY: {
                from: null,
                to: null
            },
            THURSDAY: {
                from: null,
                to: null
            },
            FRIDAY: {
                from: null,
                to: null
            },
            SATURDAY: {
                from: null,
                to: null
            },
            SUNDAY: {
                from: null,
                to: null
            }
        }
    };

    const [club, setClub] = useState(initialClubState);
    const [submitted, setSubmitted] = useState(false);
    const [errorMsg, setErrorMsg] = useState('');

    const handleInputChange = event => {
        const { name, value } = event.target;
        setClub({ ...club, [name]: value });
    };

    const handleTimeChangeFrom = event => {
        const { name, value } = event.target;
        let newClub = club;
        newClub.whenOpen[name].from = value;
        setClub(newClub);
    };

    const handleTimeChangeTo = event => {
        const { name, value } = event.target;
        let newClub = club;
        newClub.whenOpen[name].to = value;
        setClub(newClub);
    };

    const saveClub = () => {
        ClubService.create(club)
            .then(() => {
                setSubmitted(true);
                setErrorMsg('');
            })
            .catch(e => {
                console.log(e);
                setErrorMsg(e.response.data);
            });
    };

    const newClub = () => {
        setClub(initialClubState);
        setSubmitted(false);
    };

    return (
        <div>
            <RouteClub activePage="add" />
            <div className="submit-form">
                {submitted ? (
                    <div>
                        <h4>You submitted successfully!</h4>
                        <button className="btn btn-success" onClick={newClub}>
                            Create another one.
                        </button>
                    </div>
                ) : (
                    <div>
                        <div className="form-group">
                            <label htmlFor="name">Name</label>
                            <input
                                type="text"
                                className="form-control"
                                id="name"
                                required
                                value={club.name}
                                onChange={handleInputChange}
                                name="name"
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="address">Address</label>
                            <input
                                type="text"
                                className="form-control"
                                id="address"
                                required
                                value={club.address}
                                onChange={handleInputChange}
                                name="address"
                            />
                        </div>
                        <div className="form-group">
                            {daysOfWeek.map((day) => (
                                <div>
                                    <label htmlFor={day}>{day}</label>
                                    <div className="row">
                                        <input
                                            type="time"
                                            className="col m-2 form-control"
                                            id={day} from
                                            required
                                            value={club.whenOpen[day].from}
                                            onChange={handleTimeChangeFrom}
                                            name={day}
                                        />
                                        <input
                                            type="time"
                                            className="col m-2 form-control"
                                            id={day} to
                                            required
                                            value={club.whenOpen[day].to}
                                            onChange={handleTimeChangeTo}
                                            name={day}
                                        />
                                    </div>
                                </div>
                            ))}
                        </div>
                        <button onClick={saveClub} className="btn btn-success">
                            Submit club
                        </button>
                    </div>
                )}
            </div>
            <p>{(typeof errorMsg === 'string' || errorMsg instanceof String) ? errorMsg : 'An error occured.'}</p>
        </div>
    );
};
export default AddClub;