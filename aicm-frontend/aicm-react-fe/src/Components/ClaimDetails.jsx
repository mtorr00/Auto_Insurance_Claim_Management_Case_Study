import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router";
import { Link } from "react-router-dom";
import { allClaims } from '../Modules/claims';

const ClaimDetails = () => {
    const { claimId } = useParams();
    const dispatch = useDispatch();
    const { claims } = useSelector((state) => state);
    const [claim, setClaim] = useState();

    useEffect(()=>{
        dispatch(allClaims())
    },[]);

    useEffect(()=>{
        setClaim(claims.find(claim=>claim.id === parseInt(claimId, 10)))
    },[claimId,claims]);

    return claim ? (
        <div className="row">
      <div className="col-sm-12">
        <h1>Details for Claim ID {claim.id}</h1>
        <hr/>
        <h3>Details:</h3>
        <p className="lead">{claim.details}</p>
        <h3>Filer:</h3>
        <p className="lead">{claim.filer}</p>
        <h4>Supporting Documents:</h4>
        <p className="lead">{claim.urls}</p>
        <hr/>
        <p>
          <Link to="/">&laquo; back to list</Link>
        </p>
      </div>
    </div>
    ) : null
}

export default ClaimDetails