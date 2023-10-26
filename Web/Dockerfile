FROM node:18

RUN mkdir /app

WORKDIR /app

COPY . .

RUN npm install

EXPOSE 4200

CMD ["npm", "start"]

