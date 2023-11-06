FROM node:18

RUN mkdir /app

WORKDIR /app

COPY . .

# RUN npm install -g npm@10.2.3
RUN npm install --force

EXPOSE 4200

CMD ["npm", "start"]

