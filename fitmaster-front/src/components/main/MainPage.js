import { Link } from "react-router-dom";

const MainPage = () => {
    return (
        <div>
            <nav className="navbar navbar-expand navbar-dark bg-dark">
                <div className="navbar-nav mr-auto">
                    <li className="nav-item">
                        <Link to={"/coaches"} className="nav-link">
                            coaches
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to={"/clubs"} className="nav-link">
                            coaches
                        </Link>
                    </li>
                </div>
            </nav>
        </div>
    )
}

export default MainPage;