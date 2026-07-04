import axios from 'axios'

export const api = axios.create({
    baseURL: import.meta.env.VITE_CLJ_BK_API_URL,
});
 

export function searchArchiveBooks(params: { title: string; limit?: number; offset?: number }) {
  return api.get("/archive", { params });
}