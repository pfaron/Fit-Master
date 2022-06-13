import { Link } from "react-router-dom";

const MainPage = () => {
    return (
        <div className="row">
            <div className="col-auto">
                <Link to={"/clubs/"} className={"btn btn-secondary btn-lg btn-block"}>
                    Clubs
                </Link>
            </div>
            <div className="col-auto">
                <Link to={"/coaches/"} className={"btn btn-secondary btn-lg btn-block"}>
                    Coaches
                </Link>
            </div>
            <div className="col-auto">
                <Link to={"/events/scheduled/list"} className={"btn btn-secondary btn-lg btn-block"}>
                    Events
                </Link>
            </div>
        </div>
    )
}

export default MainPage;