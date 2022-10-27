import { SUCCESS_SUFFIX } from "redux-axios-middleware";
import HttpService from "../Services/HttpService";
import UserService from "../Services/UserService";

const LIST_CLAIMS = 'LIST_CLAIMS';
const ADD_CLAIM = 'ADD_CLAIM';
const DELETE_CLAIM = 'DELETE_CLAIM';

const claimsReducer = (state = [], action) => {
  switch (action.type) {
    case LIST_CLAIMS + SUCCESS_SUFFIX:
      return action.payload.data;

    case DELETE_CLAIM:
      return state.filter((claim) => claim.id !== action.payload.claim.id);

    default:
      return state;
  }
};

export default claimsReducer;

export const allClaims = () => ({
  type: LIST_CLAIMS,
  payload: {
    request: {
      url: '/demo/claims',
    },
  },
});

export const addClaim = claim => {
  console.log(`${UserService.getUsername()} added the claim ${claim.details}`);
  return {
    type: ADD_CLAIM,
    payload: {
      request: {
        url: '/demo/claims',
        method: HttpService.HttpMethods.POST,
        data: claim,
      },
    },
  }
};

export const deleteClaim = claim => {
  console.log(`${UserService.getUsername()} deletes the claim ${claim.title}`);
  return {
    type: DELETE_CLAIM,
    payload: {
      claim,
      request: {
        url: `/demo/claims/${claim.id}`,
        method: HttpService.HttpMethods.DELETE,
      },
    },
  }
};