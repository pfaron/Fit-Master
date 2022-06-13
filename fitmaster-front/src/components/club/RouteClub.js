import { Link } from "react-router-dom";

const RouteClubs = props => {
    return (
        <div className="row">
            <div className="col-auto">
                <Link to={"/clubs/add"} className={"btn btn-primary btn-lg btn-block" + (props.activePage === "add" ? ' active' : '')}>
                    Add club
                </Link>
            </div>
            <div className="col-auto">
                <Link to={"/clubs/list"} className={"btn btn-primary btn-lg btn-block" + (props.activePage === "list" ? ' active' : '')}>
                    List all clubs
                </Link>
            </div>
        </div>
    );
}

export default RouteClubs;