const mongoose = require('mongoose');

// Schéma de client
const clientSchema = new mongoose.Schema({
  name: {
    type: String,
    required: true
  },
  email: {
    type: String,
    required: true,
    unique: true
  },
  phone: {
    type: String,
    required: true
  },
  address: String,
  created_at: {
    type: Date,
    default: Date.now
  }
});

// Modèle de client
module.exports = mongoose.model('Client', clientSchema);
