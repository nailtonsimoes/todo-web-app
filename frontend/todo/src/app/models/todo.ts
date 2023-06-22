export interface Todo {
    id?: number,
    title: string,
    description?: string,
    dateForFinalize: any,
    finished: boolean,
    userId?: number
}