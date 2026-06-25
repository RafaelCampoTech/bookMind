import {
    type RouteConfig,
    route,
} from "@react-router/dev";

export default [
    route("dashboard", "./some/file.tsx"),
    // pattern ^           ^ module file
] satisfies RouteConfig;
