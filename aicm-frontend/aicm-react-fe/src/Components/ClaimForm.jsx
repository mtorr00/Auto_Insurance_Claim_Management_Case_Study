import { useState } from "react";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { addClaim } from "../Modules/Claims";
import RenderOnRole from "./RenderOnRole";

const ClaimForm = () => {

  const [details, setDetails] = useState('');
  //const [title, setTitle] = useState('');

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleSubmit = (event) => {
    event.preventDefault();
    if (!details /*|| !title*/) {
      return;
    }
    dispatch(addClaim({ details /*, title */}))
      .then(() => navigate("/"))
  };

  return (
    <div className="row">
      <div className="col-sm-6">
        <form onSubmit={handleSubmit}>
          <h1>File a new claim:</h1>
          <div className="form-group">
            <label htmlFor="details">details</label>
            <input type="text" className="form-control" placeholder="details"
                   value={details} onChange={(e) => setDetails(e.target.value)}/>
          </div>
          <div className="form-group">
            <label /*</div>htmlFor="title"*/>Supporting Documents Upload</label>
          </div>
          <RenderOnRole roles={['user']}>
            <button type="submit" className="btn btn-primary">File Claim</button>
          </RenderOnRole>
        </form>
      </div>
    </div>
  );
}

export default ClaimForm