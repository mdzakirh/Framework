
var baseConfig = require('./base-config');

var testConfig = JSON.parse(JSON.stringify(baseConfig));

testConfig.appUrl = '';


module.exports = testConfig;

