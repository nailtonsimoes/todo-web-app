import { Todo } from "./todo";

export interface User {
    id?: String,
    userName: String,
    email: String,
    password: String,
    todos?: Todo[]
}