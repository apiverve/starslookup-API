declare module '@apiverve/starslookup' {
  export interface starslookupOptions {
    api_key: string;
    secure?: boolean;
  }

  export interface starslookupResponse {
    status: string;
    error: string | null;
    data: any;
    code?: number;
  }

  export default class starslookupWrapper {
    constructor(options: starslookupOptions);

    execute(callback: (error: any, data: starslookupResponse | null) => void): Promise<starslookupResponse>;
    execute(query: Record<string, any>, callback: (error: any, data: starslookupResponse | null) => void): Promise<starslookupResponse>;
    execute(query?: Record<string, any>): Promise<starslookupResponse>;
  }
}
