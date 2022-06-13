import { Link } from "react-router-dom";

const RouteCoach = props => {
    return (
        <div className="row">
            <div className="col-auto">
                <Link to={"/coaches/add"} className={"btn btn-primary btn-lg btn-block" + (props.activePage === "add" ? ' active' : '')}>
                    Add coach
                </Link>
            </div>
            <div className="col-auto">
                <Link to={"/coaches/list"} className={"btn btn-primary btn-lg btn-block" + (props.activePage === "list" ? ' active' : '')}>
                    List all coaches
                </Link>
            </div>
        </div>
    );
}

export default RouteCoach;