# Stage 1: Build the Angular app
FROM node:20 as build

# Set the working directory
WORKDIR /usr/src/frontend

# Copy package.json and package-lock.json
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application's source code
COPY . .

# Build the Angular app
RUN npm run build 

# Stage 2: Serve the Angular app using Nginx
FROM nginx:alpine

# Copy the built Angular app from the build stage to the Nginx html folder
COPY --from=build /usr/src/frontend/dist/front /usr/share/nginx/html

# Expose the port that Nginx will listen on
EXPOSE 81

# Start Nginx in the foreground
CMD ["nginx", "-g", "daemon off;"]
