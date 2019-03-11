import {HTTP_ADD, HTTP_EDIT, HTTP_GET, HTTP_DEL} from '@/plugin/http'
import {API_SERV, API_BUILD, API_DEPLOY} from "./apidict/dict-cicd"

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

export function BuildAdd(data) {
    return HTTP_ADD(API_BUILD.URL_BASE, data);
}

export function BuildUpdate(data) {
    return HTTP_EDIT(API_BUILD.URL_BASE, data);
}

export function BuildDelete(data) {
    return HTTP_DEL(API_BUILD.URL_BASE, data);
}

export function BuildFindAllByConditions(query) {
    return HTTP_GET(API_BUILD.URL_BASE, query);
}

export function DeployAdd(data) {
    return HTTP_ADD(API_DEPLOY.URL_BASE, data);
}

export function DeployUpdate(data) {
    return HTTP_EDIT(API_DEPLOY.URL_BASE, data);
}

export function DeployDelete(data) {
    return HTTP_DEL(API_DEPLOY.URL_BASE, data);
}

export function DeployFindAllByConditions(query) {
    return HTTP_GET(API_DEPLOY.URL_BASE, query);
}

export default {
    ServAdd,
    ServUpdate,
    ServDelete,
    ServFindAllByConditions,
    BuildAdd,
    BuildUpdate,
    BuildDelete,
    BuildFindAllByConditions,
    DeployAdd,
    DeployUpdate,
    DeployDelete,
    DeployFindAllByConditions
}
