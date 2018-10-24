import {HTTP_ADD, HTTP_EDIT, HTTP_GET, HTTP_DEL} from '@/plugin/http'
import {API_DICT_DATABASE, API_DICT_COMPARE, API_DICT} from "../apidict/dict-database";

export function DbAdd(data) {
  return HTTP_ADD(API_DICT_DATABASE.URL_BASE, data);
}

export function DbUpdate(data) {
  return HTTP_EDIT(API_DICT_DATABASE.URL_BASE, data);
}

export function DbDelete(data) {
  return HTTP_DEL(API_DICT_DATABASE.URL_BASE, data);
}

export function DbFindAllByConditions(query) {
  return HTTP_GET(API_DICT_DATABASE.URL_BASE, query);
}

export function DbSetBenchmark(data) {
  return HTTP_EDIT(API_DICT_DATABASE.URL_BENCHMARK, query);
}

export function DbCompFindAllByConditions(query) {
  return HTTP_GET(API_DICT_COMPARE.URL_BASE, query);
}

export function DbComp(query) {
  return HTTP_ADD(API_DICT_COMPARE.URL_BASE, query);
}

export function DbDictFindAllByConditions(query) {
    return HTTP_GET(API_DICT.URL_BASE, query);
}

export default {
  DbAdd,
  DbUpdate,
  DbFindAllByConditions,
  DbDelete,
  DbSetBenchmark,
  DbCompFindAllByConditions,
  DbComp,
  DbDictFindAllByConditions
}

