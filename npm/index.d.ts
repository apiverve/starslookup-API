declare module '@apiverve/starslookup' {
  export interface starslookupOptions {
    api_key: string;
    secure?: boolean;
  }

  export interface starslookupResponse {
    status: string;
    error: string | null;
    data: StarsLookupData;
    code?: number;
  }


  interface StarsLookupData {
      starName: string;
      mass:     number;
      diameter: number;
      galX:     number;
      galY:     number;
      galZ:     number;
      dist:     number;
      starType: string;
      temp:     number;
      color:    string;
  }

  export default class starslookupWrapper {
    constructor(options: starslookupOptions);

    execute(callback: (error: any, data: starslookupResponse | null) => void): Promise<starslookupResponse>;
    execute(query: Record<string, any>, callback: (error: any, data: starslookupResponse | null) => void): Promise<starslookupResponse>;
    execute(query?: Record<string, any>): Promise<starslookupResponse>;
  }
}
