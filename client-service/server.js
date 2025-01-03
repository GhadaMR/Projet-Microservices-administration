const express = require('express');
const mongoose = require('mongoose');
const bodyParser = require('body-parser');
const clientRoutes = require('./routes/clientRoutes');
const { publishOrder } = require('./daprPubSub');
// Créer une application Express
const app = express();

// Middleware
app.use(bodyParser.json()); // Pour analyser le corps des requêtes en JSON

// Connexion à MongoDB
mongoose.connect('mongodb://admin:password@localhost:27017/client_db?authSource=admin', {
  useNewUrlParser: true,
  useUnifiedTopology: true
})
  .then(() => console.log('Connected to MongoDB'))
  .catch((err) => console.error('Failed to connect to MongoDB', err));

// Routes
app.use('/api/clients', clientRoutes);


app.post('/publishOrder', async (req, res) => {
  const vente = req.body;
  try {
    await publishOrder(vente);
    res.status(200).send('Order published');
  } catch (error) {
    res.status(500).send('Error publishing order');
  }
});

// Lancer le serveur
const PORT = process.env.PORT || 30222;
app.listen(PORT, () => {
  console.log(`Client Service running on port ${PORT}`);
});
