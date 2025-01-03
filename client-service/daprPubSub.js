const { DaprClient, DaprServer } = require('@dapr/dapr');
const daprClient = new DaprClient();
const PUBSUB_NAME = 'pubsub'; 
const TOPIC_NAME = 'venteTopic'; 

async function publishOrder(vente) {
  try {
    await daprClient.pubsub.publish(PUBSUB_NAME, TOPIC_NAME, vente);
    console.log('sale published:', vente);
  } catch (error) {
    console.error('Error publishing sale:', error);
  }
}

module.exports = { publishOrder };
