FROM nginx:alpine
COPY nginx.conf /etc/nginx/nginx.conf
EXPOSE 80/tcp
CMD ["/bin/sh", "-c", "exec nginx -g 'daemon off;';"]
WORKDIR /usr/share/nginx/html