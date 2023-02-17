import { User } from "./User";

export interface Todo {
    id?: String,
    title: String,
    description?: string,
    dateForFinalize: any,
    finshed: Boolean,
    user?: User
}