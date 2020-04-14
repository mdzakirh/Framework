
var baseConfig = require('./base-config');

var devConfig = JSON.parse(JSON.stringify(baseConfig));

// devConfig.appUrl = '';

module.exports = devConfig;

