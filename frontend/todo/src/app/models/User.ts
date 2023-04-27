import { Role } from "./role";
import { Todo } from "./todo";

export interface User {
    id: Number,
    userName: String,
    email: String,
    password: String,
    token: String,
    todos?: Todo[],
    roles: Role[]
}