import {EventFounders} from "./EventFounders";

export interface Events {
    id?: number,
    founder: EventFounders[],
    location: string,
    start_date: string,
    end_date: string,
    capacity: number,
    access_fee: number
}