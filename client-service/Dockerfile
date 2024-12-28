# Utiliser l'image Node.js officielle
FROM node:18-alpine

# Définir le répertoire de travail dans le conteneur
WORKDIR /usr/src/app

# Copier package.json et package-lock.json
COPY package*.json ./

# Installer les dépendances
RUN npm install

# Copier le reste des fichiers du projet
COPY . .

# Exposer le port 3000 (port par défaut pour Express)
EXPOSE 3000

# Commande pour démarrer le serveur
CMD ["npm", "start"]
