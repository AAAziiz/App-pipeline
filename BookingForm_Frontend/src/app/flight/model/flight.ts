export class Flight{
    id!:number;
    direct_flight!:boolean;
    flightType!: string;
    fromcity!: string;
    tocity!: string;
    addNearbyAirportsFrom!: boolean;
    addNearbyAirportsTo!: boolean;
    travelClass!: string;
    departureDate!: string;
    returnDate!: string;
    adults!: number;
    children!: number;
}