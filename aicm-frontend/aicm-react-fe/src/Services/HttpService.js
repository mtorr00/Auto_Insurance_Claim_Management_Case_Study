import axios from "axios"
import UserService from "./UserService";

const HttpMethods = {
    GET: 'GET',
    POST: 'POST',
    PUT: 'PUT',
    DELETE: 'DELETE',
};

const _axios = axios.create();

const configure = () => {
    _axios.interceptors.request.use((config)=>{
        if(UserService.isLoggedIn()) {
        const cb = () => {
            config.headers.Authroization = `Bearer ${UserService.getToken()}`;
            return Promise.resolve(config);
        };
        return UserService.updateToken(cb);
    }});
};

const getAxiosClient = () => _axios;

const HttpService = {
    HttpMethods,
    configure,
    getAxiosClient,
};

export default HttpService;