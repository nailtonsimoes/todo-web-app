import { Role } from "../models/role";
import { Todo } from "../models/todo";

export interface UserResponseDto {
    id?: Number,
    name: String,
    password: String,
    email: String,
    token?: String,
    roles: Role[],
    todos?: Todo[]
}