/**
 * Created by IntelliJ IDEA.
 * User: Lenovo
 * Date: 11-2-17
 * Time: 上午11:44
 * To change this template use File | Settings | File Templates.
 */
function struct(key, value) {
    this.key = key;
    this.value = value;
}

function put(key, value) {
    for (var i = 0; i < this.arr.length; i++) {
        if (this.arr[i].key === key) {
            this.arr[i].value = value;
            return;
        }
    }
    this.arr[this.arr.length] = new struct(key, value);
}

function get(key) {
    for (var i = 0; i < this.arr.length; i++) {
        if (this.arr[i].key === key) {
            return this.arr[i].value;
        }
    }
    return null;
}

function remove(key) {
    var v;
    for (var i = 0; i < this.arr.length; i++) {
        v = this.arr.pop();
        if (v.key === key) {
            continue;
        }
        this.arr.unshift(v);
    }
}

function size() {
    return this.arr.length;
}

function isEmpty() {
    return this.arr.length <= 0;
}

function Map() {
    this.arr = new Array();
    this.get = get;
    this.put = put;
    this.remove = remove;
    this.size = size;
    this.isEmpty = isEmpty;
    this.sort = sort;
}

function sort() {

}

var map = new Map();
map.put("==", "等于");
map.put("!=", "不等于");
map.put(">", "大于");
map.put("<", "小于");
