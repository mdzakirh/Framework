
var baseConfig = require('./base-config');

var localConfig = JSON.parse(JSON.stringify(baseConfig));

// localConfig.appUrl = '';

module.exports = localConfig;

