import {HTTP_ADD, HTTP_EDIT, HTTP_GET, HTTP_DEL} from '@/plugin/http'
import {API_SERV} from "./apidict/dict-cicd"

export function ServAdd(data) {
    return HTTP_ADD(API_SERV.URL_BASE, data);
}

export function ServUpdate(data) {
    return HTTP_EDIT(API_SERV.URL_BASE, data);
}

export function ServDelete(data) {
    return HTTP_DEL(API_SERV.URL_BASE, data);
}

export function ServFindAllByConditions(query) {
    return HTTP_GET(API_SERV.URL_BASE, query);
}

export default {
    ServAdd,
    ServUpdate,
    ServDelete,
    ServFindAllByConditions
}
