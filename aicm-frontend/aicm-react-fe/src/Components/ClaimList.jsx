import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link } from "react-router-dom";
import { allClaims, deleteClaim } from "../Modules/Claims";

const ClaimList = () => {

  const dispatch = useDispatch();
  const { claims } = useSelector((state) => state);

  useEffect(() => {
    dispatch(allClaims())
  }, []); // eslint-disable-line react-hooks/exhaustive-deps

  return (
    <div className="row">
      <div className="col-sm-12">
        <h1>Active Claims:</h1>
        <table className="table table-striped">
          <thead>
          <tr>
            <th>ID</th>
            <th>Details</th>
            <th>Filer</th>
            <th>Supporting Documents</th>
            <th>Status</th>
            <th>Status</th>
          </tr>
          </thead>
          <tbody>
          {claims.map((claim) => (
            <tr key={claim.id}>
              <td>{claim.id}</td>
              <td>
                <Link to={`/books/${claim.id}`}>{claim.details}</Link>
              </td>
              <td>{claim.filer}</td>
              <td>{claim.urls}</td>
              <td>{claim.status}</td>
              <td>
                <button className="btn btn-xs btn-danger" onClick={() => dispatch(deleteClaim(claim))}>
                  Delete Claim
                </button>
              </td>
            </tr>
          ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default ClaimList