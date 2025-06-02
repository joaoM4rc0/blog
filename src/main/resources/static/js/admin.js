const token = localStorage.getItem("token");
window.onload = () => {
    SwaggerUIBundle({
        url: '/v3/api-docs/admin',
        dom_id: '#swagger-ui',
        presets: [
            SwaggerUIBundle.presets.apis,
        ],
        layout: "BaseLayout",
        requestInterceptor: (req) => {
            if(token) {
                req.headers['Authorization'] = 'Bearer ' + token;
            }
            return req;
        }
});
};